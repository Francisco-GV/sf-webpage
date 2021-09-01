package com.frank.sfwebpage.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {
    private final Set<Specialty> specialties;

    public Vet() {
        specialties = new HashSet<>();
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }
}
