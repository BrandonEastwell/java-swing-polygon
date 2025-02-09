import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;


// ContainerPanel class for CE203 Assignment to use and modify if needed
// Date: 09/11/2022
// Author: F. Doctor

public class ContainerPanel extends JPanel{

    ContainerFrame conFrame;
    PolygonContainer p;
    public ContainerPanel(ContainerFrame cf) {

        conFrame = cf;   // reference to ContainerFrame object
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D comp = (Graphics2D)g;   // You will need to use a Graphics2D objects for this
        Dimension size = getSize();// You will need to use this Dimension object to get
                                        // the width / height of the JPanel in which the
                                           // Polygon is going to be drawn

        // Based on which stored PolygonContainer object you want to be retrieved from the
        // ArrayList and displayed, the object would be accessed and its drawPolygon() method
        // would be called here.

        if (conFrame.drawPolygon != null) {
            conFrame.drawPolygon.drawPolygon(comp, size);
        }
    }
}
