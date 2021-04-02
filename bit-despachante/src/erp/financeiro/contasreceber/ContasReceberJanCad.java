package erp.financeiro.contasreceber;

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
public final class ContasReceberJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private ContasReceberControl contasReceberControl;
	private ContasReceberPainelCad contasReceberPainelCad;

	public ContasReceberJanCad() {
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

	public ContasReceberControl getContasReceberCont() {
		return contasReceberControl;
	}

	public ContasReceberPainelCad getContasReceberPainelCad() {
		return contasReceberPainelCad;
	}

	public ContasReceberPainelCad getPanelContasReceberCadastro() {
		return contasReceberPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		contasReceberControl = new ContasReceberControl();
		addWindowListener(contasReceberControl.new Frame());
		contasReceberPainelCad.getTB().getExcluirBtn().addActionListener(contasReceberControl.new Exclui());
		contasReceberPainelCad.getTB().getNovoBtn().addActionListener(contasReceberControl.new Novo());
		contasReceberPainelCad.getTB().getPesquisarBtn().addActionListener(contasReceberControl.new Pesquisa());
		contasReceberPainelCad.getTB().getImprimirBtn().addActionListener(contasReceberControl.new Imprime());
		contasReceberPainelCad.getTB().getRelatorioBtn().addActionListener(contasReceberControl.new Relatorio());
		contasReceberPainelCad.getTB().getSalvarBtn().addActionListener(contasReceberControl.new Salva());
		contasReceberPainelCad.getTB().getHomeBtn().addActionListener(contasReceberControl.new Home());
		contasReceberPainelCad.getTB().getRegistrosBtn().addActionListener(contasReceberControl.new Registro());
		contasReceberPainelCad.getTB().getCsvBtn().addActionListener(contasReceberControl.new FormatoCsv());
		contasReceberPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		contasReceberPainelCad = new ContasReceberPainelCad();

		final JScrollPane scrollPane = new JScrollPane(contasReceberPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (contasReceberPainelCad.isAncestorOf(focused)) {
							contasReceberPainelCad.scrollRectToVisible(focused.getBounds());
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
		contasReceberPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		contasReceberPainelCad.reiniciarGui();
	}
}
