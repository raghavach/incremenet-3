/*// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 



import android.content.Context;
import com.dropbox.client2.session.AccessTokenPair;
import com.handynorth.moneywise_free.accounts.AccountManager;
import com.handynorth.moneywise_free.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// Referenced classes of package com.handynorth.moneywise_free.db:
//            SharedPreferencesWrapper

public class SharedPreferencesManager
{

    private static final String BUDGET_VIEW_PERIOD = "sticky.budget.view.period";
    private static final String CALCULATOR_MIN = "calculator.min";
    private static final String DROPBOX_ACCESS_TOKEN_KEY = "dropbox.accesstoken.key";
    private static final String DROPBOX_ACCESS_TOKEN_SECRET = "dropbox.accesstoken.secret";
    private static final String EXPANDED_LIST_ROWS = "sticky.expanded.list.rows";
    private static final String FILTER_KEY = "sticky.filter";
    private static final String GRAPH_TYPE_INDEX_KEY = "sticky.graphtype";
    private static final String INTERSTITIAL_AD_COUNT_DOWN = "interstitial.ad.countdown";
    private static final String LAST_EXPORT_DATE = "last.export.date";
    private static final String LIST_GROUPING_INDEX_KEY = "sticky.listgrouping";
    private static final String POPULAR_CURRENCIES_KEY = "popular.currencies";
    private static final String REGISTER_ACCOUNT_INDEX_KEY = "sticky.account.register";
    private static final String SELECTED_CURRENCY_KEY = "sticky.currency";
    private static final String SHOW_PLEASE_RATE_DIALOG = "show.pleaserate.dialog";

    public SharedPreferencesManager()
    {
    }

    public static void clearDropBoxAccessToken(Context context)
    {
        if (SharedPreferencesWrapper.contains(context, "dropbox.accesstoken.secret"))
        {
            SharedPreferencesWrapper.putString(context, "dropbox.accesstoken.key", null);
            SharedPreferencesWrapper.putString(context, "dropbox.accesstoken.secret", null);
        }
    }

    public static float getCalculatorMin(Context context)
    {
        return SharedPreferencesWrapper.getFloat(context, "calculator.min", 0.0F);
    }

    public static AccessTokenPair getDropBoxAccessToken(Context context)
    {
        String s = SharedPreferencesWrapper.getString(context, "dropbox.accesstoken.key", null);
        String s1 = SharedPreferencesWrapper.getString(context, "dropbox.accesstoken.secret", null);
        if (s == null || s1 == null)
        {
            return null;
        } else
        {
            return new AccessTokenPair(s, s1);
        }
    }

    public static int getInterstitialAdCountDown(Context context, int i)
    {
        return SharedPreferencesWrapper.getInt(context, "interstitial.ad.countdown", i);
    }

    public static Date getLastExportDate(Context context)
    {
        long l = SharedPreferencesWrapper.getLong(context, "last.export.date", 0L);
        if (l == 0L)
        {
            return null;
        } else
        {
            return new Date(l);
        }
    }

    public static List getPopularCurrencies(Context context)
    {
        String s = SharedPreferencesWrapper.getString(context, "popular.currencies", "");
        ArrayList arraylist = new ArrayList();
        StringTokenizer stringtokenizer = new StringTokenizer(s, ",");
        do
        {
            String s1;
            do
            {
                if (!stringtokenizer.hasMoreTokens())
                {
                    return arraylist;
                }
                s1 = stringtokenizer.nextToken();
            } while (s1.length() <= 0);
            arraylist.add(s1);
        } while (true);
    }

    public static int getStickyBudgetViewPeriod(Context context)
    {
        return SharedPreferencesWrapper.getInt(context, "sticky.budget.view.period", 1);
    }

    public static String getStickyCurrency(Context context)
    {
        return SharedPreferencesWrapper.getString(context, "sticky.currency", "USD");
    }

    public static Set getStickyExpandedListRows(Context context)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(SharedPreferencesWrapper.getString(context, "sticky.expanded.list.rows", ""), ",");
        HashSet hashset = new HashSet();
        do
        {
            if (!stringtokenizer.hasMoreTokens())
            {
                return hashset;
            }
            hashset.add(stringtokenizer.nextToken());
        } while (true);
    }

    public static long getStickyFilter(Context context)
    {
        return SharedPreferencesWrapper.getLong(context, "sticky.filter", -1L);
    }

    public static int getStickyGraphTypeIndex(Context context)
    {
        return SharedPreferencesWrapper.getInt(context, "sticky.graphtype", 2);
    }

    public static int getStickyListGroupingIndex(Context context)
    {
        int i = SharedPreferencesWrapper.getInt(context, "sticky.listgrouping", 3);
        if (i > 3)
        {
            i = 3;
        }
        return i;
    }

    public static int getStickyRegisterAccountIndex(Context context)
    {
        int i = SharedPreferencesWrapper.getInt(context, "sticky.account.register", 0);
        if (i >= AccountManager.getAllAccounts(context).size())
        {
            setStickyRegisterAccountIndex(context, 0);
            i = 0;
        }
        return i;
    }

    public static boolean isListRowExpanded(Context context, String s)
    {
        return SharedPreferencesWrapper.getString(context, "sticky.expanded.list.rows", "").contains(s);
    }

    public static void notifyCurrencyPicked(Context context, String s)
    {
        int i = 20;
        String s1 = SharedPreferencesWrapper.getString(context, "popular.currencies", "").replace((new StringBuilder(String.valueOf(s))).append(",").toString(), "");
        String s2 = (new StringBuilder(String.valueOf(s))).append(",").append(s1).toString();
        if (s2.length() < i)
        {
            i = s2.length();
        }
        SharedPreferencesWrapper.putString(context, "popular.currencies", s2.substring(0, i));
    }

    public static void setCalculatorMin(Context context, float f)
    {
        SharedPreferencesWrapper.putFloat(context, "calculator.min", f);
    }

    public static void setDropBoxAccessToken(Context context, AccessTokenPair accesstokenpair)
    {
        SharedPreferencesWrapper.putString(context, "dropbox.accesstoken.key", accesstokenpair.key);
        SharedPreferencesWrapper.putString(context, "dropbox.accesstoken.secret", accesstokenpair.secret);
    }

    public static void setInterstitialAdCountDown(Context context, int i)
    {
        SharedPreferencesWrapper.putInt(context, "interstitial.ad.countdown", i);
    }

    public static void setLastExportDate(Context context, Date date)
    {
        SharedPreferencesWrapper.putLong(context, "last.export.date", date.getTime());
    }

    public static void setShowPleaseRateDialog(Context context, boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        SharedPreferencesWrapper.putInt(context, "show.pleaserate.dialog", i);
    }

    public static void setStickyBudgetViewPeriod(Context context, int i)
    {
        SharedPreferencesWrapper.putInt(context, "sticky.budget.view.period", i);
    }

    public static void setStickyCurrency(Context context, String s)
    {
        SharedPreferencesWrapper.putString(context, "sticky.currency", s);
    }

    public static void setStickyExpandedListRows(Context context, Set set)
    {
        SharedPreferencesWrapper.putString(context, "sticky.expanded.list.rows", Util.stringSetToCSV(set));
    }

    public static void setStickyFilter(Context context, long l)
    {
        SharedPreferencesWrapper.putLong(context, "sticky.filter", l);
    }

    public static void setStickyGraphTypeIndex(Context context, int i)
    {
        SharedPreferencesWrapper.putInt(context, "sticky.graphtype", i);
    }

    public static void setStickyListGroupingIndex(Context context, int i)
    {
        SharedPreferencesWrapper.putInt(context, "sticky.listgrouping", i);
    }

    public static void setStickyRegisterAccountIndex(Context context, int i)
    {
        SharedPreferencesWrapper.putInt(context, "sticky.account.register", i);
    }

    public static boolean showPleaseRateDialog(Context context)
    {
        return SharedPreferencesWrapper.getInt(context, "show.pleaserate.dialog", 1) == 1;
    }
}
*/