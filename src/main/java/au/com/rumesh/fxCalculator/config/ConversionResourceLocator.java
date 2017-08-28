package au.com.rumesh.fxCalculator.config;

import au.com.rumesh.fxCalculator.enums.ConversionType;
import au.com.rumesh.fxCalculator.service.handler.CurrencyConversionHandler;

/**
 * @author rnadeera
 */
public abstract class ConversionResourceLocator {

    private ConversionResourceLocator(){ }

    public static CurrencyConversionHandler getConverter(ConversionType conversionType){
        switch (conversionType){
            case DIRECT_FEED:
                return CurrencyConversionHandler.directConversion();
            case UNITY:
                return CurrencyConversionHandler.unityConversion();
            case INVERTED:
                return CurrencyConversionHandler.invertConversion();
            case CROSS_VIA_CURRENCY:
                return CurrencyConversionHandler.crossViaConversion();
            default:
                throw new IllegalArgumentException("Unsupported conversion type");
        }
    }
}
