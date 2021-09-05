package com.frank.sfwebpage.repositories;

import com.frank.sfwebpage.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> getByLastName(String lastName);
}
