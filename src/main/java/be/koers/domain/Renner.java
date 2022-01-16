package be.koers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("R")
public class Renner extends  Personeelslid {
    private String status;

    public Renner(String naam, BigDecimal kost, Ploeg ploeg, String status) {
        super(naam, kost, ploeg);
        this.status = status;
    }

    protected Renner() {};

    public String getStatus() {
        return status;
    }
}
