package program2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import views.frame;

/**
 *
 * @author Zacheriah Mell
 * @ProgramName program2
 * @Purpose: Allows a user to create or open existing text files, edit them and
 * save changes.
 */
public class Controller
{

    private final frame frame = new frame();
    private boolean fileLoaded = false;
    private boolean fileExists = false;
    private boolean fileModified = false;
    private boolean subdir = false;

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
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

//        //filename tests
//        System.out.println("text => " + correctedName("text"));
//        System.out.println("text.txt => " + correctedName("text.txt"));
//        System.out.println("text.src => " + correctedName("text.src"));
//        
        //event handlers
        frame.textArea().addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent ke)
            {
                frame.label().setText("*");
                fileModified = true;
            }

            @Override
            public void keyPressed(KeyEvent ke)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent ke)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        frame.newItem().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (fileModified)
                {
                    int reply = JOptionPane.showConfirmDialog(frame, "do you want to discard your changes?", "Changes May be Lost", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION)
                    {
                        return;
                    }
                }
                
                fileExists = false;
                frame.textArea().setText("");
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
                if (fileModified)
                {
                    int reply = JOptionPane.showConfirmDialog(frame, "do you want to discard your changes?", "Changes May be Lost", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION)
                    {
                        return;
                    }
                }

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

                frame.label().setText("");
                fileModified = false;

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

                Path working = Paths.get(System.getProperty("user.dir"));
                File file1 = chooser.getSelectedFile();
                if (file1.exists())
                {
                    int reply = JOptionPane.showConfirmDialog(frame, "do you want to overwrite the existing file?", "File already exists", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION)
                    {
                        return;
                    }
                }
//                File file = new File("" + working + "/" + correctedName(file1.getName()));

                Path path = file1.toPath();

                try
                {

                    Path relative = working.relativize(path);

                    String content = frame.textArea().getText();
                    Files.write(path, content.getBytes());

                    frame.textField().setText("" + relative);

                    String fname = file1.getName();
//                    file1.renameTo(new File(correctedName(fname)));
                    Files.move(file1.toPath(), file1.toPath().resolveSibling(correctedName(fname)));

                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot open file");
                }

                frame.label().setText("");
                fileModified = false;
                fileExists = true;
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
                    String workingstr = working.toString();
//                    if(subdir)
//                    {
//                        workingstr = workingstr + "/subdir/";
//                    }
                    File file = new File(workingstr, frame.textField().getText());
                    Path path = file.toPath();
                    String content = frame.textArea().getText();

                    Files.deleteIfExists(path);
                    Files.write(path, content.getBytes());

                    fileExists = true;
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(frame, "Can't save file");
                }

                frame.label().setText("");
                fileModified = false;
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
                    if (fileExists)
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

        frame.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent we)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent we)
            {
                if (fileModified)
                {
                    int reply = JOptionPane.showConfirmDialog(frame, "Do you want to discard your changes?");
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                    else
                    {
                        return;
                    }
                }
                else
                {
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent we)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent we)
            {
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent we)
            {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent we)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent we)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
