package view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_BLUE;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_GREEN;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_RED;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_COLOR;


/**
 * A demo of Observer design pattern implemented with PropertyChange API. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public class ColorPanel extends JPanel implements PropertyChangeListener {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8385732728740430466L;
    
   
    
    
    
    /**
     * Create a color panel with the supplied color.
     * @param theColor the color for the background
     */
    public ColorPanel(final Color theColor) {
        super();
        setBackground(theColor);
        
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_BLUE.equals(theEvent.getPropertyName())) {
            this.setBackground(new Color(this.getBackground().getRed(), 
                                         this.getBackground().getGreen(), 
                                         (Integer) theEvent.getNewValue()));
        } else if (PROPERTY_RED.equals(theEvent.getPropertyName())) {
            
            this.setBackground(new Color((Integer) theEvent.getNewValue(), 
                                         this.getBackground().getGreen(), 
                                         this.getBackground().getBlue()));
        } else if (PROPERTY_GREEN.equals(theEvent.getPropertyName())) {
            
            this.setBackground(new Color(this.getBackground().getRed(),
                                        (Integer) theEvent.getNewValue(), 
                                        this.getBackground().getBlue()));
        }
    }
    
}
