package controller;

import static model.PropertyChangeEnabledTimeControls.PROPERTY_TIME;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.ObservableTime;
import model.PropertyChangeEnabledTimeControls;
import view.TimePanel;

/**
 *  A controller for ObservableTime.
 * 
 *  @author Charles Bryan
 *  @version Winter 2021 
 */
public class TimeController extends JPanel implements PropertyChangeListener {
    
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8452917670991316606L;
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
 
    /** Amount in Pixels for the Horizontal margin. */
    private static final int HORIZONATAL_MARGIN = 75; 
    
    /** Amount in Pixels for the Vertical margin. */
    private static final int VERTICALL_MARGIN = 75; 
    
    /** The amount of milliseconds in a second. */
    private static final int ONE_SECOND_IN_MS = 1000;
    
    /** The size of the increase/decrease buttons. */
    private static final Dimension BUTTON_SIZE = new Dimension(40, 40);
    
    /** Start text for the start/stop button. */
    private static final String BUTTON_ICON_DECREASE = "./images/ic_decrease_value.png"; 
    
    /** Stop text for the start/stop button. */
    private static final String BUTTON_ICON_INCREASE = "./images/ic_increase_value.png"; 
    
    /** The Color object this class controls. */
    private final PropertyChangeEnabledTimeControls myTime;
    
    /** The Button that when clicked increases the time. */
    private final JButton myIncreaseButton;
    
    /** The Button that when clicked decreases the time. */
    private final JButton myDecreaseButton;
    
    /**
     * Constructs a ColorSlider.
     * 
     * @param theTime the color object this class controls
     */
    public TimeController(final PropertyChangeEnabledTimeControls theTime) {
        super(new BorderLayout());
        
        myIncreaseButton = new JButton();
        myDecreaseButton = new JButton();
        myTime = theTime;
        setUpComponents();
        addListeners();
    }
    
    /**
     * Lay out the components.
     */
    private void setUpComponents() {
        final JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(VERTICALL_MARGIN, 
                                                          HORIZONATAL_MARGIN, 
                                                          VERTICALL_MARGIN, 
                                                          HORIZONATAL_MARGIN));
        
        myDecreaseButton.setIcon(new ImageIcon(BUTTON_ICON_DECREASE));
        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
        myDecreaseButton.setEnabled(false);
        
        myIncreaseButton.setIcon(new ImageIcon(BUTTON_ICON_INCREASE));
        myIncreaseButton.setPreferredSize(BUTTON_SIZE);
        
        content.add(myDecreaseButton);
        content.add(myIncreaseButton);
        
        add(content, BorderLayout.CENTER);
    }
    
    /**
     * Add actionListeners to the buttons. 
     */
    private void addListeners() {
        myTime.addPropertyChangeListener(PROPERTY_TIME, this);
        
        myTime.addPropertyChangeListener(theEvent -> {
            if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
                System.out.println(theEvent.getNewValue());
            }
        });
        
        myIncreaseButton.addActionListener(theEvent -> myTime.advance(ONE_SECOND_IN_MS));
        
        myDecreaseButton.addActionListener(theEvent -> myTime.adjustTime(-ONE_SECOND_IN_MS));
    }
    

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_TIME.equals(theEvent.getPropertyName())) {
            final int time = (Integer) theEvent.getNewValue();
            myDecreaseButton.setEnabled(time >= 1);
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("Lab 4b (Time)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final ObservableTime time = new ObservableTime();
        
        //Create and set up the content pane.
        final TimeController pane = new TimeController(time);
        pane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(pane);
        frame.pack();
        frame.setLocation(SCREEN_SIZE.width / 2 - frame.getWidth() / 2,
                    SCREEN_SIZE.height / 2 - frame.getHeight() / 2);
            
        //Create a time panel to listen to and demonstrate our 
        //ObservableTime.
        final TimePanel timePanel = new TimePanel();
        time.addPropertyChangeListener(model.PropertyChangeEnabledTimeControls.PROPERTY_TIME,
                                       timePanel);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JFrame timeViewFrame = new JFrame("The clock ticks...");
                timeViewFrame.setContentPane(timePanel);
                timeViewFrame.pack();
                timeViewFrame.setLocation(frame.getLocation().x + frame.getWidth(), 
                                       frame.getLocation().y);
                timeViewFrame.setVisible(true);
            }
        });
        
        //Display the window.
        frame.setVisible(true);
    }

    
}