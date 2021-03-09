package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Demonstrating ActionListeners.
 * 
 * @author Charles Bryan
 * @version Winter 2021
 */
public class HelloGoodByeEmpty extends JPanel {
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -1155574959121887493L;
    
    /** A button to say hello. */
    private JButton myHelloButton;
    
    /** A button to say goodbye. */
    private JButton myGoodbyeButton;
    
    /** A button to say Wait, come back. */
    private JButton myWaitButton;
    
    /** A button to say Lambda. */
    private JButton myLambdaButton;

    /** A button to say Method References. */
    private JButton myMethodReferenceButton;

    /** A label to display the message. */
    private JLabel myMessageLabel;

    /**
     * Initializes all of the fields.
     */
    public HelloGoodByeEmpty() {
        super();

        buildComponents();
        layoutComponents();
        addListeners();

    }
    
    /**
     * Instantiate the graphical components (frame, image label, buttons).
     */
    private void buildComponents() {
        myMessageLabel = new JLabel("Message");
        myMessageLabel.setOpaque(true);
        
        myHelloButton = new JButton("Say Hello");
        myGoodbyeButton = new JButton("Say Goodbye");
        myWaitButton = new JButton("Wait...!");
        myLambdaButton = new JButton("Lambda Style");
        myMethodReferenceButton = new JButton("Method Reference");
    }
    
    /**
     * Add Listeners to the components. 
     */
    private void addListeners() {

    }
    
   
    
    /**
     * Lay out the components and makes this frame visible.
     */
    private void layoutComponents() {

        setLayout(new BorderLayout());
        
        final JPanel labelPanel = new JPanel();
        labelPanel.add(myMessageLabel);
        add(labelPanel, BorderLayout.SOUTH);
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(myHelloButton);
        buttonPanel.add(myGoodbyeButton);
        buttonPanel.add(myWaitButton);
        buttonPanel.add(myLambdaButton);
        buttonPanel.add(myMethodReferenceButton);
        add(buttonPanel, BorderLayout.NORTH);
    }

    
    /**
     * Creates a JFrame to demonstrate this panel.
     * It is OK, even typical to include a main method 
     * in the same class file as a GUI for testing purposes. 
     * 
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(() -> createAndShowGui());
        // -OR-
//        EventQueue.invokeLater(HelloGoodBye::createAndShowGui);
    }   
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGui() {
        final HelloGoodBye mainPanel = 
                        new HelloGoodBye();
        
        // A size for the JFrame.
        //final Dimension frameSize = new Dimension(400, 400);
        
        final JFrame window = new JFrame("A Message");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(mainPanel);
        //window.setSize(frameSize);
        window.pack();
        window.setVisible(true);
    }
}