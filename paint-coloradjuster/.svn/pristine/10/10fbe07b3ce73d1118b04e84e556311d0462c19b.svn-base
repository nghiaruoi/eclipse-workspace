package contoller;

import static model.PropertyChangeEnabledMutableColor.*;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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


public class ValueRowPanel extends JPanel implements PropertyChangeListener {

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
    
    private static final String COLOR_RED = "Red";
    
    private static final String COLOR_GREEN = "Green";
    
    private static final String COLOR_BLUE = "Blue";
    
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
    
    /** The panel that visually displays ONLY the GREEN value for the color. */
    private final JPanel myColorDisplayPanel;
    
    /** The increasing steps for Red value. */
    private final int INCREASE_COLOR = 1;
    
    /** The decreasing steps for Red value. */
    private final int DECREASE_COLOR = -1;
    
    
    private final Map<String, String> myColorProperty = new HashMap<String, String>();
    
    private final Map<String, Supplier<Integer>> myColorGetter = 
                    new HashMap<String, Supplier<Integer>>();
    
    private final Map<String, Consumer<Integer>> myColorSetter = 
                    new HashMap<String, Consumer<Integer>>();
    
    private final Map<String, Consumer<Integer>> myColorAdjuster =
                    new HashMap<String, Consumer<Integer>>();
    
    private final Map<String, Supplier<Color>> myColorColor = 
                    new HashMap<String, Supplier<Color>>();
    
    
    private String myColorName;

    /**
     * Creates a Panel with components used to change and display the Green value for the 
     * backing Color model. 
     * @param theColor the backing model for the system
     * @param theColorName name of the color to display
     */
    public ValueRowPanel(final String theColorName, 
                         final PropertyChangeEnabledMutableColor theColor) {
        super();
        myColorName = theColorName;
        myColor = theColor;
        setUp(myColor);
        myEnableEditButton = new JCheckBox("Enable edit");
        myValueField = new JTextField();
        myIncreaseButton = new JButton();
        myDecreaseButton = new JButton();
        myValueSlider = new JSlider();
        myColorDisplayPanel = new JPanel();
        
        addProperty();
        layoutComponents();
        addListeners();
    }
    
    /**
     * ******************************************************************************************
     * I used functional programming and hashmap to mimic behavior of branching statement.
     * 
     * <p>I came up with this after the very last lesson. I did not have enough time to 
     * 
     * completely document the code
     * @param theColor
     */
    
    private void setUp(final PropertyChangeEnabledMutableColor theColor) {
        
        myColorGetter.put(COLOR_BLUE, () -> myColor.getBlue());
        myColorGetter.put(COLOR_GREEN, () -> myColor.getGreen());
        myColorGetter.put(COLOR_RED, () -> myColor.getRed());
        
        myColorSetter.put(COLOR_BLUE, a -> myColor.setBlue(a));
        myColorSetter.put(COLOR_GREEN, a -> myColor.setGreen(a));
        myColorSetter.put(COLOR_RED, a -> myColor.setRed(a));
        
        myColorAdjuster.put(COLOR_BLUE, a -> myColor.adjustBlue(a));
        myColorAdjuster.put(COLOR_GREEN, a -> myColor.adjustGreen(a));
        myColorAdjuster.put(COLOR_RED, a -> myColor.adjustRed(a));
        
        myColorColor.put(COLOR_BLUE, () -> new Color(0, 0, myColor.getBlue()));
        myColorColor.put(COLOR_GREEN, () -> new Color(0, myColor.getGreen(), 0));
        myColorColor.put(COLOR_RED, () -> new Color(myColor.getRed(), 0, 0));
    }
    
    private void addProperty() {
        myColorProperty.put(COLOR_RED, PROPERTY_RED);
        myColorProperty.put(COLOR_GREEN, PROPERTY_GREEN);
        myColorProperty.put(COLOR_BLUE, PROPERTY_BLUE);
    }
    
    
    /**
     * Setup and add the GUI components for this panel. 
     */
    private void layoutComponents() {
        myColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myColorDisplayPanel.setBackground(myColorColor.get(myColorName).get());
        final JLabel rowLabel = new JLabel(myColorName);
        rowLabel.setPreferredSize(LABEL_SIZE);
        myValueField.setText(String.valueOf(myColorGetter.get(myColorName).get()));
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
        myValueSlider.setValue(myColorGetter.get(myColorName).get());
        myValueSlider.setBackground(rightPanel.getBackground());
        myDecreaseButton.setIcon(new ImageIcon("./images/ic_decrease_value.png"));
        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
        myDecreaseButton.setEnabled(false);
        rightPanel.add(myDecreaseButton);
        rightPanel.add(myValueSlider);
        rightPanel.add(myIncreaseButton);
        
        add(myColorDisplayPanel);
        add(rowLabel);
        add(myValueField);
        add(myEnableEditButton);
        add(rightPanel);

    }
    
    private void addListeners() {
        myColor.addPropertyChangeListener(myColorProperty.get(myColorName), this);

        myColor.addPropertyChangeListener(theEvent -> {
            if (myColorProperty.get(myColorName).equals(theEvent.getPropertyName())) {
                System.out.println(theEvent.getNewValue());
            }
        });
        
        myIncreaseButton.addActionListener(theEvent -> {
            myColorAdjuster.get(myColorName).accept(INCREASE_COLOR);
            setIncreaseButton();
        });
        
        myValueSlider.addChangeListener(theEvent -> {
            myColorSetter.get(myColorName).accept(myValueSlider.getValue());
            setIncreaseButton();
            setDecreaseButton();
        });

        myValueField.addActionListener(theEvent -> {
            
            try {
                Integer.parseInt(myValueField.getText());
            } catch (final NumberFormatException e) {
                System.out.println("Numbers only");
                myValueField.setText(myColorGetter.get(myColorName).get().toString());
                
            }

            try {
                myColorSetter.get(myColorName).
                    accept(Integer.parseInt(myValueField.getText()));
            } catch (final IllegalArgumentException e) {
                System.out.println("Value is out of range!");
                
                myValueField.setText(myColorGetter.get(myColorName).get().toString());
            }

        });

        myDecreaseButton.addActionListener(theEvent -> {
            myColorAdjuster.get(myColorName).accept(DECREASE_COLOR);
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
    
    
    private void setIncreaseButton() {
        if (myColorGetter.get(myColorName).get() >= ColorModel.MAX_VALUE) {
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
        if (myColorGetter.get(myColorName).get() <= ColorModel.MIN_VALUE) {
            myDecreaseButton.setEnabled(false);
        } else {
            myDecreaseButton.setEnabled(true);
        }
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        // TODO Auto-generated method stub
        if (myColorProperty.get(myColorName).equals(theEvent.getPropertyName())) {
            myValueField.setText(theEvent.getNewValue().toString());
            myValueSlider.setValue((Integer) theEvent.getNewValue());
            myColorDisplayPanel.setBackground(myColorColor.get(myColorName).get());
        }
    }
    

}
