package erp.utilitarios.agenda.recado;

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
public final class RecadoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui guiConfiguracao;
	private RecadoControl recadoControl;
	private RecadoPainelCad recadoPainelCad;

	public RecadoJanCad() {
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
		return guiConfiguracao;
	}

	public RecadoControl getRecadoCont() {
		return recadoControl;
	}

	public RecadoPainelCad getRecadoPainelCad() {
		return recadoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		recadoControl = new RecadoControl();
		addWindowListener(recadoControl.new Frame());
		recadoPainelCad.getTB().getExcluirBtn().addActionListener(recadoControl.new Exclui());
		recadoPainelCad.getTB().getNovoBtn().addActionListener(recadoControl.new Novo());
		recadoPainelCad.getTB().getPesquisarBtn().addActionListener(recadoControl.new Pesquisa());
		recadoPainelCad.getTB().getImprimirBtn().addActionListener(recadoControl.new Imprime());
		recadoPainelCad.getTB().getRelatorioBtn().addActionListener(recadoControl.new Relatorio());
		recadoPainelCad.getTB().getSalvarBtn().addActionListener(recadoControl.new Salva());
		recadoPainelCad.getTB().getHomeBtn().addActionListener(recadoControl.new Home());
		recadoPainelCad.getTB().getRegistrosBtn().addActionListener(recadoControl.new Registro());
		recadoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		recadoPainelCad = new RecadoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(recadoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (recadoPainelCad.isAncestorOf(focused)) {
							recadoPainelCad.scrollRectToVisible(focused.getBounds());
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
		guiConfiguracao = new ConfiguracaoGui(this);
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
		guiConfiguracao.limparGui();
	}

	@Override
	public void reiniciarGui() {
		recadoPainelCad.reiniciarGui();
	}
}
