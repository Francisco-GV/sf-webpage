package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(), pet);
    }
}
