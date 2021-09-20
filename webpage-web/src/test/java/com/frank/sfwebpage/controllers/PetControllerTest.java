package com.frank.sfwebpage.controllers;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.model.PetType;
import com.frank.sfwebpage.services.OwnerService;
import com.frank.sfwebpage.services.PetService;
import com.frank.sfwebpage.services.PetTypeService;
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
class PetControllerTest {
    @Mock
    OwnerService ownerService;
    @Mock
    PetService petService;
    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petTypes;
    Pet pet;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);

        petTypes = new HashSet<>();
        long i = 0;
        for (String type : new String[] {"Dog", "Cat"}) {
            PetType petType = new PetType();
            petType.setId(++i);
            petType.setName(type);
            petTypes.add(petType);
        }

        pet = new Pet();
        pet.setOwner(owner);
        pet.setName("Pett");
        pet.setId(1L);

        owner.getPets().add(pet);

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        Mockito.verify(petService).save(Mockito.any());
    }

    @Test
    void initUpdateForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processUpdateForm() throws Exception {
        Mockito.when(ownerService.findById(Mockito.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));
    }
}