package erp.financeiro.caixa;

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
public final class CaixaJanCad extends JFrame implements Gui {

	private CaixaControl caixaControl;
	private CaixaPainelCad caixaPainelCad;
	private ConfiguracaoGui configuracaoGui;

	public CaixaJanCad() {
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

	public CaixaControl getCaixaCont() {
		return caixaControl;
	}

	public CaixaPainelCad getCaixaPainelCad() {
		return caixaPainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public CaixaPainelCad getPanelCaixaCadastro() {
		return caixaPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		caixaControl = new CaixaControl();
		addWindowListener(caixaControl.new Frame());
		caixaPainelCad.getTB().getExcluirBtn().addActionListener(caixaControl.new Exclui());
		caixaPainelCad.getTB().getNovoBtn().addActionListener(caixaControl.new Novo());
		caixaPainelCad.getTB().getPesquisarBtn().addActionListener(caixaControl.new Pesquisa());
		caixaPainelCad.getTB().getImprimirBtn().addActionListener(caixaControl.new Imprime());
		caixaPainelCad.getTB().getRelatorioBtn().addActionListener(caixaControl.new Relatorio());
		caixaPainelCad.getTB().getSalvarBtn().addActionListener(caixaControl.new Salva());
		caixaPainelCad.getTB().getHomeBtn().addActionListener(caixaControl.new Home());
		caixaPainelCad.getTB().getRegistrosBtn().addActionListener(caixaControl.new Registro());
		caixaPainelCad.getTB().getCsvBtn().addActionListener(caixaControl.new FormatoCsv());
		caixaPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		caixaPainelCad = new CaixaPainelCad();

		final JScrollPane scrollPane = new JScrollPane(caixaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (caixaPainelCad.isAncestorOf(focused)) {
							caixaPainelCad.scrollRectToVisible(focused.getBounds());
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
		caixaPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		caixaPainelCad.reiniciarGui();
	}
}
