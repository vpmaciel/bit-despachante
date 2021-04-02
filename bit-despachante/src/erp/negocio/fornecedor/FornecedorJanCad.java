package erp.negocio.fornecedor;

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
public final class FornecedorJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private FornecedorControl fornecedorControl;
	private FornecedorPainelCad fornecedorPainelCad;

	public FornecedorJanCad() {
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

	public FornecedorControl getFornecedorCont() {
		return fornecedorControl;
	}

	public FornecedorPainelCad getFornecedorPainelCad() {
		return fornecedorPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		fornecedorControl = new FornecedorControl();
		addWindowListener(fornecedorControl.new Frame());
		fornecedorPainelCad.getTB().getExcluirBtn().addActionListener(fornecedorControl.new Exclui());
		fornecedorPainelCad.getTB().getNovoBtn().addActionListener(fornecedorControl.new Novo());
		fornecedorPainelCad.getTB().getPesquisarBtn().addActionListener(fornecedorControl.new Pesquisa());
		fornecedorPainelCad.getTB().getImprimirBtn().addActionListener(fornecedorControl.new Imprime());
		fornecedorPainelCad.getTB().getRelatorioBtn().addActionListener(fornecedorControl.new Relatorio());
		fornecedorPainelCad.getTB().getSalvarBtn().addActionListener(fornecedorControl.new Salva());
		fornecedorPainelCad.getTB().getHomeBtn().addActionListener(fornecedorControl.new Home());
		fornecedorPainelCad.getTB().getRegistrosBtn().addActionListener(fornecedorControl.new Registro());
		fornecedorPainelCad.getTB().getCsvBtn().addActionListener(fornecedorControl.new FormatoCsv());
		fornecedorPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		fornecedorPainelCad = new FornecedorPainelCad();

		final JScrollPane scrollPane = new JScrollPane(fornecedorPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (fornecedorPainelCad.isAncestorOf(focused)) {
							fornecedorPainelCad.scrollRectToVisible(focused.getBounds());
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
		fornecedorPainelCad.reiniciarGui();
	}
}
