package au.com.rumesh.fxCalculator.service.handler;

import au.com.rumesh.fxCalculator.domain.Currency;
import au.com.rumesh.fxCalculator.domain.ExchangeRate;
import au.com.rumesh.fxCalculator.enums.ConversionMatrix;
import au.com.rumesh.fxCalculator.repository.CurrencyRepository;
import au.com.rumesh.fxCalculator.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author rnadeera
 */
public class CurrencyConverter {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    private ConversionMatrix conversionMatrix;

    public ConversionMatrix getConversionMatrix() {
        return conversionMatrix;
    }

    public void setConversionMatrix(ConversionMatrix conversionMatrix) {
        this.conversionMatrix = conversionMatrix;
    }


    public ExchangeRate getExchangeRate() {
        Currency baseCurrency = currencyRepository.findByCode(conversionMatrix.getBaseCurrency());
        Assert.notNull(baseCurrency, "Unable to find base currency");

        Currency termCurrency = currencyRepository.findByCode(conversionMatrix.getTermCurrency());
        Assert.notNull(termCurrency, "Unable to find term currency");

        ExchangeRate exchangeRate = exchangeRateRepository.findByBaseCurrencyAndTermCurrency(baseCurrency, termCurrency);
        Assert.notNull(exchangeRate, String.format("Unable to find exchange rate for %1s/%2s", conversionMatrix.getBaseCurrency(), conversionMatrix.getTermCurrency()));

        return exchangeRate;
    }
}
