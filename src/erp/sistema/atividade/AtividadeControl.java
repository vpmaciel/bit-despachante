package erp.sistema.atividade;

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

final class AtividadeControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((atividade == null) || (atividade.getId() == null) || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				AtividadeFac.deletarRegistro(atividade);
				getAtividadeJan().limparGui();
				atividade = new Atividade();
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
				getAtividadeJan().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Atividade> listAtividade = new LinkedList<>();

			try {
				listAtividade = new LinkedList<>(AtividadeFac.pesquisarRegistro(new Atividade()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			AtividadeArqCsv atividadeArqCsv = new AtividadeArqCsv(listAtividade);
			atividadeArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Atividade> listAtividade = new LinkedList<>();

			try {

				ArquivoJson<Atividade> arquivoJson = new ArquivoJson<>(atividade, "usuario");
				arquivoJson.gravarArquivo(AtividadeFac.getRegistro());				
			} catch (Exception e) {
				e.printStackTrace();
			}

			AtividadeArqCsv atividadeArqCsv = new AtividadeArqCsv(listAtividade);
			atividadeArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();
		}
	}



	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getAtividadeJan().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getAtividadeJan().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			atividade = new Atividade();
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
			List<Atividade> atividades = new LinkedList<>();

			if (atividade.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (atividades.add(AtividadeFac.getRegistro(atividade))) {
				AtividadeRel atividadeRel = new AtividadeRel(atividades);
				atividadeRel.retornarRelatorio();
			}
		}
	}

	public class MostraFrameAtividade extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getAtividadeJan());
			} else {
				MainControl.getAtividadeJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atividade = new Atividade();
			getAtividadeJan().limparGui();
			getAtividadePainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getAtividadeJan().getAtividadePainelPesq().pesquisarRegistro(atividade);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getAtividadeJan().getTabbedPane().setSelectedIndex(1);;
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getAtividadeJan().getAtividadePainelPesq()
					.pesquisarRegistro(new Atividade());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getAtividadeJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Atividade> atividades = new LinkedList<>();

			try {
				atividades = new LinkedList<>(AtividadeFac.pesquisarRegistro(new Atividade()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			AtividadeRel atividadeRel = new AtividadeRel(atividades);
			atividadeRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION) || !Entrada.validar(getAtividadePainelCad().getGuiNome(), "NOME", RegExp.NOME, true) || !Entrada.validar(getAtividadePainelCad().getGuiSenha(), "CÓDIGO", RegExp.NUMERO_BANCO, false)) {
					return;
				}

				if (((getAtividadePainelCad().getGuiNome().getText()) == null)
						|| (getAtividadePainelCad().getGuiNome().getText().length() == 0)) {
					getAtividadePainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					AtividadeFac.salvarRegistro(atividade);
					atividade = new Atividade();
					MainControl.getAtividadeJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getAtividadePainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_USUARIO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getAtividadePainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Atividade atividade;

	AtividadeControl() {
	}

	public void atualizarGui() {
		if (atividade == null) {
			return;
		}
		getAtividadePainelCad().getGuiNome().setText(atividade.getNome());
		getAtividadePainelCad().getGuiSenha().setText(atividade.getSenha());
	}

	public void atualizarObjeto() {

		atividade.setSenha(getAtividadePainelCad().getGuiSenha().getText());
		atividade.setNome(getAtividadePainelCad().getGuiNome().getText());
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public AtividadeJan getAtividadeJan() {
		return MainControl.getAtividadeJan();
	}

	public AtividadePainelCad getAtividadePainelCad() {
		return MainControl.getAtividadeJan().getAtividadePainelCad();
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
}