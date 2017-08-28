package au.com.rumesh.fxCalculator.service;

import au.com.rumesh.fxCalculator.config.ConversionResourceLocator;
import au.com.rumesh.fxCalculator.enums.ConversionMatrix;
import au.com.rumesh.fxCalculator.repository.CurrencyRepository;
import au.com.rumesh.fxCalculator.service.handler.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @See {@link CurrencyConversionService}
 * @author rnadeera
 */
public class CurrencyConversionServiceImpl implements CurrencyConversionService{

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public BigDecimal convertCurrency(String baseCurrency, String termCurrency, BigDecimal amount) {
        Assert.notNull(baseCurrency, "Base currency code is required.");
        Assert.notNull(termCurrency, "Term currency code is required.");
        Assert.notNull(amount, "Conversion amount is required.");

        BigDecimal exchangeRate = getExchangeRate(baseCurrency, termCurrency);

        return amount.multiply(exchangeRate).setScale(currencyRepository.findByCode(baseCurrency).getDecimalPlaces(), RoundingMode.HALF_EVEN);
    }

    private BigDecimal getExchangeRate(String baseCurrency, String termCurrency){
        ConversionMatrix conversionMatrix = ConversionMatrix.getConversionMatrix(baseCurrency, termCurrency);
        currencyConverter.setOriginalConversionMatrix(conversionMatrix);
        currencyConverter.setCurrentConversionMatrix(conversionMatrix);

        return ConversionResourceLocator.getConverter(conversionMatrix.getConversionType()).convert(currencyConverter, BigDecimal.ONE);
    }
}
