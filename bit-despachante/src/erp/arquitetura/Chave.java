package erp.arquitetura;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Chave {

	private static String getData() {
		Locale locale = Sis.getLocale();
		Calendar calendar = new GregorianCalendar();

		String data = "";
		DateFormat dateFormat = new SimpleDateFormat("MM-yyyy", locale);
		data += dateFormat.format(calendar.getTime());
		return data;
	}

	private String chave;

	public Chave() {
		super();
		String original = getData();
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
		String senha = hexString.toString();
		this.chave = senha;
		System.out.println(this.chave);
	}

	public String getChave() {
		return this.chave;
	}
}
