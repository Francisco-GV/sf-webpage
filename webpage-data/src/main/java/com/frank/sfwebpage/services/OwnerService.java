package com.frank.sfwebpage.services;

import com.frank.sfwebpage.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
