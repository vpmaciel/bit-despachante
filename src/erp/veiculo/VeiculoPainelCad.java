package erp.veiculo;

import javax.swing.BoxLayout;
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
public final class VeiculoPainelCad extends JPanel implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldData;
	private JTextField fieldPlaca;
	private JTextField fieldValor;
	private JTextField fieldDescricao;
	private JTextField fieldCpfCnpjCliente;
	private JTextField fieldNomeCliente;
	private JTextField fieldTelefoneCliente;
	private ToolBar toolBar;
	private VeiculoControl marcaControl;

	public VeiculoPainelCad() {
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

	public JTextField getGuiDescricao() {
		return fieldDescricao;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	public VeiculoControl getUsuarioCont() {
		return marcaControl;
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

		add(new JLabel("PLACA DO VEÍCULO"));

		fieldPlaca = new JTextField();
		fieldPlaca.setDocument(new EntradaMaiuscula(10));
		add(fieldPlaca);

		add(new JLabel("MARCA DO VEÍCULO"));

		fieldValor = new JTextField();
		fieldValor.setDocument(new EntradaMaiuscula(10));
		add(fieldValor);

		add(new JLabel("MODELO DO VEÍCULO"));

		fieldNomeCliente = new JTextField();
		fieldNomeCliente.setDocument(new EntradaMaiuscula(10));
		add(fieldNomeCliente);

		add(new JLabel("CPF | CNPJ DO PROPRIETÁRIO"));

		fieldCpfCnpjCliente = new JTextField();
		fieldCpfCnpjCliente.setDocument(new EntradaMaiuscula(10));
		add(fieldCpfCnpjCliente);

		add(new JLabel("NOME DO PROPRIETÁRIO"));

		fieldDescricao = new JTextField();
		fieldDescricao.setDocument(new EntradaMaiuscula(10));
		add(fieldDescricao);
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