package erp.negocio.servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.arquitetura.validacao.Entrada;
import erp.arquitetura.validacao.RegExp;
import erp.sistema.main.MainControl;

final class ServicoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((servico == null) || (servico.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ServicoFac.deletarRegistro(servico);
				getServicoJanCad().limparGui();
				servico = new Servico();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getServicoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Servico> listServico = new LinkedList<>();

			try {
				listServico = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServicoArqCsv ServicoArqCsv = new ServicoArqCsv(listServico);
			ServicoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Servico> listServico = new LinkedList<>();

			try {

				ArquivoJson<Servico> arquivoJson = new ArquivoJson<Servico>(servico, "Servico");
				arquivoJson.gravarArquivo(ServicoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServicoArqCsv ServicoArqCsv = new ServicoArqCsv(listServico);
			ServicoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getServicoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getServicoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			servico = new Servico();
			getServicoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainControl.getMainJanCad());
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Servico> servicos = new LinkedList<>();

			if (servico.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (servicos.add(ServicoFac.getRegistro(servico))) {
				ServicoRel servicoRel = new ServicoRel(servicos);
				servicoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			servico = new Servico();
			getServicoJanCad().limparGui();
			getServicoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getServicoPainelPesq().pesquisarRegistro(servico);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getServicoJanPesq());
				getServicoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getServicoPainelPesq().pesquisarRegistro(new Servico());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getServicoJanPesq());
				getServicoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Servico> servicos = new LinkedList<>();

			try {
				servicos = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServicoRel servicoRel = new ServicoRel(servicos);
			servicoRel.retornarRelatorio(true);

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				int mensagem = Msg.confirmarSalvarRegistro();
				if (mensagem != JOptionPane.YES_OPTION) {
					return;
				}
				if (((getServicoPainelCad().getGuiNome().getText()) == null)
						|| (getServicoPainelCad().getGuiNome().getText().length() == 0)) {
					getServicoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getServicoPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ServicoFac.salvarRegistro(servico);
					servico = new Servico();
					getServicoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getServicoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_SERVICO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getServicoPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Servico servico;

	public ServicoControl() {

	}

	public void atualizarGui() {
		if (servico == null) {
			return;
		}
		getServicoPainelCad().getGuiNome().setText(servico.getNome());
	}

	public void atualizarObjeto() {
		servico.setNome(getServicoPainelCad().getGuiNome().getText());

		if (getServicoPainelCad().getGuiNome().getText().length() == 0) {
			servico.setNome(null);
		}
	}

	public Servico getServico() {
		return servico;
	}

	public ServicoJanCad getServicoJanCad() {
		return MainControl.getServicoJanCad();
	}

	public ServicoJanPesq getServicoJanPesq() {
		return MainControl.getServicoJanPesq();
	}

	public ServicoPainelCad getServicoPainelCad() {
		return MainControl.getServicoJanCad().getServicoPainelCad();
	}

	public ServicoPainelPesq getServicoPainelPesq() {
		return MainControl.getServicoJanPesq().getServicoPainelPesq();
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
