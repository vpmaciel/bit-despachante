package erp.sistema.pessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.arquitetura.validacao.Entrada;
import erp.arquitetura.validacao.RegExp;
import erp.sistema.main.MainControl;

final class PessoaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((pessoa == null) || (pessoa.getId() == null) || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				PessoaFac.deletarRegistro(pessoa);
				getPessoaJanCad().limparGui();
				pessoa = new Pessoa();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				getPessoaJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Pessoa> listPessoa = new LinkedList<>();

			try {
				listPessoa = new LinkedList<>(PessoaFac.pesquisarRegistro(new Pessoa()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			PessoaArqCsv pessoaArqCsv = new PessoaArqCsv(listPessoa);
			pessoaArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Pessoa> listPessoa = new LinkedList<>();

			try {

				ArquivoJson<Pessoa> arquivoJson = new ArquivoJson<>(pessoa, "usuario");
				arquivoJson.gravarArquivo(PessoaFac.getRegistro());				
			} catch (Exception e) {
				e.printStackTrace();
			}

			PessoaArqCsv pessoaArqCsv = new PessoaArqCsv(listPessoa);
			pessoaArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();
		}
	}



	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getPessoaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getPessoaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			pessoa = new Pessoa();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				MainControl.mostrarFrame(MainControl.getMainJan());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Pessoa> pessoas = new LinkedList<>();

			if (pessoa.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (pessoas.add(PessoaFac.getRegistro(pessoa))) {
				PessoaRel pessoaRel = new PessoaRel(pessoas);
				pessoaRel.retornarRelatorio();
			}
		}
	}

	public class MostraFramePessoa extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getPessoaJan());
			} else {
				MainControl.getPessoaJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			pessoa = new Pessoa();
			getPessoaJanCad().limparGui();
			getPessoaPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getPessoaJan().getPessoaPainelPesq().pesquisarRegistro(pessoa);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getPessoaJan().getTabbedPane().setSelectedIndex(1);
				// getPessoaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getPessoaJan().getPessoaPainelPesq()
					.pesquisarRegistro(new Pessoa());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getPessoaJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Pessoa> pessoas = new LinkedList<>();

			try {
				pessoas = new LinkedList<>(PessoaFac.pesquisarRegistro(new Pessoa()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			PessoaRel pessoaRel = new PessoaRel(pessoas);
			pessoaRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION) || !Entrada.validar(getPessoaPainelCad().getGuiNome(), "NOME", RegExp.NOME, true) || !Entrada.validar(getPessoaPainelCad().getGuiSenha(), "CÓDIGO", RegExp.NUMERO_BANCO, false)) {
					return;
				}

				if (((getPessoaPainelCad().getGuiNome().getText()) == null)
						|| (getPessoaPainelCad().getGuiNome().getText().length() == 0)) {
					getPessoaPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					PessoaFac.salvarRegistro(pessoa);
					pessoa = new Pessoa();
					MainControl.getPessoaJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getPessoaPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_USUARIO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getPessoaPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Pessoa pessoa;

	PessoaControl() {
	}

	public void atualizarGui() {
		if (pessoa == null) {
			return;
		}
		getPessoaPainelCad().getGuiNome().setText(pessoa.getNome());
		getPessoaPainelCad().getGuiSenha().setText(pessoa.getSenha());
	}

	public void atualizarObjeto() {

		pessoa.setSenha(getPessoaPainelCad().getGuiSenha().getText());
		pessoa.setNome(getPessoaPainelCad().getGuiNome().getText());
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public PessoaJanCad getPessoaJanCad() {
		return MainControl.getPessoaJan();
	}

	public PessoaPainelCad getPessoaPainelCad() {
		return MainControl.getPessoaJan().getPessoaPainelCad();
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}