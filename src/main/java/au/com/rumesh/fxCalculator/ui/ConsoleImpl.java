package au.com.rumesh.fxCalculator.ui;

import au.com.rumesh.fxCalculator.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @See {@link Console}
 */
public class ConsoleImpl implements Console {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    private static Pattern inputPattern = Pattern.compile("[A-Z]{3}\\s+\\d*(\\.\\d+)?+\\s+in+\\s[A-Z]{3}");

    @Override
    public void start() {
        System.out.println("********** Welcome to Currency Converter **********\n");
        withScanner(scanner -> {
            String userInput;
            do {
                System.out.println("Enter currency conversion input as <base currency> <amount> in <term currency> or type EXIT to quit application.");
                userInput = trimUserInput(scanner);
                try {
                    switch (userInput){
                        case EXIT_OPTION:
                            System.out.println("See you soon. Have a nice day!!!");
                            break;
                        default:
                            performCurrencyConversion(userInput);
                            break;
                    }
                } catch (Exception e){
                    System.out.println(handleException(e));
                }
            } while (!EXIT_OPTION.equals(userInput));
        });
    }

    private void performCurrencyConversion(String conversionInput){
        if(!inputPattern.matcher(conversionInput).matches()){
            System.out.println("Invalid currency conversion input. Sample input 'AUD 100.00 in USD' without quotes.");
            return;
        }

        String[] inputs = conversionInput.split("\\s+");
        BigDecimal convertedAmount = currencyConversionService.convertCurrency(inputs[0], inputs[3], new BigDecimal(inputs[1]));
        System.out.println(String.format("%1s %2s = %3s %4s", inputs[0], inputs[1], inputs[3], convertedAmount.toPlainString()));
    }
}
