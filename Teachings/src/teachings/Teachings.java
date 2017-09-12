
package teachings;

/**
 *
 * @author zacheriah
 */
public class Teachings 
{

    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        Triangle1 firstTriangle = new Triangle1(3,4);  //public fields
        Triangle2 secondTriangle = new Triangle2(5,6); //private fields
        System.out.println("first triangle height: " + firstTriangle.height);
        System.out.println("second triangle height: " + secondTriangle.getHeight());
        
        System.out.println("setting first triangle height to 7 and second triangle height to 9");
        firstTriangle.height = 7;
        secondTriangle.setHeight(9);
        
        System.out.printf("First triangle height: %d\nSecond Triangle height: %d\n", firstTriangle.height, secondTriangle.getHeight());
        
    }

}
