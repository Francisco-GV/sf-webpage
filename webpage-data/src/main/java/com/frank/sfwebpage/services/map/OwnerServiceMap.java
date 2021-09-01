package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.services.OwnerService;
import com.frank.sfwebpage.services.PetService;
import com.frank.sfwebpage.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Autowired
    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        // return map.entrySet().fi;
        return null;
    }

    @Override
    public Owner save(Owner owner) {
        if (owner.getPets() != null) {
            owner.getPets().forEach(pet -> {
                if (Objects.requireNonNull(pet.getPetType()).getId() == null) {
                    petTypeService.save(pet.getPetType());
                }

                if (pet.getId() == null) {
                    petService.save(pet);
                }
            });
        }

        return super.save(owner);
    }
}
