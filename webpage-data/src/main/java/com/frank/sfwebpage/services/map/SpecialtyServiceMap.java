package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Specialty;
import com.frank.sfwebpage.services.SpecialtyService;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {
}