package be.koers.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SponsorTest {
    private final static BigDecimal budget = BigDecimal.valueOf(5_000_000);
    private Sponsor sponsorEen, sponsorTwee, sponsorDrie;
    private Ploeg ploeg, verhuisploeg;

    @BeforeEach
    void beforeEach() {
        sponsorEen = new Sponsor("sponserEen", Soort.ALGEMEEN, budget, null);
        sponsorTwee = new Sponsor("sponserTwee", Soort.ALGEMEEN, budget, null);
        ploeg = new Ploeg("directeur");

    }
    @Test
    void getBudget() {
        assertThat(sponsorEen.getBudget()).isEqualByComparingTo(budget);
        assertThat(sponsorTwee.getBudget()).isEqualByComparingTo(budget);
        ploeg.add(sponsorEen);
        ploeg.add(sponsorTwee);
        assertThat(ploeg.getTotaalBudget()).isEqualByComparingTo("10000000");
    }
}