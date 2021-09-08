package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        Owner owner1 = new Owner();
        owner1.setLastName("Gasca");
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setLastName("Villagomez");
        owner2.setId(2L);

        ownerMapService.save(owner1);
        ownerMapService.save(owner2);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(2, ownerSet.size());
    }

    @Test
    void findById() {
        long id = 2L;
        Owner owner = ownerMapService.findById(id);

        assertEquals(id, owner.getId());
    }

    @Test
    void save() {
        Owner owner3 = new Owner();
        long id = 3L;
        owner3.setId(id);

        Owner savedOwner3 = ownerMapService.save(owner3);

        assertEquals(id, savedOwner3.getId());
    }

    @Test
    void delete() {
        long id = 2L;
        ownerMapService.delete(ownerMapService.findById(id));

        assertNull(ownerMapService.findById(id));
    }

    @Test
    void deleteById() {
        long id = 1L;
        ownerMapService.deleteById(id);

        assertNull(ownerMapService.findById(id));
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName("Gasca");

        assertNotNull(owner);
    }

    @Test
    void testSave() {
    }
}