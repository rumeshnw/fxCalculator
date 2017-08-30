package au.com.rumesh.fxCalculator.service

import au.com.rumesh.fxCalculator.domain.Currency
import au.com.rumesh.fxCalculator.domain.ExchangeRate
import au.com.rumesh.fxCalculator.enums.ConversionMatrix
import au.com.rumesh.fxCalculator.repository.CurrencyRepository
import au.com.rumesh.fxCalculator.command.CurrencyConverterCommand
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author rnadeera
 */
class CurrencyConversionServiceImplSpec extends Specification {

    CurrencyConversionServiceImpl currencyConversionService
    def setup(){
        currencyConversionService = new CurrencyConversionServiceImpl(currencyConverterCommand: new CurrencyConverterCommand())
    }

    @Unroll
    def "test convertCurrency, should throw amn exception when baseCurrency #baseCurrency termCurrency #termCurrency and amount #amount"(){
        when:
        currencyConversionService.convertCurrency(baseCurrency, termCurrency, amount)

        then:
        def e = thrown(Exception)
        e.message == errorMsg

        where:
        baseCurrency | termCurrency | amount                     | errorMsg
        null         | "USD"        | new BigDecimal("100") | "Base currency code is required."
        "AUD"        | null         | new BigDecimal("100") | "Term currency code is required."
        "AUD"        | "USD"        | null                       | "Conversion amount is required."
    }

    def "test convertCurrency, should return converted amount when all parameters are valid and exchange rate exists for given base/term currency combination"(){
        given:
        currencyConversionService.currencyRepository = Mock(CurrencyRepository){
            1 * findByCode(_ as String) >> new Currency(code: "USD", decimalPlaces: 2)
        }

        and:
        currencyConversionService.currencyConverterCommand = Mock(CurrencyConverterCommand){
            getExchangeRate() >> new ExchangeRate(rate: new BigDecimal("0.8371"))
        }

        when:
        BigDecimal convertedAmount = currencyConversionService.convertCurrency(ConversionMatrix.AUD_USD.baseCurrency, ConversionMatrix.AUD_USD.termCurrency, new BigDecimal("100"))

        then:
        convertedAmount == new BigDecimal("83.71")
    }

}
