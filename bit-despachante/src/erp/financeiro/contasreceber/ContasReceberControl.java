package erp.financeiro.contasreceber;

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

final class ContasReceberControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((contasReceber == null) || (contasReceber.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ContasReceberFac.deletarRegistro(contasReceber);
				getContasReceberJanCad().limparGui();
				contasReceber = new ContasReceber();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getContasReceberJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ContasReceber> listContasReceber = new LinkedList<>();

			try {
				listContasReceber = new LinkedList<>(ContasReceberFac.pesquisarRegistro(new ContasReceber()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContasReceberArqCsv ContasReceberArqCsv = new ContasReceberArqCsv(listContasReceber);
			ContasReceberArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ContasReceber> listContasReceber = new LinkedList<>();

			try {

				ArquivoJson<ContasReceber> arquivoJson = new ArquivoJson<ContasReceber>(contasReceber, "ContasReceber");
				arquivoJson.gravarArquivo(ContasReceberFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContasReceberArqCsv ContasReceberArqCsv = new ContasReceberArqCsv(listContasReceber);
			ContasReceberArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getContasReceberJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getContasReceberJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			contasReceber = new ContasReceber();
			getContasReceberPainelCad().getGuiDataVencimento().requestFocus();
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
			List<ContasReceber> contasRecebers = new LinkedList<>();

			if (contasReceber.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (contasRecebers.add(ContasReceberFac.getRegistro(contasReceber))) {
				ContasReceberRel contasReceberRel = new ContasReceberRel(contasRecebers);
				contasReceberRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			contasReceber = new ContasReceber();
			getContasReceberJanCad().limparGui();
			getContasReceberPainelCad().getGuiDataVencimento().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContasReceberPainelPesq().pesquisarRegistro(contasReceber);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContasReceberJanPesq());
				getContasReceberJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContasReceberPainelPesq().pesquisarRegistro(new ContasReceber());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContasReceberJanPesq());
				getContasReceberJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ContasReceber> contasRecebers = new LinkedList<>();

			try {
				contasRecebers = new LinkedList<>(ContasReceberFac.pesquisarRegistro(new ContasReceber()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContasReceberRel contasReceberRel = new ContasReceberRel(contasRecebers);
			contasReceberRel.retornarRelatorio(true);

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
				if (((getContasReceberPainelCad().getGuiDataVencimento().getText()) == null)
						|| (getContasReceberPainelCad().getGuiDataVencimento().getText().length() == 0)) {
					getContasReceberPainelCad().getGuiDataVencimento().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getContasReceberPainelCad().getGuiDataVencimento(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ContasReceberFac.salvarRegistro(contasReceber);
					contasReceber = new ContasReceber();
					getContasReceberJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getContasReceberPainelCad().getGuiDataVencimento().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getContasReceberPainelCad().getGuiDataVencimento().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private ContasReceber contasReceber;

	public ContasReceberControl() {

	}

	public void atualizarGui() {
		if (contasReceber == null) {
			return;
		}
		getContasReceberPainelCad().getGuiDataVencimento().setText(contasReceber.getDataVencimento());
		getContasReceberPainelCad().getGuiDescricao().setText(contasReceber.getDescricao());
		getContasReceberPainelCad().getGuiStatus().setSelectedItem(contasReceber.getStatus());
		getContasReceberPainelCad().getGuiValor().setValue(contasReceber.getValor());
	}

	public void atualizarObjeto() {
		contasReceber.setDataVencimento(getContasReceberPainelCad().getGuiDataVencimento().getText());
		contasReceber.setDescricao(getContasReceberPainelCad().getGuiDescricao().getText());
		contasReceber.setStatus((String) getContasReceberPainelCad().getGuiStatus().getSelectedItem());
		contasReceber.setValor(Double.parseDouble(getContasReceberPainelCad().getGuiValor().getText()));

		if (getContasReceberPainelCad().getGuiDataVencimento().getText().length() == 0) {
			contasReceber.setDescricao(null);
		}
	}

	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public ContasReceberJanCad getContasReceberJanCad() {
		return MainControl.getContasReceberJanCad();
	}

	public ContasReceberJanPesq getContasReceberJanPesq() {
		return MainControl.getContasReceberJanPesq();
	}

	public ContasReceberPainelCad getContasReceberPainelCad() {
		return MainControl.getContasReceberJanCad().getContasReceberPainelCad();
	}

	public ContasReceberPainelPesq getContasReceberPainelPesq() {
		return MainControl.getContasReceberJanPesq().getContasReceberPainelPesq();
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}
}
