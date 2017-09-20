package program1;

/**
 *
 * @author zacheriah mell Program Name: program1 Purpose: Check username and
 * password combinations against a predetermined database if username and
 * password combinations are correct, checks strength of and changes passwords
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

    private boolean isValidLogin(String s) {
        return s.matches("[a-zA-Z]{2}[0-9]{6}@wcupa.edu");
    }

    private boolean isValidPassword(String s) {
        int pwStrength = 0;
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSymbol = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                hasDigit = true;
            }
            if (Character.isLowerCase(s.charAt(i))) {
                hasLower = true;
            }
            if (Character.isUpperCase(s.charAt(i))) {
                hasUpper = true;
            }
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                hasSymbol = true;
            }
        }
        if (hasDigit) {
            pwStrength++;
        }
        if (hasUpper) {
            pwStrength++;
        }
        if (hasLower) {
            pwStrength++;
        }
        if (hasSymbol) {
            pwStrength++;
        }

        return pwStrength >= 3;
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

        //seeds
        //loginText.setText("ab111111@wcupa.edu");
        //passwordText.setText("a2Foofoo");
        //end seeds
        //reset
        textArea.setText("");

        //event handlers
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean activeUsername = false;
                if (isValidLogin(loginText.getText().trim())) {

                    for (int i = 0; i < logins.size(); i++) {
                        if (logins.get(i).getUsername().equals(loginText.getText().trim())) {
                            activeUsername = true;
                            if (logins.get(i).getPassword().equals(passwordText.getText().trim())) {
                                if (changeText.getText().trim().length() >= 8) {
                                    if (!changeText.getText().trim().equals(passwordText.getText().trim())) {
                                        if (isValidPassword(changeText.getText().trim())) {
                                            logins.get(i).setPassword(changeText.getText().trim());
                                            textArea.setText("Validation: OK\nNew Password: OK");
                                        } else {
                                            textArea.setText("Validation: OK\n New Password: Too Weak");
                                        }
                                    } else {
                                        textArea.setText("Validation: OK\nNew Password: Cannot be the current password");
                                    }
                                } else {
                                    textArea.setText("Validation: OK\nNew Password: Too short");
                                }

                            } else {
                                textArea.setText("Validation: Current password incorrect");
                            }
                        }
                    }
                    if (!activeUsername) {
                        textArea.setText("Validation: No such user");
                    }
                } else {
                    textArea.setText("Validation: login format incorrect");
                }

            }
        });
        dumpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < logins.size(); i++) {
                    System.out.printf("Username: %s Password: %s\n", logins.get(i).getUsername(), logins.get(i).getPassword());
                }
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
