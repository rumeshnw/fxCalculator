package au.com.rumesh.fxCalculator.command;

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
public class CurrencyConverterCommand {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    private ConversionMatrix originalConversionMatrix;

    private ConversionMatrix currentConversionMatrix;

    public ConversionMatrix getOriginalConversionMatrix() {
        return originalConversionMatrix;
    }

    public void setOriginalConversionMatrix(ConversionMatrix originalConversionMatrix) {
        this.originalConversionMatrix = originalConversionMatrix;
    }

    public ConversionMatrix getCurrentConversionMatrix() {
        return currentConversionMatrix;
    }

    public void setCurrentConversionMatrix(ConversionMatrix currentConversionMatrix) {
        this.currentConversionMatrix = currentConversionMatrix;
    }


    public ExchangeRate getExchangeRate() {
        Currency baseCurrency = currencyRepository.findByCode(currentConversionMatrix.getBaseCurrency());
        Assert.notNull(baseCurrency, "Unable to find base currency");

        Currency termCurrency = currencyRepository.findByCode(currentConversionMatrix.getTermCurrency());
        Assert.notNull(termCurrency, "Unable to find term currency");

        ExchangeRate exchangeRate = exchangeRateRepository.findByBaseCurrencyAndTermCurrency(baseCurrency, termCurrency);
        Assert.notNull(exchangeRate, String.format("Unable to find exchange rate for %1s/%2s", originalConversionMatrix.getBaseCurrency(), originalConversionMatrix.getTermCurrency()));

        return exchangeRate;
    }
}
