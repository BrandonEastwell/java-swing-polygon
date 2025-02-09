import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// ContainerButtonHandler class for CE203 Assignment to use and modify if needed
// Date: 11/11/2022
// Author: F. Doctor

class ContainerButtonHandler implements ActionListener {
    ContainerFrame theApp;   // Reference to ContainerFrame object

    // ButtonHandler constructor
    ContainerButtonHandler(ContainerFrame app ) {
        theApp = app;
    }

    public static boolean checkHexCode (String a) //checks color for valid hex code
    {
        //regex to check valid hexadecimal color code.
        String regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        //compile the regex
        Pattern p = Pattern.compile(regex);

        //to find match between given string
        Matcher m = p.matcher(a);
        return m.matches();
    }

    // The action performed method would determine what text input or button press events
    // you might have a single event handler instance where the action performed method determines
    // the source of the event, or you might have separate event handler instances.
    // You might have separate event handler classes for managing text input retrieval and button
    // press events.

    public void actionPerformed(ActionEvent e) {
        int pSides = 0, pSidesLength = 0, pID = 0; //initialize textfields values
        String colour = null;
        boolean success = false; //validation condition

        if (e.getSource() == theApp.addButton) { //if action listener source is equal to addButton
            String[] textFields = new String[4];
            textFields = theApp.getText(); //gets values from textfield
            try { //cast string to ints
                pSides = Integer.parseInt(textFields[2]);
                pSidesLength = Integer.parseInt(textFields[3]);
                pID = Integer.parseInt(textFields[0]);
                colour = textFields[1];
                success = true; //no errors
            } catch (Exception a) { //if an error is found
                JOptionPane.showMessageDialog(theApp, "Input values can not use characters \n ie ID: 556342 \n Hex Color: 045300 \n Sides: 5 \n Sides Length: 20", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (success) { //if try condition was successful
                if (pSides > 0) { //validation
                    if (pSidesLength > 0) {
                        if (String.valueOf(pID).length() == 6) {
                            colour = "#" + colour;
                            if (checkHexCode(colour)) {
                                Color pColor = Color.decode(colour);
                                if (theApp.searchPolygon(pID) != null) {
                                    JOptionPane.showMessageDialog(theApp, "Polygon " + pID + " is already in the list \n Polygon Loaded", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(theApp, "Polygon " + pID + " has been added to the list", "ADD POLYGON", JOptionPane.INFORMATION_MESSAGE);
                                    PolygonContainer p = new PolygonContainer(pSides, pSidesLength, pID, pColor); //create new PolygonContainer object once passed all validation
                                    theApp.polygons.add(p); //add PolygonContainer object to ArrayList
                                }
                            } else { //pop up messages if field doesnt meet requirements
                                JOptionPane.showMessageDialog(theApp, "Polygon colour has to be a valid hex code \n ie '000000' with no '#'", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(theApp, "ID value has to be a 6 digit number \n ie '032852'", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(theApp, "Polygon side length has to be a positive number \n ie '20'", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(theApp, "Polygon sides has to be a positive number \n ie '100'", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == theApp.searchButton) { //if action listener source is equal to searchButton
            String[] textFields = new String[4];
            textFields = theApp.getText(); //gets values from textfield
            try { //cast ID to int
                pID = Integer.parseInt(textFields[0]);
                success = true; //no errors
            } catch (Exception a) { //if an error occurs
                JOptionPane.showMessageDialog(theApp, "ID value has to be a 6 digit number", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (success) { //if try statement was successful
                if (String.valueOf(pID).length() == 6) { //validating ID field
                    if (theApp.searchPolygon(pID) == null) { //no matches for ID found in list
                        JOptionPane.showMessageDialog(theApp, "Polygon " + pID + " was not found in the list", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else { //pop up messages if field doesnt meet requirements
                    JOptionPane.showMessageDialog(theApp, "ID value has to be a 6 digit number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == theApp.sortButton) { //if action listener source is equal to sortButton
            if (theApp.polygons.size() > 0) {
                theApp.sortList(); //calls sort method
            } else { //no items found
                JOptionPane.showMessageDialog(theApp, "No items found in the list to display", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        theApp.repaint(); //refreshes ContainerFrame
    }
}

