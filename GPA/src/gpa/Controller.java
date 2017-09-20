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
public class Controller {

    private final MyFrame frame = new MyFrame();
    private int points = 0;
    private int credits = 0;

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
                points += percentToPoints(Integer.parseInt(gradeText.getText())) * Integer.parseInt(creditsText.getText());
                credits += Integer.parseInt(creditsText.getText());
                textArea.append("\n" + classText.getText() + "\t\t" + gradeText.getText() + "\t" + calcGPA());
            }
        });
    }

    public int percentToPoints(int percent) {
        if (percent < 60) {
            return 0;
        } else if (percent < 70) {
            return 1;
        } else if (percent < 80) {
            return 2;
        } else if (percent < 90) {
            return 3;
        } else {
            return 4;
        }
    }

    public double calcGPA() {
        return (double) points / (double) credits;
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
