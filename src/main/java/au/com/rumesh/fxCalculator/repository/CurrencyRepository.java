package au.com.rumesh.fxCalculator.repository;

import au.com.rumesh.fxCalculator.domain.Currency;
import org.springframework.data.repository.Repository;

public interface CurrencyRepository extends Repository<Currency, Long> {

    Currency findByCode(String code);
}
