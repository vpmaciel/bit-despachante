package erp.negocio.veiculo.modelo;

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
public final class VeiculoModeloJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private VeiculoModeloControl veiculoModeloControl;
	private VeiculoModeloPainelCad veiculoModeloPainelCad;

	public VeiculoModeloJanCad() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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

	public VeiculoModeloControl getVeiculoModeloCont() {
		return veiculoModeloControl;
	}

	public VeiculoModeloPainelCad getVeiculoModeloPainelCad() {
		return veiculoModeloPainelCad;
	}

	@Override
	public void iniciarControlador() {
		veiculoModeloControl = new VeiculoModeloControl();
		addWindowListener(veiculoModeloControl.new Frame());
		veiculoModeloPainelCad.getTB().getExcluirBtn().addActionListener(veiculoModeloControl.new ExcluiRegistro());
		veiculoModeloPainelCad.getTB().getNovoBtn().addActionListener(veiculoModeloControl.new NovoFrame());
		veiculoModeloPainelCad.getTB().getPesquisarBtn().addActionListener(veiculoModeloControl.new PesquisaRegistro());
		veiculoModeloPainelCad.getTB().getImprimirBtn().addActionListener(veiculoModeloControl.new Relatorio());
		veiculoModeloPainelCad.getTB().getRelatorioBtn().addActionListener(veiculoModeloControl.new Imprime());
		veiculoModeloPainelCad.getTB().getSalvarBtn().addActionListener(veiculoModeloControl.new Salva());
		veiculoModeloPainelCad.getTB().getHomeBtn().addActionListener(veiculoModeloControl.new Home());
		veiculoModeloPainelCad.getTB().getRegistrosBtn().addActionListener(veiculoModeloControl.new Registro());
		veiculoModeloPainelCad.getTB().getCsvBtn().addActionListener(veiculoModeloControl.new FormatoCsv());
		veiculoModeloPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setTitle("VEÍCULO - MODELO");
		setIconImage(Imagem.getLogoTipoImage());
		veiculoModeloPainelCad = new VeiculoModeloPainelCad();

		final JScrollPane scrollPane = new JScrollPane(veiculoModeloPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (veiculoModeloPainelCad.isAncestorOf(focused)) {
							veiculoModeloPainelCad.scrollRectToVisible(focused.getBounds());
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
		veiculoModeloPainelCad.reiniciarGui();
	}
}
