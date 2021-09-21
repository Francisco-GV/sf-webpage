package com.frank.sfwebpage.services;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.model.Pet;


public interface PetService extends CrudService<Pet, Long> {
    Pet findByOwnerAndName(Owner owner, String name);
}
