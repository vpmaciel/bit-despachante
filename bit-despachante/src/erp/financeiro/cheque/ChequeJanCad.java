package erp.financeiro.cheque;

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
public final class ChequeJanCad extends JFrame implements Gui {

	private ChequeControl chequeControl;
	private ChequePainelCad chequePainelCad;
	private ConfiguracaoGui configuracaoGui;

	public ChequeJanCad() {
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

	public ChequeControl getChequeCont() {
		return chequeControl;
	}

	public ChequePainelCad getChequePainelCad() {
		return chequePainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public ChequePainelCad getPanelChequeCadastro() {
		return chequePainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		chequeControl = new ChequeControl();
		addWindowListener(chequeControl.new Frame());
		chequePainelCad.getTB().getExcluirBtn().addActionListener(chequeControl.new Exclui());
		chequePainelCad.getTB().getNovoBtn().addActionListener(chequeControl.new Novo());
		chequePainelCad.getTB().getPesquisarBtn().addActionListener(chequeControl.new Pesquisa());
		chequePainelCad.getTB().getImprimirBtn().addActionListener(chequeControl.new Imprime());
		chequePainelCad.getTB().getRelatorioBtn().addActionListener(chequeControl.new Relatorio());
		chequePainelCad.getTB().getSalvarBtn().addActionListener(chequeControl.new Salva());
		chequePainelCad.getTB().getHomeBtn().addActionListener(chequeControl.new Home());
		chequePainelCad.getTB().getRegistrosBtn().addActionListener(chequeControl.new Registro());
		chequePainelCad.getTB().getCsvBtn().addActionListener(chequeControl.new FormatoCsv());
		chequePainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		chequePainelCad = new ChequePainelCad();

		final JScrollPane scrollPane = new JScrollPane(chequePainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (chequePainelCad.isAncestorOf(focused)) {
							chequePainelCad.scrollRectToVisible(focused.getBounds());
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
		chequePainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		chequePainelCad.reiniciarGui();
	}
}
