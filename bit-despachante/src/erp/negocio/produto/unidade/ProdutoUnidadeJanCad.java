package erp.negocio.produto.unidade;

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
public final class ProdutoUnidadeJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ProdutoUnidadeControl produtoUnidadeControl;
	private ProdutoUnidadePainelCad produtoUnidadePainelCad;

	public ProdutoUnidadeJanCad() {
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

	public ProdutoUnidadePainelCad getPanelProdutoUnidadeCadastro() {
		return produtoUnidadePainelCad;
	}

	public ProdutoUnidadeControl getProdutoUnidadeCont() {
		return produtoUnidadeControl;
	}

	public ProdutoUnidadePainelCad getProdutoUnidadePainelCad() {
		return produtoUnidadePainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		produtoUnidadeControl = new ProdutoUnidadeControl();
		addWindowListener(produtoUnidadeControl.new Frame());
		produtoUnidadePainelCad.getTB().getExcluirBtn().addActionListener(produtoUnidadeControl.new Exclui());
		produtoUnidadePainelCad.getTB().getNovoBtn().addActionListener(produtoUnidadeControl.new Novo());
		produtoUnidadePainelCad.getTB().getPesquisarBtn().addActionListener(produtoUnidadeControl.new Pesquisa());
		produtoUnidadePainelCad.getTB().getImprimirBtn().addActionListener(produtoUnidadeControl.new Imprime());
		produtoUnidadePainelCad.getTB().getRelatorioBtn().addActionListener(produtoUnidadeControl.new Relatorio());
		produtoUnidadePainelCad.getTB().getSalvarBtn().addActionListener(produtoUnidadeControl.new Salva());
		produtoUnidadePainelCad.getTB().getHomeBtn().addActionListener(produtoUnidadeControl.new Home());
		produtoUnidadePainelCad.getTB().getRegistrosBtn().addActionListener(produtoUnidadeControl.new Registro());
		produtoUnidadePainelCad.getTB().getCsvBtn().addActionListener(produtoUnidadeControl.new FormatoCsv());
		produtoUnidadePainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		produtoUnidadePainelCad = new ProdutoUnidadePainelCad();

		final JScrollPane scrollPane = new JScrollPane(produtoUnidadePainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (produtoUnidadePainelCad.isAncestorOf(focused)) {
							produtoUnidadePainelCad.scrollRectToVisible(focused.getBounds());
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
		produtoUnidadePainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		produtoUnidadePainelCad.reiniciarGui();
	}
}
