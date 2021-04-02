package erp.escritorio.produto.compra.lista;

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
import erp.negocio.produto.marca.ProdutoMarca;
import erp.negocio.produto.marca.ProdutoMarcaComp;
import erp.negocio.produto.marca.ProdutoMarcaFac;
import erp.negocio.produto.unidade.ProdutoUnidade;
import erp.negocio.produto.unidade.ProdutoUnidadeComp;
import erp.negocio.produto.unidade.ProdutoUnidadeFac;

@SuppressWarnings("serial")
public final class CompraProdutoPainelCad extends JPanel implements Gui {

	private JComboBox<ProdutoMarca> boxProdutoMarca;
	private JComboBox<ProdutoUnidade> boxProdutoUnidade;
	private CompraProdutoControl compraProdutoControl;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldNome;
	private JTextField fieldQuantidade;
	private ToolBar toolBar;

	public CompraProdutoPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public CompraProdutoControl getCompraProdutoCont() {
		return compraProdutoControl;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JComboBox<ProdutoMarca> getGuiProdutoMarca() {
		return boxProdutoMarca;
	}

	public JComboBox<ProdutoUnidade> getGuiProdutoUnidade() {
		return boxProdutoUnidade;
	}

	public JTextField getGuiQuantidade() {
		return fieldQuantidade;
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

		add(new JLabel("PRODUTO"));

		fieldNome = new JTextField();
		fieldNome.setDocument(new EntradaMaiuscula(50));
		add(fieldNome);

		add(new JLabel("QUANTIDADE"));

		fieldQuantidade = new JTextField();
		fieldQuantidade.setDocument(new EntradaMaiuscula(50));
		add(fieldQuantidade);

		add(new JLabel("UNIDADE"));

		boxProdutoUnidade = new JComboBox<ProdutoUnidade>();
		List<ProdutoUnidade> produtoUnidadeList = (List<ProdutoUnidade>) ProdutoUnidadeFac.getRegistro();
		Collections.sort(produtoUnidadeList, new ProdutoUnidadeComp().new Nome());

		for (ProdutoUnidade produtoUnidade : produtoUnidadeList) {
			boxProdutoUnidade.addItem(produtoUnidade);
		}
		add(boxProdutoUnidade);

		add(new JLabel("MARCA"));

		boxProdutoMarca = new JComboBox<ProdutoMarca>();
		List<ProdutoMarca> produtoMarcaList = (List<ProdutoMarca>) ProdutoMarcaFac.getRegistro();
		Collections.sort(produtoMarcaList, new ProdutoMarcaComp().new Nome());

		for (ProdutoMarca produtoMarca : produtoMarcaList) {
			boxProdutoMarca.addItem(produtoMarca);
		}
		add(boxProdutoMarca);
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
