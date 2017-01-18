/*
 *	CS404 Project One - Skeleton (Dolan Nelson, Stanley Grata, Ben Melikant)
 *      File created 1/17/17
 */

package cs404_one;          // set the package for this file

import javax.swing.*;       // just import everything for swing - never know what we need!!
 
import java.util.ArrayList;         // to keep our lists
import java.util.Calendar;          // datetimes stored in a calendar
import java.util.Date;              // to instantiate our calendar objects

import java.io.FileInputStream;             // to create an input stream
import java.io.InputStreamReader;       // to create an input stream reader
import java.io.BufferedReader;          // for BufferedReader.readLine ()
import java.io.IOException;             // for catch (IOException)

// container for each failed login attempt
class LoginAttempt {

	public Calendar datetime;
	public String loginid;
	public String ipaddr;
}

// list of failed logins. Contains logic for processing the list of data
class FailedLoginList {

	private ArrayList<LoginAttempt> failedLogins;
	private ArrayList<String> blacklisted;
	private String logFileName;
	
	public FailedLoginList (String lfname) { 
        
            // create the lists and set the log file name
            failedLogins = new ArrayList<>();
            blacklisted  = new ArrayList<>();
            logFileName = lfname;
        }

        // readLogFile (): read from the log file specified in logFileName
        // inputs: none, returns: none
	public void readLogFile () {
            
            // create the BufferedReader for reading each line from the file
            try {
                
                // create a reader for the log file
                BufferedReader logFileReader = new BufferedReader (new InputStreamReader (new FileInputStream (logFileName)));
                String nextLine;
                
                // read each line in the file
                while ((nextLine = logFileReader.readLine()) != null) {
                    
                    // if the current line contains a HIPAA login failure, parse it out
                    if (nextLine.contains ("LOGIN: Failed")) {
                        
                        // TODO: remove debug print to System.out
                        System.out.println ("Found login fail: " + nextLine);
                        
                        // 
                    }
                }
                
            } catch (IOException e) {
                
                System.err.println ("I/O Error processing file " + logFileName);
                System.err.println ("Exception details: " + e.toString());
            }
        }
        
	public void processLogs () { }
}

// GUI component
public class StansGUI extends JFrame {
    
    // Stub main method (testing)
    public static void main (String [] args) {
        
        FailedLoginList fll = new FailedLoginList ("owncloud.log");
        fll.readLogFile ();
        fll.processLogs ();
    }
}