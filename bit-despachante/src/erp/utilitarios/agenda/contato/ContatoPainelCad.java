package erp.utilitarios.agenda.contato;

import java.util.Collections;
import java.util.List;

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
import erp.negocio.empresa.Empresa;
import erp.negocio.empresa.EmpresaComp;
import erp.negocio.empresa.EmpresaFac;
import erp.sistema.main.MainControl;

@SuppressWarnings("serial")
public final class ContatoPainelCad extends JPanel implements Gui {

	private JComboBox<Empresa> boxEmpresa;
	private JComboBox<String> boxSexo;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldBairro;
	private JTextField fieldCep;
	private JTextField fieldCidade;
	private JTextField fieldCnpj;
	private JTextField fieldComplemento;
	private JTextField fieldCpf;
	private JTextField fieldEmail;
	private JTextField fieldEstado;
	private JTextField fieldFax;
	private JTextField fieldFone1;
	private JTextField fieldFone2;
	private JTextField fieldLogradouro;
	private JTextField fieldNome;
	private JTextField fieldPais;
	private JLabel labelEmpresa;
	private ToolBar toolBar;

	public ContatoPainelCad() {
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

	public JTextField getGuiCep() {
		return fieldCep;
	}

	public JTextField getGuiCidade() {
		return fieldCidade;
	}

	public JTextField getGuiCnpj() {
		return fieldCnpj;
	}

	public JTextField getGuiComplemento() {
		return fieldComplemento;
	}

	public JTextField getGuiCpf() {
		return fieldCpf;
	}

	public JTextField getGuiEmail() {
		return fieldEmail;
	}

	public JComboBox<Empresa> getGuiEmpresa() {
		return boxEmpresa;
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

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiPais() {
		return fieldPais;
	}

	public JComboBox<String> getGuiSexo() {
		return boxSexo;
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

		add(new JLabel("NOME"));

		fieldNome = new JTextField();
		fieldNome.setDocument(new EntradaMaiuscula(50));
		add(fieldNome);

		add(new JLabel("SEXO"));

		boxSexo = new JComboBox<String>();
		boxSexo.addItem("");
		boxSexo.addItem("MASCULINO");
		boxSexo.addItem("FEMININO");
		add(boxSexo);

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
		fieldCep.setDocument(new EntradaMaiuscula(9));
		add(fieldCep);

		labelEmpresa = new JLabel("TRABALHA NA EMPRESA");
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

		add(new JLabel("CPF"));

		fieldCpf = new JTextField();
		add(fieldCpf);

		add(new JLabel("CNPJ"));

		fieldCnpj = new JTextField();
		add(fieldCnpj);
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
		Empresa empresa = null;
		List<Empresa> empresas = (List<Empresa>) EmpresaFac.getRegistro();
		Collections.sort(empresas, new EmpresaComp().new NomeFantasia());
		boxEmpresa.removeAllItems();
		for (Empresa e : empresas) {
			boxEmpresa.addItem(e);
		}
		if (MainControl.getAgendaContatoJanCad().getContatoCont().getContato() != null) {
			empresa = MainControl.getAgendaContatoJanCad().getContatoCont().getContato().getEmpresa();
			boxEmpresa.setSelectedItem(empresa);
		}

	}
}
