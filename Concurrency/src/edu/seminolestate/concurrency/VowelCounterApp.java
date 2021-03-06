package edu.seminolestate.concurrency;

import javax.swing.*;

/**
 * 
 * @author Moises Henriquez
 * @date June 12, 2016
 * 
 */
public class VowelCounterApp {

	public static void main(String[] args) {
		//Create and show this application's GUI
        SwingUtilities.invokeLater(new Runnable(){
        	
			@Override
			public void run() {
				VowelCounterUI window = new VowelCounterUI("Vowel Counter", true);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
        });
	}
}
