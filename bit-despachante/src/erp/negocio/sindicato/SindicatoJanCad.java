package erp.negocio.sindicato;

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
public final class SindicatoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private SindicatoControl sindicatoControl;
	private SindicatoPainelCad sindicatoPainelCad;

	public SindicatoJanCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {

	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public SindicatoControl getSindicatoCont() {
		return sindicatoControl;
	}

	public SindicatoPainelCad getSindicatoPainelCad() {
		return sindicatoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		sindicatoControl = new SindicatoControl();
		addWindowListener(sindicatoControl.new Frame());
		sindicatoPainelCad.getTB().getExcluirBtn().addActionListener(sindicatoControl.new Exclui());
		sindicatoPainelCad.getTB().getNovoBtn().addActionListener(sindicatoControl.new Novo());
		sindicatoPainelCad.getTB().getPesquisarBtn().addActionListener(sindicatoControl.new Pesquisa());
		sindicatoPainelCad.getTB().getImprimirBtn().addActionListener(sindicatoControl.new Imprime());
		sindicatoPainelCad.getTB().getRelatorioBtn().addActionListener(sindicatoControl.new Relatorio());
		sindicatoPainelCad.getTB().getSalvarBtn().addActionListener(sindicatoControl.new Salva());
		sindicatoPainelCad.getTB().getHomeBtn().addActionListener(sindicatoControl.new Home());
		sindicatoPainelCad.getTB().getRegistrosBtn().addActionListener(sindicatoControl.new Registro());
		sindicatoPainelCad.getTB().getCsvBtn().addActionListener(sindicatoControl.new FormatoCsv());
		sindicatoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		sindicatoPainelCad = new SindicatoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(sindicatoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (sindicatoPainelCad.isAncestorOf(focused)) {
							sindicatoPainelCad.scrollRectToVisible(focused.getBounds());
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
		sindicatoPainelCad.reiniciarGui();
	}
}
