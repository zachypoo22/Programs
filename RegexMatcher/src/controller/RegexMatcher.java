package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import views.TheFrame;

public class RegexMatcher {

  private final TheFrame frame = new TheFrame();

  public RegexMatcher() {
    frame.setTitle(getClass().getSimpleName());
    frame.setLocationRelativeTo(null);
    frame.getReportField().setEditable(false);
    frame.getReportField().setBackground(Color.yellow);
    frame.getReportField().setForeground(Color.black);

    // frame.getRegexField().setText("[a-z]+"); // seed
    // make NetBeans work for you starting from:
    // frame.getSampleStringField().addKeyListener(new KeyListener());
    
    // If you change KeyListener to KeyAdapter (fix imports) 
    // you can eliminate the two unused ones.
    
    frame.getSampleStringField().addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent ke) {
      }

      @Override
      public void keyPressed(KeyEvent ke) {
      }

      @Override
      public void keyReleased(KeyEvent ke) {
        String regex = frame.getRegexField().getText();
        String test_str = frame.getSampleStringField().getText();
        String report;
        if (test_str.matches(regex)) {
          report = "yes";
        }
        else {
          report = "no";
        }
        frame.getReportField().setText(report);
      }
    });
    
  }

  public static void main(String[] args) {
    RegexMatcher app = new RegexMatcher();
    app.frame.setVisible(true);
  }
}
