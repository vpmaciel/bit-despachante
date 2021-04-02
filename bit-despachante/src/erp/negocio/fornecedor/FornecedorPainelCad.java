package erp.negocio.fornecedor;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.EntradaMinuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class FornecedorPainelCad extends JPanel implements Gui {

	private JComboBox<String> boxTipoEmpresa;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldBairro;
	private JTextField fieldCep;
	private JTextField fieldCidade;
	private JTextField fieldCNPJ;
	private JTextField fieldComplemento;
	private JTextField fieldCPF;
	private JTextField fieldEmail;
	private JTextField fieldEstado;
	private JTextField fieldFax;
	private JTextField fieldFone1;
	private JTextField fieldFone2;
	private JTextField fieldLogradouro;
	private JTextField fieldNomeFantasia;
	private JTextField fieldPais;
	private JTextField fieldRazaoSocial;
	private ToolBar toolBar;

	public FornecedorPainelCad() {
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

	public JTextField getGuiBairro() {
		return fieldBairro;
	}

	public JTextField getGuiCep() {
		return fieldCep;
	}

	public JTextField getGuiCidade() {
		return fieldCidade;
	}

	public JTextField getGuiCnpj() {
		return fieldCNPJ;
	}

	public JTextField getGuiComplemento() {
		return fieldComplemento;
	}

	public JTextField getGuiCpf() {
		return fieldCPF;
	}

	public JTextField getGuiEmail() {
		return fieldEmail;
	}

	public JTextField getGuiEstado() {
		return fieldEstado;
	}

	public JTextField getGuiFax() {
		return fieldFax;
	}

	public JTextField getGuiFone1() {
		return fieldFone1;
	}

	public JTextField getGuiFone2() {
		return fieldFone2;
	}

	public JTextField getGuiLogradouro() {
		return fieldLogradouro;
	}

	public JTextField getGuiNomeFantasia() {
		return fieldNomeFantasia;
	}

	public JTextField getGuiPais() {
		return fieldPais;
	}

	public JTextField getGuiRazaoSocial() {
		return fieldRazaoSocial;
	}

	public JComboBox<String> getGuiTipoEmpresa() {
		return boxTipoEmpresa;
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

		add(new JLabel("NOME FANTASIA"));

		fieldNomeFantasia = new JTextField();
		fieldNomeFantasia.setDocument(new EntradaMaiuscula(50));
		add(fieldNomeFantasia);

		add(new JLabel("RAZÃO SOCIAL"));

		fieldRazaoSocial = new JTextField();
		fieldRazaoSocial.setDocument(new EntradaMaiuscula(50));
		add(fieldRazaoSocial);

		add(new JLabel("EMPRESA"));

		boxTipoEmpresa = new JComboBox<String>();
		boxTipoEmpresa.addItem("");
		boxTipoEmpresa.addItem("MATRIZ");
		boxTipoEmpresa.addItem("FILIAL");
		add(boxTipoEmpresa);

		add(new JLabel("TELEFONE"));

		fieldFone1 = new JTextField();
		add(fieldFone1);

		add(new JLabel("TELEFONE"));

		fieldFone2 = new JTextField();
		add(fieldFone2);

		add(new JLabel("FAX"));

		fieldFax = new JTextField();
		add(fieldFax);

		add(new JLabel("E-MAIL"));

		fieldEmail = new JTextField();
		fieldEmail.setDocument(new EntradaMinuscula(50));
		add(fieldEmail);

		add(new JLabel("CPF"));

		fieldCPF = new JTextField();
		add(fieldCPF);

		add(new JLabel("CNPJ"));

		fieldCNPJ = new JTextField();
		add(fieldCNPJ);

		add(new JLabel("PAÍS"));

		fieldPais = new JTextField();
		fieldPais.setDocument(new EntradaMaiuscula(50));
		add(fieldPais);

		add(new JLabel("ESTADO"));

		fieldEstado = new JTextField();
		fieldEstado.setDocument(new EntradaMaiuscula(50));
		add(fieldEstado);

		add(new JLabel("CIDADE"));

		fieldCidade = new JTextField();
		fieldCidade.setDocument(new EntradaMaiuscula(50));
		add(fieldCidade);

		add(new JLabel("BAIRRO"));

		fieldBairro = new JTextField();
		fieldBairro.setDocument(new EntradaMaiuscula(50));
		add(fieldBairro);

		add(new JLabel("LOGRADOURO"));

		fieldLogradouro = new JTextField();
		fieldLogradouro.setDocument(new EntradaMaiuscula(50));
		add(fieldLogradouro);

		add(new JLabel("COMPLEMENTO"));

		fieldComplemento = new JTextField();
		fieldComplemento.setDocument(new EntradaMaiuscula(20));
		add(fieldComplemento);

		add(new JLabel("CEP"));

		fieldCep = new JTextField();
		add(fieldCep);
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

	}

	@Override
	public void reiniciarGui() {

	}
}
