package figure;

/**
 *
 * @author Zacheriah Mell
 */
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;

public class CircleFigure extends Figure
{

private final double width;
private final double height;

public CircleFigure(double d)
{
    this(d, d);
}

public CircleFigure(double w, double h)
{
    width = w;
    height = h;
    shape = new Ellipse2D.Double(0, 0, w, h);
}

@Override
public Shape getPositionShape()
{
    return new Ellipse2D.Double(xLoc, yLoc, width * scale, height * scale);
}

@Override
public void draw(Graphics2D g2)
{
    if (stroke == null)
    {
        stroke = new BasicStroke(strokeWidth);
    }
    g2.setStroke(stroke);

    if (fillColor != null)
    {
        g2.setColor(fillColor);  // set color
        g2.fill(shape);          // and fill the shape
    }

    g2.setColor(lineColor); // set color
    g2.draw(shape);         // draw the shape (the outline)
}

}
