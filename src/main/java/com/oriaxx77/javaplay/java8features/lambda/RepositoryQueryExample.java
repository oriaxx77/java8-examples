package com.oriaxx77.javaplay.java8features.lambda;

import java.util.List;


public class RepositoryQueryExample 
{
	
	static class B
	{
		void m(){}
	}
	
	static class A
	{
		private Object o;
		
		B m()
		{
			return new B()
					{ 
						@Override
						void m(){ o.toString(); } 
					};
		}
	}
	
	static class D
	{
		B m()
		{
			B b = new A().m();
			return b;
		}
	}
	
	static interface Repository<E>
	{
		List<E> find( Predicate<E> p );
	}
	
	static interface Predicate<T>
	{
		boolean accept( T t );
	}
	
	static class IsAdminPredicate implements Predicate<User>
	{

		@Override
		public boolean accept(User t) 
		{
			return false;
		}
		
	}
	
	static class User
	{
		boolean isAdmin(){ return false; }
	}
	
	public static void main(String[] args) 
	{
		Repository<User> repo = null;
		Object o = new Object();
		
		// Non java8. Anonym inner class
		
		repo.find( new Predicate<User>() 
		{
			public boolean accept( User u )
			{
				return u.isAdmin();
			}
		});
		
		// BAD - referencing to o.
		repo.find( new Predicate<User>() 
		{
			public boolean accept( User u )
			{
				return o != null;
			}
		});
		
		// BAD - referencing to o. 
		Predicate<User> isAdmin = new Predicate<User>() 
										{
											public boolean accept( User u )
											{
												return o != null;
											}
										};
										
										
		repo.find( isAdmin );
		
		// Non java8. Implemented in it's own class. We will not reuse this. (Most likely)
		repo.find( new IsAdminPredicate() );
		
		// Java 8 with predicate
		repo.find( user -> user.isAdmin() );
		
		Predicate<User> isAdmin2 = user -> user.isAdmin();
		repo.find( isAdmin2 );
		
		// BAD - referencing to o.
		repo.find( user -> o == null);
	}
}
