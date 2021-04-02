package erp.escritorio.veiculo.pedido.placa;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class PedidoPlacaPainelCad extends JPanel implements Gui {

	private JComboBox<String> boxFazerPlaca;
	private JComboBox<String> boxFazerTarjeta;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldCidade;
	private JTextField fieldCor;
	private JTextField fieldEstado;
	private JTextField fieldNome;
	private JTextField fieldPlaca;
	private JTextField fieldRenavam;
	private PedidoPlacaControl pedidoPlacaControl;
	private ToolBar toolBar;

	public PedidoPlacaPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public JTextField getGuiCidade() {
		return fieldCidade;
	}

	public JTextField getGuiCor() {
		return fieldCor;
	}

	public JTextField getGuiEstado() {
		return fieldEstado;
	}

	public JComboBox<String> getGuiFazerPlaca() {
		return boxFazerPlaca;
	}

	public JComboBox<String> getGuiFazerTarjeta() {
		return boxFazerTarjeta;
	}

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiPlaca() {
		return fieldPlaca;
	}

	public JTextField getGuiRenavam() {
		return fieldRenavam;
	}

	public PedidoPlacaControl getPedidoPlacaCont() {
		return pedidoPlacaControl;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	@Override
	public void iniciarControlador() {
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		toolBar = new ToolBar();

		add(toolBar.getTB());

		add(new JLabel("NOME"));

		fieldNome = new JTextField();
		fieldNome.setDocument(new EntradaMaiuscula(50));
		add(fieldNome);

		add(new JLabel("PLACA"));

		fieldPlaca = new JTextField();
		fieldPlaca.setDocument(new EntradaMaiuscula(50));
		add(fieldPlaca);

		add(new JLabel("RENAVAM"));

		fieldRenavam = new JTextField();
		fieldRenavam.setDocument(new EntradaMaiuscula(50));
		add(fieldRenavam);

		add(new JLabel("CIDADE"));

		fieldCidade = new JTextField();
		fieldCidade.setDocument(new EntradaMaiuscula(50));
		add(fieldCidade);

		add(new JLabel("ESTADO"));

		fieldEstado = new JTextField();
		fieldEstado.setDocument(new EntradaMaiuscula(50));
		add(fieldEstado);

		add(new JLabel("COR"));

		fieldCor = new JTextField();
		fieldCor.setDocument(new EntradaMaiuscula(50));
		add(fieldCor);

		add(new JLabel("FAZER PLACA"));

		boxFazerPlaca = new JComboBox<String>();
		boxFazerPlaca.addItem("");
		boxFazerPlaca.addItem("AGUARDANDO");
		add(boxFazerPlaca);

		add(new JLabel("FAZER TARJETA"));

		boxFazerTarjeta = new JComboBox<String>();
		boxFazerTarjeta.addItem("");
		boxFazerTarjeta.addItem("AGUARDANDO");
		boxFazerTarjeta.addItem("PROCESSANDO");
		add(boxFazerTarjeta);

	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setBorder(Sis.getBordaPainel());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

	}
}
