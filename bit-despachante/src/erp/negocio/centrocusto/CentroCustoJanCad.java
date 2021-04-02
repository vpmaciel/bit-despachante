package erp.negocio.centrocusto;

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
public final class CentroCustoJanCad extends JFrame implements Gui {

	private CentroCustoControl centroCustoControl;
	private CentroCustoPainelCad centroCustoPainelCad;
	private ConfiguracaoGui configuracaoGui;

	public CentroCustoJanCad() {
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

	public CentroCustoControl getCentroCustoCont() {
		return centroCustoControl;
	}

	public CentroCustoPainelCad getCentroCustoPainelCad() {
		return centroCustoPainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public CentroCustoPainelCad getPanelCentroCustoCadastro() {
		return centroCustoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		centroCustoControl = new CentroCustoControl();
		addWindowListener(centroCustoControl.new Frame());
		centroCustoPainelCad.getTB().getExcluirBtn().addActionListener(centroCustoControl.new Exclui());
		centroCustoPainelCad.getTB().getNovoBtn().addActionListener(centroCustoControl.new Novo());
		centroCustoPainelCad.getTB().getPesquisarBtn().addActionListener(centroCustoControl.new Pesquisa());
		centroCustoPainelCad.getTB().getImprimirBtn().addActionListener(centroCustoControl.new Imprime());
		centroCustoPainelCad.getTB().getRelatorioBtn().addActionListener(centroCustoControl.new Relatorio());
		centroCustoPainelCad.getTB().getSalvarBtn().addActionListener(centroCustoControl.new Salva());
		centroCustoPainelCad.getTB().getHomeBtn().addActionListener(centroCustoControl.new Home());
		centroCustoPainelCad.getTB().getRegistrosBtn().addActionListener(centroCustoControl.new Registro());
		centroCustoPainelCad.getTB().getCsvBtn().addActionListener(centroCustoControl.new FormatoCsv());
		centroCustoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		centroCustoPainelCad = new CentroCustoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(centroCustoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (centroCustoPainelCad.isAncestorOf(focused)) {
							centroCustoPainelCad.scrollRectToVisible(focused.getBounds());
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
		centroCustoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		centroCustoPainelCad.reiniciarGui();
	}
}
