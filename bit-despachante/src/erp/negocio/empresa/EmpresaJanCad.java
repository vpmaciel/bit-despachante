package erp.negocio.empresa;

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
public final class EmpresaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private EmpresaControl empresaControl;
	private EmpresaPainelCad empresaPainelCad;

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

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public EmpresaControl getEmpresaCont() {
		return empresaControl;
	}

	public EmpresaPainelCad getEmpresaPainelCad() {
		return empresaPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		empresaControl = new EmpresaControl();
		addWindowListener(empresaControl.new Frame());
		empresaPainelCad.getTB().getExcluirBtn().addActionListener(empresaControl.new Exclui());
		empresaPainelCad.getTB().getNovoBtn().addActionListener(empresaControl.new NovoFrame());
		empresaPainelCad.getTB().getPesquisarBtn().addActionListener(empresaControl.new Pesquisa());
		empresaPainelCad.getTB().getImprimirBtn().addActionListener(empresaControl.new Imprime());
		empresaPainelCad.getTB().getRelatorioBtn().addActionListener(empresaControl.new Relatorio());
		empresaPainelCad.getTB().getSalvarBtn().addActionListener(empresaControl.new Salva());
		empresaPainelCad.getTB().getHomeBtn().addActionListener(empresaControl.new Home());
		empresaPainelCad.getTB().getRegistrosBtn().addActionListener(empresaControl.new Registro());
		empresaPainelCad.getTB().getCsvBtn().addActionListener(empresaControl.new FormatoCsv());
		empresaPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		empresaPainelCad = new EmpresaPainelCad();

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
		empresaPainelCad.reiniciarGui();
	}
}
