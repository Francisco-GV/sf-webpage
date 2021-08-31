package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.PetType;
import com.frank.sfwebpage.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
