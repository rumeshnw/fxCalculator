package au.com.rumesh.fxCalculator.config;

import au.com.rumesh.fxCalculator.service.CurrencyConversionService;
import au.com.rumesh.fxCalculator.service.CurrencyConversionServiceImpl;
import au.com.rumesh.fxCalculator.command.CurrencyConverterCommand;
import au.com.rumesh.fxCalculator.ui.Console;
import au.com.rumesh.fxCalculator.ui.ConsoleImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rnadeera
 */
@Configuration
@Import(PersistanceConfig.class)
@EnableJpaRepositories("au.com.rumesh.fxCalculator.repository")
public class AppConfig {

    @Bean
    public Console console(){
        return new ConsoleImpl();
    }

    @Bean
    @Transactional
    public CurrencyConversionService currencyConversionService(){
        return new CurrencyConversionServiceImpl();
    }

    @Bean
    @Transactional
    public CurrencyConverterCommand currencyConverterCommand(){
        return new CurrencyConverterCommand();
    }
}
