package edu.seminolestate.concurrency;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

public class VowelCounterUI extends JFrame implements ActionListener
{
	//JPanels
	private JPanel pnlNorth;
	private JPanel pnlWest;
	private JPanel pnlEast;
	private JPanel pnlSouth;
	
	//JFileChooser
	private JFileChooser files;
	
	//JLists
	private JList list1;
	private JList list2;
	
	//ScroolPanes
    private JScrollPane scroll1;
    private JScrollPane scroll2;
	
	//JLabel and Font
	private JLabel lblTitle;
	private Font fntTitle;
	
	//JButtons
	private JButton btnAddFiles;
	private JButton btnProcess;
	private JButton btnClear;
	private JButton btnHelp;
	
	//Constant
		private static final String HELP_MESSAGE = "Click the 'Add Files' button to select a file. The selected file will be displayed on\n"
												 + "the left. Repeat this for each file you want to search. Next click the 'Process' button\n"
												 + "The number of vowels in the file will be displayed on the right of each file.";
	
	//Constructor
	public VowelCounterUI(String title, boolean visible){
		super(title);
		
		//Set size and lock it
		setSize(779, 320);
		setResizable(false);
		
		//Center window to screen
		setLocationRelativeTo(null);
		
		//Initialize UI
		initUserInterface();
		
		//Render JFrame
		setVisible(visible); //<== Must be applied after UI is initialized to render components
	}
	
	//Methods
	
	private void initUserInterface(){
		
		//Instantiate new border layout for JFrame
		setLayout(new BorderLayout());
		
		//Instantiate new label and font
		fntTitle = new Font("Serif", Font.BOLD, 30);
		lblTitle = new JLabel("Count Vowels in Big Files!");
		lblTitle.setFont(fntTitle);
		
		//Instantiate Add Files button
		btnAddFiles = new JButton("Add Files");
		btnAddFiles.setPreferredSize(new Dimension(100,40));
		btnAddFiles.addActionListener(this);
		
		//Instantiate Process button
		btnProcess = new JButton("Process");
		btnProcess.setPreferredSize(new Dimension(100,40));
		btnProcess.addActionListener(this);
		
		//Instantiate Clear button
		btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(100,40));
		btnClear.addActionListener(this);
		
		//Instantiate Help button
		btnHelp = new JButton("Help");
		btnHelp.setPreferredSize(new Dimension(100,40));
		btnHelp.addActionListener(this);
		
		//Instantiate new list and scroll pane
		list1 = new JList();
		scroll1 = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.add(list1);
		
		//Instantiate new list and scroll pane
		list2 = new JList();
		scroll2 = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.add(list2);
		
		//Instantiate and add components to north panel
		pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		pnlNorth.add(lblTitle);
		
		//Instantiate and add components to west panel
		pnlWest = new JPanel(new FlowLayout(FlowLayout.LEFT, 185, 20));
		pnlWest.setBorder(BorderFactory.createTitledBorder("Files"));
		pnlWest.add(scroll1);
		
		//Instantiate and add components to east panel
		pnlEast = new JPanel(new FlowLayout(FlowLayout.LEFT, 185, 20));
		pnlEast.setBorder(BorderFactory.createTitledBorder("Vowels"));
		//TODO add elements
		
		//Instantiate and add components to south panel
		pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		pnlSouth.add(btnAddFiles);
		pnlSouth.add(btnProcess);
		pnlSouth.add(btnClear);
		pnlSouth.add(btnHelp);
		
		
		//Add JPanel elements to JFrame
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlEast, BorderLayout.EAST);
		add(pnlWest, BorderLayout.WEST);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		//If add files button is clicked...
		if(source == btnAddFiles){
			
		}
		
		//If process button is clicked...
		if(source == btnProcess){
			
		}
		
		//If clear button is clicked...
		if(source == btnClear){
			
		}
		
		//If help button is clicked...
		if(source == btnHelp){
			Thread help = new Thread(new Runnable(){
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, HELP_MESSAGE, "Help", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			//Start new thread
			help.start();
		}
	}
	
	public void openFile(){
		
//		try
//		{
//			
//		}
//		catch (FileNotFoundException ex)
//		{
//			ex.printStackTrace();
//		}
	}
	
	public int countVowels(Object file){
		
		return 0;
	}
}
