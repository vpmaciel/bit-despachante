package erp.arquitetura;

import java.text.Normalizer;

public class Formatacao {

    public static String removerAcentos(String str) {
	return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}
