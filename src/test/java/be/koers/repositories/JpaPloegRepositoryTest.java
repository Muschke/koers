package be.koers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql({"/insertPloeg.sql", "/insertSponsor.sql", "/insertPersoneel.sql"})
@Import(JpaPloegRepository.class)
class JpaPloegRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final JpaPloegRepository repository;
    public JpaPloegRepositoryTest(JpaPloegRepository repository) {
        this.repository = repository;
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestPloeg()))
                .hasValueSatisfying(ploeg -> assertThat(ploeg.getDirecteur()).isEqualTo("testdirecteur"));
    }
    @Test void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }

    private long idVanTestPloeg(){
        return jdbcTemplate.queryForObject("select id from ploegen where directeur = 'testdirecteur'", Long.class);
    }
}