package au.com.rumesh.fxCalculator;


import au.com.rumesh.fxCalculator.config.AppConfig;
import au.com.rumesh.fxCalculator.ui.Console;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bootstrap class to start the application
 *
 * @author rnadeera
 */
public class ApplicationBootstrap {
    public static void main( String[] args ) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Console console = ctx.getBean(Console.class);
        console.start();
    }
}
