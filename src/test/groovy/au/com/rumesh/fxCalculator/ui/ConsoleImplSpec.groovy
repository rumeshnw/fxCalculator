package au.com.rumesh.fxCalculator.ui

import au.com.rumesh.fxCalculator.service.CurrencyConversionService
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author rnadeera
 */
class ConsoleImplSpec extends Specification {

    ConsoleImpl consoleImpl
    def setup(){
        consoleImpl = new ConsoleImpl()
    }

    @Unroll
    def "test performCurrencyConversion, should return invalid input format message when conversion string is #conversionString"(){
        given:
        consoleImpl.currencyConversionService = Mock(CurrencyConversionService){
            0 * convertCurrency(*_) >> BigDecimal.ZERO
        }

        when:
        String conversionResponse = consoleImpl.performCurrencyConversion(conversionString)

        then:
        conversionResponse == "Invalid currency conversion input. Sample input 'AUD 100.00 in USD' without quotes."

        where:
        _ | conversionString
        _ | ""
        _ | "   "
        _ | "ABVDhdhfhsf"
        _ | "AUD"
        _ | "AUD 100"
        _ | "AUD 100 in"
        _ | "100 AUD in JPY"
    }

    @Unroll
    def "test performCurrencyConversion, should return #output when base currency #base term currency #term and amount #amount"(){
        given:
        String inputString = "$base $amount in $term"

        and:
        consoleImpl.currencyConversionService = Mock(CurrencyConversionService){
            1 * convertCurrency(*_) >> new BigDecimal("100")
        }

        when:
        String response = consoleImpl.performCurrencyConversion(inputString)

        then:
        response == output

        where:
        base    | term  | amount            | output
        "AUD"   | "JPY" | BigDecimal.ONE    | "AUD 1 = JPY 100"
    }
}
