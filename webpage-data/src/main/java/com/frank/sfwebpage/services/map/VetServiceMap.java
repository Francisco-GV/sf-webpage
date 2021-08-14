package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Vet;
import com.frank.sfwebpage.services.CrudService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Vet save(Vet vet) {
        return super.save(vet.getId(), vet);
    }
}
