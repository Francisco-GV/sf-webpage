package com.frank.sfwebpage.repositories;

import com.frank.sfwebpage.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
