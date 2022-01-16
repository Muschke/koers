package be.koers.repositories;

import be.koers.domain.Ploeg;

import java.util.Optional;

public interface PloegRepository {
    Optional<Ploeg> findById(long id);
}
