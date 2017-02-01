/*
 *  StansGUI.java: GUI implementation for Project One.
 *  This file will contain a GUI built using Java's Swing package.
 *  TODO: add file description
 *
 *  Created 1/18/17 for CS404 by Ben Melikant
 *  Contributors: Dolan Nelson, Stan Grata
 *
 *  Last edited: 1/18/17 by Ben Melikant
 */
package cs404_one;          // set the package for this file

import java.awt.Dimension;
import java.awt.GridBagLayout;

import java.awt.*;
import javax.swing.*;       // just import everything for swing - never know what we need!!

// GUI component
public class StansGUI
{
    
    // member variables (GUI objects)
    private JFrame      jf_mainWindow;
    private JMenuBar    jmb_mainMenu;
    private JMenuItem   jmi_goMenuItem;
    
    // initGUI (): Create the GUI
    private void initGUI () {
        
        jf_mainWindow = new JFrame ();
        jf_mainWindow.setLayout(new BorderLayout ());
        
        jf_mainWindow.setTitle ("Log Parser");
        jf_mainWindow.setPreferredSize(new Dimension (400, 300));
        jf_mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize all the window controls
        initMainMenu ();
        initComponents ();
        
        // display the GUI!
        jf_mainWindow.pack();
        jf_mainWindow.setVisible(true);
    }
    
    // initMainMenu (): Initialize the main program menu (top menu)
    // inputs: none, returns: none
    private void initMainMenu () {
        
        // create the menu controls
        jmb_mainMenu = new JMenuBar ();
        
        // create the GO! button
        jmi_goMenuItem = new JMenuItem ("GO!");
        
        // add all the menu items to the main menu
        jmb_mainMenu.add (jmi_goMenuItem);
        
        // add the menu controls to the main window if it exists
        if (jf_mainWindow != null) 
        {            
            jf_mainWindow.setJMenuBar(jmb_mainMenu);
        }
    }
    
    // initComponents (): Set up all the components of the GUI
    // inputs: none, returns: none
    private void initComponents () {
        
        // create a test text area
        JTextArea testArea = new JTextArea (80, 25);
        
        jf_mainWindow.getContentPane().add (testArea, BorderLayout.CENTER);
    }

    // Stub main method (testing)
    public static void main(String[] args) 
    {

        // create a new StansGUI
        StansGUI myGui = new StansGUI ();
        
        // create the GUI
        javax.swing.SwingUtilities.invokeLater (new Runnable () {
            public void run () { myGui.initGUI (); }});
            
    }  // end main
}
