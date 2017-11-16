package controller;

import figure.Figure;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import views.AddRectDialog;
import views.Canvas;
import views.FigureFrame;

public class Controller {

  private final FigureFrame frame = new FigureFrame();
  private final Canvas canvas = frame.getCanvas();

  // figures which appear in canvas and in list
  private final List<Figure> figureList = new ArrayList<>();

  // model for Figure selection list
  private final DefaultListModel listModel = new DefaultListModel();

  // dialogs
  private AddRectDialog addRectDialog = new AddRectDialog(frame, true);

  public Controller() {
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

    

    frame.getLoadSamples().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        List<Figure> samples = Helpers.getSampleFigureList();
        figureList.clear();

        for (Figure sample : samples) {
          figureList.add(sample);
        }

        listModel.removeAllElements();
        for (Figure figure : figureList) {
          listModel.addElement(figure);
        }
        canvas.repaint();
      }
    });

    // Invoke the addRect dialog
    frame.getAddRectDialog().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
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

  }

  public static void main(String[] args) {
    Controller app = new Controller();
    app.frame.setVisible(true);
  }
}
