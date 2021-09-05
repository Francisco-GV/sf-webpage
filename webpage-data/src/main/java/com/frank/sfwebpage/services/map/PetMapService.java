package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
}
