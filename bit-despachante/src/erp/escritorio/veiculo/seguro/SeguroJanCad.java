package erp.escritorio.veiculo.seguro;

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
public final class SeguroJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private SeguroControl seguroControl;
	private SeguroPainelCad seguroPainelCad;

	public SeguroJanCad() {
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

	public SeguroPainelCad getPanelSeguroCadastro() {
		return seguroPainelCad;
	}

	public SeguroControl getSeguroCont() {
		return seguroControl;
	}

	public SeguroPainelCad getSeguroPainelCad() {
		return seguroPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		seguroControl = new SeguroControl();
		addWindowListener(seguroControl.new Frame());
		seguroPainelCad.getTB().getExcluirBtn().addActionListener(seguroControl.new Exclui());
		seguroPainelCad.getTB().getNovoBtn().addActionListener(seguroControl.new Novo());
		seguroPainelCad.getTB().getPesquisarBtn().addActionListener(seguroControl.new Pesquisa());
		seguroPainelCad.getTB().getImprimirBtn().addActionListener(seguroControl.new Imprime());
		seguroPainelCad.getTB().getRelatorioBtn().addActionListener(seguroControl.new Relatorio());
		seguroPainelCad.getTB().getSalvarBtn().addActionListener(seguroControl.new Salva());
		seguroPainelCad.getTB().getHomeBtn().addActionListener(seguroControl.new Home());
		seguroPainelCad.getTB().getRegistrosBtn().addActionListener(seguroControl.new Registro());
		seguroPainelCad.getTB().getCsvBtn().addActionListener(seguroControl.new FormatoCsv());
		seguroPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		seguroPainelCad = new SeguroPainelCad();

		final JScrollPane scrollPane = new JScrollPane(seguroPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (seguroPainelCad.isAncestorOf(focused)) {
							seguroPainelCad.scrollRectToVisible(focused.getBounds());
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
		seguroPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		seguroPainelCad.reiniciarGui();
	}
}
