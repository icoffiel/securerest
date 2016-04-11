package com.nerdery.icoffiel.securerest.web.rest.controller;

import com.nerdery.icoffiel.securerest.SecurerestApplication;
import com.nerdery.icoffiel.securerest.domain.manufacturer.Manufacturer;
import com.nerdery.icoffiel.securerest.repository.manufacturer.ManufacturerRepository;
import com.nerdery.icoffiel.securerest.service.manufacturer.ManufacturerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SecurerestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class ManufacturerControllerIntegrationTest {
    private static final String DEFAULT_NAME = "AAAAA";

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    private MockMvc restController;

    private Manufacturer manufacturer;

    @PostConstruct
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ManufacturerController controller = new ManufacturerController(manufacturerService);
        this.restController = MockMvcBuilders.standaloneSetup(controller)
            .build();
    }

    @Before
    public void initTests() {
        manufacturer = new Manufacturer(DEFAULT_NAME);
    }

    @Transactional
    @Test
    public void saveManufacturer() throws Exception {
        int databaseBeforeSize = manufacturerRepository.findAll().size();

        restController.perform(
                post("/api/manufacturers")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(TestUtil.objectToJsonBytes(manufacturer)))
                .andExpect(status().isCreated());

        // Check that the database is updated
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        assertThat(manufacturers).hasSize(databaseBeforeSize + 1);
        Manufacturer testManufacturer = manufacturers.get(manufacturers.size() - 1);
        assertThat(testManufacturer.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Transactional
    @Test
    public void getAllManufacturers() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        // Get all the manufacturers
        restController.perform(get("/api/manufacturers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem((int) manufacturer.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Transactional
    @Test
    public void getManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        // Get all the manufacturers
        restController.perform(get("/api/manufacturers/{id}", manufacturer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value((int) manufacturer.getId()))
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Transactional
    @Test
    public void deleteManufacturer() throws Exception {
        // Initialize the database
        manufacturerService.save(manufacturer);

        int databaseSizeBeforeDelete = manufacturerRepository.findAll().size();

        // Get the manufacturer
        restController.perform(delete("/api/manufacturers/{id}", manufacturer.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        assertThat(manufacturers).hasSize(databaseSizeBeforeDelete - 1);
    }
}