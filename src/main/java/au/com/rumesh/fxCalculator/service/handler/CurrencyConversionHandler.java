package au.com.rumesh.fxCalculator.service.handler;

import au.com.rumesh.fxCalculator.command.CurrencyConverterCommand;
import au.com.rumesh.fxCalculator.config.ConversionResourceLocator;
import au.com.rumesh.fxCalculator.domain.ExchangeRate;
import au.com.rumesh.fxCalculator.enums.ConversionMatrix;

import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Interface for facilitating different {@link au.com.rumesh.fxCalculator.domain.Currency} conversions
 *
 * @author rnadeera
 */
@FunctionalInterface
public interface CurrencyConversionHandler {

    BigDecimal convert(CurrencyConverterCommand currencyConverterCommand, BigDecimal rate);

    /**
     * Strategy to handle direct feed base to term currency conversion
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle direct feed conversion
     */
    static CurrencyConversionHandler directConversion(){
        return (currencyConverterCommand, rate) -> {
            ExchangeRate exchangeRate = currencyConverterCommand.getExchangeRate();
            return rate.multiply(exchangeRate.getRate());
        };
    }

    /**
     * Strategy to handle unity currency conversion from base to term currency
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle unity conversion
     */
    static CurrencyConversionHandler unityConversion(){
        return (currencyConverterCommand, rate) -> rate.multiply(BigDecimal.ONE);
    }

    /**
     * Strategy to handle inverted currency conversion from base to term currency
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle invert conversion
     */
    static CurrencyConversionHandler invertConversion(){
        return (currencyConverterCommand, rate) -> {
            ConversionMatrix intermediateConversionMatrix = ConversionMatrix.getConversionMatrix(currencyConverterCommand.getCurrentConversionMatrix().getTermCurrency(),
                    currencyConverterCommand.getCurrentConversionMatrix().getBaseCurrency());
            currencyConverterCommand.setCurrentConversionMatrix(intermediateConversionMatrix);

            ExchangeRate exchangeRate = currencyConverterCommand.getExchangeRate();
            return rate.multiply(BigDecimal.ONE.divide(exchangeRate.getRate(), MathContext.DECIMAL128));
        };
    }


    /**
     * Strategy to handle currency conversion from base to term currency via cross currency
     *
     * @return Concrete {@link CurrencyConversionHandler} to handle cross via currency conversion
     */
    static CurrencyConversionHandler crossViaConversion(){
        return (currencyConverterCommand, rate) -> {
            ConversionMatrix originalConversionMatrix = currencyConverterCommand.getCurrentConversionMatrix();

            ConversionMatrix intermediateConversionMatrix = ConversionMatrix.getConversionMatrix(originalConversionMatrix.getBaseCurrency(),
                    originalConversionMatrix.getCrossViaCurrency());
            currencyConverterCommand.setCurrentConversionMatrix(intermediateConversionMatrix);

            BigDecimal intermediateRate = rate.multiply(ConversionResourceLocator.getConverter(intermediateConversionMatrix.getConversionType()).convert(currencyConverterCommand, BigDecimal.ONE));

            intermediateConversionMatrix = ConversionMatrix.getConversionMatrix(originalConversionMatrix.getCrossViaCurrency(), originalConversionMatrix.getTermCurrency());
            currencyConverterCommand.setCurrentConversionMatrix(intermediateConversionMatrix);

            return ConversionResourceLocator.getConverter(intermediateConversionMatrix.getConversionType()).convert(currencyConverterCommand, intermediateRate);
        };
    }
}
