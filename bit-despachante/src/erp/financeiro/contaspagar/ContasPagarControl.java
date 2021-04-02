package erp.financeiro.contaspagar;

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

final class ContasPagarControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((contasPagar == null) || (contasPagar.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ContasPagarFac.deletarRegistro(contasPagar);
				getContasPagarJanCad().limparGui();
				contasPagar = new ContasPagar();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getContasPagarJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ContasPagar> listContasPagar = new LinkedList<>();

			try {
				listContasPagar = new LinkedList<>(ContasPagarFac.pesquisarRegistro(new ContasPagar()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContasPagarArqCsv ContasPagarArqCsv = new ContasPagarArqCsv(listContasPagar);
			ContasPagarArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ContasPagar> listContasPagar = new LinkedList<>();

			try {

				ArquivoJson<ContasPagar> arquivoJson = new ArquivoJson<ContasPagar>(contasPagar, "ContasPagar");
				arquivoJson.gravarArquivo(ContasPagarFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContasPagarArqCsv ContasPagarArqCsv = new ContasPagarArqCsv(listContasPagar);
			ContasPagarArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getContasPagarJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getContasPagarJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			contasPagar = new ContasPagar();
			getContasPagarPainelCad().getGuiDataVencimento().requestFocus();
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
			List<ContasPagar> contasPagars = new LinkedList<>();

			if (contasPagar.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (contasPagars.add(ContasPagarFac.getRegistro(contasPagar))) {
				ContasPagarRel contasPagarRel = new ContasPagarRel(contasPagars);
				contasPagarRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			contasPagar = new ContasPagar();
			getContasPagarJanCad().limparGui();
			getContasPagarPainelCad().getGuiDataVencimento().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContasPagarPainelPesq().pesquisarRegistro(contasPagar);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContasPagarJanPesq());
				getContasPagarJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContasPagarPainelPesq().pesquisarRegistro(new ContasPagar());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContasPagarJanPesq());
				getContasPagarJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ContasPagar> contasPagars = new LinkedList<>();

			try {
				contasPagars = new LinkedList<>(ContasPagarFac.pesquisarRegistro(new ContasPagar()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContasPagarRel contasPagarRel = new ContasPagarRel(contasPagars);
			contasPagarRel.retornarRelatorio(true);

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
				if (((getContasPagarPainelCad().getGuiDataVencimento().getText()) == null)
						|| (getContasPagarPainelCad().getGuiDataVencimento().getText().length() == 0)) {
					getContasPagarPainelCad().getGuiDataVencimento().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getContasPagarPainelCad().getGuiDataVencimento(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ContasPagarFac.salvarRegistro(contasPagar);
					contasPagar = new ContasPagar();
					getContasPagarJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getContasPagarPainelCad().getGuiDataVencimento().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getContasPagarPainelCad().getGuiDataVencimento().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private ContasPagar contasPagar;

	public ContasPagarControl() {

	}

	public void atualizarGui() {
		if (contasPagar == null) {
			return;
		}
		getContasPagarPainelCad().getGuiDataVencimento().setText(contasPagar.getDataVencimento());
		getContasPagarPainelCad().getGuiDescricao().setText(contasPagar.getDescricao());
		getContasPagarPainelCad().getGuiStatus().setSelectedItem(contasPagar.getStatus());
		getContasPagarPainelCad().getGuiValor().setValue(contasPagar.getValor());
	}

	public void atualizarObjeto() {
		contasPagar.setDataVencimento(getContasPagarPainelCad().getGuiDataVencimento().getText());
		contasPagar.setDescricao(getContasPagarPainelCad().getGuiDescricao().getText());
		contasPagar.setStatus((String) getContasPagarPainelCad().getGuiStatus().getSelectedItem());
		contasPagar.setValor(Double.parseDouble(getContasPagarPainelCad().getGuiValor().getText()));

		if (getContasPagarPainelCad().getGuiDataVencimento().getText().length() == 0) {
			contasPagar.setDescricao(null);
		}
	}

	public ContasPagar getContasPagar() {
		return contasPagar;
	}

	public ContasPagarJanCad getContasPagarJanCad() {
		return MainControl.getContasPagarJanCad();
	}

	public ContasPagarJanPesq getContasPagarJanPesq() {
		return MainControl.getContasPagarJanPesq();
	}

	public ContasPagarPainelCad getContasPagarPainelCad() {
		return MainControl.getContasPagarJanCad().getContasPagarPainelCad();
	}

	public ContasPagarPainelPesq getContasPagarPainelPesq() {
		return MainControl.getContasPagarJanPesq().getContasPagarPainelPesq();
	}

	public void setContasPagar(ContasPagar contasPagar) {
		this.contasPagar = contasPagar;
	}
}
