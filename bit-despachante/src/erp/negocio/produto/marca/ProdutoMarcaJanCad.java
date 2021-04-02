package erp.negocio.produto.marca;

import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public final class ProdutoMarcaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ProdutoMarcaControl produtoMarcaControl;
	private ProdutoMarcaPainelCad produtoMarcaPainelCad;

	public ProdutoMarcaJanCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public void desabilitarGui() {
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public ProdutoMarcaPainelCad getPanelProdutoMarcaCadastro() {
		return produtoMarcaPainelCad;
	}

	public ProdutoMarcaControl getProdutoMarcaCont() {
		return produtoMarcaControl;
	}

	public ProdutoMarcaPainelCad getProdutoMarcaPainelCad() {
		return produtoMarcaPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		produtoMarcaControl = new ProdutoMarcaControl();
		addWindowListener(produtoMarcaControl.new Frame());
		produtoMarcaPainelCad.getTB().getExcluirBtn().addActionListener(produtoMarcaControl.new Exclui());
		produtoMarcaPainelCad.getTB().getNovoBtn().addActionListener(produtoMarcaControl.new Novo());
		produtoMarcaPainelCad.getTB().getPesquisarBtn().addActionListener(produtoMarcaControl.new Pesquisa());
		produtoMarcaPainelCad.getTB().getImprimirBtn().addActionListener(produtoMarcaControl.new Imprime());
		produtoMarcaPainelCad.getTB().getRelatorioBtn().addActionListener(produtoMarcaControl.new Relatorio());
		produtoMarcaPainelCad.getTB().getSalvarBtn().addActionListener(produtoMarcaControl.new Salva());
		produtoMarcaPainelCad.getTB().getHomeBtn().addActionListener(produtoMarcaControl.new Home());
		produtoMarcaPainelCad.getTB().getRegistrosBtn().addActionListener(produtoMarcaControl.new Registro());
		produtoMarcaPainelCad.getTB().getCsvBtn().addActionListener(produtoMarcaControl.new FormatoCsv());
		produtoMarcaPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		produtoMarcaPainelCad = new ProdutoMarcaPainelCad();

		final JScrollPane scrollPane = new JScrollPane(produtoMarcaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (produtoMarcaPainelCad.isAncestorOf(focused)) {
							produtoMarcaPainelCad.scrollRectToVisible(focused.getBounds());
						}
					}
				});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);
		setContentPane(scrollPane);
		pack();
	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(Sis.getTamanhoJanela());
		setMinimumSize(Sis.getTamanhoJanela());
		setSize(Sis.getTamanhoJanela());
		setMaximumSize(Sis.getTamanhoJanela());
	}

	@Override
	public void iniciarTabela() {
	}

	@Override
	public void limparGui() {
		produtoMarcaPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		produtoMarcaPainelCad.reiniciarGui();
	}
}
