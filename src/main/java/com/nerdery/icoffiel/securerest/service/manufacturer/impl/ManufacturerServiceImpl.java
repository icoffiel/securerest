package com.nerdery.icoffiel.securerest.service.manufacturer.impl;

import com.nerdery.icoffiel.securerest.domain.manufacturer.Manufacturer;
import com.nerdery.icoffiel.securerest.repository.manufacturer.ManufacturerRepository;
import com.nerdery.icoffiel.securerest.service.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The concrete implementation of the {@link ManufacturerService}.
 *
 * Created by icoffiel on 4/8/2016.
 */
@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Manufacturer findOne(long id) {
        return manufacturerRepository.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long id) {
        manufacturerRepository.delete(id);
    }
}
