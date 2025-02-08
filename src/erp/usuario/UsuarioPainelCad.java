package erp.usuario;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class UsuarioPainelCad extends JPanel implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldNome;
	private JTextField fieldSenha;
	private JTextField fieldEmail;
	private ToolBar toolBar;
	private UsuarioControl usuarioControl;

	public UsuarioPainelCad() {
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

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JTextField getGuiSenha() {
		return fieldSenha;
	}
	
	public JTextComponent getGuiEmail() {
		return fieldEmail;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	public UsuarioControl getUsuarioCont() {
		return usuarioControl;
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
		
		add(new JLabel("EMAIL"));

		fieldEmail = new JTextField();
		fieldEmail.setDocument(new EntradaMaiuscula(100));
		add(fieldEmail);

		add(new JLabel("SENHA"));

		fieldSenha = new JTextField();
		fieldSenha.setDocument(new EntradaMaiuscula(10));
		add(fieldSenha);
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
