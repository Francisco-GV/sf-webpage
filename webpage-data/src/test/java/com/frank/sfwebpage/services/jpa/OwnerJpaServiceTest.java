package com.frank.sfwebpage.services.jpa;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    private Owner dummy1;
    private Owner dummy2;

    @BeforeEach
    void setUp() {
        dummy1 = new Owner();
        dummy1.setId(1L);
        dummy1.setLastName("Gasca");

        dummy2 = new Owner();
        dummy2.setId(2L);
        dummy2.setLastName("Villagomez");
    }

    @Test
    void findByLastName() {
        // ############ Mockito configuration ############
        when(ownerRepository.findByLastName(dummy1.getLastName()))
                .thenReturn(Optional.of(dummy1));
        // ###############################################

        Owner owner = ownerJpaService.findByLastName(dummy1.getLastName());

        assertEquals(dummy1.getLastName(), owner.getLastName());
    }

    @Test
    void findAll() {
        // ############ Mockito configuration ############
        when(ownerRepository.findAll())
                .thenReturn(new HashSet<>(Set.of(dummy1, dummy2)));
        // ###############################################

        Set<Owner> owners = ownerJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        // ############ Mockito configuration ############
        when(ownerRepository.findById(dummy2.getId()))
                .thenReturn(Optional.of(dummy2));
        // ###############################################

        Owner owner = ownerJpaService.findById(dummy2.getId());

        assertNotNull(owner);
        assertEquals(dummy2.getId(), owner.getId());

    }

    @Test
    void save() {
        Owner dummy3 = new Owner();
        dummy3.setId(3L);
        // ############ Mockito configuration ############
        when(ownerRepository.save(dummy3))
                .thenReturn(dummy3);
        // ###############################################

        Owner savedDummy = ownerRepository.save(dummy3);

        assertNotNull(savedDummy);
        assertEquals(dummy3.getId(), savedDummy.getId());
    }

    @Test
    void delete() {
        ownerJpaService.delete(dummy1);
        verify(ownerRepository, times(1)).delete(dummy1);
    }

    @Test
    void deleteById() {
        long id = 1L;
        ownerJpaService.deleteById(id);
        verify(ownerRepository, times(1)).deleteById(id);
    }
}