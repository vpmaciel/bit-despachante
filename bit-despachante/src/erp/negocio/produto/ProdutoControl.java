package erp.negocio.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;
import erp.sistema.main.MainJan;

final class ProdutoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((produto == null) || (produto.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ProdutoFac.deletarRegistro(produto);
				getProdutoJanCad().limparGui();
				produto = new Produto();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getProdutoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Produto> listProduto = new LinkedList<>();

			try {
				listProduto = new LinkedList<>(ProdutoFac.pesquisarRegistro(new Produto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoArqCsv ProdutoArqCsv = new ProdutoArqCsv(listProduto);
			ProdutoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Produto> listProduto = new LinkedList<>();

			try {

				ArquivoJson<Produto> arquivoJson = new ArquivoJson<Produto>(produto, "Produto");
				arquivoJson.gravarArquivo(ProdutoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoArqCsv ProdutoArqCsv = new ProdutoArqCsv(listProduto);
			ProdutoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getProdutoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getProdutoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			produto = new Produto();
			getProdutoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainJan.getFrameMain());
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Produto> listProduto = new LinkedList<>();

			if (produto.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (listProduto.add(ProdutoFac.getRegistro(produto))) {
				ProdutoRel ProdutoRel = new ProdutoRel(listProduto);
				ProdutoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			produto = new Produto();
			getProdutoJanCad().limparGui();
			getProdutoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoPainelPesq().pesquisarRegistro(produto);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoJanPesq());
				getProdutoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoPainelPesq().pesquisarRegistro(new Produto());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoJanPesq());
				getProdutoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Produto> listProduto = new LinkedList<>();

			try {
				listProduto = new LinkedList<>(ProdutoFac.pesquisarRegistro(new Produto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoRel ProdutoRel = new ProdutoRel(listProduto);
			ProdutoRel.retornarRelatorio(true);

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

				if (((getProdutoPainelCad().getGuiNome().getText()) == null)
						|| (getProdutoPainelCad().getGuiNome().getText().length() == 0)) {
					getProdutoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ProdutoFac.salvarProduto(produto);
					produto = new Produto();
					getProdutoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getProdutoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_Produto_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getProdutoPainelCad().getGuiNome().requestFocus();
					} else if (mensagem.contains("INDEX_Produto_CODIGO")) {
						Msg.avisoCampoDuplicado("CÓDIGO");
						getProdutoPainelCad().getGuiPreco().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Produto produto;

	public ProdutoControl() {

	}

	public void atualizarGui() {
		if (produto == null) {
			return;
		}
		getProdutoPainelCad().getGuiNome().setText(produto.getNome());
		getProdutoPainelCad().getGuiPreco().setText(String.valueOf(produto.getPreco()));
	}

	public void atualizarObjeto() {
		produto.setPreco(Double.parseDouble(getProdutoPainelCad().getGuiPreco().getText()));
		produto.setNome(getProdutoPainelCad().getGuiNome().getText());
	}

	public Produto getProduto() {
		return produto;
	}

	public ProdutoJanCad getProdutoJanCad() {
		return MainControl.getProdutoJanCad();
	}

	public ProdutoJanPesq getProdutoJanPesq() {
		return MainControl.getProdutoJanPesq();
	}

	public ProdutoPainelCad getProdutoPainelCad() {
		return MainControl.getProdutoJanCad().getProdutoPainelCad();
	}

	public ProdutoPainelPesq getProdutoPainelPesq() {
		return MainControl.getProdutoJanPesq().getProdutoPainelPesq();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}