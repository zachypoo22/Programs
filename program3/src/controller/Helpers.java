package controller;

import figure.RectangleFigure;
import figure.CircleFigure;
import figure.Figure;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import views.AddRectDialog;
import views.Canvas;
import views.FigureFrame;
import views.AddCircDialog;

class Helpers {

  static List<Figure> getSampleFigureList() {
    List<Figure> figures = new ArrayList<>();
    Figure figure;

    figure = new RectangleFigure(300, 100);
    figure.setLocation(220, 140);
    figure.setLineColor(Color.magenta);
    figure.setFillColor(Color.yellow);
    figure.setStrokeWidth(12f);
    figure.setTitle("aura glow");
    figures.add(figure);

    figure = new RectangleFigure(300, 300);
    figure.setLocation(100, 100);
    figure.setStrokeWidth(3f);
    figure.setTitle("austere");
    figures.add(figure);

    figure = new RectangleFigure(250, 180);
    figure.setLocation(40, 40);
    figure.setLineColor(Color.blue);
    figure.setFillColor(Color.red);
    figure.setStrokeWidth(4.2f);
    figure.setTitle("red square");
    figures.add(figure);

    return figures;
  }

    static void addEventHandlers(
          AddRectDialog addRectDialog,
          List<Figure> figureList,
          DefaultListModel listModel,
          FigureFrame frame,
          Canvas canvas
  ) {

    // the Add Button
    addRectDialog.getAddButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Figure fig = Helpers.makeFigureFromDialog(addRectDialog);
          figureList.add(0, fig);

          listModel.removeAllElements();
          for (Figure figure : figureList) {
            listModel.addElement(figure);
          }
          canvas.repaint();
          addRectDialog.setVisible(false);
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(frame, ex.toString());
        }
      }
    });

    // the Cancel Button
    addRectDialog.getCancelButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addRectDialog.setVisible(false);
      }
    });

    // the ChooseLineColor button
    addRectDialog.getChooseLineColor().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color color
                = JColorChooser.showDialog(frame, "Choose color", Color.white);
        if (color != null) {
          addRectDialog.getLineColorField().setBackground(color);
        }
      }
    });

    // the Choose FillColor button
    addRectDialog.getChooseFillColor().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color color
                = JColorChooser.showDialog(frame, "Choose color", Color.white);
        if (color != null) {
          addRectDialog.getFillColorField().setBackground(color);
        }
      }
    });

  }
  
  static void addEventHandlers(
          AddCircDialog addCircDialog,
          List<Figure> figureList,
          DefaultListModel listModel,
          FigureFrame frame,
          Canvas canvas
  ) {

    // the Add Button
    addCircDialog.getAddButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Figure fig = Helpers.makeFigureFromDialog(addCircDialog);
          figureList.add(0, fig);

          listModel.removeAllElements();
          for (Figure figure : figureList) {
            listModel.addElement(figure);
          }
          canvas.repaint();
          addCircDialog.setVisible(false);
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(frame, ex.toString());
        }
      }
    });

    // the Cancel Button
    addCircDialog.getCancelButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addCircDialog.setVisible(false);
      }
    });

    // the ChooseLineColor button
    addCircDialog.getChooseLineColor().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color color
                = JColorChooser.showDialog(frame, "Choose color", Color.white);
        if (color != null) {
          addCircDialog.getLineColorField().setBackground(color);
        }
      }
    });

    // the Choose FillColor button
    addCircDialog.getChooseFillColor().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color color
                = JColorChooser.showDialog(frame, "Choose color", Color.white);
        if (color != null) {
          addCircDialog.getFillColorField().setBackground(color);
        }
      }
    });

  }

  static Figure makeFigureFromDialog(AddRectDialog dialog) throws Exception {
    String widthText = dialog.getWidthField().getText().trim();
    String heightText = dialog.getHeightField().getText().trim();
    String strokeWidthText
            = dialog.getStrokeWidthField().getText().trim();

    double width = Double.parseDouble(widthText);
    double height = Double.parseDouble(heightText);
    float strokeWidth = Float.parseFloat(strokeWidthText);

    if (width <= 0 || height <= 0 || strokeWidth <= 0) {
      throw new Exception("fields must have positive values");
    }

    String title = dialog.getTitleField().getText().trim();

    Color lineColor = dialog.getLineColorField().getBackground();
    Color fillColor = dialog.getFillColorField().getBackground();

    Figure fig = new RectangleFigure(width, height);
    fig.setStrokeWidth(strokeWidth);
    if (!lineColor.equals(Color.WHITE)) {
      fig.setLineColor(lineColor);
    }
    if (!fillColor.equals(Color.WHITE)) {
      fig.setFillColor(fillColor);
    }
    if (!title.isEmpty()) {
      fig.setTitle(title);
    }

    return fig;
  }
  
    static Figure makeFigureFromDialog(AddCircDialog dialog) throws Exception {
    String DiamterText = dialog.getDiamterField().getText().trim();
    String strokeWidthText
            = dialog.getStrokeWidthField().getText().trim();

    double diamter = Double.parseDouble(DiamterText);
    float strokeWidth = Float.parseFloat(strokeWidthText);

    if (diamter <= 0 || strokeWidth <= 0) {
      throw new Exception("fields must have positive values");
    }

    String title = dialog.getTitleField().getText().trim();

    Color lineColor = dialog.getLineColorField().getBackground();
    Color fillColor = dialog.getFillColorField().getBackground();

    Figure fig = new CircleFigure(diamter);
    fig.setStrokeWidth(strokeWidth);
    if (!lineColor.equals(Color.WHITE)) {
      fig.setLineColor(lineColor);
    }
    if (!fillColor.equals(Color.WHITE)) {
      fig.setFillColor(fillColor);
    }
    if (!title.isEmpty()) {
      fig.setTitle(title);
    }

    return fig;
  }
}
