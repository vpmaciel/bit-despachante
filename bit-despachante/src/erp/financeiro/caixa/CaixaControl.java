package erp.financeiro.caixa;

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
import erp.negocio.produto.Produto;
import erp.negocio.produto.ProdutoFac;
import erp.negocio.servico.Servico;
import erp.negocio.servico.ServicoFac;
import erp.sistema.main.MainControl;

final class CaixaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((caixa == null) || (caixa.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				CaixaFac.deletarRegistro(caixa);
				getCaixaJanCad().limparGui();
				caixa = new Caixa();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getCaixaJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Caixa> listCaixa = new LinkedList<>();

			try {
				listCaixa = new LinkedList<>(CaixaFac.pesquisarRegistro(new Caixa()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CaixaArqCsv CaixaArqCsv = new CaixaArqCsv(listCaixa);
			CaixaArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Caixa> listCaixa = new LinkedList<>();

			try {

				ArquivoJson<Caixa> arquivoJson = new ArquivoJson<Caixa>(caixa, "Caixa");
				arquivoJson.gravarArquivo(CaixaFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			CaixaArqCsv CaixaArqCsv = new CaixaArqCsv(listCaixa);
			CaixaArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getCaixaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getCaixaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			caixa = new Caixa();
			getCaixaPainelCad().getGuiEntrada().requestFocus();
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
			List<Caixa> caixas = new LinkedList<>();

			if (caixa.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (caixas.add(CaixaFac.getRegistro(caixa))) {
				CaixaRel caixaRel = new CaixaRel(caixas);
				caixaRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			caixa = new Caixa();
			getCaixaJanCad().limparGui();
			getCaixaPainelCad().getGuiEntrada().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCaixaPainelPesq().pesquisarRegistro(caixa);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCaixaJanPesq());
				getCaixaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCaixaPainelPesq().pesquisarRegistro(new Caixa());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCaixaJanPesq());
				getCaixaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Caixa> caixas = new LinkedList<>();

			try {
				caixas = new LinkedList<>(CaixaFac.pesquisarRegistro(new Caixa()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CaixaRel caixaRel = new CaixaRel(caixas);
			caixaRel.retornarRelatorio(true);

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
				if (((getCaixaPainelCad().getGuiEntrada().getText()) == null)
						|| (getCaixaPainelCad().getGuiEntrada().getText().length() == 0)) {
					getCaixaPainelCad().getGuiEntrada().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getCaixaPainelCad().getGuiEntrada(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					CaixaFac.salvarRegistro(caixa);
					caixa = new Caixa();
					getCaixaJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getCaixaPainelCad().getGuiEntrada().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getCaixaPainelCad().getGuiEntrada().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Caixa caixa;

	public CaixaControl() {

	}

	public void atualizarGui() {
		if (caixa == null) {
			return;
		}
		getCaixaPainelCad().getGuiEntrada().setText(caixa.getUsuario());
	}

	public void atualizarObjeto() {
		caixa.setUsuario(getCaixaPainelCad().getGuiEntrada().getText());

		if (getCaixaPainelCad().getGuiEntrada().getText().length() == 0) {
			caixa.setUsuario(null);
		}
	}

	public void calcularCaixa() {

		List<Produto> listProdutos = new LinkedList<Produto>();
		listProdutos.addAll(ProdutoFac.getRegistro());
		List<Servico> listServicos = new LinkedList<Servico>();
		listServicos.addAll(ServicoFac.getRegistro());

		for (Produto produto : listProdutos) {
			produto.getPreco();
			if (produto.getPreco() < 0.0) {
				Math.abs(produto.getPreco());
			}

		}

		for (Servico servico : listServicos) {
			servico.getPreco();
			if (servico.getPreco() < 0.0) {
				Math.abs(servico.getPreco());
			}

		}

	}

	public Caixa getCaixa() {
		return caixa;
	}

	public CaixaJanCad getCaixaJanCad() {
		return MainControl.getCaixaJanCad();
	}

	public CaixaJanPesq getCaixaJanPesq() {
		return MainControl.getCaixaJanPesq();
	}

	public CaixaPainelCad getCaixaPainelCad() {
		return MainControl.getCaixaJanCad().getCaixaPainelCad();
	}

	public CaixaPainelPesq getCaixaPainelPesq() {
		return MainControl.getCaixaJanPesq().getCaixaPainelPesq();
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
}
