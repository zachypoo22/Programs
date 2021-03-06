package teachings;

/**
 *
 * @author Zacheriah Mell
 */
public class Triangle2 
{
    private int base;
    private int height;
    
    /**
     *
     * @param base length of base
     * @param height length of height
     */
    public Triangle2(int base, int height)
    {
        this.base = base;
        this.height = height;
    }
    public Triangle2()
    {
        this(0,0);
    }
    
    public int getBase()
    {
        return base;
    }
    public int getHeight()
    {
        return height;
    }
    public void setBase(int base)
    {
        this.base = base;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
}
