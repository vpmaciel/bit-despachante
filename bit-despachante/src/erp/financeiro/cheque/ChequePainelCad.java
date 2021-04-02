package erp.financeiro.cheque;

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
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;
import erp.negocio.banco.Banco;
import erp.negocio.banco.BancoComp;
import erp.negocio.banco.BancoFac;
import erp.negocio.cliente.Cliente;
import erp.negocio.cliente.ClienteComp;
import erp.negocio.cliente.ClienteFac;

@SuppressWarnings("serial")
public final class ChequePainelCad extends JPanel implements Gui {

	private JComboBox<Banco> boxBancoCompensacao;
	private JComboBox<Cliente> boxCliente;
	private JComboBox<String> boxSituacao;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldAgenciaBancaria;
	private JTextField fieldCapitalSocial;
	private JTextField fieldCNPJ;
	private JTextField fieldContaCorrente;
	private JTextField fieldCPF;
	private JTextField fieldDataCompensacao;
	private JTextField fieldDataEntrada;
	private JTextField fieldDataVencimento;
	private JTextField fieldEmitenteCheque;
	private JTextField fieldInscricaoEstadual;
	private JTextField fieldInscricaoMunicipal;
	private JTextField fieldNumeroCheque;
	private JTextField fieldTelefoneEmitente;
	private JTextField fieldValorCheque;
	private ToolBar toolBar;

	public ChequePainelCad() {
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
		return fieldDataCompensacao;
	}

	public JTextField getGuiCapitalSocial() {
		return fieldCapitalSocial;
	}

	public JTextField getGuiCidade() {
		return fieldDataVencimento;
	}

	public JTextField getGuiCnpj() {
		return fieldCNPJ;
	}

	public JTextField getGuiCpf() {
		return fieldCPF;
	}

	public JTextField getGuiEstado() {
		return fieldValorCheque;
	}

	public JTextField getGuiFone1() {
		return fieldNumeroCheque;
	}

	public JTextField getGuiFone2() {
		return fieldEmitenteCheque;
	}

	public JTextField getGuiInscricaoEstadual() {
		return fieldInscricaoEstadual;
	}

	public JTextField getGuiInscricaoMunicipal() {
		return fieldInscricaoMunicipal;
	}

	public JComboBox<String> getGuiLogradouro() {
		return boxSituacao;
	}

	public JTextField getGuiNomeFantasia() {
		return fieldDataEntrada;
	}

	public JTextField getGuiNumeroFuncionarios() {
		return fieldContaCorrente;
	}

	public JTextField getGuiPais() {
		return fieldTelefoneEmitente;
	}

	public JTextField getGuiRamoAtividade() {
		return fieldAgenciaBancaria;
	}

	public JComboBox<Banco> getGuiRazaoSocial() {
		return boxBancoCompensacao;
	}

	public JComboBox<Cliente> getGuiTipoEmpresa() {
		return boxCliente;
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

		add(new JLabel("DATA DE ENTRADA"));

		fieldDataEntrada = new JTextField();
		fieldDataEntrada.setDocument(new EntradaMaiuscula(50));
		add(fieldDataEntrada);

		add(new JLabel("CLIENTE"));

		boxCliente = new JComboBox<Cliente>();
		List<Cliente> clienteList = (List<Cliente>) ClienteFac.getRegistro();
		Collections.sort(clienteList, new ClienteComp().new Nome());

		for (Cliente cliente : clienteList) {
			boxCliente.addItem(cliente);
		}
		add(boxCliente);

		add(new JLabel("BANCO COMPENSAÇÃO"));

		boxBancoCompensacao = new JComboBox<Banco>();
		List<Banco> bancoList = (List<Banco>) BancoFac.getRegistro();
		Collections.sort(bancoList, new BancoComp().new Nome());

		for (Banco banco : bancoList) {
			boxBancoCompensacao.addItem(banco);
		}
		add(boxBancoCompensacao);

		add(new JLabel("NÚMERO DA AGÊNCIA BANCÁRIA"));

		fieldAgenciaBancaria = new JTextField();
		fieldAgenciaBancaria.setDocument(new EntradaMaiuscula(50));
		add(fieldAgenciaBancaria);

		add(new JLabel("CONTA CORRENTE"));

		fieldContaCorrente = new JTextField();
		fieldContaCorrente.setDocument(new EntradaMaiuscula(6));
		add(fieldContaCorrente);

		add(new JLabel("NÚMERO DO CHEQUE"));

		fieldNumeroCheque = new JTextField();
		add(fieldNumeroCheque);

		add(new JLabel("EMITENTE DO CHEQUE"));

		fieldEmitenteCheque = new JTextField();
		add(fieldEmitenteCheque);

		add(new JLabel("CPF DO EMITENTE"));

		fieldCPF = new JTextField();
		add(fieldCPF);

		add(new JLabel("CNPJ DO EMITENTE"));

		fieldCNPJ = new JTextField();
		add(fieldCNPJ);

		add(new JLabel("TELEFONE DO EMITENTE"));

		fieldTelefoneEmitente = new JTextField();
		fieldTelefoneEmitente.setDocument(new EntradaMaiuscula(50));
		add(fieldTelefoneEmitente);

		add(new JLabel("VALOR DO CHEQUE"));

		fieldValorCheque = new JTextField();
		fieldValorCheque.setDocument(new EntradaMaiuscula(50));
		add(fieldValorCheque);

		add(new JLabel("DATA DO VENCIMENTO"));

		fieldDataVencimento = new JTextField();
		fieldDataVencimento.setDocument(new EntradaMaiuscula(50));
		add(fieldDataVencimento);

		add(new JLabel("DATA DE COMPENSAÇÃO"));

		fieldDataCompensacao = new JTextField();
		fieldDataCompensacao.setDocument(new EntradaMaiuscula(50));
		add(fieldDataCompensacao);

		add(new JLabel("SITUAÇÃO"));

		boxSituacao = new JComboBox<String>();
		boxSituacao.addItem("");
		boxSituacao.addItem("COMPENSADO");
		boxSituacao.addItem("DESCONTAR CHEQUE");
		boxSituacao.addItem("SEM FUNDO");
		boxSituacao.addItem("SUSTADO");
		boxSituacao.addItem("OUTROS");
		add(boxSituacao);

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
