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
    @OneToMany(mappedBy = "ploeg")
    private Set<Personeelslid> personeelsleden;

    public Ploeg(String directeur) {
        this.directeur = directeur;
        this.sponsors = new LinkedHashSet<>();
        this.personeelsleden = new LinkedHashSet<>();
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

    /* methods bij set personeelseden of daarop gebaseerd */

    public Set<Personeelslid> getPersoneelsleden() {
        return Collections.unmodifiableSet(personeelsleden);
    }


    public boolean add(Renner renner) {
        if(renner == null){
            throw new NullPointerException();
        }
        var toegevoegd = personeelsleden.add(renner);
        var oudePloeg = renner.getPloeg();
        if(oudePloeg != null && oudePloeg !=this) {
            oudePloeg.personeelsleden.remove(renner);
        }
        if(this != oudePloeg) {
            renner.setPloeg(this);
        }
        return toegevoegd;
    }

    public boolean remove(Renner renner) {
        if(renner == null) {
            throw new NullPointerException();
        }
        if(!personeelsleden.contains(renner)) {
            throw new SponsorNietGevondenException();
        }
        return personeelsleden.remove(renner);
    }


}
