package ls.doohickeys;

import static ls.doohickeys.Util.requireNotNull;

public final class Pair<F,S>
{

    private final F first;
    private final S second;

    private Pair(F f, S o2)
    {
        first = requireNotNull(f, "First element can't be null");
        second = requireNotNull(o2, "Second element can't be null");
    }

    public F first() { return first; }

    public S second() { return second; }

    public static <T1,T2> Pair<T1,T2> pair(T1 o1,T2 o2)
    {
        return new Pair<>(o1,o2);
    }

}
