package erp.financeiro.vendas.produto;

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
public final class VendaProdutoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private VendaProdutoControl vendaProdutoControl;
	private VendaProdutoPainelCad vendaProdutoPainelCad;

	public VendaProdutoJanCad() {
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

	public VendaProdutoPainelCad getPanelVendaProdutoCadastro() {
		return vendaProdutoPainelCad;
	}

	public VendaProdutoControl getVendaProdutoCont() {
		return vendaProdutoControl;
	}

	public VendaProdutoPainelCad getVendaProdutoPainelCad() {
		return vendaProdutoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		vendaProdutoControl = new VendaProdutoControl();
		addWindowListener(vendaProdutoControl.new Frame());
		vendaProdutoPainelCad.getTB().getExcluirBtn().addActionListener(vendaProdutoControl.new Exclui());
		vendaProdutoPainelCad.getTB().getNovoBtn().addActionListener(vendaProdutoControl.new Novo());
		vendaProdutoPainelCad.getTB().getPesquisarBtn().addActionListener(vendaProdutoControl.new Pesquisa());
		vendaProdutoPainelCad.getTB().getImprimirBtn().addActionListener(vendaProdutoControl.new Imprime());
		vendaProdutoPainelCad.getTB().getRelatorioBtn().addActionListener(vendaProdutoControl.new Relatorio());
		vendaProdutoPainelCad.getTB().getSalvarBtn().addActionListener(vendaProdutoControl.new Salva());
		vendaProdutoPainelCad.getTB().getHomeBtn().addActionListener(vendaProdutoControl.new Home());
		vendaProdutoPainelCad.getTB().getRegistrosBtn().addActionListener(vendaProdutoControl.new Registro());
		vendaProdutoPainelCad.getTB().getCsvBtn().addActionListener(vendaProdutoControl.new FormatoCsv());
		vendaProdutoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		vendaProdutoPainelCad = new VendaProdutoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(vendaProdutoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (vendaProdutoPainelCad.isAncestorOf(focused)) {
							vendaProdutoPainelCad.scrollRectToVisible(focused.getBounds());
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
		vendaProdutoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		vendaProdutoPainelCad.reiniciarGui();
	}
}
