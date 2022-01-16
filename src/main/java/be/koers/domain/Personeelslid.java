package be.koers.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "personeel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class Personeelslid {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private long id;
    private String naam;
    private BigDecimal kost;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "ploegid")
    private Ploeg ploeg;

    public Personeelslid(String naam, BigDecimal kost, Ploeg ploeg) {
        this.naam = naam;
        this.kost = kost;
        setPloeg(ploeg);
    }
    protected Personeelslid() {};

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getKost() {
        return kost;
    }

    public Ploeg getPloeg() {
        return ploeg;
    }

    public void setPloeg(Ploeg ploeg) {
        this.ploeg = ploeg;
    }

    /*Equals en hashtag overriden omdat*/
}
