package erp.sistema.atividade;

import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public final class AtividadeJan extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTabbedPane tabbedPane;
	private AtividadeControl atividadeControl;
	private AtividadePainelCad atividadePainelCad;
	private AtividadePainelPesq atividadePainelPesq;

	public AtividadeJan() {
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

	public AtividadePainelCad getPanelAtividade() {
		return atividadePainelCad;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public AtividadeControl getAtividadeCont() {
		return atividadeControl;
	}

	public AtividadePainelCad getAtividadePainelCad() {
		return atividadePainelCad;
	}

	public AtividadePainelPesq getAtividadePainelPesq() {
		return atividadePainelPesq;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		atividadeControl = new AtividadeControl();
		addWindowListener(atividadeControl.new Frame());
		atividadePainelCad.getTB().getExcluirBtn().addActionListener(atividadeControl.new Exclui());
		atividadePainelCad.getTB().getNovoBtn().addActionListener(atividadeControl.new Novo());
		atividadePainelCad.getTB().getPesquisarBtn().addActionListener(atividadeControl.new Pesquisa());
		atividadePainelCad.getTB().getImprimirBtn().addActionListener(atividadeControl.new Imprime());
		atividadePainelCad.getTB().getRelatorioBtn().addActionListener(atividadeControl.new Relatorio());
		atividadePainelCad.getTB().getSalvarBtn().addActionListener(atividadeControl.new Salva());
		atividadePainelCad.getTB().getHomeBtn().addActionListener(atividadeControl.new Home());
		atividadePainelCad.getTB().getRegistrosBtn().addActionListener(atividadeControl.new Registro());
		atividadePainelCad.getTB().getCsvBtn().addActionListener(atividadeControl.new FormatoCsv());
		atividadePainelCad.getTB().getJsonBtn().addActionListener(atividadeControl.new FormatoJson());
		
		atividadePainelPesq.iniciarControlador();
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		tabbedPane = new JTabbedPane();
		atividadePainelCad = new AtividadePainelCad();
		atividadePainelPesq = new AtividadePainelPesq();

		final JScrollPane scrollPane = new JScrollPane(atividadePainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (atividadePainelCad.isAncestorOf(focused)) {
							atividadePainelCad.scrollRectToVisible(focused.getBounds());
						}
					}
				});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tabbedPane.addTab("Cadastro", scrollPane);
		tabbedPane.addTab("Pesquisa", atividadePainelPesq);

		add(tabbedPane);
		setContentPane(tabbedPane);
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
		atividadePainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		atividadePainelCad.reiniciarGui();
	}
}
