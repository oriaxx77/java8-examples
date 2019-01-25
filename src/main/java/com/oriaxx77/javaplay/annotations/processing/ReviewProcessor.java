/**
 * 
 */
package com.oriaxx77.javaplay.annotations.processing;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * An annotation processor that process our source files
 * annotated by the {@link Review} annotation. It creates a report
 * of the annotated files. Use the runMyProcessor.bat next to that file.
 * @author BagyuraI
 */

@SupportedAnnotationTypes("com.oriaxx77.javaplay.annotations.processing.Review")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ReviewProcessor extends AbstractProcessor
{

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) 
	{

		for (Element annotatedElement : roundEnv.getElementsAnnotatedWith( Review.class)) 
		{
			Review review = annotatedElement.getAnnotation( Review.class );
			StringBuilder msg = new StringBuilder( "Reviewed Element found: " )
												.append( annotatedElement )
												.append( review );
			processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg );
			
    	  	  
		}
		
		return true;
	}

	

}
