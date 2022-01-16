package be.koers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("S")
public class Staf extends Personeelslid{
    @Enumerated(EnumType.STRING)
    private Type type;

    public Staf(String naam, BigDecimal kost, Ploeg ploeg, Type type) {
        super(naam, kost, ploeg);
        this.type = type;
    }
    protected Staf() {};

    public Type getType() {
        return type;
    }
}
