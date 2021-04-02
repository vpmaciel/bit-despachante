package erp.negocio.produto.categoria;

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

final class ProdutoCategoriaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((produtoCategoria == null) || (produtoCategoria.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ProdutoCategoriaFac.deletarRegistro(produtoCategoria);
				getProdutoCategoriaJanCad().limparGui();
				produtoCategoria = new ProdutoCategoria();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getProdutoCategoriaJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoCategoria> listProdutoCategoria = new LinkedList<>();

			try {
				listProdutoCategoria = new LinkedList<>(ProdutoCategoriaFac.pesquisarRegistro(new ProdutoCategoria()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoCategoriaArqCsv ProdutoCategoriaArqCsv = new ProdutoCategoriaArqCsv(listProdutoCategoria);
			ProdutoCategoriaArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoCategoria> listProdutoCategoria = new LinkedList<>();

			try {

				ArquivoJson<ProdutoCategoria> arquivoJson = new ArquivoJson<ProdutoCategoria>(produtoCategoria,
						"ProdutoCategoria");
				arquivoJson.gravarArquivo(ProdutoCategoriaFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoCategoriaArqCsv ProdutoCategoriaArqCsv = new ProdutoCategoriaArqCsv(listProdutoCategoria);
			ProdutoCategoriaArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getProdutoCategoriaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getProdutoCategoriaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			produtoCategoria = new ProdutoCategoria();
			getProdutoCategoriaPainelCad().getGuiNome().requestFocus();
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
			List<ProdutoCategoria> produtoCategorias = new LinkedList<>();

			if (produtoCategoria.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (produtoCategorias.add(ProdutoCategoriaFac.getRegistro(produtoCategoria))) {
				ProdutoCategoriaRel produtoCategoriaRel = new ProdutoCategoriaRel(produtoCategorias);
				produtoCategoriaRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			produtoCategoria = new ProdutoCategoria();
			getProdutoCategoriaJanCad().limparGui();
			getProdutoCategoriaPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoCategoriaPainelPesq().pesquisarRegistro(produtoCategoria);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoCategoriaJanPesq());
				getProdutoCategoriaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoCategoriaPainelPesq().pesquisarRegistro(new ProdutoCategoria());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoCategoriaJanPesq());
				getProdutoCategoriaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoCategoria> produtoCategorias = new LinkedList<>();

			try {
				produtoCategorias = new LinkedList<>(ProdutoCategoriaFac.pesquisarRegistro(new ProdutoCategoria()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoCategoriaRel produtoCategoriaRel = new ProdutoCategoriaRel(produtoCategorias);
			produtoCategoriaRel.retornarRelatorio(true);

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
				if (((getProdutoCategoriaPainelCad().getGuiNome().getText()) == null)
						|| (getProdutoCategoriaPainelCad().getGuiNome().getText().length() == 0)) {
					getProdutoCategoriaPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getProdutoCategoriaPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ProdutoCategoriaFac.salvarRegistro(produtoCategoria);
					produtoCategoria = new ProdutoCategoria();
					getProdutoCategoriaJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getProdutoCategoriaPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getProdutoCategoriaPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private ProdutoCategoria produtoCategoria;

	public ProdutoCategoriaControl() {

	}

	public void atualizarGui() {
		if (produtoCategoria == null) {
			return;
		}
		getProdutoCategoriaPainelCad().getGuiNome().setText(produtoCategoria.getNome());
	}

	public void atualizarObjeto() {
		produtoCategoria.setNome(getProdutoCategoriaPainelCad().getGuiNome().getText());

		if (getProdutoCategoriaPainelCad().getGuiNome().getText().length() == 0) {
			produtoCategoria.setNome(null);
		}
	}

	public ProdutoCategoria getProdutoCategoria() {
		return produtoCategoria;
	}

	public ProdutoCategoriaJanCad getProdutoCategoriaJanCad() {
		return MainControl.getProdutoCategoriaJanCad();
	}

	public ProdutoCategoriaJanPesq getProdutoCategoriaJanPesq() {
		return MainControl.getProdutoCategoriaJanPesq();
	}

	public ProdutoCategoriaPainelCad getProdutoCategoriaPainelCad() {
		return MainControl.getProdutoCategoriaJanCad().getProdutoCategoriaPainelCad();
	}

	public ProdutoCategoriaPainelPesq getProdutoCategoriaPainelPesq() {
		return MainControl.getProdutoCategoriaJanPesq().getProdutoCategoriaPainelPesq();
	}

	public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
		this.produtoCategoria = produtoCategoria;
	}
}
