package ls.doohickeys;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

public final class Util
{

    public static final String NL = System.getProperty("line.separator");

    private Util() {}


    public static <T> T requireNotNull(final T o,final String msg)
    {
        if (o == null) throw new IllegalArgumentException(String.valueOf(msg));
        return o;
    }

    @SafeVarargs
    public static <K,V> Map<K,V> map(Pair<K,V>... entries)
    {
        final Map<K,V> ret = new HashMap<>(entries.length);
        for (Pair<K,V> e : entries) ret.put(e.first(),e.second());
        return ret;
    }

    /**
     * A syntactic sugar for {@link ls.chess.util.Pair#pair(Object, Object)}, for nicer looking code when creating maps
     * @return A new pair.
     * @see #map(Pair[])
     */
    public static <K,V> Pair<K,V> e(K k, V v)
    {
        return Pair.pair(k,v);
    }


    /**
     * Return a stream of integers between the two given number, including these numbers.
     * If the starting number is larger than the end number (to), a reverse stream is returned.
     * if (from == to), a single element sequence is returned, with that single number.
     * @param from The starting bound
     * @param to The end bound
     * @return A sequence of number, from 'from' to 'to', inclusive; possibly a descending sequence.
     */
    public static IntStream rangeInclusive(int from, int to)
    {
        if (from == to) return IntStream.of(from);
        return from < to ? //is it ascending?
                rangeClosed(from, to) :
                rangeClosed(to, from).map(i -> from - i + to); //reverse

    }

    @SuppressWarnings("unused")
    public static void dbg(String s) {
        System.err.println(String.valueOf(s));
    }

    /**
     * Tests that given string isn't null or empty.
     * Throws an {@link IllegalArgumentException} if it's null or empty.
     * @param s The string to check
     * @return The string passed if it's not empty or null, otherwise throws an exception.
     */
    public static String requireNotEmpty(final String s, final String msg)
    {
        final String m = String.valueOf(msg);
        if (requireNotNull(s,m).equals(""))
            throw new IllegalArgumentException(m);
        return s;
    }
}
