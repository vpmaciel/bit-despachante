package erp.negocio.produto.marca;

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

final class ProdutoMarcaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((produtoMarca == null) || (produtoMarca.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ProdutoMarcaFac.deletarRegistro(produtoMarca);
				getProdutoMarcaJanCad().limparGui();
				produtoMarca = new ProdutoMarca();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getProdutoMarcaJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoMarca> listProdutoMarca = new LinkedList<>();

			try {
				listProdutoMarca = new LinkedList<>(ProdutoMarcaFac.pesquisarRegistro(new ProdutoMarca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoMarcaArqCsv ProdutoMarcaArqCsv = new ProdutoMarcaArqCsv(listProdutoMarca);
			ProdutoMarcaArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoMarca> listProdutoMarca = new LinkedList<>();

			try {

				ArquivoJson<ProdutoMarca> arquivoJson = new ArquivoJson<ProdutoMarca>(produtoMarca, "ProdutoMarca");
				arquivoJson.gravarArquivo(ProdutoMarcaFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoMarcaArqCsv ProdutoMarcaArqCsv = new ProdutoMarcaArqCsv(listProdutoMarca);
			ProdutoMarcaArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getProdutoMarcaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getProdutoMarcaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			produtoMarca = new ProdutoMarca();
			getProdutoMarcaPainelCad().getGuiNome().requestFocus();
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
			List<ProdutoMarca> produtoMarcas = new LinkedList<>();

			if (produtoMarca.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (produtoMarcas.add(ProdutoMarcaFac.getRegistro(produtoMarca))) {
				ProdutoMarcaRel produtoMarcaRel = new ProdutoMarcaRel(produtoMarcas);
				produtoMarcaRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			produtoMarca = new ProdutoMarca();
			getProdutoMarcaJanCad().limparGui();
			getProdutoMarcaPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoMarcaPainelPesq().pesquisarRegistro(produtoMarca);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoMarcaJanPesq());
				getProdutoMarcaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getProdutoMarcaPainelPesq().pesquisarRegistro(new ProdutoMarca());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getProdutoMarcaJanPesq());
				getProdutoMarcaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<ProdutoMarca> produtoMarcas = new LinkedList<>();

			try {
				produtoMarcas = new LinkedList<>(ProdutoMarcaFac.pesquisarRegistro(new ProdutoMarca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ProdutoMarcaRel produtoMarcaRel = new ProdutoMarcaRel(produtoMarcas);
			produtoMarcaRel.retornarRelatorio(true);

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
				if (((getProdutoMarcaPainelCad().getGuiNome().getText()) == null)
						|| (getProdutoMarcaPainelCad().getGuiNome().getText().length() == 0)) {
					getProdutoMarcaPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getProdutoMarcaPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ProdutoMarcaFac.salvarRegistro(produtoMarca);
					produtoMarca = new ProdutoMarca();
					getProdutoMarcaJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getProdutoMarcaPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getProdutoMarcaPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private ProdutoMarca produtoMarca;

	public ProdutoMarcaControl() {

	}

	public void atualizarGui() {
		if (produtoMarca == null) {
			return;
		}
		getProdutoMarcaPainelCad().getGuiNome().setText(produtoMarca.getNome());
	}

	public void atualizarObjeto() {
		produtoMarca.setNome(getProdutoMarcaPainelCad().getGuiNome().getText());

		if (getProdutoMarcaPainelCad().getGuiNome().getText().length() == 0) {
			produtoMarca.setNome(null);
		}
	}

	public ProdutoMarca getProdutoMarca() {
		return produtoMarca;
	}

	public ProdutoMarcaJanCad getProdutoMarcaJanCad() {
		return MainControl.getProdutoMarcaJanCad();
	}

	public ProdutoMarcaJanPesq getProdutoMarcaJanPesq() {
		return MainControl.getProdutoMarcaJanPesq();
	}

	public ProdutoMarcaPainelCad getProdutoMarcaPainelCad() {
		return MainControl.getProdutoMarcaJanCad().getProdutoMarcaPainelCad();
	}

	public ProdutoMarcaPainelPesq getProdutoMarcaPainelPesq() {
		return MainControl.getProdutoMarcaJanPesq().getProdutoMarcaPainelPesq();
	}

	public void setProdutoMarca(ProdutoMarca produtoMarca) {
		this.produtoMarca = produtoMarca;
	}
}
