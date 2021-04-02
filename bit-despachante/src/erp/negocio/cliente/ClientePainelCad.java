package erp.negocio.cliente;

import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import erp.negocio.empresa.Empresa;
import erp.negocio.empresa.EmpresaComp;
import erp.negocio.empresa.EmpresaFac;
import erp.sistema.main.MainControl;

@SuppressWarnings("serial")
public final class ClientePainelCad extends JPanel implements Gui {

	private JComboBox<String> boxDeficiencia;
	private JComboBox<Empresa> boxEmpresa;
	private JComboBox<String> boxEscolaridade;
	private JComboBox<String> boxEstadoCivil;
	private JComboBox<String> boxSexo;
	private JComboBox<String> boxStatus;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldBairro;
	private JTextField fieldCargo;
	private JTextField fieldCep;
	private JTextField fieldCidade;
	private JTextField fieldCNPJ;
	private JTextField fieldComplemento;
	private JTextField fieldCPF;
	private JTextField fieldDataNascimento;
	private JTextField fieldEmail;
	private JTextField fieldEstado;
	private JTextField fieldFone1;
	private JTextField fieldFone2;
	private JTextField fieldLogradouro;
	private JTextField fieldNome;
	private JTextField fieldPais;
	private JTextField fieldRGNumero;
	private JTextField fieldRGOrgaoEmisssor;
	private JTextField fieldSalario;
	private JLabel labelEmpresa;
	private ToolBar toolBar;

	public ClientePainelCad() {
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

	public JTextField getGuiBairro() {
		return fieldBairro;
	}

	public JTextField getGuiCargo() {
		return fieldCargo;
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

	public JTextField getGuiDataNascimento() {
		return fieldDataNascimento;
	}

	public JComboBox<String> getGuiDeficiencia() {
		return boxDeficiencia;
	}

	public JTextField getGuiEmail() {
		return fieldEmail;
	}

	public JComboBox<Empresa> getGuiEmpresa() {
		return boxEmpresa;
	}

	public JComboBox<String> getGuiEscolaridade() {
		return boxEscolaridade;
	}

	public JTextField getGuiEstado() {
		return fieldEstado;
	}

	public JComboBox<String> getGuiEstadoCivil() {
		return boxEstadoCivil;
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

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiPais() {
		return fieldPais;
	}

	public JTextField getGuiRGNumero() {
		return fieldRGNumero;
	}

	public JTextField getGuiRGOrgaoEmisssor() {
		return fieldRGOrgaoEmisssor;
	}

	public JTextField getGuiSalario() {
		return fieldSalario;
	}

	public JComboBox<String> getGuiSexo() {
		return boxSexo;
	}

	public JComboBox<String> getGuiStatus() {
		return boxStatus;
	}

	public JLabel getLabelEmpresa() {
		return labelEmpresa;
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

		add(new JLabel("NOME DO CLIENTE"));

		fieldNome = new JTextField();
		fieldNome.setDocument(new EntradaMaiuscula(50));
		add(fieldNome);

		add(new JLabel("ESTADO CIVIL"));

		boxEstadoCivil = new JComboBox<String>();
		boxEstadoCivil.addItem("");
		boxEstadoCivil.addItem("SOLTEIRO (A)");
		boxEstadoCivil.addItem("CASADO (A)");
		boxEstadoCivil.addItem("SEPARADO (A)");
		boxEstadoCivil.addItem("DIVORCIADO (A)");
		boxEstadoCivil.addItem("VIÚVO (A)");
		add(boxEstadoCivil);

		add(new JLabel("SEXO"));

		boxSexo = new JComboBox<String>();
		boxSexo.addItem("");
		boxSexo.addItem("MASCULINO");
		boxSexo.addItem("FEMININO");
		add(boxSexo);

		add(new JLabel("DEFICIÊNCIA"));

		boxDeficiencia = new JComboBox<String>();
		boxDeficiencia.addItem("");
		boxDeficiencia.addItem("NÃO POSSUI DEFICIÊNCIA");
		boxDeficiencia.addItem("POSSUI DEFICIÊNCIA");
		add(boxDeficiencia);

		add(new JLabel("ESCOLARIDADE"));

		boxEscolaridade = new JComboBox<String>();
		boxEscolaridade.addItem("");
		boxEscolaridade.addItem("PÓS DOUTORADO");
		boxEscolaridade.addItem("DOUTORADO");
		boxEscolaridade.addItem("MESTRADO");
		boxEscolaridade.addItem("PÓS GRADUAÇÃO");
		boxEscolaridade.addItem("SUPERIOR COMPLETO");
		boxEscolaridade.addItem("SUPERIOR INCOMPLETO");
		boxEscolaridade.addItem("SEGUNDO GRAU COMPLETO");
		boxEscolaridade.addItem("SEGUNDO GRAU INCOMPLETO");
		boxEscolaridade.addItem("PRIMEIRO GRAU COMPLETO");
		boxEscolaridade.addItem("PRIMEIRO GRAU INCOMPLETO");
		add(boxEscolaridade);

		add(new JLabel("DATA DE NASCIMENTO"));

		fieldDataNascimento = new JFormattedTextField();
		add(fieldDataNascimento);

		add(new JLabel("TELEFONE"));

		fieldFone1 = new JTextField();
		add(fieldFone1);

		add(new JLabel("TELEFONE"));

		fieldFone2 = new JTextField();
		add(fieldFone2);

		add(new JLabel("E-MAIL"));

		fieldEmail = new JTextField();
		fieldEmail.setDocument(new EntradaMinuscula(50));
		add(fieldEmail);

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

		add(new JLabel("CARGO"));

		fieldCargo = new JTextField();
		fieldCargo.setDocument(new EntradaMaiuscula(50));
		add(fieldCargo);

		labelEmpresa = new JLabel("EMPRESA");
		labelEmpresa.setCursor(Sis.getNovaJanelaCursor());
		add(labelEmpresa);

		boxEmpresa = new JComboBox<Empresa>();
		List<Empresa> empresas = (List<Empresa>) EmpresaFac.getRegistro();
		Collections.sort(empresas, new EmpresaComp().new NomeFantasia());
		for (Empresa empresa : empresas) {
			boxEmpresa.addItem(empresa);
		}
		boxEmpresa.setMaximumRowCount(5);
		add(boxEmpresa);

		add(new JLabel("SALÁRIO"));

		fieldSalario = new JTextField();
		fieldSalario.setDocument(new EntradaMaiuscula(10));
		add(fieldSalario);

		add(new JLabel("CPF"));

		fieldCPF = new JTextField();
		add(fieldCPF);

		add(new JLabel("IDENTIDADE NÚMERO"));

		fieldRGNumero = new JTextField();
		fieldRGNumero.setDocument(new EntradaMaiuscula(15));
		add(fieldRGNumero);

		add(new JLabel("IDENTIDADE ÓRGÃO EMISSOR"));

		fieldRGOrgaoEmisssor = new JTextField();
		fieldRGOrgaoEmisssor.setDocument(new EntradaMaiuscula(20));
		add(fieldRGOrgaoEmisssor);

		add(new JLabel("CNPJ"));

		fieldCNPJ = new JTextField();
		add(fieldCNPJ);

		add(new JLabel("STATUS"));

		boxStatus = new JComboBox<String>();
		boxStatus.addItem("");
		boxStatus.addItem("ATIVO");
		boxStatus.addItem("INATIVO");
		add(boxStatus);
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
		Empresa empresa = null;
		List<Empresa> empresas = (List<Empresa>) EmpresaFac.getRegistro();
		Collections.sort(empresas, new EmpresaComp().new NomeFantasia());
		boxEmpresa.removeAllItems();
		for (Empresa b : empresas) {
			boxEmpresa.addItem(b);
		}
		if (MainControl.getClienteJanCad().getClienteCont().getCliente() != null) {
			empresa = MainControl.getClienteJanCad().getClienteCont().getCliente().getEmpresa();
			boxEmpresa.setSelectedItem(empresa);
		}
	}
}
