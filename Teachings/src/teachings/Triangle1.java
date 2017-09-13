package teachings;

/**
 *
 * @author zacheriah
 */
public class Triangle1 
{
    public int base;
    public int height;
    
    public Triangle1(int base, int height)
    {
        this.base = base;
        this.height = height;
    }
    public Triangle1()
    {
        this(0,0);
    }
    
    private double calcArea()
    {
        return .5 * base * height;
    }
    
    public double getArea()
    {
        return calcArea();
    }
}
