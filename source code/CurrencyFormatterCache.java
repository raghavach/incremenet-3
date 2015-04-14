/*




import android.util.Log;

import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CurrencyFormatterCache
{

    private static final Integer BIG_INT = Integer.valueOf(9999);
    private static final int MAX_ELEMENTS = 500;
    private static final Integer ONE = Integer.valueOf(1);
    private static HashMap cache;
    private static HashMap hitCounter;

    public CurrencyFormatterCache()
    {
    }

    private static void decimate()
    {
        int i;
        HashSet hashset;
        i = 3;
        hashset = new HashSet(200);
_L3:
        if (hashset.size() < 150) goto _L2; else goto _L1
_L1:
        Iterator iterator1 = hashset.iterator();
_L5:
        if (!iterator1.hasNext())
        {
            Log.d(" ", (new StringBuilder("Decimating currency cache, threshold=")).append(i).toString());
            return;
        }
        break MISSING_BLOCK_LABEL_152;
_L2:
        Iterator iterator = hitCounter.entrySet().iterator();
_L4:
label0:
        {
            if (iterator.hasNext())
            {
                break label0;
            }
            i += 3;
        }
          goto _L3
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        if (((Integer)entry.getValue()).intValue() <= i)
        {
            hitCounter.put((String)entry.getKey(), BIG_INT);
            hashset.add((String)entry.getKey());
        }
          goto _L4
        String s = (String)iterator1.next();
        cache.remove(s);
        hitCounter.remove(s);
          goto _L5
    }

    public static String get(float f, Currency currency, boolean flag)
    {
        if (cache == null)
        {
            cache = new HashMap();
            hitCounter = new HashMap();
        }
        String s = getKey(f, currency, flag);
        Integer integer = (Integer)hitCounter.get(s);
        if (integer != null)
        {
            hitCounter.put(s, Integer.valueOf(1 + integer.intValue()));
        }
        return (String)cache.get(s);
    }

    private static final String getKey(float f, Currency currency, boolean flag)
    {
        String s;
        if (flag)
        {
            s = "1";
        } else
        {
            s = "0";
        }
        return (new StringBuilder(String.valueOf(String.valueOf(f)))).append(currency.getCurrencyCode()).append(s).toString();
    }

    public static void put(float f, Currency currency, String s, boolean flag)
    {
        if (cache == null)
        {
            cache = new HashMap();
            hitCounter = new HashMap();
        }
        if (cache.size() >= 500)
        {
            decimate();
        }
        String s1 = getKey(f, currency, flag);
        cache.put(s1, s);
        hitCounter.put(s1, ONE);
    }

    public static void refreshCache()
    {
        cache = null;
    }

}
*/