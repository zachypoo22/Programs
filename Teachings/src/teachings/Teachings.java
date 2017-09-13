
package teachings;

/**
 *
 * @author Zacheriah Mell
 */
public class Teachings 
{

    /**
     * @param args why are you calling my main like this?
     */
    public static void main(String[] args) 
    {
        Triangle1 firstTriangle = new Triangle1(3,4);  //public fields
        Triangle2 secondTriangle = new Triangle2(5,6); //private fields
        System.out.println("first triangle height: " + firstTriangle.height);
        System.out.println("second triangle height: " + secondTriangle.getHeight());
        System.out.println("first triangle area: " + firstTriangle.getArea());
        
        System.out.println("setting first triangle height to 7 and second triangle height to 9");
        firstTriangle.height = 7;
        secondTriangle.setHeight(9);
        
        System.out.printf("First triangle height: %d\nSecond Triangle height: %d\n", firstTriangle.height, secondTriangle.getHeight());
        
        System.out.println("best number: " + MyMath.bestNumber);
        System.out.println("2 to the 10th power: " + MyMath.pow(2, 10));
        System.out.println("4 * 8 = " + MyMath.mul(4, 8));        
    }

}
