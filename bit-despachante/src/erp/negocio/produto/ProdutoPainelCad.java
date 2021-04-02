package erp.negocio.produto;

import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;
import erp.negocio.fornecedor.Fornecedor;
import erp.negocio.fornecedor.FornecedorComp;
import erp.negocio.fornecedor.FornecedorFac;
import erp.negocio.produto.categoria.ProdutoCategoria;
import erp.negocio.produto.categoria.ProdutoCategoriaComp;
import erp.negocio.produto.categoria.ProdutoCategoriaFac;
import erp.negocio.produto.marca.ProdutoMarca;
import erp.negocio.produto.marca.ProdutoMarcaComp;
import erp.negocio.produto.marca.ProdutoMarcaFac;
import erp.negocio.produto.unidade.ProdutoUnidade;
import erp.negocio.produto.unidade.ProdutoUnidadeComp;
import erp.negocio.produto.unidade.ProdutoUnidadeFac;

@SuppressWarnings("serial")
public final class ProdutoPainelCad extends JPanel implements Gui {

	private JComboBox<Fornecedor> boxFornecedor;
	private JComboBox<ProdutoCategoria> boxProdutoCategoria;
	private JComboBox<ProdutoMarca> boxProdutoMarca;
	private JComboBox<ProdutoUnidade> boxProdutoUnidade;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldEstoqueAtual;
	private JTextField fieldEstoqueMinimo;
	private JTextField fieldNome;
	private JTextField fieldPreco;
	private ProdutoControl ProdutoControl;

	private ToolBar toolBar;

	public ProdutoPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public JComboBox<Fornecedor> getBoxFornecedor() {
		return boxFornecedor;
	}

	public JComboBox<ProdutoCategoria> getBoxProdutoCategoria() {
		return boxProdutoCategoria;
	}

	public JComboBox<ProdutoMarca> getBoxProdutoMarca() {
		return boxProdutoMarca;
	}

	public JComboBox<ProdutoUnidade> getBoxProdutoUnidade() {
		return boxProdutoUnidade;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public JTextField getFieldEstoqueAtual() {
		return fieldEstoqueAtual;
	}

	public JTextField getFieldEstoqueMinimo() {
		return fieldEstoqueMinimo;
	}

	public JTextField getFieldNome() {
		return fieldNome;
	}

	public JTextField getFieldPreco() {
		return fieldPreco;
	}

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiPreco() {
		return fieldPreco;
	}

	public ProdutoControl getProdutoCont() {
		return ProdutoControl;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	@Override
	public void iniciarControlador() {
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		toolBar = new ToolBar();

		add(toolBar.getTB());

		add(new JLabel("NOME"));

		fieldNome = new JTextField();
		fieldNome.setDocument(new EntradaMaiuscula(50));
		add(fieldNome);

		add(new JLabel("ESTOQUE ATUAL"));

		fieldEstoqueAtual = new JTextField();
		fieldEstoqueAtual.setDocument(new EntradaMaiuscula(50));
		add(fieldEstoqueAtual);

		add(new JLabel("ESTOQUE MÍNIMO"));

		fieldEstoqueMinimo = new JTextField();
		fieldEstoqueMinimo.setDocument(new EntradaMaiuscula(50));
		add(fieldEstoqueMinimo);

		add(new JLabel("PREÇO"));

		fieldPreco = new JTextField();
		fieldPreco.setDocument(new EntradaMaiuscula(10));
		add(fieldPreco);

		add(new JLabel("UNIDADE"));

		boxProdutoUnidade = new JComboBox<ProdutoUnidade>();
		List<ProdutoUnidade> produtoUnidadeList = (List<ProdutoUnidade>) ProdutoUnidadeFac.getRegistro();
		Collections.sort(produtoUnidadeList, new ProdutoUnidadeComp().new Nome());

		for (ProdutoUnidade produtoUnidade : produtoUnidadeList) {
			boxProdutoUnidade.addItem(produtoUnidade);
		}
		add(boxProdutoUnidade);

		add(new JLabel("CATEGORIA"));

		boxProdutoCategoria = new JComboBox<ProdutoCategoria>();
		List<ProdutoCategoria> bancoList = (List<ProdutoCategoria>) ProdutoCategoriaFac.getRegistro();
		Collections.sort(bancoList, new ProdutoCategoriaComp().new Nome());

		for (ProdutoCategoria categoria : bancoList) {
			boxProdutoCategoria.addItem(categoria);
		}
		add(boxProdutoCategoria);

		add(new JLabel("MARCA"));

		boxProdutoMarca = new JComboBox<ProdutoMarca>();
		List<ProdutoMarca> produtoMarcaList = (List<ProdutoMarca>) ProdutoMarcaFac.getRegistro();
		Collections.sort(produtoMarcaList, new ProdutoMarcaComp().new Nome());

		for (ProdutoMarca produtoMarca : produtoMarcaList) {
			boxProdutoMarca.addItem(produtoMarca);
		}
		add(boxProdutoMarca);

		add(new JLabel("FORNECEDOR"));

		boxFornecedor = new JComboBox<Fornecedor>();
		List<Fornecedor> fornecedorList = (List<Fornecedor>) FornecedorFac.getRegistro();
		Collections.sort(fornecedorList, new FornecedorComp().new Nome());

		for (Fornecedor fornecedor : fornecedorList) {
			boxFornecedor.addItem(fornecedor);
		}
		add(boxFornecedor);
	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setBorder(Sis.getBordaPainel());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public void iniciarTabela() {
	}

	@Override
	public void limparGui() {
		configuracaoGui.limparGui();
	}

	@Override
	public void reiniciarGui() {

	}
}
