
package gpa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import views.MyFrame;

/**
 *
 * @author zachm
 */
public class Controller 
{
    private final MyFrame frame = new MyFrame();
    private double gpa = 0;
    private int currentPoints = 0;
    private int possiblePoints = 0;
    private int currentCredits = 0;
    
    public Controller() {
        frame.setTitle("GPA");
        frame.setLocationRelativeTo(null);
        
        JButton addButton = frame.getAddButton();
        JTextField gradeText = frame.getGradeText();
        JTextField classText = frame.getClassText();
        JTextField creditsText = frame.getCredits();
        JTextArea textArea = frame.getTextArea();
        
        //event handlers
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCredits += Integer.parseInt(creditsText.getText());
                //currentPoints +=
                possiblePoints += calcPoints(Double.parseDouble(gradeText.getText()), Integer.parseInt(creditsText.getText())); 
                calcGPA();
                
            }
        });
    }
    public int percentToPoints(double percent)
    {
        if(percent < 60)
        {
            return 0;
        }
        else if(percent < 70)
        {
            return 1;
        }
        else if(percent <80)
        {
            return 2;
        }
        else if(percent < 90)
        {
            return 3;
        }
        else
        {
            return 4;
        }
    }
    
    public int calcPoints(double percent, int creds)
    {
        return percentToPoints(percent) * creds;
    }
    
    public void calcGPA()
    {
        
    }
    
    public static void main(String[] args) 
    {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
