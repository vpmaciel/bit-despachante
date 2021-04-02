package erp.financeiro.contaspagar;

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
public final class ContasPagarJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ContasPagarControl contasPagarControl;
	private ContasPagarPainelCad contasPagarPainelCad;

	public ContasPagarJanCad() {
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

	public ContasPagarControl getContasPagarCont() {
		return contasPagarControl;
	}

	public ContasPagarPainelCad getContasPagarPainelCad() {
		return contasPagarPainelCad;
	}

	public ContasPagarPainelCad getPanelContasPagarCadastro() {
		return contasPagarPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		contasPagarControl = new ContasPagarControl();
		addWindowListener(contasPagarControl.new Frame());
		contasPagarPainelCad.getTB().getExcluirBtn().addActionListener(contasPagarControl.new Exclui());
		contasPagarPainelCad.getTB().getNovoBtn().addActionListener(contasPagarControl.new Novo());
		contasPagarPainelCad.getTB().getPesquisarBtn().addActionListener(contasPagarControl.new Pesquisa());
		contasPagarPainelCad.getTB().getImprimirBtn().addActionListener(contasPagarControl.new Imprime());
		contasPagarPainelCad.getTB().getRelatorioBtn().addActionListener(contasPagarControl.new Relatorio());
		contasPagarPainelCad.getTB().getSalvarBtn().addActionListener(contasPagarControl.new Salva());
		contasPagarPainelCad.getTB().getHomeBtn().addActionListener(contasPagarControl.new Home());
		contasPagarPainelCad.getTB().getRegistrosBtn().addActionListener(contasPagarControl.new Registro());
		contasPagarPainelCad.getTB().getCsvBtn().addActionListener(contasPagarControl.new FormatoCsv());
		contasPagarPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		contasPagarPainelCad = new ContasPagarPainelCad();

		final JScrollPane scrollPane = new JScrollPane(contasPagarPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (contasPagarPainelCad.isAncestorOf(focused)) {
							contasPagarPainelCad.scrollRectToVisible(focused.getBounds());
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
		contasPagarPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		contasPagarPainelCad.reiniciarGui();
	}
}
