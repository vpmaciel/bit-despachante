package erp.negocio.seguradora;

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
public final class SeguradoraJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private SeguradoraControl seguradoraControl;
	private SeguradoraPainelCad seguradoraPainelCad;

	public SeguradoraJanCad() {
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

	public SeguradoraPainelCad getPanelSeguradoraCadastro() {
		return seguradoraPainelCad;
	}

	public SeguradoraControl getSeguradoraCont() {
		return seguradoraControl;
	}

	public SeguradoraPainelCad getSeguradoraPainelCad() {
		return seguradoraPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		seguradoraControl = new SeguradoraControl();
		addWindowListener(seguradoraControl.new Frame());
		seguradoraPainelCad.getTB().getExcluirBtn().addActionListener(seguradoraControl.new Exclui());
		seguradoraPainelCad.getTB().getNovoBtn().addActionListener(seguradoraControl.new Novo());
		seguradoraPainelCad.getTB().getPesquisarBtn().addActionListener(seguradoraControl.new Pesquisa());
		seguradoraPainelCad.getTB().getImprimirBtn().addActionListener(seguradoraControl.new Imprime());
		seguradoraPainelCad.getTB().getRelatorioBtn().addActionListener(seguradoraControl.new Relatorio());
		seguradoraPainelCad.getTB().getSalvarBtn().addActionListener(seguradoraControl.new Salva());
		seguradoraPainelCad.getTB().getHomeBtn().addActionListener(seguradoraControl.new Home());
		seguradoraPainelCad.getTB().getRegistrosBtn().addActionListener(seguradoraControl.new Registro());
		seguradoraPainelCad.getTB().getCsvBtn().addActionListener(seguradoraControl.new FormatoCsv());
		seguradoraPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		seguradoraPainelCad = new SeguradoraPainelCad();

		final JScrollPane scrollPane = new JScrollPane(seguradoraPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (seguradoraPainelCad.isAncestorOf(focused)) {
							seguradoraPainelCad.scrollRectToVisible(focused.getBounds());
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
		seguradoraPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		seguradoraPainelCad.reiniciarGui();
	}
}
