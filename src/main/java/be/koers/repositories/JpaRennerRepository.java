package be.koers.repositories;

import be.koers.domain.Ploeg;
import be.koers.domain.Renner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaRennerRepository implements RennerRepository {
    private final EntityManager manager;

    public JpaRennerRepository(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public Optional<Renner> findById(long id){
        return Optional.ofNullable(manager.find(Renner.class, id));
    }
}
