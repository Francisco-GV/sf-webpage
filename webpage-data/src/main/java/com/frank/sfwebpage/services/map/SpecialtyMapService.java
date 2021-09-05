package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Specialty;
import com.frank.sfwebpage.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class SpecialtyMapService extends AbstractMapService<Specialty, Long> implements SpecialtyService {
}