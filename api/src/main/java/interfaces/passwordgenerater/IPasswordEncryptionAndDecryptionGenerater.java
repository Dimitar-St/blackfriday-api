package passwordgenerater;

public interface IPasswordEncryptionAndDecryptionGenerater {
	
	String encrypt(String strToEncrypt, String secret);
	
	String decrypt(String strToDecrypt, String secret);
}
