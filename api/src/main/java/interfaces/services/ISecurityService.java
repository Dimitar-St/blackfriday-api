package services;

public interface ISecurityService {
	
	String encrypt(String strToEncrypt, String secret);
	
	String decrypt(String strToDecrypt, String secret);
}
