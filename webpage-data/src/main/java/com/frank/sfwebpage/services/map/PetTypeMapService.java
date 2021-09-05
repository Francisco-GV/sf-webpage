package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.PetType;
import com.frank.sfwebpage.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
}
