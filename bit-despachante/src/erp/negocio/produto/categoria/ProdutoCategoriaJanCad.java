package erp.negocio.produto.categoria;

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
public final class ProdutoCategoriaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ProdutoCategoriaControl servicoControl;
	private ProdutoCategoriaPainelCad servicoPainelCad;

	public ProdutoCategoriaJanCad() {
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

	public ProdutoCategoriaPainelCad getPanelProdutoCategoriaCadastro() {
		return servicoPainelCad;
	}

	public ProdutoCategoriaControl getProdutoCategoriaCont() {
		return servicoControl;
	}

	public ProdutoCategoriaPainelCad getProdutoCategoriaPainelCad() {
		return servicoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		servicoControl = new ProdutoCategoriaControl();
		addWindowListener(servicoControl.new Frame());
		servicoPainelCad.getTB().getExcluirBtn().addActionListener(servicoControl.new Exclui());
		servicoPainelCad.getTB().getNovoBtn().addActionListener(servicoControl.new Novo());
		servicoPainelCad.getTB().getPesquisarBtn().addActionListener(servicoControl.new Pesquisa());
		servicoPainelCad.getTB().getImprimirBtn().addActionListener(servicoControl.new Imprime());
		servicoPainelCad.getTB().getRelatorioBtn().addActionListener(servicoControl.new Relatorio());
		servicoPainelCad.getTB().getSalvarBtn().addActionListener(servicoControl.new Salva());
		servicoPainelCad.getTB().getHomeBtn().addActionListener(servicoControl.new Home());
		servicoPainelCad.getTB().getRegistrosBtn().addActionListener(servicoControl.new Registro());
		servicoPainelCad.getTB().getCsvBtn().addActionListener(servicoControl.new FormatoCsv());
		servicoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		servicoPainelCad = new ProdutoCategoriaPainelCad();

		final JScrollPane scrollPane = new JScrollPane(servicoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (servicoPainelCad.isAncestorOf(focused)) {
							servicoPainelCad.scrollRectToVisible(focused.getBounds());
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
		servicoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		servicoPainelCad.reiniciarGui();
	}
}
