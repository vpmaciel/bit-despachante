package erp.financeiro.vendas.produto;

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
import erp.negocio.cliente.Cliente;
import erp.negocio.produto.Produto;
import erp.sistema.main.MainControl;

final class VendaProdutoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((vendaProduto == null) || (vendaProduto.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				VendaProdutoFac.deletarRegistro(vendaProduto);
				getVendaProdutoJanCad().limparGui();
				vendaProduto = new VendaProduto();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getVendaProdutoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VendaProduto> listVendaProduto = new LinkedList<>();

			try {
				listVendaProduto = new LinkedList<>(VendaProdutoFac.pesquisarRegistro(new VendaProduto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VendaProdutoArqCsv VendaProdutoArqCsv = new VendaProdutoArqCsv(listVendaProduto);
			VendaProdutoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VendaProduto> listVendaProduto = new LinkedList<>();

			try {

				ArquivoJson<VendaProduto> arquivoJson = new ArquivoJson<VendaProduto>(vendaProduto, "VendaProduto");
				arquivoJson.gravarArquivo(VendaProdutoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VendaProdutoArqCsv VendaProdutoArqCsv = new VendaProdutoArqCsv(listVendaProduto);
			VendaProdutoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVendaProdutoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVendaProdutoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			vendaProduto = new VendaProduto();
			getVendaProdutoPainelCad().getGuiEntrada().requestFocus();
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
			List<VendaProduto> vendaProdutos = new LinkedList<>();

			if (vendaProduto.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (vendaProdutos.add(VendaProdutoFac.getRegistro(vendaProduto))) {
				VendaProdutoRel vendaProdutoRel = new VendaProdutoRel(vendaProdutos);
				vendaProdutoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			vendaProduto = new VendaProduto();
			getVendaProdutoJanCad().limparGui();
			getVendaProdutoPainelCad().getGuiEntrada().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVendaProdutoPainelPesq().pesquisarRegistro(vendaProduto);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVendaProdutoJanPesq());
				getVendaProdutoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVendaProdutoPainelPesq().pesquisarRegistro(new VendaProduto());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVendaProdutoJanPesq());
				getVendaProdutoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VendaProduto> vendaProdutos = new LinkedList<>();

			try {
				vendaProdutos = new LinkedList<>(VendaProdutoFac.pesquisarRegistro(new VendaProduto()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VendaProdutoRel vendaProdutoRel = new VendaProdutoRel(vendaProdutos);
			vendaProdutoRel.retornarRelatorio(true);

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
				if (((getVendaProdutoPainelCad().getGuiEntrada().getText()) == null)
						|| (getVendaProdutoPainelCad().getGuiEntrada().getText().length() == 0)) {
					getVendaProdutoPainelCad().getGuiEntrada().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getVendaProdutoPainelCad().getGuiEntrada(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VendaProdutoFac.salvarRegistro(vendaProduto);
					vendaProduto = new VendaProduto();
					getVendaProdutoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getVendaProdutoPainelCad().getGuiEntrada().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getVendaProdutoPainelCad().getGuiEntrada().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private VendaProduto vendaProduto;

	public VendaProdutoControl() {

	}

	public void atualizarGui() {
		if (vendaProduto == null) {
			return;
		}
		getVendaProdutoPainelCad().getGuiCliente().setSelectedItem(vendaProduto.getCliente());
		getVendaProdutoPainelCad().getGuiData().setText(vendaProduto.getData());
		getVendaProdutoPainelCad().getGuiEntrada().setValue(vendaProduto.getEntrada());
		getVendaProdutoPainelCad().getGuiEstorno().setValue(vendaProduto.getEstorno());
		getVendaProdutoPainelCad().getGuiHora().setText(vendaProduto.getHora());
		getVendaProdutoPainelCad().getGuiProduto().setSelectedItem(vendaProduto.getProduto());
		getVendaProdutoPainelCad().getGuiQtde().setValue(vendaProduto.getQtde());
		getVendaProdutoPainelCad().getGuiUsuario().setText(vendaProduto.getUsuario().getNome());
	}

	public void atualizarObjeto() {
		vendaProduto.setCliente((Cliente) getVendaProdutoPainelCad().getGuiCliente().getSelectedItem());
		vendaProduto.setProduto((Produto) getVendaProdutoPainelCad().getGuiProduto().getSelectedItem());
		getVendaProdutoPainelCad().getGuiQtde().setValue(vendaProduto.getQtde());
		getVendaProdutoPainelCad().getGuiUsuario().setText(vendaProduto.getUsuario().getNome());
		getVendaProdutoPainelCad().getGuiData().setText(vendaProduto.getData());
		getVendaProdutoPainelCad().getGuiEntrada().setValue(vendaProduto.getEntrada());
		getVendaProdutoPainelCad().getGuiEstorno().setValue(vendaProduto.getEstorno());
		getVendaProdutoPainelCad().getGuiHora().setText(vendaProduto.getUsuario().getNome());

		if (getVendaProdutoPainelCad().getGuiEntrada().getText().length() == 0) {
			vendaProduto.setProduto(null);
		}
	}

	public VendaProduto getVendaProduto() {
		return vendaProduto;
	}

	public VendaProdutoJanCad getVendaProdutoJanCad() {
		return MainControl.getVendaProdutoJanCad();
	}

	public VendaProdutoJanPesq getVendaProdutoJanPesq() {
		return MainControl.getVendaProdutoJanPesq();
	}

	public VendaProdutoPainelCad getVendaProdutoPainelCad() {
		return MainControl.getVendaProdutoJanCad().getVendaProdutoPainelCad();
	}

	public VendaProdutoPainelPesq getVendaProdutoPainelPesq() {
		return MainControl.getVendaProdutoJanPesq().getVendaProdutoPainelPesq();
	}

	public void setVendaProduto(VendaProduto vendaProduto) {
		this.vendaProduto = vendaProduto;
	}
}
