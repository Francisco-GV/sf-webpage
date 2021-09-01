package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Vet;
import com.frank.sfwebpage.services.SpecialtyService;
import com.frank.sfwebpage.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    @Autowired
    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet vet) {
        if (vet.getSpecialties().size() > 0) {
            vet.getSpecialties().forEach(specialty -> {
                if (specialty.getId() == null) {
                    specialtyService.save(specialty);
                }
            });
        }

        return super.save(vet);
    }
}
