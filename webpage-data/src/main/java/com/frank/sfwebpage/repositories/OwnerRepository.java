package com.frank.sfwebpage.repositories;

import com.frank.sfwebpage.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);
    Set<Owner> findAllByLastName(String lastName);
}
