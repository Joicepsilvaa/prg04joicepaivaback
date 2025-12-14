package br.com.ifba.plantas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.ifba.plantas.entity.Planta;

@DataJpaTest
@ActiveProfiles("test")
class PlantasRepositoryTest {

    @Autowired
    private PlantasRepository repository;

    @Test
    void saveAndFindAll_shouldReturnPlanta() {

        Planta planta = new Planta(
                null,
                "Planta da Sala",
                "Sala",
                "2024-12-10",
                "Planta saudável",
                1L
        );

        repository.save(planta);

        List<Planta> plantas = repository.findAll();

        assertThat(plantas).isNotEmpty();
    }
}