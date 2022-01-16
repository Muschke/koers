package be.koers.repositories;

import be.koers.domain.Ploeg;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import java.util.Optional;

@Repository
public class JpaPloegRepository implements PloegRepository {
    private final EntityManager manager;

    public JpaPloegRepository(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public Optional<Ploeg> findById(long id){
        return Optional.ofNullable(manager.find(Ploeg.class, id));
    }
}
