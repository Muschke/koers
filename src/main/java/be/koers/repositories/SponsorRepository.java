package be.koers.repositories;

import be.koers.domain.Ploeg;
import be.koers.domain.Sponsor;

import java.util.Optional;

public interface SponsorRepository {
    Optional<Sponsor> findById(long id);
}
