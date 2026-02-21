package erp.sistema.login;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;

@SuppressWarnings("serial")
public final class LoginPainel extends JPanel implements Gui {

    private JButton buttonEntrar;
    private ConfiguracaoGui configuracaoGui;
    private JTextField fieldNome;
    private JPasswordField fieldSenha;

    public LoginPainel() {
	iniciarLayout();
	iniciarGui();
	iniciarFocoControlador();
	iniciarGuiControlador();
    }

    public void atualizarLabel() {
    }

    @Override
    public void atualizarTable() {
    }

    public JButton getButtonEntrar() {
	return buttonEntrar;
    }

    @Override
    public ConfiguracaoGui getConfiguracaoGui() {
	return configuracaoGui;
    }

    public JTextField getGUINome() {
	return fieldNome;
    }

    public JPasswordField getGUISenha() {
	return fieldSenha;
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

	add(new JLabel("USUÁRIO"));

	fieldNome = new JTextField(50);
	fieldNome.setDocument(new EntradaMaiuscula(50));
	fieldNome.requestFocus();
	fieldNome.setText("ADMIN");
	add(fieldNome);

	add(new JLabel("SENHA"));

	fieldSenha = new JPasswordField(50);
	fieldSenha.setDocument(new EntradaMaiuscula(8));
	fieldSenha.setText("ADMIN");
	add(fieldSenha);

	add(new JLabel(""));

	buttonEntrar = new JButton("Entrar");
	buttonEntrar.setPreferredSize(new Dimension(100, 30));
	buttonEntrar.setMinimumSize(new Dimension(100, 30));
	buttonEntrar.setSize(new Dimension(100, 30));
	buttonEntrar.setMaximumSize(new Dimension(100, 30));

	add(buttonEntrar);
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
