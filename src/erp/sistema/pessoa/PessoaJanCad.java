package erp.sistema.pessoa;

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
public final class PessoaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTabbedPane tabbedPane;
	private PessoaControl pessoaControl;
	private PessoaPainelCad pessoaPainelCad;
	private PessoaPainelPesq pessoaPainelPesq;

	public PessoaJanCad() {
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

	public PessoaPainelCad getPanelPessoa() {
		return pessoaPainelCad;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public PessoaControl getPessoaCont() {
		return pessoaControl;
	}

	public PessoaPainelCad getPessoaPainelCad() {
		return pessoaPainelCad;
	}

	public PessoaPainelPesq getPessoaPainelPesq() {
		return pessoaPainelPesq;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		pessoaControl = new PessoaControl();
		addWindowListener(pessoaControl.new Frame());
		pessoaPainelCad.getTB().getExcluirBtn().addActionListener(pessoaControl.new Exclui());
		pessoaPainelCad.getTB().getNovoBtn().addActionListener(pessoaControl.new Novo());
		pessoaPainelCad.getTB().getPesquisarBtn().addActionListener(pessoaControl.new Pesquisa());
		pessoaPainelCad.getTB().getImprimirBtn().addActionListener(pessoaControl.new Imprime());
		pessoaPainelCad.getTB().getRelatorioBtn().addActionListener(pessoaControl.new Relatorio());
		pessoaPainelCad.getTB().getSalvarBtn().addActionListener(pessoaControl.new Salva());
		pessoaPainelCad.getTB().getHomeBtn().addActionListener(pessoaControl.new Home());
		pessoaPainelCad.getTB().getRegistrosBtn().addActionListener(pessoaControl.new Registro());
		pessoaPainelCad.getTB().getCsvBtn().addActionListener(pessoaControl.new FormatoCsv());
		pessoaPainelCad.getTB().getJsonBtn().addActionListener(pessoaControl.new FormatoJson());
		
		pessoaPainelPesq.iniciarControlador();
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		tabbedPane = new JTabbedPane();
		pessoaPainelCad = new PessoaPainelCad();
		pessoaPainelPesq = new PessoaPainelPesq();

		final JScrollPane scrollPane = new JScrollPane(pessoaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (pessoaPainelCad.isAncestorOf(focused)) {
							pessoaPainelCad.scrollRectToVisible(focused.getBounds());
						}
					}
				});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tabbedPane.addTab("Cadastro", scrollPane);
		tabbedPane.addTab("Pesquisa", pessoaPainelPesq);

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
		pessoaPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		pessoaPainelCad.reiniciarGui();
	}
}
