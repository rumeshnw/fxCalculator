package au.com.rumesh.fxCalculator.service.handler

import au.com.rumesh.fxCalculator.BaseIntegrationSpec
import au.com.rumesh.fxCalculator.command.CurrencyConverterCommand
import au.com.rumesh.fxCalculator.enums.ConversionMatrix
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

/**
 * @author rnadeera
 */
@Transactional
@Rollback
class CurrencyConversionHandlerIntegrationSpec extends BaseIntegrationSpec {

    @Autowired
    CurrencyConverterCommand currencyConverterCommand

    def "test crossViaConversion, should apply cross via currency conversion strategy and return exchange rate for base/term currency combination"(){
        given:
        currencyConverterCommand.currentConversionMatrix = ConversionMatrix.AUD_JPY
        currencyConverterCommand.originalConversionMatrix = ConversionMatrix.AUD_JPY

        when:
        BigDecimal exchangeRate = CurrencyConversionHandler.crossViaConversion().convert(currencyConverterCommand, new BigDecimal("1"))

        then:
        exchangeRate == new BigDecimal("100.410145")

    }
}
