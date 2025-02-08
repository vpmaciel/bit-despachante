package erp.arquitetura.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import erp.sistema.main.MainControl;
import erp.sistema.main.MainJan;
import erp.sistema.main.SobrePainel;

public final class Msg {

	private static Object[] botoesSimNao = new Object[] { "Sim", "Nao" };
	
	public static void avisoFecharSistema() {
		JOptionPane.showMessageDialog(MainControl.getLoginJan().getLoginPainelCad(), "O sistema será fechado !", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void avisoCampoInvalido(Object campo) {
		JOptionPane.showMessageDialog(null, campo.toString().toUpperCase() + " inválido !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void avisoCampoDuplicado() {
		JOptionPane.showMessageDialog(null, "Já está cadastrado em outro registro !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void avisoCampoDiferente(JLabel label1, JLabel label2) {
		JOptionPane.showMessageDialog(null, label1.getText() + " e " + label2.getText() + " são diferentes !",
				"Informação", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void ajuda() {
		JOptionPane.showMessageDialog(MainJan.getFrameMain(), new SobrePainel(), "Sobre o Sistema",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void avisoCampoDuplicado(String campo) {
		JOptionPane.showMessageDialog(null, campo + " : " + "\nJá está cadastrado em outro registro !", "Informação",
				JOptionPane.WARNING_MESSAGE);
	}

	public static void avisoCampoDuplicado(String campo, String valor) {
		JOptionPane.showMessageDialog(null, campo + " : " + valor + "\nJá está cadastrado em outro registro !",
				"Informação", JOptionPane.WARNING_MESSAGE);
	}

	public static void avisoCampoObrigatorio(Object campo) {
		JOptionPane.showMessageDialog(null, "Voce precisa preencher o campo " + campo.toString().toUpperCase(),
				"Informação", JOptionPane.WARNING_MESSAGE);
	}


	public static void avisoImprimiRegistroNaoCadastrado() {
		JOptionPane.showMessageDialog(null, "Registro não cadastrado no sistema !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void avisoRegistroEncontrado(Long totalPesquisaRegistro) {
		if (totalPesquisaRegistro > 0) {
			JOptionPane.showMessageDialog(null, "Registros encontrados: " + totalPesquisaRegistro, "Informação",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Registro não encontrado !", "Informação", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void avisoUsuarioInvalido() {
		JOptionPane.showMessageDialog(null, "Usuário inválido !", "Informação", JOptionPane.ERROR_MESSAGE);
	}

	public static void avisoUsuarioNaoExcluiRegistro() {
		JOptionPane.showMessageDialog(null, "Voce não pode excluir este usuário !", "Informação",
				JOptionPane.WARNING_MESSAGE);
	}

	public static void avisoUsuarioNaoLogado() {
		JOptionPane.showMessageDialog(null, "Usuário não está Logado !", "Informação", JOptionPane.WARNING_MESSAGE);
	}

	public static int confirmarExcluiRegistro() {
		return JOptionPane.showOptionDialog(null, "Excluir o registro ?", "Informação", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, botoesSimNao, botoesSimNao[JOptionPane.INFORMATION_MESSAGE]);
	}

	public static int confirmarFecharCaixa() {
		return JOptionPane.showOptionDialog(null, "Fechar o caixa ?", "Informação", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, botoesSimNao, botoesSimNao[JOptionPane.INFORMATION_MESSAGE]);
	}

	public static int confirmarFecharJanela() {
		return JOptionPane.showOptionDialog(null, "Deseja fechar a janela ?", "Informação",
				JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, botoesSimNao,
				botoesSimNao[JOptionPane.INFORMATION_MESSAGE]);
	}

	public static int confirmarSairDoSistema() {
		return JOptionPane.showOptionDialog(null, "Sair do Sistema ?", "Informação", JOptionPane.WARNING_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, botoesSimNao, botoesSimNao[JOptionPane.YES_NO_OPTION]);
	}

	public static int confirmarSalvarRegistro() {
		return JOptionPane.showOptionDialog(null, "Salvar o registro ?", "Informação", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, botoesSimNao, botoesSimNao[JOptionPane.INFORMATION_MESSAGE]);
	}

	public static void erroAbrirArquivo() {
		JOptionPane.showMessageDialog(null, "Não foi possível abrir arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroArquivo() {
		JOptionPane.showMessageDialog(null, "Erro de arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroArquivoNaoEncontrado() {
		JOptionPane.showMessageDialog(null, "Não foi possível encontrar arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCampoInvalido() {
		JOptionPane.showMessageDialog(null, "Digitou tecla não permitida para este campo !", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCodificacao() {
		JOptionPane.showMessageDialog(null, "Não foi possível codificar !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroConectarDataBase() {
		JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados !", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void erroConsultarRegistro() {
		JOptionPane.showMessageDialog(null, "Não foi possível realizar a Consulta", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCriarArquivo() {
		JOptionPane.showMessageDialog(null, "Não foi possível criar arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCriarPasta() {
		JOptionPane.showMessageDialog(null, "Não foi possível criar pasta !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroDesconectarDataBase() {
		JOptionPane.showMessageDialog(null, "Não foi possível desconectar ao banco de dados !", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void erroExcluiRegistro() {
		JOptionPane.showMessageDialog(null, "Não foi possível excluir o Registro", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroGeral() {
		JOptionPane.showMessageDialog(null, "Foi encontrado um erro !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroLookAndFeel() {
		JOptionPane.showMessageDialog(null, "Não foi possível utilizar Look and Feel !", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void erroPesquisarRegistro() {
		JOptionPane.showMessageDialog(null, "Não foi possível pesquisar registro !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroSalvarRegistro() {
		JOptionPane.showMessageDialog(null, "Não foi possível inserir o registro !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroTeclaNaoPermitida() {
		JOptionPane.showMessageDialog(null, "Voce digitou uma tecla não permitida para este campo !", "Informação",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void erroValorInvalido() {
		JOptionPane.showMessageDialog(null, "Valor inválido !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void sucessoAtualizarRegistro() {
		JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoConectarDataBase() {
		JOptionPane.showMessageDialog(null, "Conexão efetuada com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoConsultarRegistro() {
		JOptionPane.showMessageDialog(null, "Consulta realizada com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoDesconectarDataBase() {
		JOptionPane.showMessageDialog(null, "Desconexão efetuada com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoExcluiRegistro() {
		JOptionPane.showMessageDialog(null, "Registro excluído com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoInserirRegistro() {
		JOptionPane.showMessageDialog(null, "Registro inserido com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoSalvarRegistro() {
		JOptionPane.showMessageDialog(null, "Registro salvo com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void sucessoFecharCaixa() {
		JOptionPane.showMessageDialog(null, "Caixa fechado com sucesso !", "Informação",
				JOptionPane.INFORMATION_MESSAGE);

	}
}
