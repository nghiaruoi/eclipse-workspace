package contoller;

import static model.PropertyChangeEnabledMutableColor.PROPERTY_BLUE;


import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import model.ColorModel;
import model.PropertyChangeEnabledMutableColor;

/**
 * Represents a Panel with components used to change and display the Blue value for the 
 * backing Color model.
 *
 * @author Charles Bryan
 * @author Your Name
 * @version Autumn 2019
 */
public class BlueRowPanel extends JPanel implements PropertyChangeListener {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 2284116355218892348L;
    
    /** The size of the increase/decrease buttons. */
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
    
    /** The size of the text label. */
    private static final Dimension LABEL_SIZE = new Dimension(45, 26);
    
    /** The number of columns in width of the TextField. */
    private static final int TEXT_FIELD_COLUMNS = 3;
    
    /** The amount of padding for the change panel. */
    private static final int HORIZONTAL_PADDING = 30;
    
    /** The backing model for the system. */
    private final PropertyChangeEnabledMutableColor myColor;

    /** The CheckBox that enables/disables editing of the TextField. */
    private final JCheckBox myEnableEditButton;
    
    /** The TextField that allows the user to type input for the color value. */
    private final JTextField myValueField;
    
    /** The Button that when clicked increases the color value. */
    private final JButton myIncreaseButton;
    
    /** The Button that when clicked decreases the color value. */
    private final JButton myDecreaseButton;
    
    /** The Slider that when adjusted, changes the color value. */
    private final JSlider myValueSlider;
    
    /** The panel that visually displays ONLY the BLUE value for the color. */
    private final JPanel myColorDisplayPanel;
    
    /** The increasing steps for Red value. */
    private final int INCREASE_COLOR = 1;
    
    /** The decreasing steps for Red value. */
    private final int DECREASE_COLOR = -1;
    
    /**
     * Creates a Panel with components used to change and display the Blue value for the 
     * backing Color model. 
     * @param theColor the backing model for the system
     */
    public BlueRowPanel(final PropertyChangeEnabledMutableColor theColor) {
        super();
        myColor = theColor;
        myEnableEditButton = new JCheckBox("Enable edit");
        myValueField = new JTextField();
        myIncreaseButton = new JButton();
        myDecreaseButton = new JButton();
        myValueSlider = new JSlider();
        myColorDisplayPanel = new JPanel();
        layoutComponents();
        addListeners();
    }
    
    /**
     * Setup and add the GUI components for this panel. 
     */
    private void layoutComponents() {
        myColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myColorDisplayPanel.setBackground(new Color(0, 0, myColor.getBlue()));
        final JLabel rowLabel = new JLabel("Blue: ");
        rowLabel.setPreferredSize(LABEL_SIZE);
        myValueField.setText(String.valueOf(myColor.getBlue()));
        myValueField.setEditable(false);
        myValueField.setColumns(TEXT_FIELD_COLUMNS);
        myValueField.setHorizontalAlignment(JTextField.RIGHT);
        
        final JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 
                                                             HORIZONTAL_PADDING, 
                                                             0, 
                                                             HORIZONTAL_PADDING));
        rightPanel.setBackground(rightPanel.getBackground().darker());
        myIncreaseButton.setIcon(new ImageIcon("./images/ic_increase_value.png"));
        myIncreaseButton.setPreferredSize(BUTTON_SIZE);
        myValueSlider.setMaximum(ColorModel.MAX_VALUE);
        myValueSlider.setMinimum(ColorModel.MIN_VALUE);
        myValueSlider.setValue(myColor.getBlue());
        myValueSlider.setBackground(rightPanel.getBackground());
        myDecreaseButton.setIcon(new ImageIcon("./images/ic_decrease_value.png"));
        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
        rightPanel.add(myDecreaseButton);
        rightPanel.add(myValueSlider);
        rightPanel.add(myIncreaseButton);
        
        add(myColorDisplayPanel);
        add(rowLabel);
        add(myValueField);
        add(myEnableEditButton);
        add(rightPanel);
    }
    
    /**
     * Add listeners (event handlers) to any GUI components that require handling.  
     */
    private void addListeners() {
        //DO not remove the following statement.
        myColor.addPropertyChangeListener(PROPERTY_BLUE, this);
        
        myColor.addPropertyChangeListener(theEvent -> {
            if (PROPERTY_BLUE.equals(theEvent.getPropertyName())) {
                System.out.println(theEvent.getNewValue());
                
            }
        });
        
        myIncreaseButton.addActionListener(theEvent -> {
            myColor.adjustBlue(INCREASE_COLOR);
            setIncreaseButton();
        });
        
        myValueSlider.addChangeListener(theEvent -> {
            myColor.setBlue(myValueSlider.getValue());
            setIncreaseButton();
            setDecreaseButton();
        });

        myValueField.addActionListener(theEvent -> {
            try {
                Integer.parseInt(myValueField.getText());
            } catch (final NumberFormatException e) {
                System.out.println("Numbers only");
                myValueField.setText(String.valueOf(myColor.getBlue()));
            }

            try {
                myColor.setBlue(Integer.parseInt(myValueField.getText()));
            } catch (final IllegalArgumentException e) {
                System.out.println("Value is out of range!");
                myValueField.setText(String.valueOf(myColor.getBlue()));
            }

        });

        myDecreaseButton.addActionListener(theEvent -> {
            myColor.adjustBlue(DECREASE_COLOR);
            setDecreaseButton();
        });

        myEnableEditButton.addActionListener(theEvent -> {
            if (myEnableEditButton.isSelected()) {
                myValueField.setEditable(true);
            } else {
                myValueField.setEditable(false);
            }
        });
    }
    
    /**
     * Private helper method, enable or disable myIncreaseButton, dependent on
     * Red value.
     */
    private void setIncreaseButton() {
        if (myColor.getBlue() >= ColorModel.MAX_VALUE) {
            myIncreaseButton.setEnabled(false);
        } else {
            myIncreaseButton.setEnabled(true);
        }
    }

    /**
     * Private helper method, enable or disable myDecreaseButton, dependent on
     * Red value.
     */
    private void setDecreaseButton() {
        if (myColor.getBlue() <= ColorModel.MIN_VALUE) {
            myDecreaseButton.setEnabled(false);
        } else {
            myDecreaseButton.setEnabled(true);
        }
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_BLUE.equals(theEvent.getPropertyName())) {
            myValueField.setText(theEvent.getNewValue().toString());
            myValueSlider.setValue((Integer) theEvent.getNewValue());
            myColorDisplayPanel.
                setBackground(new Color(0, 0, (Integer) theEvent.getNewValue()));
        }
    }
}
