package be.koers.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PloegTest {
    private final static BigDecimal budget = BigDecimal.valueOf(5_000_000);
    private Sponsor sponsorEen, sponsorTwee, sponsorDrie;
    private Ploeg ploeg, verhuisploeg;

    @BeforeEach
    void beforeEach() {
        sponsorEen = new Sponsor("sponserEen", Soort.ALGEMEEN, budget, null);
        sponsorTwee = new Sponsor("sponserTwee", Soort.ALGEMEEN, budget, null);
        sponsorDrie = new Sponsor("sponserDrie", Soort.ALGEMEEN, budget, null);
        ploeg = new Ploeg("directeur");
        verhuisploeg = new Ploeg("anderdirecteur");
    }

    @Test
    void addAndRemove() {
        ploeg.add(sponsorEen);
        assertThat(ploeg.getSponsors()).containsOnly(sponsorEen);
        ploeg.add(sponsorTwee);
        assertThat(ploeg.getSponsors()).contains(sponsorEen, sponsorTwee);
        ploeg.add(sponsorDrie);
        assertThat(ploeg.getSponsors()).contains(sponsorEen, sponsorTwee, sponsorDrie);
        ploeg.remove(sponsorEen);
        assertThat(ploeg.getSponsors()).contains(sponsorTwee, sponsorDrie);
        ploeg.remove(sponsorTwee);
        assertThat(ploeg.getSponsors()).containsOnly(sponsorDrie);
        ploeg.remove(sponsorDrie);
        assertThat(ploeg.getSponsors()).isEmpty();

    }

    @Test
    void sponserVerandertVanPloeg() {
        ploeg.add(sponsorEen);
        assertThat(ploeg.getSponsors()).containsOnly(sponsorEen);
        verhuisploeg.add(sponsorEen);
        assertThat(ploeg.getSponsors()).isEmpty();
        assertThat(verhuisploeg.getSponsors()).containsOnly(sponsorEen);
    }

    @Test
    void getTotaalBudget() {
        ploeg.add(sponsorEen);
        ploeg.add(sponsorTwee);
        ploeg.add(sponsorDrie);
        assertThat(ploeg.getTotaalBudget()).isEqualByComparingTo("15000000");
    }
}