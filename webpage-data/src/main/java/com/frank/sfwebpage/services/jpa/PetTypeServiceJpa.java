package com.frank.sfwebpage.services.jpa;

import com.frank.sfwebpage.model.PetType;
import com.frank.sfwebpage.repositories.PetTypeRepository;
import com.frank.sfwebpage.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class PetTypeServiceJpa implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetTypeServiceJpa(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> PetTypes = new HashSet<>();

        petTypeRepository.findAll().forEach(PetTypes::add);

        return PetTypes;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType PetType) {
        return petTypeRepository.save(PetType);
    }

    @Override
    public void delete(PetType PetType) {
        petTypeRepository.delete(PetType);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
