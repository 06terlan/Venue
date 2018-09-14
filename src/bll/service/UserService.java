package bll.service;

import bll.Address;
import bll.Building;
import bll.NormalCustomer;
import bll.User;

public class UserService {

	 public int addCustomer(User nCustomer)
	 {
		 try {	            
	            int addressId = nCustomer.getAddress().add();
	            int customerID = nCustomer.add();
	            
	            return customerID;
	            //building.add();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return 0; 
	 }
	 public User findByUserNamePassword(String userName, String password)	 
	 {
		 User user = null;
		 try {
			user = User.findByUserNamePassword(userName, password);
			
	     } catch (Exception e) {
	            e.printStackTrace();
	        } 
		 return user;
	 }
	 public User findByUserName(String userName)	 
	 {
		 User user = null;
		 try {
			user = User.findByUserName(userName);
			
	     } catch (Exception e) {
	            e.printStackTrace();
	        } 
		 return user;
	 }
}
