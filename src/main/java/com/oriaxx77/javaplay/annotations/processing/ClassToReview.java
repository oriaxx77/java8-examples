package com.oriaxx77.javaplay.annotations.processing;

/**
 * A class to demonstrate the usage of the {@link Reviewed}
 * @author BagyuraI
 */
@Review(
		author=@Author( value=@Name( first="Istvan", last="Bagyura") ),
		reviewer=@Reviewer( value=@Name( first="X", last="Y") ),
		date="2999/10/08"
		)
public class ClassToReview
{
	
}