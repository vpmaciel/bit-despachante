package erp.negocio.veiculo.marca;

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
public final class VeiculoMarcaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private VeiculoMarcaControl veiculoMarcaControl;
	private VeiculoMarcaPainelCad veiculoMarcaPainelCad;

	public VeiculoMarcaJanCad() {
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

	public VeiculoMarcaControl getVeiculoMarcaCont() {
		return veiculoMarcaControl;
	}

	public VeiculoMarcaPainelCad getVeiculoMarcaPainelCad() {
		return veiculoMarcaPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		veiculoMarcaControl = new VeiculoMarcaControl();
		addWindowListener(veiculoMarcaControl.new Frame());
		veiculoMarcaPainelCad.getTB().getExcluirBtn().addActionListener(veiculoMarcaControl.new ExcluiRegistro());
		veiculoMarcaPainelCad.getTB().getNovoBtn().addActionListener(veiculoMarcaControl.new NovoFrame());
		veiculoMarcaPainelCad.getTB().getPesquisarBtn().addActionListener(veiculoMarcaControl.new PesquisaRegistro());
		veiculoMarcaPainelCad.getTB().getImprimirBtn().addActionListener(veiculoMarcaControl.new Relatorio());
		veiculoMarcaPainelCad.getTB().getRelatorioBtn().addActionListener(veiculoMarcaControl.new Imprime());
		veiculoMarcaPainelCad.getTB().getSalvarBtn().addActionListener(veiculoMarcaControl.new Salva());
		veiculoMarcaPainelCad.getTB().getHomeBtn().addActionListener(veiculoMarcaControl.new Home());
		veiculoMarcaPainelCad.getTB().getRegistrosBtn().addActionListener(veiculoMarcaControl.new Registro());
		veiculoMarcaPainelCad.getTB().getCsvBtn().addActionListener(veiculoMarcaControl.new FormatoCsv());
		veiculoMarcaPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		veiculoMarcaPainelCad = new VeiculoMarcaPainelCad();

		final JScrollPane scrollPane = new JScrollPane(veiculoMarcaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (veiculoMarcaPainelCad.isAncestorOf(focused)) {
							veiculoMarcaPainelCad.scrollRectToVisible(focused.getBounds());
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
		veiculoMarcaPainelCad.reiniciarGui();
	}
}
