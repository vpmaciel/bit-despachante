package erp.negocio.cartorio;

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
public final class CartorioJanCad extends JFrame implements Gui {

	private CartorioControl cartorioControl;
	private CartorioPainelCad cartorioPainelCad;
	private ConfiguracaoGui configuracaoGui;

	public CartorioJanCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {

	}

	public CartorioControl getCartorioCont() {
		return cartorioControl;
	}

	public CartorioPainelCad getCartorioPainelCad() {
		return cartorioPainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		cartorioControl = new CartorioControl();
		addWindowListener(cartorioControl.new Frame());
		cartorioPainelCad.getTB().getExcluirBtn().addActionListener(cartorioControl.new Exclui());
		cartorioPainelCad.getTB().getNovoBtn().addActionListener(cartorioControl.new Novo());
		cartorioPainelCad.getTB().getPesquisarBtn().addActionListener(cartorioControl.new Pesquisa());
		cartorioPainelCad.getTB().getImprimirBtn().addActionListener(cartorioControl.new Imprime());
		cartorioPainelCad.getTB().getRelatorioBtn().addActionListener(cartorioControl.new Relatorio());
		cartorioPainelCad.getTB().getSalvarBtn().addActionListener(cartorioControl.new Salva());
		cartorioPainelCad.getTB().getHomeBtn().addActionListener(cartorioControl.new Home());
		cartorioPainelCad.getTB().getRegistrosBtn().addActionListener(cartorioControl.new Registro());
		cartorioPainelCad.getTB().getCsvBtn().addActionListener(cartorioControl.new FormatoCsv());
		cartorioPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		cartorioPainelCad = new CartorioPainelCad();

		final JScrollPane scrollPane = new JScrollPane(cartorioPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (cartorioPainelCad.isAncestorOf(focused)) {
							cartorioPainelCad.scrollRectToVisible(focused.getBounds());
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
		configuracaoGui.limparGui();
	}

	@Override
	public void reiniciarGui() {
		cartorioPainelCad.reiniciarGui();
	}
}
