package erp.negocio.contador;

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
public final class ContadorJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ContadorControl contadorControl;
	private ContadorPainelCad contadorPainelCad;

	public ContadorJanCad() {
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

	public ContadorControl getContadorCont() {
		return contadorControl;
	}

	public ContadorPainelCad getContadorPainelCad() {
		return contadorPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		contadorControl = new ContadorControl();
		addWindowListener(contadorControl.new Frame());
		contadorPainelCad.getTB().getExcluirBtn().addActionListener(contadorControl.new Exclui());
		contadorPainelCad.getTB().getNovoBtn().addActionListener(contadorControl.new Novo());
		contadorPainelCad.getTB().getPesquisarBtn().addActionListener(contadorControl.new Pesquisa());
		contadorPainelCad.getTB().getImprimirBtn().addActionListener(contadorControl.new Imprime());
		contadorPainelCad.getTB().getRelatorioBtn().addActionListener(contadorControl.new Relatorio());
		contadorPainelCad.getTB().getSalvarBtn().addActionListener(contadorControl.new Salva());
		contadorPainelCad.getTB().getHomeBtn().addActionListener(contadorControl.new Home());
		contadorPainelCad.getTB().getRegistrosBtn().addActionListener(contadorControl.new Registro());
		contadorPainelCad.getTB().getCsvBtn().addActionListener(contadorControl.new FormatoCsv());
		contadorPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		contadorPainelCad = new ContadorPainelCad();

		final JScrollPane scrollPane = new JScrollPane(contadorPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (contadorPainelCad.isAncestorOf(focused)) {
							contadorPainelCad.scrollRectToVisible(focused.getBounds());
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
		contadorPainelCad.reiniciarGui();
	}
}
