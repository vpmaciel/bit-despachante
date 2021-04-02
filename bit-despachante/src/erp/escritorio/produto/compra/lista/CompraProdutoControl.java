package erp.escritorio.produto.compra.lista;

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
import erp.negocio.produto.marca.ProdutoMarca;
import erp.negocio.produto.unidade.ProdutoUnidade;
import erp.sistema.main.MainControl;

final class CompraProdutoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((compraProduto == null) || (compraProduto.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				CompraProdutoFac.deletarRegistro(compraProduto);
				getCompraProdutoJanCad().limparGui();
				compraProduto = new CompraProduto();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getCompraProdutoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<CompraProduto> listCompraProduto = new LinkedList<>();

			try {
				listCompraProduto = new LinkedList<>(CompraProdutoFac.pesquisarRegistro(new CompraProduto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CompraProdutoArqCsv CompraProdutoArqCsv = new CompraProdutoArqCsv(listCompraProduto);
			CompraProdutoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<CompraProduto> listCompraProduto = new LinkedList<>();

			try {

				ArquivoJson<CompraProduto> arquivoJson = new ArquivoJson<CompraProduto>(compraProduto, "CompraProduto");
				arquivoJson.gravarArquivo(CompraProdutoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			CompraProdutoArqCsv CompraProdutoArqCsv = new CompraProdutoArqCsv(listCompraProduto);
			CompraProdutoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getCompraProdutoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getCompraProdutoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			compraProduto = new CompraProduto();
			getCompraProdutoPainelCad().getGuiNome().requestFocus();
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
			List<CompraProduto> compraProdutos = new LinkedList<>();

			if (compraProduto.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (compraProdutos.add(CompraProdutoFac.getRegistro(compraProduto))) {
				CompraProdutoRel compraProdutoRel = new CompraProdutoRel(compraProdutos);
				compraProdutoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			compraProduto = new CompraProduto();
			getCompraProdutoJanCad().limparGui();
			getCompraProdutoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCompraProdutoPainelPesq().pesquisarRegistro(compraProduto);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCompraProdutoJanPesq());
				getCompraProdutoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCompraProdutoPainelPesq().pesquisarRegistro(new CompraProduto());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCompraProdutoJanPesq());
				getCompraProdutoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<CompraProduto> compraProdutos = new LinkedList<>();

			try {
				compraProdutos = new LinkedList<>(CompraProdutoFac.pesquisarRegistro(new CompraProduto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CompraProdutoRel compraProdutoRel = new CompraProdutoRel(compraProdutos);
			compraProdutoRel.retornarRelatorio(true);

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
				if (((getCompraProdutoPainelCad().getGuiNome().getText()) == null)
						|| (getCompraProdutoPainelCad().getGuiNome().getText().length() == 0)) {
					getCompraProdutoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getCompraProdutoPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					CompraProdutoFac.salvarRegistro(compraProduto);
					compraProduto = new CompraProduto();
					getCompraProdutoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getCompraProdutoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getCompraProdutoPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private CompraProduto compraProduto;

	public CompraProdutoControl() {

	}

	public void atualizarGui() {
		if (compraProduto == null) {
			return;
		}
		getCompraProdutoPainelCad().getGuiNome().setText(compraProduto.getNome());
		getCompraProdutoPainelCad().getGuiProdutoMarca().setSelectedItem(compraProduto.getProdutoMarca());
		getCompraProdutoPainelCad().getGuiProdutoUnidade().setSelectedItem(compraProduto.getProdutoUnidade());
		getCompraProdutoPainelCad().getGuiQuantidade().setText("" + compraProduto.getQuantidade());
	}

	public void atualizarObjeto() {
		compraProduto.setNome(getCompraProdutoPainelCad().getGuiNome().getText());
		compraProduto
				.setProdutoMarca((ProdutoMarca) getCompraProdutoPainelCad().getGuiProdutoMarca().getSelectedItem());
		compraProduto.setProdutoUnidade(
				(ProdutoUnidade) getCompraProdutoPainelCad().getGuiProdutoUnidade().getSelectedItem());
		compraProduto.setQuantidade(Double.parseDouble(getCompraProdutoPainelCad().getGuiNome().getText()));

		if (getCompraProdutoPainelCad().getGuiNome().getText().length() == 0) {
			compraProduto.setNome(null);
		}
	}

	public CompraProduto getCompraProduto() {
		return compraProduto;
	}

	public CompraProdutoJanCad getCompraProdutoJanCad() {
		return MainControl.getCompraProdutoJanCad();
	}

	public CompraProdutoJanPesq getCompraProdutoJanPesq() {
		return MainControl.getCompraProdutoJanPesq();
	}

	public CompraProdutoPainelCad getCompraProdutoPainelCad() {
		return MainControl.getCompraProdutoJanCad().getCompraProdutoPainelCad();
	}

	public CompraProdutoPainelPesq getCompraProdutoPainelPesq() {
		return MainControl.getCompraProdutoJanPesq().getCompraProdutoPainelPesq();
	}

	public void setCompraProduto(CompraProduto compraProduto) {
		this.compraProduto = compraProduto;
	}
}
