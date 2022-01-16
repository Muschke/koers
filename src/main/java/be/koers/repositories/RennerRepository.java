package be.koers.repositories;

import be.koers.domain.Ploeg;
import be.koers.domain.Renner;

import java.util.Optional;

public interface RennerRepository {
    Optional<Renner> findById(long id);
}
