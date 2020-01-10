/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */
package view;

import static program.Computer.PROPERTY_READ;
import static program.Computer.PROPERTY_EXECUTED;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JTextArea; 

/**
 * Creates a Data Output Text Area.
 * 
 * @author Minh Nguyen
 * @version 13 November 2019
 */
public class TextArea extends JTextArea implements PropertyChangeListener {

    /**
     *  A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -6098549775869809404L;
    
    /**
     * Padding for the border.
     */
    private static final int PADDING = 5;
    
    /**
     * The rows value of the text area.
     */
    private static final int TEXT_AREA_ROWS = 10;
    
    /**
     * The columns value of the text area.
     */
    private static final int TEXT_AREA_COLS = 50;
    
    /**
     * A new line character.
     */
    private static final String NEWLINE = "\n";
    
    /**
     * Construct a Data Output Text Area.
     */
    public TextArea() {
        super(TEXT_AREA_ROWS, TEXT_AREA_COLS);
        
        setUpComponents();
    }
    
    /**
     * Display messages to text area when it hears property changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        final String propertyName = theEvent.getPropertyName();
        
        if (PROPERTY_READ.equals(propertyName)) {
            this.append("The file is finished loading. Ready to be executed.");
            this.append(NEWLINE);
        } else if (PROPERTY_EXECUTED.equals(propertyName)) {
        	this.append(theEvent.getNewValue().toString());
        	this.append(NEWLINE);
        }
    }
    
    /**
     * Set up the text area.
     */
    private void setUpComponents() {
        
        // Cannot edit text area.
        this.setEditable(false);
        
        // Create border for the text area.
        this.setBorder(BorderFactory.
                                   createCompoundBorder(BorderFactory.
                                                        createEtchedBorder(),
                                                        BorderFactory.
                                                        createEmptyBorder(PADDING,
                                                                          PADDING,
                                                                          PADDING,
                                                                          PADDING)));
        this.setCaretPosition(this.getDocument().getLength());
        
    }

}
