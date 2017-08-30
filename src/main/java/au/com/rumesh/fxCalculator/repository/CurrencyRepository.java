package au.com.rumesh.fxCalculator.repository;

import au.com.rumesh.fxCalculator.domain.Currency;
import org.springframework.data.repository.Repository;

/**
 * Repository to handle persistence operation for {@link Currency} domain
 *
 * @author rnadeera
 */
public interface CurrencyRepository extends Repository<Currency, Long> {

    Currency findByCode(String code);
}
