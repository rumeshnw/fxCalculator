package au.com.rumesh.fxCalculator.enums;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Matrix to derive base currency to term currency conversion mechanism
 *
 * @author rnadeera
 */
public enum ConversionMatrix {
    //Base currency AUD to other currencies
    AUD_AUD("AUD", "AUD", null, ConversionType.UNITY),
    AUD_CAD("AUD", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_CNY("AUD", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_CZK("AUD", "CZK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_DKK("AUD", "DKK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_EUR("AUD", "EUR", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_GBP("AUD", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_JPY("AUD", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_NOK("AUD", "NOK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_NZD("AUD", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    AUD_USD("AUD", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency CAD to other currencies
    CAD_AUD("CAD", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_CAD("CAD", "CAD", null, ConversionType.UNITY),
    CAD_CNY("CAD", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_CZK("CAD", "CZK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_DKK("CAD", "DKK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_EUR("CAD", "EUR", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_GBP("CAD", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_JPY("CAD", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_NOK("CAD", "NOK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_NZD("CAD", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CAD_USD("CAD", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency CNY to other currencies
    CNY_AUD("CNY", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_CAD("CNY", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_CNY("CNY", "CNY", null, ConversionType.UNITY),
    CNY_CZK("CNY", "CZK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_DKK("CNY", "DKK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_EUR("CNY", "EUR", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_GBP("CNY", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_JPY("CNY", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_NOK("CNY", "NOK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_NZD("CNY", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CNY_USD("CNY", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency CZK to other currencies
    CZK_AUD("CZK", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CZK_CAD("CZK", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CZK_CNY("CZK", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CZK_CZK("CZK", "CZK", null, ConversionType.UNITY),
    CZK_DKK("CZK", "DKK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    CZK_EUR("CZK", "EUR", null, ConversionType.INVERTED),
    CZK_GBP("CZK", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CZK_JPY("CZK", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CZK_NOK("CZK", "NOK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    CZK_NZD("CZK", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    CZK_USD("CZK", "USD", "EUR", ConversionType.CROSS_VIA_CURRENCY),

    //Base currency DKK to other currencies
    DKK_AUD("DKK", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    DKK_CAD("DKK", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    DKK_CNY("DKK", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    DKK_CZK("DKK", "CZK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    DKK_DKK("DKK", "DKK", null, ConversionType.UNITY),
    DKK_EUR("DKK", "EUR", null, ConversionType.INVERTED),
    DKK_GBP("DKK", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    DKK_JPY("DKK", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    DKK_NOK("DKK", "NOK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    DKK_NZD("DKK", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    DKK_USD("DKK", "USD", "EUR", ConversionType.CROSS_VIA_CURRENCY),

    //Base currency EUR to other currencies
    EUR_AUD("EUR", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    EUR_CAD("EUR", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    EUR_CNY("EUR", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    EUR_CZK("EUR", "CZK", null, ConversionType.DIRECT_FEED),
    EUR_DKK("EUR", "DKK", null, ConversionType.DIRECT_FEED),
    EUR_EUR("EUR", "EUR", null, ConversionType.UNITY),
    EUR_GBP("EUR", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    EUR_JPY("EUR", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    EUR_NOK("EUR", "NOK", null, ConversionType.DIRECT_FEED),
    EUR_NZD("EUR", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    EUR_USD("EUR", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency GBP to other currencies
    GBP_AUD("GBP", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_CAD("GBP", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_CNY("GBP", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_CZK("GBP", "CZK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_DKK("GBP", "DKK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_EUR("GBP", "EUR", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_GBP("GBP", "GBP", null, ConversionType.UNITY),
    GBP_JPY("GBP", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_NOK("GBP", "NOK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_NZD("GBP", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    GBP_USD("GBP", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency JPY to other currencies
    JPY_AUD("JPY", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_CAD("JPY", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_CNY("JPY", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_CZK("JPY", "CZK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_DKK("JPY", "DKK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_EUR("JPY", "EUR", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_GBP("JPY", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_JPY("JPY", "JPY", null, ConversionType.UNITY),
    JPY_NOK("JPY", "NOK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_NZD("JPY", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    JPY_USD("JPY", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency NOK to other currencies
    NOK_AUD("NOK", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NOK_CAD("NOK", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NOK_CNY("NOK", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NOK_CZK("NOK", "CZK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    NOK_DKK("NOK", "DKK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    NOK_EUR("NOK", "EUR", null, ConversionType.INVERTED),
    NOK_GBP("NOK", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NOK_JPY("NOK", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NOK_NOK("NOK", "NOK", null, ConversionType.UNITY),
    NOK_NZD("NOK", "NZD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NOK_USD("NOK", "USD", "EUR", ConversionType.CROSS_VIA_CURRENCY),

    //Base currency NZD to other currencies
    NZD_AUD("NZD", "AUD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_CAD("NZD", "CAD", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_CNY("NZD", "CNY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_CZK("NZD", "CZK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_DKK("NZD", "DKK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_EUR("NZD", "EUR", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_GBP("NZD", "GBP", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_JPY("NZD", "JPY", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_NOK("NZD", "NOK", "USD", ConversionType.CROSS_VIA_CURRENCY),
    NZD_NZD("NZD", "NZD", null, ConversionType.UNITY),
    NZD_USD("NZD", "USD", null, ConversionType.DIRECT_FEED),

    //Base currency USD to other currencies
    USD_AUD("USD", "AUD", null, ConversionType.INVERTED),
    USD_CAD("USD", "CAD", null, ConversionType.INVERTED),
    USD_CNY("USD", "CNY", null, ConversionType.INVERTED),
    USD_CZK("USD", "CZK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    USD_DKK("USD", "DKK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    USD_EUR("USD", "EUR", null, ConversionType.INVERTED),
    USD_GBP("USD", "GBP", null, ConversionType.INVERTED),
    USD_JPY("USD", "JPY", null, ConversionType.DIRECT_FEED),
    USD_NOK("USD", "NOK", "EUR", ConversionType.CROSS_VIA_CURRENCY),
    USD_NZD("USD", "NZD", null, ConversionType.INVERTED),
    USD_USD("USD", "USD", null, ConversionType.UNITY);


    private String baseCurrency;
    private String termCurrency;
    private String crossViaCurrency;
    private ConversionType conversionType;


    ConversionMatrix(String baseCurrency, String termCurrency, String crossViaCurrency, ConversionType conversionType) {
        this.baseCurrency = baseCurrency;
        this.termCurrency = termCurrency;
        this.crossViaCurrency = crossViaCurrency;
        this.conversionType = conversionType;
    }

    public static ConversionMatrix getConversionMatrix(String baseCurrency, String termCurrency){
        Optional<ConversionMatrix> conversionMatrixOptional = Stream.of(ConversionMatrix.values())
                .filter(conversionMatrix -> conversionMatrix.isMatchingConversion(baseCurrency, termCurrency))
                .findFirst();

        return conversionMatrixOptional.orElseThrow(() -> new IllegalArgumentException(String.format("Unable to find Rate for %1$2s/%2$2s", baseCurrency, termCurrency)));
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTermCurrency() {
        return termCurrency;
    }

    public String getCrossViaCurrency() {
        return crossViaCurrency;
    }

    public ConversionType getConversionType() {
        return conversionType;
    }

    private boolean isMatchingConversion(String baseCurrency, String termCurrency){
        return this.baseCurrency.equalsIgnoreCase(baseCurrency) && this.termCurrency.equalsIgnoreCase(termCurrency);
    }
}
