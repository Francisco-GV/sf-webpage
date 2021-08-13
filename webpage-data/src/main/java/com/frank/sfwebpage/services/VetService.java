package com.frank.sfwebpage.services;

import com.frank.sfwebpage.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findByLastName(String lastName);
    Vet findById(Long id);
    Vet save (Vet vet);
    Set<Vet> findAll();
}
