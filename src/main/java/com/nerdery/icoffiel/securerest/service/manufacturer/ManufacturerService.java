package com.nerdery.icoffiel.securerest.service.manufacturer;

import com.nerdery.icoffiel.securerest.domain.manufacturer.Manufacturer;

import java.util.List;

/**
 * Created by icoffiel on 4/8/2016.
 */
public interface ManufacturerService {

    /**
     * Retrieve the list of all Manufacturers.
     * @return A {@link List} of {@link Manufacturer} objects
     */
    List<Manufacturer> findAll();

    /**
     * Save a Manufacturer to the database.
     *
     * @param manufacturer The {@link Manufacturer} to save.
     * @return The saved {@link Manufacturer}
     */
    Manufacturer save(Manufacturer manufacturer);

    /**
     * Retrieve a single Manufacturer by it's ID.
     *
     * @param id The ID of the {@link Manufacturer}.
     * @return The {@link Manufacturer}.
     */
    Manufacturer findOne(long id);

    /**
     * Delete a single Manufacturer by it's ID.
     * @param id The ID of the {@link Manufacturer}.
     */
    void delete(long id);
}
