package erp.negocio.veiculo.documento;

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
public final class VeiculoDocumentoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private VeiculoDocumentoControl veiculoDocumentoControl;
	private VeiculoDocumentoPainelCad veiculoDocumentoPainelCad;

	public VeiculoDocumentoJanCad() {
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

	public VeiculoDocumentoControl getVeiculoDocumentoCont() {
		return veiculoDocumentoControl;
	}

	public VeiculoDocumentoPainelCad getVeiculoDocumentoPainelCad() {
		return veiculoDocumentoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		veiculoDocumentoControl = new VeiculoDocumentoControl();
		addWindowListener(veiculoDocumentoControl.new Frame());
		veiculoDocumentoPainelCad.getLabelVeiculo().addMouseListener(veiculoDocumentoControl.new MostraFrame());
		veiculoDocumentoPainelCad.getTB().getExcluirBtn().addActionListener(veiculoDocumentoControl.new Exclui());
		veiculoDocumentoPainelCad.getTB().getNovoBtn().addActionListener(veiculoDocumentoControl.new Novo());
		veiculoDocumentoPainelCad.getTB().getPesquisarBtn().addActionListener(veiculoDocumentoControl.new Pesquisa());
		veiculoDocumentoPainelCad.getTB().getImprimirBtn().addActionListener(veiculoDocumentoControl.new Imprime());
		veiculoDocumentoPainelCad.getTB().getRelatorioBtn().addActionListener(veiculoDocumentoControl.new Relatorio());
		veiculoDocumentoPainelCad.getTB().getSalvarBtn().addActionListener(veiculoDocumentoControl.new Salva());
		veiculoDocumentoPainelCad.getTB().getHomeBtn().addActionListener(veiculoDocumentoControl.new Home());
		veiculoDocumentoPainelCad.getTB().getRegistrosBtn().addActionListener(veiculoDocumentoControl.new Registro());
		veiculoDocumentoPainelCad.getTB().getCsvBtn().addActionListener(veiculoDocumentoControl.new FormatoCsv());
		veiculoDocumentoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());

		veiculoDocumentoPainelCad = new VeiculoDocumentoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(veiculoDocumentoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (veiculoDocumentoPainelCad.isAncestorOf(focused)) {
							veiculoDocumentoPainelCad.scrollRectToVisible(focused.getBounds());
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
		veiculoDocumentoPainelCad.reiniciarGui();
	}
}
