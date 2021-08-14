package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {
    @Override
    public Owner save(Owner owner) {
        return super.save(owner.getId(), owner);
    }
}
