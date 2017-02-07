/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs404_one;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Dolan
 */
//Class to be called in main.
public class FailFinder 
{
    private ArrayList<LoginAttempt> myFailedLogins;
    /////////////////////NEW//////////////////////
    private ArrayList<String> myBlackList;
    /////////////////////NEW//////////////////////
    public ArrayList<String> uniqueNames;
    public FailFinder(FailedLoginList f) 
    {
        myFailedLogins = f.failedLogins;
        uniqueNames = new ArrayList();
        //////////////////NEW////////////////////////
        myBlackList = new ArrayList();
        /////////////////NEW////////////////////////
        loadUniqueNames();
    }
//Finds all of the unique names in the log.

    public void loadUniqueNames() 
    {
        uniqueNames.add(myFailedLogins.get(0).loginid);
        for (int i = 1; i < myFailedLogins.size(); i++) 
        {
            if (!uniqueNames.contains(myFailedLogins.get(i).loginid)) 
            {
                uniqueNames.add(myFailedLogins.get(i).loginid);
            }
        }
    } // end get unique names
    
//Shows all of the names with failed logins.
    public String showName(int n) 
    {
        return uniqueNames.get(n);
    }
    
//Shows user name and time of the failed login.
    public void showRecord(String loggedNames) 
    {
        for (int i = 0; i < myFailedLogins.size(); i++) 
        {
            if (myFailedLogins.get(i).loginid.equals(loggedNames)) 
            {
                System.out.println("Name: " + myFailedLogins.get(i).loginid +
                        ",  " + myFailedLogins.get(i).datetime.getTime());
            }
        }
    }
    
//Displays datetime of first entry (unique name)  converted to milliseconds.
//Then displays the elapsed time (in milliseconds) for each of the following entries.    
    public void showTimes(String loggedNames) 
    {
        Long tempNum = 0L;

        for (int i = 0; i < myFailedLogins.size(); i++) 
        {
            if (myFailedLogins.get(i).loginid.equals(loggedNames)) 
              
            {

                if (tempNum == 0) 
                {
                    tempNum = myFailedLogins.get(i).datetime.getTimeInMillis();
                    System.out.println("setting first number: " + tempNum); 
                    /////////////////////////NEW///////////////////////////////////
                    if ((tempNum +2)-tempNum > 300000)
                    {
                    myBlackList.add(myFailedLogins.get(i).loginid);
                    //System.out.println(myBlackList + "Blacklisted from the start!!!");
                    }                                        
                    ////////////////////////////NEW////////////////////////////////
                } else
                    
                {
                    long tempResult = (myFailedLogins.get(i).datetime.getTimeInMillis() - tempNum);
                    System.out.println("The real number in milliseconds from the first entry is " + (tempResult));
                    ////////////////////////////NEW////////////////////////////////
                    if (( tempResult +2)- tempResult > 300000);
                    {
                    myBlackList.add(myFailedLogins.get(i).loginid);
                    System.out.println(myBlackList + "Has been blacklisted.");                                        
                    }                                        
                    ///////////////////////////NEW/////////////////////////////////
                }
            }
        }        
    }
    
//Simply shows all of the names (each instance) in the failed login list.
    public void showAllNames() 
    {
        for (int i = 0; i < uniqueNames.size(); i++) 
        {
            showRecord(showName(i));
        }
    }
} // end
