package au.com.rumesh.fxCalculator.repository;

import au.com.rumesh.fxCalculator.domain.Currency;
import au.com.rumesh.fxCalculator.domain.ExchangeRate;
import org.springframework.data.repository.Repository;

/**
 * Repository to handle persistence operation for {@link ExchangeRate} domain
 *
 * @author rnadeera
 */
public interface ExchangeRateRepository extends Repository<ExchangeRate, Long> {

    ExchangeRate findByBaseCurrencyAndTermCurrency(Currency baseCurrency, Currency termCurrency);
}
