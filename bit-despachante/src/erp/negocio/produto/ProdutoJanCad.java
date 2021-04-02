package erp.negocio.produto;

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
public final class ProdutoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ProdutoControl ProdutoControl;
	private ProdutoPainelCad ProdutoPainelCad;

	public ProdutoJanCad() {
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

	public ProdutoControl getProdutoCont() {
		return ProdutoControl;
	}

	public ProdutoPainelCad getProdutoPainelCad() {
		return ProdutoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		ProdutoControl = new ProdutoControl();
		addWindowListener(ProdutoControl.new Frame());
		ProdutoPainelCad.getTB().getExcluirBtn().addActionListener(ProdutoControl.new Exclui());
		ProdutoPainelCad.getTB().getNovoBtn().addActionListener(ProdutoControl.new Novo());
		ProdutoPainelCad.getTB().getPesquisarBtn().addActionListener(ProdutoControl.new Pesquisa());
		ProdutoPainelCad.getTB().getImprimirBtn().addActionListener(ProdutoControl.new Imprime());
		ProdutoPainelCad.getTB().getRelatorioBtn().addActionListener(ProdutoControl.new Relatorio());
		ProdutoPainelCad.getTB().getSalvarBtn().addActionListener(ProdutoControl.new Salva());
		ProdutoPainelCad.getTB().getHomeBtn().addActionListener(ProdutoControl.new Home());
		ProdutoPainelCad.getTB().getRegistrosBtn().addActionListener(ProdutoControl.new Registro());
		ProdutoPainelCad.getTB().getCsvBtn().addActionListener(ProdutoControl.new FormatoCsv());
		ProdutoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		ProdutoPainelCad = new ProdutoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(ProdutoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (ProdutoPainelCad.isAncestorOf(focused)) {
							ProdutoPainelCad.scrollRectToVisible(focused.getBounds());
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
		ProdutoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		ProdutoPainelCad.reiniciarGui();
	}
}