/*	EECS 4670
 * 	Brian Toth
 * 	Lab 3
 *  Prof: Dr. Thomas
 * 	12/8/2024
 */

package Lab3;
import java.io.File;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; //Java insists on error checking (to the point of refusing to compile!) even though it is not strictly necessary for this lab. These are not otherwise utilized in any way.
import java.io.FileNotFoundException; 

public class Lab3 {
	public static void main(String[] args)
	{
		long StartTime = System.currentTimeMillis();
		BigInteger N, N1, N2, N3, C1, C2, C3, c, d, e, password_hash, N64;
		c = new BigInteger("109a38fc0e39f499971009099b4825fe8bf42411bed554785a6be64fce6043598099e6b4e60ee79cc54e1dd143bba50b1511f371f76ad36362084122c3da1cf2a9579bd477e5cb9579851a1ebfb3a6d0afd4b870a585992682df20ab90f076f40a2659d2903560fa8b2564d212d77b58c6a3d00d96a373f510ecf24e663a5502", 16);
		d = new BigInteger("03cc2b0377e0aaeff809cafc44efbb0f97f38db64b2e39e87041206d0f292b84f8c254ef2629bf11edcdba7bb8e9667442d1842aa0009506fa3c3ab4cbf1be29cc8bdfdec72b4a93da09c378238d597e0303ae469b380cf642426237450a0fd0360585441aed49c37833857f5ee33061f63fc379ffee7b8be41cbaea29ae186d", 16);
		N = new BigInteger("9441c25d8e87f09a6aaeb4d7be5d4c67da89c6f8fbe2b40506d786171619ff80cc1ed1aafb3dc849d21b47b28f6dbf9032e6937cf008d1a63fa7733f572ca722cc8060fd8b235fc443b98fe087dda2a6d7578b22fadefead8fbc335a2e45e723bdb64c3df655f2ba1af67957f271cf6d85b76aa49073eb4292571c20d3122f7b", 16);
		e = new BigInteger("10001", 16);
		password_hash = new BigInteger("3ca666b86416985b7679c5d65e46c5b1f75cab92b9eb7837374db1524f81393d", 16);
		N64 = new BigInteger("4d2fc34fba6e3f1d1", 16);
		N1 = new BigInteger("be24c12157b5c34dedd819ebd7a723bee19a8131dfb768cacba433a52ea31614c5935abd834f38f47a8d8dee631cac2cf41974b47f16ad35bfa36d68a36b9f21ae0c3566a72b757ad60595dbf03759c4c0b5d97dad17e1c51967c9c6127799d131c8d87f1a6861047b468660c2cd8419b57919453fe8e37a30df0d5d103592b1", 16);
		N2 = new BigInteger("b128d6b0b42372ac53a0c4d62b909e303ea2b6c6e43d54920eedf157e5d34be98926b8c6dfd6d80c10da2fdf8ca3f52f42d773552a6a2d62f48697ce5811d3a454b013e9a11224548bcae75bd9484429c00d384495691ab4b7e122d550b72f32d8190ece790dc16950cde0d2127920f2519c8bfb2fc919d6bfcc52968c42452b", 16);
		N3 = new BigInteger("d35f012d3429a059228dee4e015aad78c1157a602a66d1702fed0f0965155e060828096fd0a290b98e47624ac1899d20d825676222a8005e3280e1b23db48f46739893a632ff2b0b85763e218fbacadb1f86309db9545190f67885310874453b7730f16f590c2698136c12bcb7878e909027c80e97dd9bb22de13a74eda0d207", 16);
		C1 = new BigInteger("51e832bde36f6edb91795e5261de9ea559b2cfa4845a790748e9c1c3b1b8b71496fd9f04d438302c57af8e2f8c099d514a34d0771afaffc005792bf4a78449f60e276aadbc005b8f23440fb3ece2469f3de087558d9fe537780c046f72aef639492ff2d0d10e65be02f5cd51653f090b1ccc610c5b6bf5181b34cb6366336a2e", 16);
		C2 = new BigInteger("6bc5446f1bb111fbe0bac375171d1ad7bbe58d5fd12bf69e802245faf751bbc6a47c5b5b5e6760cc1f9750313e93de7528407526235b41e38589762153c9b82891ac614e141c3d514d17b5f578c92c235b4e21aabbb8c5cf52c700ec702da039cf0422f0ca50a4bd9ee89c449f622454e83c35ff3ac995bfd3a23add0bcd1a42", 16);
		C3 = new BigInteger("3e7886bcedfd98761b476d5df205961425588bf087a0d42897500d8f4792eb444ac93f266b4e2b4ff63f3cd77136d75af1fbe6477638fa532546a2d2c2d7fe30da00fe7a62269dce65e666f9ad7c59a8dd946f82d71f96d55e5b9690a51c75ce4a6f28b6a451e15ba3a5e317a4ac23aff3a1472be394a4c17cf1fd1be856e0be", 16);
		
		// Task 1
		System.out.println("Decrypting Message: " + decrypt_message(N, e, d, c));	//this outputs in decimal, not hexadecimal (unlike the rest of the lab), but that is how it was provided in the handout.
		// Task 2
		System.out.print("Cracking Hashed Salted Password: ");
		String[] passwords = crack_hashed_password(password_hash, "Top_Passwords.txt");
		if (passwords == null) System.out.println("ERROR: No matching password / salt found");
		else System.out.println("Password = " + passwords[0] + " and salt = " + passwords[1]);
		// Task 3
		System.out.print("Factors of " + N64.toString() + ": ");
		BigInteger[] factors = get_factors(N64);
		if (factors == null) System.out.println("ERROR: Unable to factor " + N64.toString());
		else System.out.println("Factor1: " + factors[0] + " Factor2: " + factors[1]);
		BigInteger PrivateKey = get_private_key_from_p_q_e(factors[0], factors[1], e);
		System.out.println("Private Key: " + PrivateKey.toString() + " (0x" + PrivateKey.toString(16) + ")");
		// Task 4
		System.out.println("Recovered Message: " + recoverMessage(N1, N2, N3, C1, C2, C3));
		System.out.println("\nElapsed time: " + (System.currentTimeMillis() - StartTime) / 1000.0 + " seconds");
	}
	//RSA decryption algorithm. m = c^d mod N.
	public static BigInteger decrypt_message(BigInteger N, BigInteger e, BigInteger d, BigInteger c)	//originally I'd also written encrypt_message (to verify the output of this), but lost it after having to reconstruct the lab.
	{
		return c.modPow(d, N);
	}
	//takes a password hash and filename and returns the password & salt used for the hash.
	public static String[] crack_hashed_password(BigInteger PWHash, String passwordList) 
	{	
		try //Java insists on exception checking here.
		{
			byte[] pwHashArray = PWHash.toByteArray();	//convert PWHash into a byte array
			File passFile = new File(passwordList);
			Scanner passwordScanner = new Scanner(passFile);
			String password;
			String salt;
			String[] targets = new String[2];	//will contain the password and salt returned.
			MessageDigest hashAlgo = MessageDigest.getInstance("SHA-256");	//using SHA-256
			while(passwordScanner.hasNextLine())	//for each of the 1000 passwords...
			{
				password = passwordScanner.nextLine();
				Scanner saltScanner = new Scanner(passFile);
				while(saltScanner.hasNextLine())	//...we pair it with 1000 salts.
				{
					salt = saltScanner.nextLine();
					String concatenated = password + salt;	//concatenating password & current salt
					hashAlgo.update(concatenated.getBytes());
					byte[] hashArray = hashAlgo.digest();
					if(MessageDigest.isEqual(hashArray, pwHashArray))	//comparing byte arrays: PWHash and hashed concatenation
					{
						targets[0] = password;	//if equal, return current password & salt
						targets[1] = salt;
						passwordScanner.close();
						saltScanner.close();
						return targets;
					}
				}
				saltScanner.close();
			}
			passwordScanner.close();
		}
		catch(FileNotFoundException | NoSuchAlgorithmException e)
		{
			System.out.println("Failed to open scanners for file, or SHA-256 hash doesn't exist.");
			e.printStackTrace();
		}
		String[] failed = null;	//returns null upon scanner opening failure, as if no password & salt were found.
		return failed;
	}
	//takes a BigInteger with two factors and returns them.
	public static BigInteger[] get_factors(BigInteger N)
	{
		BigInteger[] factors = new BigInteger[2];
		BigInteger x = N.sqrt();
		while(true)
		{
			if(x.getLowestSetBit() != 0)	//if X is even, add one (such that it'll be odd).
			{
				x = x.add(BigInteger.ONE);
			}
			BigInteger[] div = N.divideAndRemainder(x);	//[0]: N/X; [1]: N % X i.e. the remainder
			if(div[1].compareTo(BigInteger.ZERO) == 0)	//if no remainder:
			{
				factors[0] = x;			// X
				factors[1] = div[0];	// N/X
				return factors;
			}
			x = x.subtract(BigInteger.TWO);	//otherwise, subtract 2 from x and repeat.
		}
	}
	//RSA private key generation algorithm. d = e^-1 mod phi.
	public static BigInteger get_private_key_from_p_q_e(BigInteger p, BigInteger q, BigInteger e)
	{
		return e.modInverse((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)));
	}
	//Chinese Remainder Theorem-based attack. Leans on having multiple ciphertexts, their public keys, and a very low e (e = 3).
	public static String recoverMessage(BigInteger N1, BigInteger N2, BigInteger N3, BigInteger C1, BigInteger C2, BigInteger C3)
	{
		BigInteger e = new BigInteger("3");
		//Chinese Remainder Theorem.
		BigInteger N = N1.multiply(N2).multiply(N3);	// N1 * N2 * N3
		BigInteger m1 = N.divide(N1);
		BigInteger i1 = m1.modInverse(N1);	//inverse for m1.
		BigInteger m2 = N.divide(N2);
		BigInteger i2 = m2.modInverse(N2);
		BigInteger m3 = N.divide(N3);
		BigInteger i3 = m3.modInverse(N3);
		BigInteger combined = C1.multiply(m1).multiply(i1).add(C2.multiply(m2).multiply(i2)).add(C3.multiply(m3).multiply(i3)).mod(N);
		String hexPlaintext = bigIntegerCubeRoot(combined).toString(16);	//cube root the result and convert it into a hexadecimal string.
		return translator(hexPlaintext);	//translate said hex string into characters and return it.
	}
	//takes hexadecimal plaintext and translates it byte-by-byte into readable characters.
	//Only works on even-length inputs, which all hex strings encoding to ASCII should be.
	public static String translator(String hexPlaintext)
	{
		char[] charArray = hexPlaintext.toCharArray();
		String end = "";
		for(int i = 0; i < charArray.length; i = i + 2)	//takes it in two-number increments: all hex values corresponding to a char are double-digited.
		{
			String currSubstring = "" + charArray[i] + charArray[i+1];
			char currChar = (char)Integer.parseInt(currSubstring, 16);
			end = end + currChar;
		}
		return end;
	}
	//no cube root method provided in BigInteger by default.
	public static BigInteger bigIntegerCubeRoot(BigInteger N)
	{
		BigInteger i = BigInteger.ZERO;	//low; starts at 0
		BigInteger j = N; //high; starts at N
		while(i.compareTo(j) < 0)
		{
			BigInteger k = i.add(j).divide(BigInteger.TWO);	//middle value
			BigInteger kCubed = k.pow(3);	//cubed.
			if(kCubed.compareTo(N) < 0)	//if the middle cubed value is less than N...
			{
				i = k.add(BigInteger.ONE); //i = k + 1
			}
			else
			{
				j = k;
			}
		}
		return i;
	}
}