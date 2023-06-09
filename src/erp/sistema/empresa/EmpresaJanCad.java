package erp.sistema.empresa;

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
public final class EmpresaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTabbedPane tabbedPane;
	private EmpresaControl empresaControl;
	private EmpresaPainelCad empresaPainelCad;
	private EmpresaPainelPesq empresaPainelPesq;

	public EmpresaJanCad() {
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

	public EmpresaPainelCad getPanelEmpresa() {
		return empresaPainelCad;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public EmpresaControl getEmpresaCont() {
		return empresaControl;
	}

	public EmpresaPainelCad getEmpresaPainelCad() {
		return empresaPainelCad;
	}

	public EmpresaPainelPesq getEmpresaPainelPesq() {
		return empresaPainelPesq;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		empresaControl = new EmpresaControl();
		addWindowListener(empresaControl.new Frame());
		empresaPainelCad.getTB().getExcluirBtn().addActionListener(empresaControl.new Exclui());
		empresaPainelCad.getTB().getNovoBtn().addActionListener(empresaControl.new Novo());
		empresaPainelCad.getTB().getPesquisarBtn().addActionListener(empresaControl.new Pesquisa());
		empresaPainelCad.getTB().getImprimirBtn().addActionListener(empresaControl.new Imprime());
		empresaPainelCad.getTB().getRelatorioBtn().addActionListener(empresaControl.new Relatorio());
		empresaPainelCad.getTB().getSalvarBtn().addActionListener(empresaControl.new Salva());
		empresaPainelCad.getTB().getHomeBtn().addActionListener(empresaControl.new Home());
		empresaPainelCad.getTB().getRegistrosBtn().addActionListener(empresaControl.new Registro());
		empresaPainelCad.getTB().getCsvBtn().addActionListener(empresaControl.new FormatoCsv());
		empresaPainelCad.getTB().getJsonBtn().addActionListener(empresaControl.new FormatoJson());
		
		empresaPainelPesq.iniciarControlador();
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		tabbedPane = new JTabbedPane();
		empresaPainelCad = new EmpresaPainelCad();
		empresaPainelPesq = new EmpresaPainelPesq();

		final JScrollPane scrollPane = new JScrollPane(empresaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (empresaPainelCad.isAncestorOf(focused)) {
							empresaPainelCad.scrollRectToVisible(focused.getBounds());
						}
					}
				});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tabbedPane.addTab("Cadastro", scrollPane);
		tabbedPane.addTab("Pesquisa", empresaPainelPesq);

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
		empresaPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		empresaPainelCad.reiniciarGui();
	}
}
