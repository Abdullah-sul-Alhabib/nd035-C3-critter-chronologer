package com.udacity.jdnd.course3.critter.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * The type Customer.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "owner", targetEntity = Pet.class)
    private List<Pet> pets;

}
