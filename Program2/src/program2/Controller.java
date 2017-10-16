package program2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import views.frame;

/**
 *
 * @author Zacheriah Mell
 */
public class Controller
{

    private final frame frame = new frame();
    private boolean fileLoaded = false;
    private boolean fileExists = false;
    
    //filename check
    public static String correctedName(String s)
    {
        if (s.matches(".*\\..*"))
        {
            return s;
        }
        else
        {
            return "" + s + ".txt";
        }

    }

    private static JFileChooser getFileChooser()
    {
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Editable Files", "txt"));
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser;
    }

    public Controller()
    {
        frame.setTitle("Text Editor");
        frame.setLocationRelativeTo(null);
        frame.textArea().enable(false);

//        //filename tests
//        System.out.println("text => " + correctedName("text"));
//        System.out.println("text.txt => " + correctedName("text.txt"));
//        System.out.println("text.src => " + correctedName("text.src"));
//        

        //event handlers
        frame.newItem().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                frame.textArea().enable(true);
                frame.label().setText("*");
                fileLoaded = true;
            }
        });
        frame.openItem().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JFileChooser chooser = Controller.getFileChooser();
                int status = chooser.showOpenDialog(frame);
                if (status != JFileChooser.APPROVE_OPTION)
                {
                    return;
                }

                frame.textArea().enable(true);

                File file = chooser.getSelectedFile();
                Path path = file.toPath();

                try
                {
                    Path working = Paths.get(System.getProperty("user.dir"));
                    Path relative = working.relativize(path);

                    String content = new String(Files.readAllBytes(path));
                    frame.textArea().setText(content);
                    frame.textArea().setCaretPosition(0);
                    frame.textField().setText("" + relative);

                    fileLoaded = true;
                    fileExists = true;

                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(frame, "cant open this file");
                }
                
            }
        });
        frame.saveAsItem().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JFileChooser chooser = Controller.getFileChooser();
                int status = chooser.showSaveDialog(frame);
                if (status != JFileChooser.APPROVE_OPTION)
                {
                    return;
                }

                File file = chooser.getSelectedFile();
                Path path = file.toPath();

                try
                {
                    Path working = Paths.get(System.getProperty("user.dir"));
                    Path relative = working.relativize(path);

                    String content = frame.textArea().getText();
                    Files.write(path, content.getBytes());

                    frame.textField().setText("" + relative);

                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot open file");
                }
            }
        });
        frame.saveItem().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {

                try
                {
                    Path working = Paths.get(System.getProperty("user.dir"));

                    File file = new File(working.toString(), frame.textField().getText());
                    System.out.println(frame.textField().getText());


                    Path path = file.toPath();

                    Path relative = working.relativize(path);

                    String content = frame.textArea().getText();
                    
                    Files.deleteIfExists(path);
                    Files.write(path, content.getBytes());
                    
                    fileExists = true;
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(frame, "Can't save file");
                }
            }
        });
        frame.fileMenu().addMenuListener(new MenuListener()
        {
            @Override
            public void menuSelected(MenuEvent me)
            {
                if (!fileLoaded)
                {
                    frame.saveAsItem().setEnabled(false);
                    frame.saveItem().setEnabled(false);
                }
                else
                {
                    frame.saveAsItem().setEnabled(true);
                    if(fileExists)
                    {
                        frame.saveItem().setEnabled(true);
                    }
                    else
                    {
                        frame.saveItem().setEnabled(false);
                    }
                }
            }

            @Override
            public void menuDeselected(MenuEvent me)
            {

            }

            @Override
            public void menuCanceled(MenuEvent me)
            {

            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
