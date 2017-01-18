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

import javax.swing.*;       // just import everything for swing - never know what we need!!

// GUI component
public class StansGUI extends JFrame 
{
    
    // Stub main method (testing)
    public static void main (String [] args) 
    {    
        FailedLoginList fll = new FailedLoginList ("owncloud.log");
        fll.readLogFile ();
        fll.processLogs ();
    }
}