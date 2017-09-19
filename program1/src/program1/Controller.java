/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program1;

/**
 *
 * @author zacheriah
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import views.MyFrame;

public class Controller {

    private final MyFrame frame = new MyFrame();

    class login {

        private String password;
        private String username;

        public login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    private boolean isValidPassword(String s) {
        return true;
    }

    private boolean isValidLogin(String s) {
        return true;
    }

    public Controller() {
        frame.setTitle("Login");
        frame.setLocationRelativeTo(null);

        JButton changeButton = frame.getChangeButton();
        JButton dumpButton = frame.getDumpButton();
        JTextField changeText = frame.getChangeText();
        JTextField loginText = frame.getLoginText();
        JTextArea textArea = frame.getTextArea();
        JTextField passwordText = frame.getPasswordText();
        ArrayList<login> logins = new ArrayList<login>();

        logins.add(new login("ab111111@wcupa.edu", "a2Foofoo"));
        logins.add(new login("cd222222@wcupa.edu", "b.Barbar"));
        logins.add(new login("ef333333@wcupa.edu", "123456.x"));

        //event handlers
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidLogin(loginText.getText())) {
                    if (isValidPassword(passwordText.getText())) {
                        {
                            for (int i = 0; i < logins.size(); i++) {
                                if (logins.get(i).getUsername().equals(loginText.getText())) {
                                    if (logins.get(i).getPassword().equals(passwordText.getText())) {
                                        logins.get(i).setPassword(changeText.getText());
                                    }
                                }
                            }
                        }
                    }
                    else {
                        textArea.setText("invalid password");
                    }
                }
                else {
                    textArea.setText("invalid username");
                }
            }
        });
        dumpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
