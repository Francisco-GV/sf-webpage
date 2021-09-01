package com.frank.sfwebpage.bootstrap;

import com.frank.sfwebpage.model.Owner;
import com.frank.sfwebpage.model.Pet;
import com.frank.sfwebpage.model.PetType;
import com.frank.sfwebpage.model.Vet;
import com.frank.sfwebpage.services.OwnerService;
import com.frank.sfwebpage.services.PetTypeService;
import com.frank.sfwebpage.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {
        // Pet Types
        PetType dogType = new PetType();
        dogType.setName("dog");
        petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("cat");
        petTypeService.save(catType);

        System.out.println("PetTypes loaded...");

        // Owners
        Owner owner1 = new Owner();
        owner1.setFirstName("Greer");
        owner1.setLastName("Twinbourne");
        owner1.setAddress("82 Fuller Junction");
        owner1.setCity("Naples");
        owner1.setTelephone("941-777-6268");
        // Pets
        {
            Pet pet1 = new Pet();
            pet1.setPetType(dogType);
            pet1.setBirthDate(LocalDate.of(2018, Month.APRIL, 15));
            pet1.setName("Dumbi");

            owner1.getPets().add(pet1);
        }

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bucky");
        owner2.setLastName("Inkles");
        owner2.setAddress("4 Westport Avenue");
        owner2.setCity("Paninggaran");
        owner2.setTelephone("214-979-0076");
        // Pets
        {
            Pet pet1 = new Pet();
            pet1.setPetType(catType);
            pet1.setBirthDate(LocalDate.of(2019, Month.OCTOBER, 2));
            pet1.setName("Shiro");

            Pet pet2 = new Pet();
            pet2.setPetType(catType);
            pet2.setBirthDate(LocalDate.of(2018, Month.FEBRUARY, 23));
            pet2.setName("Kuro");

            owner2.getPets().add(pet1);
            owner2.getPets().add(pet2);
        }

        ownerService.save(owner2);

        System.out.println("Owners loaded...");

        // Vets
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Vets loaded...");
    }
}
