package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextArea;
import java.awt.RenderingHints;

import figure.Figure;
import java.awt.geom.AffineTransform;
import java.util.List;

public class Canvas extends JTextArea
{

private static List<Figure> figures = null;
private static Figure topFigure;

public static Figure getTopFigure()
{
    return topFigure;
}

public void setFigures(List<Figure> figures)
{
    this.figures = figures;
}

public static void updateTop()
{
    if (figures.size() > 0)
    {
        //System.out.println(figures.get(0));
        topFigure = figures.get(0);
    }

    if (figures == null)
    {
        //System.out.println("null figure");
        return;
    }
}

@Override
public void paintComponent(Graphics g)
{
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
    );

    AffineTransform t = g2.getTransform(); // save the transform settings

    for (int i = figures.size() - 1; i >= 0; --i)
    {
        Figure figure = figures.get(i);
        double x = figure.getLocation().x;
        double y = figure.getLocation().y;
        double scale = figure.getScale();
        g2.translate(x, y);
        g2.scale(scale, scale);
        figure.draw(g2);
        g2.setTransform(t); // restore each after drawing

    }
    if (figures.size() > 0)
    {
        //System.out.println(figures.get(0));
        topFigure = figures.get(0);
    }

    if (figures == null)
    {
        //System.out.println("null figure");
        return;
    }
}
}
