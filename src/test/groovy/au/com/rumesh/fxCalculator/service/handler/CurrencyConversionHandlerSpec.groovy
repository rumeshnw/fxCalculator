package au.com.rumesh.fxCalculator.service.handler

import au.com.rumesh.fxCalculator.command.CurrencyConverterCommand
import au.com.rumesh.fxCalculator.domain.ExchangeRate
import au.com.rumesh.fxCalculator.enums.ConversionMatrix
import spock.lang.Ignore
import spock.lang.Specification

import java.math.MathContext

/**
 * @author rnadeera
 */
class CurrencyConversionHandlerSpec extends Specification {

    def "test directConversion, should apply direct conversion strategy and return exchange rate for base/term currency combination"(){
        given:
        CurrencyConverterCommand currencyConverter = Mock(CurrencyConverterCommand){
            1 * getExchangeRate() >> new ExchangeRate(rate: new BigDecimal("0.8371"))
        }

        and:
        currencyConverter.currentConversionMatrix = ConversionMatrix.AUD_USD
        currencyConverter.originalConversionMatrix = ConversionMatrix.AUD_USD

        when:
        BigDecimal exchangeRate = CurrencyConversionHandler.directConversion().convert(currencyConverter, new BigDecimal("1"))

        then:
        exchangeRate == new BigDecimal("0.8371")
    }

    def "test unityConversion, should apply unity conversion strategy and return exchange rate for base/term currency combination"(){
        given:
        CurrencyConverterCommand currencyConverter = Mock(CurrencyConverterCommand){
            0 * getExchangeRate() >> new ExchangeRate(rate: new BigDecimal("1"))
        }

        when:
        BigDecimal exchangeRate = CurrencyConversionHandler.unityConversion().convert(currencyConverter, new BigDecimal("1"))

        then:
        exchangeRate == new BigDecimal("1")
    }

    def "test invertConversion, should apply invert conversion strategy and return exchange rate for base/term currency combination"(){
        given:
        CurrencyConverterCommand currencyConverter = Mock(CurrencyConverterCommand){
            1 * getExchangeRate() >> new ExchangeRate(rate: new BigDecimal("0.8371"))

            2 * getCurrentConversionMatrix() >> ConversionMatrix.USD_AUD
        }

        when:
        BigDecimal exchangeRate = CurrencyConversionHandler.invertConversion().convert(currencyConverter, new BigDecimal("1"))

        then:
        exchangeRate == BigDecimal.ONE.divide(new BigDecimal("0.8371"), MathContext.DECIMAL128)
    }
}
