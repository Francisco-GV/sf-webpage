package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Vet;
import com.frank.sfwebpage.services.VetService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Vet save(Vet vet) {
        return super.save(vet.getId(), vet);
    }
}
