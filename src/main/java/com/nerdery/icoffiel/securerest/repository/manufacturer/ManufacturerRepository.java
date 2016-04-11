package com.nerdery.icoffiel.securerest.repository.manufacturer;

import com.nerdery.icoffiel.securerest.domain.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by icoffiel on 4/8/2016.
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
