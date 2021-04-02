package erp.negocio.produto.unidade;

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

final class ProdutoUnidadeControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((produtoUnidade == null) || (produtoUnidade.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ProdutoUnidadeFac.deletarRegistro(produtoUnidade);
				getProdutoUnidadeJanCad().limparGui();
				produtoUnidade = new ProdutoUnidade();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getProdutoUnidadeJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoUnidade> listProdutoUnidade = new LinkedList<>();

			try {
				listProdutoUnidade = new LinkedList<>(ProdutoUnidadeFac.pesquisarRegistro(new ProdutoUnidade()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoUnidadeArqCsv ProdutoUnidadeArqCsv = new ProdutoUnidadeArqCsv(listProdutoUnidade);
			ProdutoUnidadeArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoUnidade> listProdutoUnidade = new LinkedList<>();

			try {

				ArquivoJson<ProdutoUnidade> arquivoJson = new ArquivoJson<ProdutoUnidade>(produtoUnidade,
						"ProdutoUnidade");
				arquivoJson.gravarArquivo(ProdutoUnidadeFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoUnidadeArqCsv ProdutoUnidadeArqCsv = new ProdutoUnidadeArqCsv(listProdutoUnidade);
			ProdutoUnidadeArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getProdutoUnidadeJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getProdutoUnidadeJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			produtoUnidade = new ProdutoUnidade();
			getProdutoUnidadePainelCad().getGuiNome().requestFocus();
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
			List<ProdutoUnidade> produtoUnidades = new LinkedList<>();

			if (produtoUnidade.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (produtoUnidades.add(ProdutoUnidadeFac.getRegistro(produtoUnidade))) {
				ProdutoUnidadeRel produtoUnidadeRel = new ProdutoUnidadeRel(produtoUnidades);
				produtoUnidadeRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			produtoUnidade = new ProdutoUnidade();
			getProdutoUnidadeJanCad().limparGui();
			getProdutoUnidadePainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoUnidadePainelPesq().pesquisarRegistro(produtoUnidade);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoUnidadeJanPesq());
				getProdutoUnidadeJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoUnidadePainelPesq().pesquisarRegistro(new ProdutoUnidade());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoUnidadeJanPesq());
				getProdutoUnidadeJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoUnidade> produtoUnidades = new LinkedList<>();

			try {
				produtoUnidades = new LinkedList<>(ProdutoUnidadeFac.pesquisarRegistro(new ProdutoUnidade()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoUnidadeRel produtoUnidadeRel = new ProdutoUnidadeRel(produtoUnidades);
			produtoUnidadeRel.retornarRelatorio(true);

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
				if (((getProdutoUnidadePainelCad().getGuiNome().getText()) == null)
						|| (getProdutoUnidadePainelCad().getGuiNome().getText().length() == 0)) {
					getProdutoUnidadePainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getProdutoUnidadePainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ProdutoUnidadeFac.salvarRegistro(produtoUnidade);
					produtoUnidade = new ProdutoUnidade();
					getProdutoUnidadeJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getProdutoUnidadePainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getProdutoUnidadePainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private ProdutoUnidade produtoUnidade;

	public ProdutoUnidadeControl() {

	}

	public void atualizarGui() {
		if (produtoUnidade == null) {
			return;
		}
		getProdutoUnidadePainelCad().getGuiNome().setText(produtoUnidade.getNome());
	}

	public void atualizarObjeto() {
		produtoUnidade.setNome(getProdutoUnidadePainelCad().getGuiNome().getText());

		if (getProdutoUnidadePainelCad().getGuiNome().getText().length() == 0) {
			produtoUnidade.setNome(null);
		}
	}

	public ProdutoUnidade getProdutoUnidade() {
		return produtoUnidade;
	}

	public ProdutoUnidadeJanCad getProdutoUnidadeJanCad() {
		return MainControl.getProdutoUnidadeJanCad();
	}

	public ProdutoUnidadeJanPesq getProdutoUnidadeJanPesq() {
		return MainControl.getProdutoUnidadeJanPesq();
	}

	public ProdutoUnidadePainelCad getProdutoUnidadePainelCad() {
		return MainControl.getProdutoUnidadeJanCad().getProdutoUnidadePainelCad();
	}

	public ProdutoUnidadePainelPesq getProdutoUnidadePainelPesq() {
		return MainControl.getProdutoUnidadeJanPesq().getProdutoUnidadePainelPesq();
	}

	public void setProdutoUnidade(ProdutoUnidade produtoUnidade) {
		this.produtoUnidade = produtoUnidade;
	}
}
