package erp.sistema.evento;

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
public final class EventoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private EventoControl eventoControl;
	private EventoPainelCad eventoPainelCad;

	public EventoJanCad() {
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

	public EventoControl getEventoCont() {
		return eventoControl;
	}

	public EventoPainelCad getEventoPainelCad() {
		return eventoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		eventoControl = new EventoControl();
		addWindowListener(eventoControl.new Frame());
		eventoPainelCad.getTB().getExcluirBtn().addActionListener(eventoControl.new Exclui());
		eventoPainelCad.getTB().getNovoBtn().addActionListener(eventoControl.new Novo());
		eventoPainelCad.getTB().getPesquisarBtn().addActionListener(eventoControl.new Pesquisa());
		eventoPainelCad.getTB().getImprimirBtn().addActionListener(eventoControl.new Imprime());
		eventoPainelCad.getTB().getRelatorioBtn().addActionListener(eventoControl.new Relatorio());
		eventoPainelCad.getTB().getSalvarBtn().addActionListener(eventoControl.new Salva());
		eventoPainelCad.getTB().getHomeBtn().addActionListener(eventoControl.new Home());
		eventoPainelCad.getTB().getRegistrosBtn().addActionListener(eventoControl.new Registro());
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		eventoPainelCad = new EventoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(eventoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (eventoPainelCad.isAncestorOf(focused)) {
							eventoPainelCad.scrollRectToVisible(focused.getBounds());
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
		eventoPainelCad.reiniciarGui();
	}
}
