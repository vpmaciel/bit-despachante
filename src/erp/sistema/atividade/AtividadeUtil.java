package erp.sistema.atividade;

import java.security.MessageDigest;

public final class AtividadeUtil {

	public static void criarUsuario() {
		Atividade atividade = new Atividade();
		atividade.setNome("ADMIN");
		atividade.setSenha("123");

		String original = atividade.getSenha();
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
		atividade.setSenha(senha);

		int usuarioRegTotal = AtividadeFac.getRegistro().size();

		if (usuarioRegTotal < 1) {
			try {
				AtividadeFac.salvarRegistro(atividade);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
