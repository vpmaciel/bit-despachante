package erp.financeiro.vendas.servico;

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
public final class VendaServicoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private VendaServicoControl vendaServicoControl;
	private VendaServicoPainelCad vendaServicoPainelCad;

	public VendaServicoJanCad() {
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

	public VendaServicoPainelCad getPanelVendaServicoCadastro() {
		return vendaServicoPainelCad;
	}

	public VendaServicoControl getVendaServicoCont() {
		return vendaServicoControl;
	}

	public VendaServicoPainelCad getVendaServicoPainelCad() {
		return vendaServicoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		vendaServicoControl = new VendaServicoControl();
		addWindowListener(vendaServicoControl.new Frame());
		vendaServicoPainelCad.getTB().getExcluirBtn().addActionListener(vendaServicoControl.new Exclui());
		vendaServicoPainelCad.getTB().getNovoBtn().addActionListener(vendaServicoControl.new Novo());
		vendaServicoPainelCad.getTB().getPesquisarBtn().addActionListener(vendaServicoControl.new Pesquisa());
		vendaServicoPainelCad.getTB().getImprimirBtn().addActionListener(vendaServicoControl.new Imprime());
		vendaServicoPainelCad.getTB().getRelatorioBtn().addActionListener(vendaServicoControl.new Relatorio());
		vendaServicoPainelCad.getTB().getSalvarBtn().addActionListener(vendaServicoControl.new Salva());
		vendaServicoPainelCad.getTB().getHomeBtn().addActionListener(vendaServicoControl.new Home());
		vendaServicoPainelCad.getTB().getRegistrosBtn().addActionListener(vendaServicoControl.new Registro());
		vendaServicoPainelCad.getTB().getCsvBtn().addActionListener(vendaServicoControl.new FormatoCsv());
		vendaServicoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		vendaServicoPainelCad = new VendaServicoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(vendaServicoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (vendaServicoPainelCad.isAncestorOf(focused)) {
							vendaServicoPainelCad.scrollRectToVisible(focused.getBounds());
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
		vendaServicoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		vendaServicoPainelCad.reiniciarGui();
	}
}
