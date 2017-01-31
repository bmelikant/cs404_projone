/*
 *  FailedLoginList.java: utility class to process log files
 *  Class FailedLoginList will process a given log file for failed logins
 *  and return a list of users that should be disabled due to multiple
 *  login failures. Also contained in this file is the class LoginAttempt
 *  which is a container class for a single failed login attempt from the
 *  log file.
 *
 *  Created 1/18/17 for CS404 by Benjamin Melikant
 *  Contributors: Dolan Nelson, Stan Grata
 *
 *  Last edited 1/18/17 by Ben Melikant
 */

package cs404_one;

// utility imports for calendars and storage
import java.util.ArrayList;             // to keep our lists
import java.util.GregorianCalendar;     // datetimes stored in a calendar

// formatting classes
import java.text.SimpleDateFormat;
import java.text.ParseException;

// java file I/O
import java.io.FileInputStream;         // to create an input stream
import java.io.InputStreamReader;       // to create an input stream reader
import java.io.BufferedReader;          // for BufferedReader.readLine ()
import java.io.IOException;             // for catch (IOException)

// imports for native file I/O (read file creation date)
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

// container for each failed login attempt
class LoginAttempt 
{
	public GregorianCalendar datetime = new GregorianCalendar();
	public String loginid = "";
	public String ipaddr = "";
        
        public LoginAttempt(){
            
        }
        
        
}

// list of failed logins. Contains logic for processing the list of data
public class FailedLoginList 
{

	public ArrayList<LoginAttempt> failedLogins;
	private ArrayList<String> blacklisted;
	private String logFileName;
        // private int calendarYear;
	
	public FailedLoginList (String lfname) 
        { 
        
            // create the lists and set the log file name
            failedLogins = new ArrayList<>();
            blacklisted  = new ArrayList<>();
            logFileName = lfname;
        }

        // readLogFile (): read from the log file specified in logFileName
        // inputs: none, returns: none
	public void readLogFile () 
        {
    
            // create the BufferedReader for reading each line from the file
            try 
            {
                // log dates are always in the below format
                SimpleDateFormat logfmt = new SimpleDateFormat ("MMM dd HH:mm:ss");
                
                // attempt to read the creation date of the log file (for the year component)
                BasicFileAttributes attr = Files.readAttributes(Paths.get (logFileName), BasicFileAttributes.class);
                System.out.println ("The log file was created " + attr.creationTime());
                
                // create a reader for the log file
                BufferedReader logFileReader = new BufferedReader (new InputStreamReader (new FileInputStream (logFileName)));
                String nextLine;
                
                // read each line in the file
                while ((nextLine = logFileReader.readLine()) != null) 
                {

                    // if the current line contains a HIPAA login failure, parse it out
                    if (nextLine.contains ("LOGIN: Failed")) 
                    {
                        
                        // TODO: remove debug print to System.out
                        System.out.println ("Found login failure: " + nextLine);
                        
                        LoginAttempt la = new LoginAttempt ();

                        // parse out the date from the current line
                        la.datetime = new GregorianCalendar ();
                        la.datetime.setTime (logfmt.parse(nextLine));
                            
                        // TODO: Fetch specific error code (most likely Bad Password) here
                        
                        // next we want to grab the username associated with the error
                        la.loginid = nextLine.substring(nextLine.indexOf ('(')+1, nextLine.indexOf(')'));
                                                

                        // and last, the attemped login IP address
                        la.ipaddr = nextLine.substring (nextLine.indexOf('[', nextLine.indexOf("LOGIN:"))+1, nextLine.indexOf(']', nextLine.indexOf("LOGIN:")));
                        
                        // everything should have succeeded - add the item to the list
                        failedLogins.add (la);
                        
                        // informational print
                        System.out.println ("Login attempt failed for username " + la.loginid);
                        System.out.println ("Login attempt date / time: " + la.datetime.getTime().toString ());
                        System.out.println ("Login attempt origin: " + la.ipaddr);
                    }
                }                
            }
            
            catch (IOException e) 
            {
                
                System.err.println ("I/O Error processing file " + logFileName);
                System.err.println ("Exception details: " + e.toString());
                
            } 
            
            catch (ParseException e) 
            {
                
                System.err.println ("Parse error reading date from log " + logFileName);
                System.err.println ("Exception details: " + e.toString ());
            }
        }
        public ArrayList copyFailed(){
            return failedLogins;
        }
        
	public void processLogs() { }
}