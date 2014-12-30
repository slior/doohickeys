package ls.doohickeys;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Optional.empty;
import static ls.doohickeys.Pair.pair;
import static ls.doohickeys.Util.requireNotNull;

/**
 * Enables a simple pattern matching on the data given.
 */
public final class PatternMatch
{
    private PatternMatch() {}

    public static <D,R> MatchBuilder<D,R> on(D data)
    {
        return new MatchBuilder<>(data);
    }

    public static final class MatchCaseBuilder<D,R>
    {
        private final Predicate<D> predicate;
        private final MatchBuilder<D, R> mb;

        MatchCaseBuilder(Predicate<D> pred,MatchBuilder<D,R> mb)
        {
            this.predicate = requireNotNull(pred,"Predicate can't be null for match case");
            this.mb = mb;
        }

        public MatchBuilder<D,R> then(Function<D,R> expr)
        {
            mb.addCase(predicate,expr);
            return mb;
        }

        public MatchBuilder<D, R> then(R retVal)
        {
            return then(x -> retVal);
        }
    }

    public static final class MatchBuilder<D, R>
    {
        private final D data;

        private final List<Pair<Predicate<D>,Function<D,R>>> cases = new LinkedList<>();
        private Optional<Function<D, R>> defaultCase = empty();

        private MatchBuilder(D _data) { this.data = _data; }

        public MatchCaseBuilder<D,R> when(Predicate<D> pred)
        {
            return new MatchCaseBuilder<>(pred,this);
        }
        public MatchCaseBuilder<D, R> when(D value) { return when(i -> i.equals(value)); }

        public MatchBuilder<D,R> otherwise(Function<D,R> expr)
        {
            this.defaultCase = Optional.of(expr);
            return this;
        }


        void addCase(Predicate<D> cond,Function<D,R> eval)
        {
            requireNotNull(cond,"Condition for match case can't be null");
            requireNotNull(eval,"Expression for match case can't be null");
            this.cases.add(pair(cond, eval));
        }
        public R resolve()
        {
            for (Pair<Predicate<D>,Function<D,R>> c : cases)
                if (c.first().test(data)) return c.second().apply(data);

            return defaultCase
                    .map(expr -> expr.apply(data))
                    .orElseThrow(() -> new RuntimeException("No matching case for " + String.valueOf(data)));
        }


    }
}
