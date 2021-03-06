package erp.negocio.centrocusto;

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

final class CentroCustoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((centroCusto == null) || (centroCusto.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				CentroCustoFac.deletarRegistro(centroCusto);
				getCentroCustoJanCad().limparGui();
				centroCusto = new CentroCusto();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getCentroCustoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<CentroCusto> listCentroCusto = new LinkedList<>();

			try {
				listCentroCusto = new LinkedList<>(CentroCustoFac.pesquisarRegistro(new CentroCusto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CentroCustoArqCsv CentroCustoArqCsv = new CentroCustoArqCsv(listCentroCusto);
			CentroCustoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<CentroCusto> listCentroCusto = new LinkedList<>();

			try {

				ArquivoJson<CentroCusto> arquivoJson = new ArquivoJson<CentroCusto>(centroCusto, "CentroCusto");
				arquivoJson.gravarArquivo(CentroCustoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			CentroCustoArqCsv CentroCustoArqCsv = new CentroCustoArqCsv(listCentroCusto);
			CentroCustoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getCentroCustoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getCentroCustoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			centroCusto = new CentroCusto();
			getCentroCustoPainelCad().getGuiNome().requestFocus();
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
			List<CentroCusto> centroCustos = new LinkedList<>();

			if (centroCusto.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (centroCustos.add(CentroCustoFac.getRegistro(centroCusto))) {
				CentroCustoRel centroCustoRel = new CentroCustoRel(centroCustos);
				centroCustoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			centroCusto = new CentroCusto();
			getCentroCustoJanCad().limparGui();
			getCentroCustoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCentroCustoPainelPesq().pesquisarRegistro(centroCusto);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCentroCustoJanPesq());
				getCentroCustoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCentroCustoPainelPesq().pesquisarRegistro(new CentroCusto());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCentroCustoJanPesq());
				getCentroCustoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<CentroCusto> centroCustos = new LinkedList<>();

			try {
				centroCustos = new LinkedList<>(CentroCustoFac.pesquisarRegistro(new CentroCusto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CentroCustoRel centroCustoRel = new CentroCustoRel(centroCustos);
			centroCustoRel.retornarRelatorio(true);

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
				if (((getCentroCustoPainelCad().getGuiNome().getText()) == null)
						|| (getCentroCustoPainelCad().getGuiNome().getText().length() == 0)) {
					getCentroCustoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getCentroCustoPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					CentroCustoFac.salvarRegistro(centroCusto);
					centroCusto = new CentroCusto();
					getCentroCustoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getCentroCustoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getCentroCustoPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private CentroCusto centroCusto;

	public CentroCustoControl() {

	}

	public void atualizarGui() {
		if (centroCusto == null) {
			return;
		}
		getCentroCustoPainelCad().getGuiNome().setText(centroCusto.getNome());
	}

	public void atualizarObjeto() {
		centroCusto.setNome(getCentroCustoPainelCad().getGuiNome().getText());

		if (getCentroCustoPainelCad().getGuiNome().getText().length() == 0) {
			centroCusto.setNome(null);
		}
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public CentroCustoJanCad getCentroCustoJanCad() {
		return MainControl.getCentroCustoJanCad();
	}

	public CentroCustoJanPesq getCentroCustoJanPesq() {
		return MainControl.getCentroCustoJanPesq();
	}

	public CentroCustoPainelCad getCentroCustoPainelCad() {
		return MainControl.getCentroCustoJanCad().getCentroCustoPainelCad();
	}

	public CentroCustoPainelPesq getCentroCustoPainelPesq() {
		return MainControl.getCentroCustoJanPesq().getCentroCustoPainelPesq();
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
}
