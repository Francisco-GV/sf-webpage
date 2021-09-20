package com.frank.sfwebpage.controllers;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        Set<Owner> ownerSet = new HashSet<>(2);
        for (int i = 0; i < 2; i++) {
            Owner owner = new Owner();
            owner.setId((long) i + 1);
            ownerSet.add(owner);
        }

        Mockito.when(ownerService.findAllByLastName(Mockito.anyString())).thenReturn(ownerSet);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attribute("owners", Matchers.hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        long id = 1L;

        Owner owner = new Owner();
        owner.setId(id);

        Mockito.when(ownerService.findAllByLastName(Mockito.anyString())).thenReturn(Set.of(owner));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/%d".formatted(id)));
    }

    @Test
    void displayOwners() throws Exception {
        long id = 1L;

        Owner owner = new Owner();
        owner.setId(id);

        Mockito.when(ownerService.findById(id)).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
                .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.hasProperty("id", Matchers.is(id))));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void processCreationForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);

        Mockito.when(ownerService.save(Mockito.any())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(ownerService, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);

        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);

        Mockito.when(ownerService.save(Mockito.any())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(ownerService, Mockito.times(1)).save(Mockito.any());

    }
}