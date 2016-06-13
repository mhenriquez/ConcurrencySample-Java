package edu.seminolestate.concurrency;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

import javax.swing.*;

/**
 * 
 * @author Moises Henriquez
 * @date June 12, 2016
 * 
 */
public class VowelCounterUI extends JFrame implements ActionListener
{
	//JPanels
	private JPanel pnlNorth;
	private JPanel pnlWest;
	private JPanel pnlEast;
	private JPanel pnlSouth;
	
	//J
	private DefaultListModel listModel;
	
	//JLists
	private JList list1;
	private JList list2;
	
	private JTextArea outputArea; //<-------
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
		btnAddFiles.setPreferredSize(new Dimension(100, 40));
		btnAddFiles.addActionListener(this);
		
		//Instantiate Process button
		btnProcess = new JButton("Process");
		btnProcess.setPreferredSize(new Dimension(100, 40));
		btnProcess.addActionListener(this);
		
		//Instantiate Clear button
		btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(100, 40));
		btnClear.addActionListener(this);
		
		//Instantiate Help button
		btnHelp = new JButton("Help");
		btnHelp.setPreferredSize(new Dimension(100, 40));
		btnHelp.addActionListener(this);
		
		//JlistModel
		listModel = new DefaultListModel();
		
		//Instantiate new list and scroll pane
		list1 = new JList();
		scroll1 = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setPreferredSize(new Dimension(360, 160));
		
		//Instantiate new list and scroll pane
		list2 = new JList();
		scroll2 = new JScrollPane(list2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setPreferredSize(new Dimension(360, 160));
		
		//Instantiate and add components to north panel
		pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		pnlNorth.add(lblTitle);
		
		//Instantiate and add components to west panel
		pnlWest = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 3));
		pnlWest.setBorder(BorderFactory.createTitledBorder("Added files"));
		pnlWest.add(scroll1);
		
		//Instantiate and add components to east panel
		pnlEast = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 3));
		pnlEast.setBorder(BorderFactory.createTitledBorder("Vowel counts"));
		pnlEast.add(scroll2);
		
		//Instantiate and add components to south panel
		pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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
			Thread addFiles = new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						analyzePath();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		
		//If process button is clicked...
		if(source == btnProcess){
			
		}
		
		//If clear button is clicked...
		if(source == btnClear){
			list1.removeAll();
			list2.removeAll();
			listModel.clear();
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
	
	/*
	 * 
	 */
	public void analyzePath() throws IOException {
		//Get path to user-selected file or directory
		Path path = getFileOrDirectoryPath();
		
		
		
		
		
		
		
		
		//If path exists, display info
		if(path != null && Files.exists(path)){
			//Gather file or directory information
			StringBuilder builder = new StringBuilder();
			
			builder.append(String.format("%s:%n", path.getFileName()));
			builder.append(String.format("%s a directory%n", Files.isDirectory(path) ? "Is" : "Is not"));
			builder.append(String.format("%s an absolute path%n", path.isAbsolute() ? "Is" : "Is not"));
			builder.append(String.format("Last Modified: %s%n", Files.getLastModifiedTime(path)));
			builder.append(String.format("Size: %s%n", Files.size(path)));
			builder.append(String.format("Path: %s%n", path));
			builder.append(String.format("Absolute path: %s%n", path.toAbsolutePath()));
			
			//Output directory listing
			if(Files.isDirectory(path)){
				//
				builder.append(String.format("%nDirectory contents:%n"));
				//
				//Object for iterating through a directory's contents
				DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
				//
				for(Path p : directoryStream){
					builder.append(String.format("%s%n", path));
				}
			}
			//Display string content
			outputArea.setText(builder.toString());
			
		}
	}
	
	/*
	 * 
	 * @return
	 */
	private Path getFileOrDirectoryPath(){
		//Configure dialog allowing selection of a file or directory
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//fileChooser.setMultiSelectionEnabled(true);
		
		int result = fileChooser.showOpenDialog(this);
		
		//If user clicked cancel button on dialog
		if(result == JFileChooser.CANCEL_OPTION){
			return null;
		}
		
		
		
		
		//Return Path representing the selected file
		return fileChooser.getSelectedFile().toPath();
	}
	
	public int countVowels(Object file){
		
		return 0;
	}
}
