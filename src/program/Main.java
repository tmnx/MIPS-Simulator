package program;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.SimulatorView;

/**
 * Instantiating and starting the GUI.
 * 
 * @author Minh Nguyen
 * @version 13 November 2019
 */
public final class Main {
    
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private Main() {
        throw new IllegalStateException();
    }
    
    /**
     * The main method to kick off the GUI.
     * 
     * @param theArgs is the command line arguments.
     */
    public static void main(final String[] theArgs) {
        
        // Set the Metal Look and Feel.
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        // Turn off metal's use of bold fonts.
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimulatorView().start();
            }
        });
    }
}

