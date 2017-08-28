package au.com.rumesh.fxCalculator.service.handler

import au.com.rumesh.fxCalculator.domain.ExchangeRate
import au.com.rumesh.fxCalculator.enums.ConversionMatrix
import au.com.rumesh.fxCalculator.domain.Currency
import au.com.rumesh.fxCalculator.repository.CurrencyRepository
import au.com.rumesh.fxCalculator.repository.ExchangeRateRepository
import spock.lang.Specification
import spock.lang.Unroll

class CurrencyConverterSpec extends Specification {

    @Unroll
    def "test getExchangeRate, should throw an exception when base currency is #baseCurrency and term currency is #termCurrency"(){
        given:
        CurrencyConverter currencyConverter = new CurrencyConverter(currentConversionMatrix: ConversionMatrix.AUD_CAD)

        and:
        currencyConverter.currencyRepository = Mock(CurrencyRepository){
            findByCode("AUD") >> baseCurrency

            findByCode("CAD") >> termCurrency
        }

        when:
        currencyConverter.getExchangeRate()

        then:
        def e = thrown(Exception)
        e.message == errorMessage

        where:
        baseCurrency                | termCurrency              | errorMessage
        null                        | new Currency(code: "CAD") | "Unable to find base currency"
        new Currency(code: "AUD")   | null                      | "Unable to find term currency"
    }

    def "test getExchangeRate, should throw an exception when exchange rate not exists for base/term currency combination"(){
        given:
        CurrencyConverter currencyConverter = new CurrencyConverter(originalConversionMatrix: ConversionMatrix.AUD_CAD, currentConversionMatrix: ConversionMatrix.AUD_USD)

        and:
        currencyConverter.currencyRepository = Mock(CurrencyRepository){
            2 * findByCode(_ as String) >> {String code -> new Currency(code: code)}
        }

        and:
        currencyConverter.exchangeRateRepository = Mock(ExchangeRateRepository){
            1 * findByBaseCurrencyAndTermCurrency(_ as Currency, _ as Currency) >> null
        }

        when:
        currencyConverter.getExchangeRate()

        then:
        def e = thrown(Exception)
        e.message == "Unable to find exchange rate for ${currencyConverter.originalConversionMatrix.baseCurrency}/${currencyConverter.originalConversionMatrix.termCurrency}"
    }

    def "test getExchangeRate, should return ExchangeRate instance when exchange rate exists for given base/term currency combination"(){
        given:
        CurrencyConverter currencyConverter = new CurrencyConverter(originalConversionMatrix: ConversionMatrix.AUD_CAD, currentConversionMatrix: ConversionMatrix.AUD_USD)

        and:
        ExchangeRate exchangeRate = new ExchangeRate(baseCurrency: new Currency(code: ConversionMatrix.AUD_USD.baseCurrency), termCurrency: new Currency(code: ConversionMatrix.AUD_USD.termCurrency), rate: 0.8371)

        and:
        currencyConverter.currencyRepository = Mock(CurrencyRepository){
            2 * findByCode(_ as String) >> {String code -> new Currency(code: code)}
        }

        and:
        currencyConverter.exchangeRateRepository = Mock(ExchangeRateRepository){
            1 * findByBaseCurrencyAndTermCurrency(_ as Currency, _ as Currency) >> exchangeRate
        }

        when:
        ExchangeRate response = currencyConverter.getExchangeRate()

        then:
        response.baseCurrency == exchangeRate.baseCurrency
        response.termCurrency == exchangeRate.termCurrency
        response.rate == exchangeRate.rate
    }
}
