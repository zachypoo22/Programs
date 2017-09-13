package teachings;

/**
 *
 * @author Zacheriah Mell
 */
public class MyMath 
{
    public static final int bestNumber = 1024;
    
    public static int pow(int base, int power)
    {
        if (power == 1)
            return base;
        else
            return base * pow(base, power - 1);
    }
    
    public static int mul(int a, int b)
    {
        if (b == 0)
            return 0;
        else
            return a + mul(a, b-1);
    }
}
