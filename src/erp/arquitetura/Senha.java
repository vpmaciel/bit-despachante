package erp.arquitetura;

import java.security.MessageDigest;

public class Senha {
    
    public static String criptografar(String senha) {	
	    String original = senha;
	    
	    if(original == null || original.equals("")) {
		original = "";
	    }
	    
	    MessageDigest algorithm = null;
	    byte messageDigest[] = null;
	    try {
		algorithm = MessageDigest.getInstance("SHA-256");
		messageDigest = algorithm.digest(original.getBytes("UTF-8"));
	    } catch (Exception exception) {
		// TODO Auto-generated catch block
		exception.printStackTrace();		
	    }

	    StringBuilder hexString = new StringBuilder();
	    for (byte b : messageDigest) {
		hexString.append(String.format("%02X", 0xFF & b));
	    }
	    return hexString.toString();
    }
}
