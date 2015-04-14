/*




import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

// Referenced classes of package com.handynorth.moneywise_free.util:
//            CurrencyFormatterCache

public class CurrencyUtil
{
    private static class CurrencySymbol
    {

        private boolean space;
        private String symbol;
        private boolean symbolBeforePrice;

        private String decorate(String s)
        {
            String s1;
            if (space)
            {
                s1 = " ";
            } else
            {
                s1 = "";
            }
            if (symbolBeforePrice)
            {
                return (new StringBuilder(String.valueOf(symbol))).append(s1).append(s).toString();
            } else
            {
                return (new StringBuilder(String.valueOf(s))).append(s1).append(symbol).toString();
            }
        }


        private CurrencySymbol(String s, boolean flag, boolean flag1)
        {
            symbol = s;
            symbolBeforePrice = flag;
            space = flag1;
        }

        CurrencySymbol(String s, boolean flag, boolean flag1, CurrencySymbol currencysymbol)
        {
            this(s, flag, flag1);
        }
    }


    private static final int BRL = 4;
    private static final int CHF = 9;
    private static final int DOLLAR = 2;
    private static final int EUR = 3;
    private static final int JPY = 5;
    private static final int KR = 1;
    private static final int NONE = -1;
    private static final int POUND = 6;
    private static final int RAND = 7;
    private static final int RUPEE = 8;
    private static final int WONG = 10;
    private static Map currencySymbols;
    private static NumberFormat numberFormatNoDecimals;
    private static NumberFormat numberFormatWithDecimals;
    private static NumberFormat numberFormatWithNoCurrency;

    public CurrencyUtil()
    {
    }

    public static String formatAmount(Context context, float f, Currency currency)
    {
        return formatAmount(context, f, currency, true);
    }

    public static String formatAmount(Context context, float f, Currency currency, boolean flag)
    {
        String s = CurrencyFormatterCache.get(f, currency, flag);
        if (s != null)
        {
            return s;
        }
        boolean flag1;
        String s1;
        if (!flag && f == (float)(int)f)
        {
            flag1 = false;
        } else
        {
            flag1 = true;
        }
        if (currencySymbols.containsKey(currency.getCurrencyCode()))
        {
            s1 = ((CurrencySymbol)currencySymbols.get(currency.getCurrencyCode())).decorate(formatAmountWithNoCurrency(context, f));
        } else
        {
            s1 = getNumberFormatter(context, currency, flag1).format(f);
        }
        CurrencyFormatterCache.put(f, currency, s1, flag);
        return s1;
    }

    public static String formatAmountWithNoCurrency(Context context, float f)
    {
        return getNumberFormatterNoCurrency(context).format(f);
    }

    public static String[] getAllCurrencies(Context context, boolean flag)
    {
        Object obj = Arrays.asList(context.getResources().getStringArray(0x7f0d0019));
        List list = SharedPreferencesManager.getPopularCurrencies(context);
        if (flag)
        {
            LinkedList linkedlist = new LinkedList(((java.util.Collection) (obj)));
            linkedlist.removeAll(list);
            obj = linkedlist;
        }
        ArrayList arraylist = new ArrayList(((List) (obj)).size() + list.size());
        arraylist.addAll(list);
        arraylist.addAll(((java.util.Collection) (obj)));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public static Drawable getCurrencyImage(Context context, String s, boolean flag)
    {
        boolean flag1;
        Resources resources;
        if (!flag && Preferences.useLightTheme(context))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        resources = context.getResources();
        switch (getInternalCurencyCode(s))
        {
        case 9: // '\t'
        default:
            return resources.getDrawable(0x7f0200ab);

        case 2: // '\002'
            int l1;
            if (flag1)
            {
                l1 = 0x7f02007d;
            } else
            {
                l1 = 0x7f02007e;
            }
            return resources.getDrawable(l1);

        case 3: // '\003'
            int k1;
            if (flag1)
            {
                k1 = 0x7f020075;
            } else
            {
                k1 = 0x7f020076;
            }
            return resources.getDrawable(k1);

        case 4: // '\004'
            int j1;
            if (flag1)
            {
                j1 = 0x7f020073;
            } else
            {
                j1 = 0x7f020074;
            }
            return resources.getDrawable(j1);

        case 5: // '\005'
            int i1;
            if (flag1)
            {
                i1 = 0x7f020081;
            } else
            {
                i1 = 0x7f020082;
            }
            return resources.getDrawable(i1);

        case 6: // '\006'
            int l;
            if (flag1)
            {
                l = 0x7f020077;
            } else
            {
                l = 0x7f020078;
            }
            return resources.getDrawable(l);

        case 7: // '\007'
            int k;
            if (flag1)
            {
                k = 0x7f020079;
            } else
            {
                k = 0x7f02007a;
            }
            return resources.getDrawable(k);

        case 8: // '\b'
            int j;
            if (flag1)
            {
                j = 0x7f02007b;
            } else
            {
                j = 0x7f02007c;
            }
            return resources.getDrawable(j);

        case 10: // '\n'
            break;
        }
        int i;
        if (flag1)
        {
            i = 0x7f02007f;
        } else
        {
            i = 0x7f020080;
        }
        return resources.getDrawable(i);
    }

    public static CharSequence getCurrencyLabel(String s)
    {
        switch (getInternalCurencyCode(s))
        {
        default:
            s = "";
            // fall through

        case -1: 
            return s;

        case 1: // '\001'
            return "kr";

        case 9: // '\t'
            return "Fr.";
        }
    }

    private static final int getInternalCurencyCode(String s)
    {
        if (s.equals("EUR"))
        {
            return 3;
        }
        if (s.equals("USD") || s.equals("CAD") || s.equals("AUD") || s.equals("NZD") || s.equals("HKD") || s.equals("TWD") || s.equals("MXN") || s.equals("SGD") || s.equals("ARS") || s.equals("CLP") || s.equals("COP"))
        {
            return 2;
        }
        if (s.equals("GBP") || s.equals("LBP") || s.equals("SYP") || s.equals("EGP"))
        {
            return 6;
        }
        if (s.equals("SEK") || s.equals("DKK") || s.equals("NOK") || s.equals("ISK"))
        {
            return 1;
        }
        if (s.equals("BRL"))
        {
            return 4;
        }
        if (s.equals("JPY") || s.equals("CNY"))
        {
            return 5;
        }
        if (s.equals("ZAR"))
        {
            return 7;
        }
        if (s.equals("INR"))
        {
            return 8;
        }
        if (s.equals("CHF"))
        {
            return 9;
        }
        return !s.equals("KRW") ? -1 : 10;
    }

    private static NumberFormat getNumberFormatter(Context context, Currency currency, boolean flag)
    {
        NumberFormat numberformat;
        int i;
        int j;
        if (flag)
        {
            if (numberFormatWithDecimals == null)
            {
                numberFormatWithDecimals = NumberFormat.getCurrencyInstance();
                int k = Preferences.getNumberOfDecimals(context);
                numberFormatWithDecimals.setMinimumFractionDigits(k);
                numberFormatWithDecimals.setMaximumFractionDigits(k);
            }
            numberformat = numberFormatWithDecimals;
        } else
        {
            if (numberFormatNoDecimals == null)
            {
                numberFormatNoDecimals = NumberFormat.getCurrencyInstance();
                numberFormatNoDecimals.setMinimumFractionDigits(0);
                numberFormatNoDecimals.setMaximumFractionDigits(0);
            }
            numberformat = numberFormatNoDecimals;
        }
        i = numberformat.getMaximumFractionDigits();
        j = numberformat.getMinimumFractionDigits();
        numberformat.setCurrency(currency);
        numberformat.setMaximumFractionDigits(i);
        numberformat.setMinimumFractionDigits(j);
        return numberformat;
    }

    private static NumberFormat getNumberFormatterNoCurrency(Context context)
    {
        if (numberFormatWithNoCurrency == null)
        {
            int i = Preferences.getNumberOfDecimals(context);
            numberFormatWithNoCurrency = NumberFormat.getNumberInstance();
            numberFormatWithNoCurrency.setMinimumFractionDigits(i);
            numberFormatWithNoCurrency.setMaximumFractionDigits(i);
        }
        return numberFormatWithNoCurrency;
    }

    public static boolean hasCurrencyImage(Context context, String s)
    {
        switch (getInternalCurencyCode(s))
        {
        case 9: // '\t'
        default:
            return false;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
            return true;
        }
    }

    public static void refreshNumberOfDecimals(int i)
    {
        numberFormatWithDecimals = NumberFormat.getCurrencyInstance();
        numberFormatWithDecimals.setMinimumFractionDigits(i);
        numberFormatWithDecimals.setMaximumFractionDigits(i);
        numberFormatWithNoCurrency = NumberFormat.getNumberInstance();
        numberFormatWithNoCurrency.setMinimumFractionDigits(i);
        numberFormatWithNoCurrency.setMaximumFractionDigits(i);
    }

    public static float textToFloat(Context context, String s)
    {
        int i = Preferences.getNumberOfDecimals(context);
        String s1 = s.replaceAll("[^\\-\\d\\.\\,]", "");
        if (s1.length() != 0)
        {
            int j = s1.lastIndexOf(",");
            int k = s1.lastIndexOf(".");
            int l;
            String s2;
            String s3;
            if (j > k)
            {
                l = j;
            } else
            {
                l = k;
            }
            s2 = s1.substring(l + 1);
            if (l >= 0 && (s2.length() != 3 || i != 0))
            {
                s1 = (new StringBuilder(String.valueOf(s1.substring(0, l)))).append("x").append(s2).toString();
            }
            s3 = s1.replaceAll("[\\.\\,]", "").replaceAll("x", ".");
            if (s3.length() != 0 && !s3.equals("."))
            {
                return Float.parseFloat(s3);
            }
        }
        return 0.0F;
    }

    static 
    {
        currencySymbols = new HashMap();
        currencySymbols.put("SEK", new CurrencySymbol("kr", false, true, null));
        currencySymbols.put("DKK", new CurrencySymbol("kr", true, true, null));
        currencySymbols.put("NOK", new CurrencySymbol("kr", true, true, null));
        currencySymbols.put("PLN", new CurrencySymbol("z\u0142", false, true, null));
        currencySymbols.put("BGN", new CurrencySymbol("\u043B\u0432.", false, true, null));
        currencySymbols.put("CZK", new CurrencySymbol("K\u010D", false, true, null));
        currencySymbols.put("HUF", new CurrencySymbol("Ft", false, true, null));
        currencySymbols.put("RUB", new CurrencySymbol("\u0420\u0443\u0431", false, true, null));
        currencySymbols.put("HRK", new CurrencySymbol("kn", false, true, null));
        currencySymbols.put("RSD", new CurrencySymbol("Din.", false, true, null));
        currencySymbols.put("IDR", new CurrencySymbol("Rp", false, false, null));
        currencySymbols.put("LVL", new CurrencySymbol("Ls", true, true, null));
        currencySymbols.put("LTL", new CurrencySymbol("Lt", false, true, null));
        currencySymbols.put("ZAR", new CurrencySymbol("R", true, true, null));
        currencySymbols.put("TRY", new CurrencySymbol("TL", false, true, null));
        currencySymbols.put("MXN", new CurrencySymbol("$", true, false, null));
        currencySymbols.put("CLP", new CurrencySymbol("$", true, false, null));
        currencySymbols.put("ARS", new CurrencySymbol("$", true, false, null));
        currencySymbols.put("COP", new CurrencySymbol("$", true, false, null));
        currencySymbols.put("MYR", new CurrencySymbol("RM", true, true, null));
        currencySymbols.put("VEF", new CurrencySymbol("Bs.", true, true, null));
        currencySymbols.put("BAM", new CurrencySymbol("KM", true, true, null));
    }
}
*/