package com.frank.sfwebpage.repositories;

import com.frank.sfwebpage.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
