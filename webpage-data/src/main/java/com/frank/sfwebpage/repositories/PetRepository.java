package com.frank.sfwebpage.repositories;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Long> {
    Optional<Pet> findByOwnerAndNameIgnoreCase(Owner owner, String name);
}
