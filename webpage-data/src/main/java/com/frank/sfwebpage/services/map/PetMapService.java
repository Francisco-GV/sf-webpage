package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet findByOwnerAndName(Owner owner, String name) {
        return map.values().stream()
                .filter(pet -> pet.getOwner().getId().equals(owner.getId()))
                .filter(pet -> pet.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
