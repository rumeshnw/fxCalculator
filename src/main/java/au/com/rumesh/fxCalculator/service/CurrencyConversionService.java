package au.com.rumesh.fxCalculator.service;

import java.math.BigDecimal;

/**
 * Service for handling currency conversion process
 *
 * @author rnadeera
 */
public interface CurrencyConversionService {

    BigDecimal convertCurrency(String baseCurrency, String termCurrency, BigDecimal amount);
}
