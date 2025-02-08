package erp.cliente;

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
public final class ClientePainelCad extends JPanel implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldNome;
	private JTextField fieldCpfCnpj;
	private JTextField fieldTelefone;
	private JTextField fieldEmail;
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

	public JTextField getGuiCpfCnpj() {
		return fieldCpfCnpj;
	}

	public JTextField getGuiEmail() {
		return fieldEmail;
	}

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiTelefone() {
		return fieldTelefone;
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

		add(new JLabel("CPF | CNPJ"));

		fieldCpfCnpj = new JTextField();
		fieldCpfCnpj.setDocument(new EntradaMaiuscula(20));
		add(fieldCpfCnpj);

		add(new JLabel("E-MAIL"));

		fieldEmail = new JTextField();
		fieldEmail.setDocument(new EntradaMaiuscula(70));
		add(fieldEmail);

		add(new JLabel("TELEFONE"));

		fieldTelefone = new JTextField();
		fieldTelefone.setDocument(new EntradaMaiuscula(15));
		add(fieldTelefone);

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