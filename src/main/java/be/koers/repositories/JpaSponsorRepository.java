package be.koers.repositories;

import be.koers.domain.Renner;
import be.koers.domain.Sponsor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaSponsorRepository implements SponsorRepository {
    private final EntityManager manager;

    public JpaSponsorRepository(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public Optional<Sponsor> findById(long id){
        return Optional.ofNullable(manager.find(Sponsor.class, id));
    }
}
