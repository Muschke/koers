package be.koers.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sponsors")
public class Sponsor {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private long id;
    private String naam;
    private Soort soort;
    private BigDecimal budget;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name="sponsors_id")
    private Ploeg ploeg;

    public Sponsor(String naam, Soort soort, BigDecimal budget, Ploeg ploeg) {
        this.naam = naam;
        this.soort = soort;
        this.budget = budget;
        setPloeg(ploeg);
    }

    protected Sponsor() {};

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Soort getSoort() {
        return soort;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public Ploeg getPloeg() {
        return ploeg;
    }

    public void setPloeg(Ploeg ploeg) {
        this.ploeg = ploeg;
    }
}
