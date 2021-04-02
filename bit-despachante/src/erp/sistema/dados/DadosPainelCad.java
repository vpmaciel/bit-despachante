package erp.sistema.dados;

import javax.swing.BoxLayout;
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
public final class DadosPainelCad extends JPanel implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldCnpj;
	private JTextField fieldCpf;
	private JTextField fieldCrc;
	private JTextField fieldEmail;
	private JTextField fieldFax;
	private JTextField fieldFone1;
	private JTextField fieldFone2;
	private JTextField fieldNome;
	private JTextField fieldSite;
	private ToolBar toolBar;

	public DadosPainelCad() {
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

	public JTextField getGuiCnpj() {
		return fieldCnpj;
	}

	public JTextField getGuiCpf() {
		return fieldCpf;
	}

	public JTextField getGuiCrc() {
		return fieldCrc;
	}

	public JTextField getGuiEmail() {
		return fieldEmail;
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

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiSite() {
		return fieldSite;
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

		add(new JLabel("CNPJ/CPF"));

		fieldCnpj = new JTextField();
		add(fieldCnpj);

		add(new JLabel("TELEFONE"));

		fieldFone1 = new JTextField();
		add(fieldFone1);

		add(new JLabel("E-MAIL"));

		fieldEmail = new JTextField();
		fieldEmail.setDocument(new EntradaMinuscula(50));
		add(fieldEmail);
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
