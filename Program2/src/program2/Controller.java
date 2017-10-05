
package program2;

import java.io.File;
import javax.swing.JFileChooser;
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
    private static JFileChooser getFileChooser() {
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
        
        //event handlers
        
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
