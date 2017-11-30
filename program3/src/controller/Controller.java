package controller;

import figure.Figure;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import views.AddRectDialog;
import views.AddCircDialog;
import views.Canvas;
import views.FigureFrame;

public class Controller
{

Figure selectedFigure = null;

private int lastX, lastY;
private Figure highlightedFigure;

private final FigureFrame frame = new FigureFrame();
private final Canvas canvas = frame.getCanvas();

// figures which appear in canvas and in list
private final List<Figure> figureList = new ArrayList<>();

// model for Figure selection list
private final DefaultListModel listModel = new DefaultListModel();

// dialogs
private AddRectDialog addRectDialog = new AddRectDialog(frame, true);
private AddCircDialog addCircDialog = new AddCircDialog(frame, true);

public static String correctedName(String s)
{
    if (s.matches(".*\\..*"))
    {
        return s;
    }
    else
    {
        return "" + s + ".fig";
    }

}

private static JFileChooser getFileChooser()
{
    JFileChooser chooser = new JFileChooser();

    chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    chooser.addChoosableFileFilter(new FileNameExtensionFilter("Editable Files", "fig"));
    chooser.setAcceptAllFileFilterUsed(false);
    return chooser;
}

public Controller()
{
    frame.setTitle("Figures");
    frame.setLocationRelativeTo(null);
    frame.setSize(800, 500);

    canvas.setFigures(figureList);

    frame.getFigureList().setModel(listModel);

    // keep figureList from taking too much vertical space
    frame.getFigureList().getParent().setPreferredSize(new Dimension(0, 0));

    // permit only single element selection
    frame.getFigureList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // set the spinner model
    frame.getScaleSpinner().setModel(new SpinnerNumberModel(1.0, 0.1, 5.0, 0.05));

    frame.getFigureList().addListSelectionListener(new ListSelectionListener()
    {
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        highlightedFigure = (Figure) frame.getFigureList().getSelectedValue();
    }
    });

    frame.getScaleSpinner().addChangeListener(new ChangeListener()
    {
    @Override
    public void stateChanged(ChangeEvent e)
    {
        if (Canvas.getTopFigure() == null)
        {
            return;
        }
        double d = (Double) frame.getScaleSpinner().getValue();
        Canvas.getTopFigure().setScale(d);
        canvas.repaint();
    }
    });

    frame.getRemoveBtn().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (highlightedFigure == null)
        {
            return;
        }

        figureList.remove(highlightedFigure);

        listModel.removeAllElements();
        for (Figure figure : figureList)
        {
            listModel.addElement(figure);
        }
        frame.repaint();
        Canvas.updateTop();

        //fix the spinner
        if (Canvas.getTopFigure() == null)
        {
            frame.getScaleSpinner().setValue(1);
        }
        else
        {
            frame.getScaleSpinner().setValue(Canvas.getTopFigure().getScale());
        }
    }
    });

    frame.getMoveBtn().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (Canvas.getTopFigure() == null)
        {
            return;
        }

        //change the actual list
        selectedFigure = highlightedFigure;
        Figure tempFigure = highlightedFigure;
        figureList.remove(highlightedFigure);
        figureList.add(0, tempFigure);

        //redo the displayed list
        listModel.removeAllElements();
        for (Figure figure : figureList)
        {
            listModel.addElement(figure);
        }
        
        canvas.repaint();
        Canvas.updateTop();
        
        //fix the spinner
//        System.out.println(Canvas.getTopFigure());
//        System.out.println(Canvas.getTopFigure().getScale());
        frame.getScaleSpinner().setValue(Canvas.getTopFigure().getScale());
    }
    });

    canvas.addMouseListener(new MouseAdapter()
    {
    @Override
    public void mousePressed(MouseEvent e)
    {

        if (Canvas.getTopFigure() == null)
        {
            return;
        }

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (Canvas.getTopFigure().getPositionShape().contains(mouseX, mouseY))
        {
            selectedFigure = Canvas.getTopFigure();
        }

        lastX = mouseX;
        lastY = mouseY;

    }

    public void mouseReleased(MouseEvent e)
    {
        selectedFigure = null;
    }
    });

    canvas.addMouseMotionListener(new MouseMotionAdapter()
    {
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (selectedFigure == null)
        {
            return;
        }
        int x = e.getX();
        int y = e.getY();

        int incX = x - lastX;
        int incY = y - lastY;

        lastX = x;
        lastY = y;

        selectedFigure.incLocation(incX, incY);
        canvas.repaint();

    }
    });

    frame.getLoadSamples().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        List<Figure> samples = Helpers.getSampleFigureList();
        figureList.clear();

        for (Figure sample : samples)
        {
            figureList.add(sample);
        }

        listModel.removeAllElements();
        for (Figure figure : figureList)
        {
            listModel.addElement(figure);
        }
        canvas.repaint();
    }
    });

    // Invoke the addRect dialog
    frame.getAddRectDialog().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        addRectDialog.setLocationRelativeTo(null);
        addRectDialog.setTitle("Add a RectangleFigure");

        addRectDialog.getHeightField().setText("" + 100);
        addRectDialog.getWidthField().setText("" + 200);
        addRectDialog.getStrokeWidthField().setText("" + 1);
        addRectDialog.getTitleField().setText("");

        addRectDialog.getLineColorField().setEditable(false);
        addRectDialog.getFillColorField().setEditable(false);

        addRectDialog.getLineColorField().setBackground(Color.black);
        addRectDialog.getFillColorField().setBackground(Color.white);

        addRectDialog.setVisible(true);
    }
    });

    // addRectDialog needs the remaining arguments to do its work in events
    Helpers.addEventHandlers(addRectDialog, figureList, listModel, frame, canvas);

    frame.getAddCircleDialog().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        addCircDialog.setLocationRelativeTo(null);
        addCircDialog.setTitle("Add a CircleFigure");

        addCircDialog.getDiamterField().setText("" + 100);
        addCircDialog.getStrokeWidthField().setText("" + 1);
        addCircDialog.getTitleField().setText("");

        addCircDialog.getLineColorField().setEditable(false);
        addCircDialog.getFillColorField().setEditable(false);

        addCircDialog.getLineColorField().setBackground(Color.black);
        addCircDialog.getFillColorField().setBackground(Color.white);

        addCircDialog.setVisible(true);
    }
    });

    Helpers.addEventHandlers(addCircDialog, figureList, listModel, frame, canvas);

    frame.getSave().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (figureList.size() <= 0)
        {
            JOptionPane.showMessageDialog(null, "You can't save an empty list");
            return;
        }

        try
        {
            JFileChooser chooser = Controller.getFileChooser();
            int status = chooser.showSaveDialog(frame);
            if (status != JFileChooser.APPROVE_OPTION)
            {
                return;
            }

            File file0 = chooser.getSelectedFile();
            File file = new File(correctedName("" + file0.toPath()));

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(figureList);

        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "File Error");
        }

    }
    });

    frame.getLoad().addActionListener(new ActionListener()
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {

        JFileChooser chooser = Controller.getFileChooser();
        int status = chooser.showSaveDialog(frame);
        if (status != JFileChooser.APPROVE_OPTION)
        {
            return;
        }

        File file = chooser.getSelectedFile();

        try
        {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);

            List<Figure> fileList = (List<Figure>) oos.readObject();

            for (Figure f : fileList)
            {
                figureList.add(f);
                listModel.addElement(f);
            }

            canvas.repaint();

            if (Canvas.getTopFigure() != null)
            {
                frame.getScaleSpinner().setValue(Canvas.getTopFigure().getScale());
            }

            //Canvas.getTopFigure();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "File Error");
        }
        catch (ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Class Error");
        }
    }
    });
}

public static void main(String[] args)
{
    Controller app = new Controller();
    app.frame.setVisible(true);
}
}
