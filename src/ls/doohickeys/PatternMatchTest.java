package ls.doohickeys;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class PatternMatchTest
{

    @Test
    public void factorial() { assertEquals(24, fact(4)); }

    private int fact(int n)
    {
        return PatternMatch.<Integer,Integer>
                on(n)
                .when(0).then(1)
                .when(i -> i > 0).then(i -> i * fact(i-1))
                .resolve();
    }

    private int fib(int n)
    {
        return PatternMatch.<Integer,Integer>
                on(n)
                .when(1).then(1)
                .when(2).then(1)
                .otherwise(i -> fib(i-1) + fib(i-2))
                .resolve();
    }

    @Test
    public void defaultCaseWithRecursion() throws Exception
    {
        assertEquals(5,fib(5));

    }
}
