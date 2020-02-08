package com.blackfriday.api.services;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Singleton;

import services.ISecurityService;

@Singleton
public class SecurityService implements ISecurityService {
	private SecretKeySpec secretKey;
	private byte[] key;
	
	public SecurityService() {
		
	}
	
	public void setKey(String myKey) {
		MessageDigest sha = null;
		 try {
			 key = myKey.getBytes("UTF-8");
			 sha = MessageDigest.getInstance("SHA-1");
			 key = sha.digest(key);
			 key = Arrays.copyOf(key, 16); 
			 secretKey = new SecretKeySpec(key, "AES");
		 } catch(NoSuchAlgorithmException e) {
			 e.printStackTrace();
		 } catch(UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
	}
	
	
	public String encrypt(String strToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public String decrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
}
