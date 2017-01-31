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

import java.util.ArrayList;
import javax.swing.*;       // just import everything for swing - never know what we need!!

// GUI component
public class StansGUI extends JFrame {

    // Stub main method (testing)
    public static void main(String[] args) {

        // Stan: This is the way that failed login list should be used from the GUI.
        // create a new isntance of the login list with the logfile name (fetched thru
        // the browse dialog), then call the two methods readLogFile() and processLogs()
        FailedLoginList fll = new FailedLoginList("owncloud.log");
        fll.readLogFile();

 
      //  System.out.println("User # 23 (" + users.get(12).name + ") has " + users.get(12).returnArraySize() + " logins");
        // what do we want to do with the results here?
        // should this function return a list of Strings maybe?
        fll.processLogs ();
        
        FailFinder test = new FailFinder(fll);
        
        //test.showAllNames();
        //test.showRecord(test.showName(2));
        //test.showTimes(test.showName(2));
          
        

    }  // end main
}
