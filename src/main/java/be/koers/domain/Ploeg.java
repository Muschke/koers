package be.koers.domain;

import be.koers.SponsorNietGevondenException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ploegen")
public class Ploeg {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private long id;
    private String directeur;
    //set sponsors
    @OneToMany(mappedBy = "ploeg")
    private Set<Sponsor> sponsors;

    //set renners

    public Ploeg(String directeur) {
        this.directeur = directeur;
        this.sponsors = new LinkedHashSet<>();
    }
    protected Ploeg() {};

    public long getId() {
        return id;
    }

    public String getDirecteur() {
        return directeur;
    }
    /* methods bij set sponsors of daarop gebaseerd */
    public Set<Sponsor> getSponsors() {
        return Collections.unmodifiableSet(sponsors);
    }

    public boolean add(Sponsor sponsor) {
        if(sponsor == null) {
            throw new NullPointerException();
        }
        var toegevoegd = sponsors.add(sponsor);
        var oudePloeg = sponsor.getPloeg();
        if(oudePloeg != null && oudePloeg != this) {
            oudePloeg.sponsors.remove(sponsor);
        }
        if(this != oudePloeg) {
            sponsor.setPloeg(this);
        }
        return toegevoegd;
    }

    public boolean remove(Sponsor sponsor) {
        if(sponsor == null) {
            throw new NullPointerException();
        }
        if(!sponsors.contains(sponsor)) {
            throw new SponsorNietGevondenException();
        }
        return sponsors.remove(sponsor);
    }

    //methode om totale budget van de ploeg te krijgen
    public BigDecimal getTotaalBudget() {
        BigDecimal totaalBudget = BigDecimal.ZERO;
        for(var sponsor:sponsors) {
            var budget = sponsor.getBudget();
            totaalBudget = totaalBudget.add(budget);
        }
        return totaalBudget;
    }


}
