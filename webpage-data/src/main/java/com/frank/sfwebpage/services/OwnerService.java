package com.frank.sfwebpage.services;

import com.frank.sfwebpage.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    Set<Owner> findAllByLastName(String lastName);
}
