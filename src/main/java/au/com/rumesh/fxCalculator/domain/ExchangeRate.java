package au.com.rumesh.fxCalculator.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity class holds Base Currency to Term Currency exchange rate
 *
 * @author rnadeera
 */
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "baseCurrency_id", referencedColumnName = "id", nullable = false)
    private Currency baseCurrency;

    @OneToOne
    @JoinColumn(name = "termCurrency_id", referencedColumnName = "id", nullable = false)
    private Currency termCurrency;

    @Column(nullable = false)
    private BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getTermCurrency() {
        return termCurrency;
    }

    public void setTermCurrency(Currency termCurrency) {
        this.termCurrency = termCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
