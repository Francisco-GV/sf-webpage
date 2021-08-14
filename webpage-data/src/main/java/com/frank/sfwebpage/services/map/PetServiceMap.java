package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(), pet);
    }
}
