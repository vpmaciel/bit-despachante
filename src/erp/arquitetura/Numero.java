package erp.arquitetura;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Numero {

	public static String FloatToString(float numeroFloat) {

		// Crie um objeto DecimalFormat para formatar o número
		DecimalFormat formato = new DecimalFormat("0.00"); // Define o formato desejado

		String numeroString = formato.format(numeroFloat);

		System.out.println("Número formatado: " + numeroString);
		return numeroString;
	}

	public static float stringToFloat(String numeroString) {

		// Crie um formato numérico com a localidade adequada (no caso, a localidade
		// padrão)
		NumberFormat format = NumberFormat.getInstance();

		float numeroFloat = 0.0f;

		try {
			Number numero = format.parse(numeroString);
			numeroFloat = numero.floatValue();
			System.out.println("Número float: " + numeroFloat);
		} catch (ParseException e) {
			System.err.println("Erro de conversão: " + e.getMessage());
		}

		return numeroFloat;
	}

}
