package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Owner save(Owner owner) {
        return super.save(owner.getId(), owner);
    }

    @Override
    public Owner findByLastName(String lastName) {
        // return map.entrySet().fi;
        return null;
    }
}
