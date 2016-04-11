package com.nerdery.icoffiel.securerest.web.rest.controller;

import com.nerdery.icoffiel.securerest.domain.manufacturer.Manufacturer;
import com.nerdery.icoffiel.securerest.service.manufacturer.ManufacturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for a RESTful endpoint for {@link Manufacturer} operations.
 *
 * Created by icoffiel on 4/8/2016.
 */
@RestController
@RequestMapping("/api")
public class ManufacturerController {

    private final Logger log = LoggerFactory.getLogger(ManufacturerController.class);

    private final ManufacturerService manufacturerService;

    @Autowired
    ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /**
     * Retrieve the list of all manufacturers.
     *
     * @return A {@link ResponseEntity} with a status code of 200 (OK) and the list of {@link Manufacturer} objects found.
     */
    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Manufacturer>> getAllManufacturer() {
        return new ResponseEntity<>(manufacturerService.findAll(), HttpStatus.OK);
    }

    /**
     * Get a Manufacturer using it's ID.
     *
     * @param id The ID of the {@link Manufacturer} to look up.
     * @return A {@link ResponseEntity} with a status code of 200 (OK) and the {@link Manufacturer} found, otherwise a 404 if not found.
     */
    @RequestMapping(value = "/manufacturers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable long id) {
        log.debug("REST request to get manufacturer : {}", id);
        Manufacturer manufacturer = manufacturerService.findOne(id);
        return Optional.ofNullable(manufacturer)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK
                ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Save a Manufacturer.
     * TODO - handle cases where the ID of the manufacturer already exists.
     *
     * @param manufacturer The {@link Manufacturer} to save
     * @return A {@link ResponseEntity} with a status of 201 (CREATED) and the {@link Manufacturer}.
     */
    @RequestMapping(value = "/manufacturers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> saveManufacturer(@RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerService.save(manufacturer), HttpStatus.CREATED);
    }

    /**
     * Delete a Manufacturer.
     *
     * @param id The ID of the {@link Manufacturer} to delete.
     * @return A {@link ResponseEntity} with a status of 200 (OK).
     */
    @RequestMapping(value = "/manufacturers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteManufacturer(@PathVariable long id) {
        log.debug("REST request to delete manufacturer : {}", id);
        manufacturerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
