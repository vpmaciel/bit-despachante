package erp.arquitetura;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Data {

    public static boolean abrirNavegador() {
	Locale locale = Sis.getLocale();
	Calendar calendar = new GregorianCalendar();

	String data = "";

	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", locale);
	data += dateFormat.format(calendar.getTime());

	int segundo = Integer.parseInt(data.substring(6, 8));
	int minuto = Integer.parseInt(data.substring(3, 5));

	if (minuto % 5 == 0 && segundo < 3) {
	    try {
		Thread.sleep(2000);
	    } catch (InterruptedException interruptedException) {
		interruptedException.printStackTrace();
	    }
	    return true;
	}

	return false;
    }

    public static String getData() {
	Locale locale = Sis.getLocale();
	Calendar calendar = new GregorianCalendar();
	String data = getDia(calendar.get(Calendar.DAY_OF_WEEK));
	DateFormat dateFormat = new SimpleDateFormat("-dd-MM-yyyy", locale);
	data += dateFormat.format(calendar.getTime());
	return data;
    }

    public static String getDataHoraArquivo() {
	Locale locale = Sis.getLocale();
	Calendar calendar = new GregorianCalendar();

	String data = "";
	DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd]-[HH-mm-ss]", locale);
	data += dateFormat.format(calendar.getTime());
	return data;
    }

    public static String getDataHoraCompleta() {
	Locale locale = Sis.getLocale();
	Calendar calendar = new GregorianCalendar();

	String data = "DATA E HORA: ";
	data += getDia(calendar.get(Calendar.DAY_OF_WEEK));
	DateFormat dateFormat = new SimpleDateFormat(" - [ dd-MM-yyyy ] - [ HH:mm:ss.SSSS ]", locale);
	data += dateFormat.format(calendar.getTime());
	return data;
    }

    public static String getDataHoraSimples() {
	Locale locale = Sis.getLocale();
	Calendar calendar = new GregorianCalendar();
	String data = getDia(calendar.get(Calendar.DAY_OF_WEEK));
	DateFormat dateFormat = new SimpleDateFormat(" - [ dd-MM-yyyy ] - [ HH:mm:ss ]", locale);
	data += dateFormat.format(calendar.getTime());
	return data;
    }

    public static String getDia(int dia) {
	String nome = "";

	switch (dia) {

	case Calendar.SUNDAY:
	    nome = "- [ DOMINGO ]";
	    break;
	case Calendar.MONDAY:
	    nome = "- [ SEGUNDA-FEIRA ]";
	    break;
	case Calendar.TUESDAY:
	    nome = "- [ TERÇA-FEIRA ]";
	    break;
	case Calendar.WEDNESDAY:
	    nome = "- [ QUARTA-FEIRA ]";
	    break;
	case Calendar.THURSDAY:
	    nome = "- [ QUINTA-FEIRA ]";
	    break;
	case Calendar.FRIDAY:
	    nome = "- [ SEXTA-FEIRA ]";
	    break;
	case Calendar.SATURDAY:
	    nome = "- [ SÁBADO ]";
	    break;
	}
	return nome;
    }

    public static String getHora() {
	Locale locale = Sis.getLocale();
	Calendar calendar = new GregorianCalendar();

	String data = "";
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSSS", locale);
	data += dateFormat.format(calendar.getTime());
	return data;
    }

    public static Date retornaData(String dataString) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date data = null;
	try {
	    data = dateFormat.parse(dataString);
	    System.out.println("Data convertida: " + data);
	} catch (java.text.ParseException e) {
	    e.printStackTrace();
	}

	return data;
    }

    public String getDataFormatada(Date data) {
	if (data == null) {
	    return null;
	}

	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");

	return sdf.format(data);
    }

    private Data() {

    }

}
