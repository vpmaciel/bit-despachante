package erp.escritorio.veiculo.pedido.placa;

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
public final class PedidoPlacaJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private PedidoPlacaControl pedidoPlacaControl;
	private PedidoPlacaPainelCad pedidoPlacaPainelCad;

	public PedidoPlacaJanCad() {
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

	public PedidoPlacaPainelCad getPanelPedidoPlacaCadastro() {
		return pedidoPlacaPainelCad;
	}

	public PedidoPlacaControl getPedidoPlacaCont() {
		return pedidoPlacaControl;
	}

	public PedidoPlacaPainelCad getPedidoPlacaPainelCad() {
		return pedidoPlacaPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		pedidoPlacaControl = new PedidoPlacaControl();
		addWindowListener(pedidoPlacaControl.new Frame());
		pedidoPlacaPainelCad.getTB().getExcluirBtn().addActionListener(pedidoPlacaControl.new Exclui());
		pedidoPlacaPainelCad.getTB().getNovoBtn().addActionListener(pedidoPlacaControl.new Novo());
		pedidoPlacaPainelCad.getTB().getPesquisarBtn().addActionListener(pedidoPlacaControl.new Pesquisa());
		pedidoPlacaPainelCad.getTB().getImprimirBtn().addActionListener(pedidoPlacaControl.new Imprime());
		pedidoPlacaPainelCad.getTB().getRelatorioBtn().addActionListener(pedidoPlacaControl.new Relatorio());
		pedidoPlacaPainelCad.getTB().getSalvarBtn().addActionListener(pedidoPlacaControl.new Salva());
		pedidoPlacaPainelCad.getTB().getHomeBtn().addActionListener(pedidoPlacaControl.new Home());
		pedidoPlacaPainelCad.getTB().getRegistrosBtn().addActionListener(pedidoPlacaControl.new Registro());
		pedidoPlacaPainelCad.getTB().getCsvBtn().addActionListener(pedidoPlacaControl.new FormatoCsv());
		pedidoPlacaPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		pedidoPlacaPainelCad = new PedidoPlacaPainelCad();

		final JScrollPane scrollPane = new JScrollPane(pedidoPlacaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (pedidoPlacaPainelCad.isAncestorOf(focused)) {
							pedidoPlacaPainelCad.scrollRectToVisible(focused.getBounds());
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
		pedidoPlacaPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		pedidoPlacaPainelCad.reiniciarGui();
	}
}
