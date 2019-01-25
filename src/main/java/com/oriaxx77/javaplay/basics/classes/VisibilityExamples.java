/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

import com.oriaxx77.javaplay.utility.Print;

/**
 * @author BagyuraI
 *
 */
public class VisibilityExamples 
{
	
	/**
	 * Our 
	 * @author BagyuraI
	 *
	 */
	static class User
	{
		private String loginName;
		
		public User(String loginName) 
		{
			super();
			this.loginName = loginName;
		}

		public void login() { Print.print( "User.login: " + loginName ); }
		
		void generateId() { Print.print( "User.generateId: " + loginName ); }
		
	}
	
	static class SuperUser extends User
	{

		public SuperUser(String loginName) 
		{
			super(loginName);
			// Illegal to reference to a private variable
			// this.loginName;
		}

		@Override
		public void login() { Print.print( "SuperUser.login" ); }

		@Override
		void generateId() { super.generateId(); }
		
	}
	
		
		
}
