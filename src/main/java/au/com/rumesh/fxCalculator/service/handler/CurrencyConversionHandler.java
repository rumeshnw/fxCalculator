package au.com.rumesh.fxCalculator.service.handler;

import au.com.rumesh.fxCalculator.config.ConversionResourceLocator;
import au.com.rumesh.fxCalculator.domain.ExchangeRate;
import au.com.rumesh.fxCalculator.enums.ConversionMatrix;

import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Interface for {@link au.com.rumesh.fxCalculator.domain.Currency} conversions
 *
 * @author rnadeera
 */
public interface CurrencyConversionHandler {

    BigDecimal convert(CurrencyConverter currencyConverter, BigDecimal rate);

    /**
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle direct feed conversion
     */
    static CurrencyConversionHandler directConversion(){
        return (currencyConverter, rate) -> {
            ExchangeRate exchangeRate = currencyConverter.getExchangeRate();
            return rate.multiply(exchangeRate.getRate());
        };
    }

    /**
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle unity conversion
     */
    static CurrencyConversionHandler unityConversion(){
        return (currencyConverter, rate) -> rate.multiply(BigDecimal.ONE);
    }

    /**
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle inverted conversion
     */
    static CurrencyConversionHandler invertConversion(){
        return (currencyConverter, rate) -> {
            ConversionMatrix intermediateConversionMatrix = ConversionMatrix.getConversionMatrix(currencyConverter.getConversionMatrix().getTermCurrency(),
                    currencyConverter.getConversionMatrix().getBaseCurrency());
            currencyConverter.setConversionMatrix(intermediateConversionMatrix);

            ExchangeRate exchangeRate = currencyConverter.getExchangeRate();
            return rate.multiply(BigDecimal.ONE.divide(exchangeRate.getRate(), MathContext.DECIMAL128));
        };
    }


    /**
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle cross via currency conversion
     */
    static CurrencyConversionHandler crossViaConversion(){
        return (currencyConverter, rate) -> {
            ConversionMatrix originalConversionMatrix = currencyConverter.getConversionMatrix();

            ConversionMatrix intermediateConversionMatrix = ConversionMatrix.getConversionMatrix(originalConversionMatrix.getBaseCurrency(),
                    originalConversionMatrix.getCrossViaCurrency());
            currencyConverter.setConversionMatrix(intermediateConversionMatrix);

            BigDecimal intermediateRate = rate.multiply(ConversionResourceLocator.getConverter(intermediateConversionMatrix.getConversionType()).convert(currencyConverter, BigDecimal.ONE));

            intermediateConversionMatrix = ConversionMatrix.getConversionMatrix(originalConversionMatrix.getCrossViaCurrency(), originalConversionMatrix.getTermCurrency());
            currencyConverter.setConversionMatrix(intermediateConversionMatrix);

            return ConversionResourceLocator.getConverter(intermediateConversionMatrix.getConversionType()).convert(currencyConverter, intermediateRate);
        };
    }
}
