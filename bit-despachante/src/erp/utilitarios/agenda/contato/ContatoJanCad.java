package erp.utilitarios.agenda.contato;

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
public final class ContatoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ContatoControl contatoControl;
	private ContatoPainelCad contatoPainelCad;

	public ContatoJanCad() {
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

	public ContatoControl getContatoCont() {
		return contatoControl;
	}

	public ContatoPainelCad getContatoPainelCad() {
		return contatoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		contatoControl = new ContatoControl();
		addWindowListener(contatoControl.new Frame());
		contatoPainelCad.getLabelEmpresa().addMouseListener(contatoControl.new MostraJanCad());
		contatoPainelCad.getTB().getExcluirBtn().addActionListener(contatoControl.new Exclui());
		contatoPainelCad.getTB().getNovoBtn().addActionListener(contatoControl.new Novo());
		contatoPainelCad.getTB().getPesquisarBtn().addActionListener(contatoControl.new Pesquisa());
		contatoPainelCad.getTB().getImprimirBtn().addActionListener(contatoControl.new Imprime());
		contatoPainelCad.getTB().getRelatorioBtn().addActionListener(contatoControl.new Relatorio());
		contatoPainelCad.getTB().getSalvarBtn().addActionListener(contatoControl.new Salva());
		contatoPainelCad.getTB().getHomeBtn().addActionListener(contatoControl.new Home());
		contatoPainelCad.getTB().getRegistrosBtn().addActionListener(contatoControl.new Registro());
		contatoPainelCad.getTB().getCsvBtn().addActionListener(contatoControl.new FormatoCsv());
		contatoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		contatoPainelCad = new ContatoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(contatoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (contatoPainelCad.isAncestorOf(focused)) {
							contatoPainelCad.scrollRectToVisible(focused.getBounds());
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
		contatoPainelCad.reiniciarGui();
	}
}
