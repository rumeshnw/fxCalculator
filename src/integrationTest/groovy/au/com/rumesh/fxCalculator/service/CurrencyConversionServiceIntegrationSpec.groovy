package au.com.rumesh.fxCalculator.service

import au.com.rumesh.fxCalculator.BaseIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Unroll

/**
 * @author rnadeera
 */
@Transactional
@Rollback
class CurrencyConversionServiceIntegrationSpec extends BaseIntegrationSpec {

    @Autowired
    CurrencyConversionService currencyConversionService

    @Unroll
    def "test convertCurrency, should return #convertedAmount when base currency #base term currency #base and amount #amount"(){
        when:
        BigDecimal response = currencyConversionService.convertCurrency(base, term, amount)

        then:
        response == convertedAmount

        where:
        base    | term  | amount                        | convertedAmount
        "AUD"   | "AUD" | BigDecimal.TEN                | BigDecimal.TEN
        "AUD"   | "USD" | new BigDecimal("100")     | new BigDecimal("83.71")
        "JPY"   | "USD" | BigDecimal.ONE                | new BigDecimal("0.01")
        "AUD"   | "JPY" | BigDecimal.ONE                | new BigDecimal("100")
        "DKK"   | "CZK" | new BigDecimal("125.75")  | new BigDecimal("466.51")
    }

    def "test convertCurrency, should throw an exception when base/term conversion cannot be calculated"(){
        when:
        BigDecimal response = currencyConversionService.convertCurrency("KRW", "FJD", new BigDecimal("1000.00"))

        then:
        def e = thrown(Exception)
        e.message == "Unable to find Rate for KRW/FJD"
    }
}
