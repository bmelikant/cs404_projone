/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs404_one;

import java.util.ArrayList;

/**
 *
 * @author Dolan
 */

//Class to be called in main.
class FailFinder {

	private ArrayList<LoginAttempt> failedLogins;
	private ArrayList<String> blacklisted_users;
	private ArrayList<String> ip_addrs;
	
	public FailFinder (ArrayList<LoginAttempt> loginList) {
	
		blacklisted_users = new ArrayList<>();
		ip_addrs = new ArrayList<>();
		
		failedLogins = loginList;
	}
	
	public void processFails () {
	
		// get blacklisted users
		for (int i = 0; i < failedLogins.size (); i++) {
		
			Long logtime = failedLogins.get(i).datetime.getTimeInMillis();
			Long addfivemins = logtime + 300000;
			
			// process the current failed login
			String user = failedLogins.get(i).loginid;
                        
                        if (blacklisted_users.contains(user))
                            continue;
                        
			int failcount = 0;
			
			// sub loop: find failed logins for this user
			for (int j = i+1; j < failedLogins.size (); j++) {
			
				// if this is the same user, check the timestamp
				String nextuser = failedLogins.get(j).loginid;
				
				if (nextuser.equals (user)) {
				
					Long comparetime = failedLogins.get(j).datetime.getTimeInMillis();
					
					// failed logins within five minutes should increase by one
					if (comparetime < addfivemins)
						failcount++;
						
					if (failcount >= 2) {
					
						blacklisted_users.add (user);
                                                ip_addrs.add (failedLogins.get(i).ipaddr);
						break;
					}
				}
			}
                }
                
                // print out the data lists
                for (int i = 0; i < blacklisted_users.size(); i++)
                    System.out.println ("Blacklisted: " + blacklisted_users.get(i));
                
                for (int i = 0; i < ip_addrs.size(); i++)
                    System.out.println ("IP Addresses: " + ip_addrs.get(i) + "\n\n");
        }
	
        public int      usernameCount   ()      { return blacklisted_users.size(); }
	public String   getUsernameAt   (int i) { return blacklisted_users.get(i); }
	public String   getUserIPAddrs 	(int i) { return ip_addrs.get(i); }
	
	
}