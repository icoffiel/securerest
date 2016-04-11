package com.nerdery.icoffiel.securerest.domain.manufacturer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Manufacturer entity used to save the manufacturer to the DB.
 *
 * Created by icoffiel on 4/8/2016.
 */
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Manufacturer() {}

    public Manufacturer(String theName) {
        this.name = theName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
