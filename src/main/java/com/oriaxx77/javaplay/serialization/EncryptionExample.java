/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;

/**
 * Example for securing the serialization with the JCE api.
 * It uses the {@link SealedObject} to encrypt/decrypt a serializable object.
 * @author BagyuraI
 */
public class EncryptionExample 
{
	/**
	 * Cipher to create a {@link SealedObject}.
	 * The SealedObject will use this to encrypt the provided Object with.
	 */
	private static Cipher encryptor;
	/**
	 * Cipher to decrypt a {@link SealedObject}.
	 * The {@link SealedObject} will use this decriptor to get the original object.
	 */
	private static Cipher decryptor;
	
	
	
	static {
		try
		{
			// Static block to create the encryptor and decryptor.
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
	        keyGen.init(56);
	        Key secretKey = keyGen.generateKey();
			
	        encryptor = Cipher.getInstance( "DES" );
	        encryptor.init( Cipher.ENCRYPT_MODE, secretKey );
			
	        decryptor = Cipher.getInstance( "DES" );
	        decryptor.init( Cipher.DECRYPT_MODE, secretKey);
		}
		catch( Exception ex )
		{
			throw new RuntimeException( ex );
		}
	}
	
	/**
	 * Runs this example.
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException 
	{
		// Oject to serialize
		Double fourtyTwo = new Double( 42 );
		// Secure serialization with an encriptor
		byte[] binaryFortyTwo = serialize( fourtyTwo );
		// Deserialize the encripted object
		Double deserializedFourtyTwo = (Double)deserialize( binaryFortyTwo );
		// Validate the code.
		System.out.println( deserializedFourtyTwo.equals( fourtyTwo ));
	}

	/**
	 * Deserialize the provided data. It assumes the it was encrypted with the {@link #encryptor}.
	 * It uses the {@link #decryptor} to get the original data.
	 * @param binaryObject The binary object. It should be a {@link SealedObject}
	 * @return The deserialized object. 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static Object deserialize(byte[] binaryObject)  throws ClassNotFoundException, IOException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException 
	{
		try ( ObjectInputStream in = new ObjectInputStream( new ByteArrayInputStream( binaryObject )) )
		{
			// NOTE: read the sealed object and get the wrapped object.
			SealedObject sealedObject = (SealedObject)in.readObject();
			return sealedObject.getObject( decryptor );
		}
		
	}

	/**
	 * Serialize an object. First it wraps the provided object with a {@link SealedObject},
	 * then it serialize. The returned data is a serialized {@link SealedObject}
	 * @param object Object to serialize
	 * @return A serialized {@link SealedObject} that wraps the original object.
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 */
	private static byte[] serialize(Serializable object) throws IOException, IllegalBlockSizeException 
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try ( ObjectOutputStream out = new ObjectOutputStream( baos ))
		{
			// Note: wrap the serializable with some encryption. 
			SealedObject sealedObject = new SealedObject(object, encryptor);
			out.writeObject( sealedObject );
			return baos.toByteArray();
		}
		
	}
}
