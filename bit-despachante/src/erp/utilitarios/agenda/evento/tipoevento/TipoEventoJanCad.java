package erp.utilitarios.agenda.evento.tipoevento;

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
public final class TipoEventoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private TipoEventoControl tipoEventoControl;
	private TipoEventoPainelCad tipoEventoPainelCad;

	public TipoEventoJanCad() {
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

	public TipoEventoControl getTipoEventoCont() {
		return tipoEventoControl;
	}

	public TipoEventoPainelCad getTipoEventoPainelCad() {
		return tipoEventoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		tipoEventoControl = new TipoEventoControl();
		addWindowListener(tipoEventoControl.new Frame());
		tipoEventoPainelCad.getTB().getExcluirBtn().addActionListener(tipoEventoControl.new Exclui());
		tipoEventoPainelCad.getTB().getNovoBtn().addActionListener(tipoEventoControl.new Novo());
		tipoEventoPainelCad.getTB().getPesquisarBtn().addActionListener(tipoEventoControl.new Pesquisa());
		tipoEventoPainelCad.getTB().getImprimirBtn().addActionListener(tipoEventoControl.new Imprime());
		tipoEventoPainelCad.getTB().getRelatorioBtn().addActionListener(tipoEventoControl.new Relatorio());
		tipoEventoPainelCad.getTB().getSalvarBtn().addActionListener(tipoEventoControl.new Salva());
		tipoEventoPainelCad.getTB().getHomeBtn().addActionListener(tipoEventoControl.new Home());
		tipoEventoPainelCad.getTB().getRegistrosBtn().addActionListener(tipoEventoControl.new Registro());
		tipoEventoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());

		tipoEventoPainelCad = new TipoEventoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(tipoEventoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (tipoEventoPainelCad.isAncestorOf(focused)) {
							tipoEventoPainelCad.scrollRectToVisible(focused.getBounds());
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
		tipoEventoPainelCad.reiniciarGui();
	}
}
