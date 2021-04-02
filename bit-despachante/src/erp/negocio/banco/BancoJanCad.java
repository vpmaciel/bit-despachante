package erp.negocio.banco;

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
public final class BancoJanCad extends JFrame implements Gui {

	private BancoControl bancoControl;
	private BancoPainelCad bancoPainelCad;
	private ConfiguracaoGui configuracaoGui;

	public BancoJanCad() {
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

	public BancoControl getBancoCont() {
		return bancoControl;
	}

	public BancoPainelCad getBancoPainelCad() {
		return bancoPainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		bancoControl = new BancoControl();
		addWindowListener(bancoControl.new Frame());
		bancoPainelCad.getTB().getExcluirBtn().addActionListener(bancoControl.new Exclui());
		bancoPainelCad.getTB().getNovoBtn().addActionListener(bancoControl.new Novo());
		bancoPainelCad.getTB().getPesquisarBtn().addActionListener(bancoControl.new Pesquisa());
		bancoPainelCad.getTB().getImprimirBtn().addActionListener(bancoControl.new Imprime());
		bancoPainelCad.getTB().getRelatorioBtn().addActionListener(bancoControl.new Relatorio());
		bancoPainelCad.getTB().getSalvarBtn().addActionListener(bancoControl.new Salva());
		bancoPainelCad.getTB().getHomeBtn().addActionListener(bancoControl.new Home());
		bancoPainelCad.getTB().getRegistrosBtn().addActionListener(bancoControl.new Registro());
		bancoPainelCad.getTB().getCsvBtn().addActionListener(bancoControl.new FormatoCsv());
		bancoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		bancoPainelCad = new BancoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(bancoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (bancoPainelCad.isAncestorOf(focused)) {
							bancoPainelCad.scrollRectToVisible(focused.getBounds());
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
		bancoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		bancoPainelCad.reiniciarGui();
	}
}