package br.com.ifba.plantas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.ifba.plantas.entity.Especie;

@DataJpaTest
@ActiveProfiles("test")
class EspecieRepositoryTest {

    @Autowired
    private EspecieRepository repository;

}