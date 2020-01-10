/*
 * TCSS 372 Autumn 2019
 * Project 1
 * Minh Nguyen - tmn1014
 */

package view;

import static program.Computer.PROPERTY_EXECUTED;
import static program.Computer.PROPERTY_READ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.text.DefaultCaret;

import program.Computer;

/**
 * The MIPS Simulator GUI.
 * 
 * @author Codie Bryan, Minh Nguyen
 * @version 03 November 2019
 */
public class SimulatorView extends JFrame {
	
	/**
	 * A generated serial version UID for object Serialization.
	 */
	private static final long serialVersionUID = 1090576091747490680L;
	
	/**
	 * The title for the frame.
	 */
	private static final String TITLE = "MIPS Simulator";
	
	/**
     * Padding for the border.
     */
    private static final int PADDING = 5;
    
    /**
     * Amount in Pixels for the Horizontal margin.
     */
    private static final int HORIZONATAL_MARGIN = 10;
    
    /**
     * Amount in Pixels for the Vertical margin.
     */
    private static final int VERTICALL_MARGIN = 10;
	
	/**
	 * The computer to run the simulator.
	 */
	private Computer myComputer;
	
	/**
	 * The Menu bar.
	 */
	private JMenuBar myMenuBar;
	
    /**
     * A file chooser for user to select a race file.
     */
    private JFileChooser myFileChooser;
    
    /**
     * The current directory for the file open dialog.
     */
    private File myDirectory;
    
    /**
     * The center panel of the frame.
     */
    private JPanel myCenterPanel;
    
    /**
     * The text output area.
     */
    private TextArea myTextArea;
    
    /**
     * A Tabbed Pane for data output.
     */
    private JTabbedPane myTabbedPane;
    
    /**
     * The register panel.
     */
    private JPanel myRegisterPanel;
    
    /**
     * The data memory panel.
     */
    private JPanel myMemoryPanel;
    
    /**
     * The scroll pane for data memory panel.
     */
    private JScrollPane myScrollPane;
	
	/**
	 * Instantiates fields
	 * 
	 * @param theComputer the computer to run simulator.
	 */
	public SimulatorView() {
		// Overloading the JFrame's constructor to set the JFrame title.
		super(TITLE);
		myComputer = new Computer();
		instantiateComponents();
	}
	
	/**
     * Start the Graphical User Interface.
     */
    public void start() {   
        // Close the operation when exit frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        // Set up the components and layouts:
        myCenterPanel.setLayout(new BorderLayout());
        // Create empty border for the center panel.
        myCenterPanel.setBorder(BorderFactory.
                                createEmptyBorder(PADDING, VERTICALL_MARGIN,
                                                  HORIZONATAL_MARGIN, VERTICALL_MARGIN));
        
        setUpMenuBar();
        setUpTextArea();
        setUpRegisterPanel();
        setUpMemoryPanel();
        
        addListeners();
        
        // Add panel to the main frame.
        add(myCenterPanel, BorderLayout.CENTER);
        
        pack();
        
        setResizable(false);
        
        // Set Frame to visible.
        setVisible(true);
    }
    
    /**
     * Instantiate components in the GUI.
     */
    private void instantiateComponents() {
    	myMenuBar = new JMenuBar();
    	myDirectory = new File(".");
        myFileChooser = new JFileChooser(myDirectory);
        
        myCenterPanel = new JPanel();        
        myTextArea = new TextArea();
        myTabbedPane = new JTabbedPane();
        
        myRegisterPanel = new JPanel();
    	myRegisterPanel.setLayout(new FlowLayout());
    	myRegisterPanel.setPreferredSize(new Dimension(200, 550));
    	myRegisterPanel.setBorder(BorderFactory.
    							createLineBorder(Color.LIGHT_GRAY, 1));
    	myRegisterPanel.setOpaque(false);
    	
    	myMemoryPanel = new JPanel();
    	myMemoryPanel.setLayout(new GridLayout(0,1));
    	myMemoryPanel.setBorder(BorderFactory.
    							createLineBorder(Color.LIGHT_GRAY, 1));
    	myMemoryPanel.setOpaque(false);
    }
    
    /**
     * Set up the menu bar.
     */
    private void setUpMenuBar() {
        // Create load race item and add action listener to it.
        final JMenuItem loadFile = new JMenuItem("Load File");
        loadFile.addActionListener(theEvent -> handleLoadFile());
        
        // Create exit item and add action listener to it.
        final JMenuItem exitItem = new JMenuItem("Exit");
        // Terminate the program when user select "Exit".
        exitItem.addActionListener(theEvent ->
                                   dispatchEvent(new WindowEvent(this,
                                                                 WindowEvent.
                                                                 WINDOW_CLOSING)));
        // Create execution item
        final JMenuItem executionItem = new JMenuItem("Execute");
        executionItem.addActionListener(theEvent -> handleExecute());
        executionItem.setEnabled(false);
        
        // Create file menu and add item to menu:
        final JMenu fileMenu = new JMenu("Controls");
        fileMenu.add(loadFile);
        fileMenu.addSeparator();
        fileMenu.add(executionItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        myMenuBar.add(fileMenu);
        setJMenuBar(myMenuBar);
    }
    
    /**
     * Handle the event of Menu Item Load Race. Opens a file chooser dialog and let
     * user load file.
     */
    private void handleLoadFile() {
        // Open a dialog for user to select a file.
        final int choice = myFileChooser.showOpenDialog(this);
        
        // If user chooses a file:
        if (choice == JFileChooser.APPROVE_OPTION) {
            try {
                myComputer.readFile(myFileChooser.getSelectedFile()); // try to load file
                myMenuBar.getMenu(0).getMenuComponent(2).setEnabled(true);
            } catch (final IOException e) {
                // If invalid file format, display error message.
                JOptionPane.showMessageDialog(null, "File cannot be read",
                                              "ERROR!" , JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Set up the text area and add it to the center panel.
     */
    private void setUpTextArea() {

        // Add text area to a scroll pane.
        final JScrollPane textAreaScrollPane = 
                        new JScrollPane(myTextArea,
                                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Always update the text area to be the bottom to display most recent message.
        final DefaultCaret caret = (DefaultCaret) myTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Add tab
        myTabbedPane.addTab("Console", textAreaScrollPane);
        
        myCenterPanel.add(myTabbedPane, BorderLayout.SOUTH);
    }
    
    /**
     * Set up the register panel with registers and values.
     */
    private void setUpRegisterPanel() { 
    	
    	JLabel title = new JLabel("REGISTERS:");
    	title.setPreferredSize(new Dimension(150, 20));
    	myRegisterPanel.add(title);
    	
    	// add a label for each register and add to panel
		myComputer.getMyRegisters().entrySet().forEach(entry->{
			JLabel label = new JLabel(entry.getKey() 
									  + " : " +
									  entry.getValue());
			label.setPreferredSize(new Dimension(150, 14));
			myRegisterPanel.add(label);
		});
		
		// add to main panel
		myCenterPanel.add(myRegisterPanel, BorderLayout.EAST);
		validate();
    }
    
    /**
     * Set up the memory data panel.
     */
    private void setUpMemoryPanel() {
    	JLabel title = new JLabel("DATA MEMORY");
    	title.setPreferredSize(new Dimension(150, 20));
    	myMemoryPanel.add(title);
    	
    	// add a label for each memory location and add to panel
    	for(int i = 0; i < myComputer.getMyDataMem().length; i++) {
    		JLabel label = new JLabel("Memory location " + i + " : " 
    								  + myComputer.getMyDataMem()[i]);
    		label.setPreferredSize(new Dimension(150, 15));
    		myMemoryPanel.add(label);
    	}

    	// Add a scroll pane.
        myScrollPane = new JScrollPane(myMemoryPanel,
                                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        myScrollPane.setPreferredSize(new Dimension(20, 550));
    	myScrollPane.setViewportView(myMemoryPanel);
    	
    	// add to main panel
    	myCenterPanel.add(myScrollPane, BorderLayout.CENTER);
    }   
    
    /**
     * Update the register values.
     */
    private void updateRegisterPanel() {
        myRegisterPanel.removeAll();
        setUpRegisterPanel();
    }
    
    /**
     * Update the memory addresses with the new values.
     */
    private void updateMemoryPanel() {
    	myCenterPanel.remove(myScrollPane);
    	myScrollPane.remove(myMemoryPanel);
    	myMemoryPanel.removeAll();
    	setUpMemoryPanel();
    	myCenterPanel.revalidate();
    }
    
    /**
     * Handle the execute option in the menu.
     * Start the execution of the program loaded in.
     */
    private void handleExecute() {
    	myComputer.startExecution();
    	updateRegisterPanel();
    	updateMemoryPanel();
    }
    
    /**
     * Add property change listeners to components.
     */
    private void addListeners() {
    	myComputer.myPropertyChangeSupport.addPropertyChangeListener(PROPERTY_READ, myTextArea);
    	myComputer.myPropertyChangeSupport.addPropertyChangeListener(PROPERTY_EXECUTED, myTextArea);
    }
    
}
