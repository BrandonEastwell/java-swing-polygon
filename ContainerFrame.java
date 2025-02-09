import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


// ContainerFrame class for CE203 Assignment to use and modify if needed
// Date: 11/11/2022
// Author: F. Doctor

// A skeleton JFrame class has been provided which you would need to modifiy to include the other GUI components
// data structre and functionality specified in the assignment specification
public class ContainerFrame extends JFrame{


    // Here I have provided an example of two PolygonContainer objects where the number of sides
    // and side length of the polygon are hardcoded.
    // These should be input from the application text fields where the user would type them in.
    // This would create new PolygonContainer objects that would be stored in and accessed from
    // an ArrayList data structure rather than be explicitly defined as below
    JTextField polyID;
    JTextField polyColor;
    JTextField polySides;
    JTextField polySideLength;
    JButton addButton;
    JButton searchButton;
    JButton sortButton;
    ArrayList<PolygonContainer> polygons = new ArrayList<> (); //ArrayList polygons structure
    PolygonContainer drawPolygon; //polygon to be drawn
    public void createComponents() { //creates GUI elements and adds them to the frame

        JPanel drawPanel = new ContainerPanel(this);
        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH); //top menu
        add(drawPanel, BorderLayout.CENTER); //draw panel
        add(botPanel, BorderLayout.SOUTH); //bottom menu
        JLabel IDLabel = new JLabel("Polygon ID: ");
        polyID = new JTextField(8);
        JLabel colorLabel = new JLabel("Hex Colour: ");
        polyColor = new JTextField(8);
        JLabel sidesLabel = new JLabel("Polygon Sides: ");
        polySides = new JTextField(8);
        JLabel sideLengthLabel = new JLabel("Polygon Sides Length: ");
        polySideLength= new JTextField(8);
        topPanel.add(IDLabel);
        topPanel.add(polyID);
        topPanel.add(colorLabel);
        topPanel.add(polyColor);
        topPanel.add(sidesLabel);
        topPanel.add(polySides);
        topPanel.add(sideLengthLabel);
        topPanel.add(polySideLength);

        addButton = new JButton("ADD POLYGON");
        addButton.addActionListener(new ContainerButtonHandler(this));
        botPanel.add(addButton);
        searchButton = new JButton("LOAD POLYGON");
        searchButton.addActionListener(new ContainerButtonHandler(this));
        botPanel.add(searchButton);
        sortButton = new JButton("DISPLAY LIST");
        sortButton.addActionListener(new ContainerButtonHandler(this));
        botPanel.add(sortButton);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// Close action.

    }
    public String[] getText() { //retrieve the values entered in the input fields
        String[] values = new String[4];
        String value1 = polyID.getText();
        String value2 = polyColor.getText();
        String value3 = polySides.getText();
        String value4 = polySideLength.getText();
        values[0] = value1;
        values[1] = value2;
        values[2] = value3;
        values[3] = value4;

        return values;
    }

    public PolygonContainer searchPolygon(int pID) { //searches the list for a matching ID
        for (int i = 0; i < polygons.size(); i++) {
            int oID = polygons.get(i).getID();
            if (pID == oID) { //if a match is found
                drawPolygon = polygons.get(i); //set found polygon to be drawn in the frame
                return polygons.get(i);
            }
        }
        return null;
    }
    public void sortList() { //sorts the list in descending order using ID
        polygons.sort(((o1, o2) -> o1.compareTo(o2))); //automatically sort with compareTo
        System.out.println("\n");
        JFrame f = new JFrame("Sorted Polygon List");
        f.add(new JList(polygons.toArray())); //create new frame to display list
        f.pack();
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        for (int i = 0; i < polygons.size(); i++) {
            System.out.println(polygons.get(i)); //loop through and display list in command console
        }
    }

    public static void main(String[] args) {
        ContainerFrame cFrame = new ContainerFrame();
        cFrame.createComponents();
    }

}
