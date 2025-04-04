package erp.sistema.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import erp.arquitetura.Senha;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainController;
import erp.usuario.Usuario;
import erp.usuario.UsuarioFac;

public class LoginControl {

    public class ButtonEntrar implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent actionEvent) {

	    if (!entradaValida()) {
		return;
	    }

	    // SE O USUÁRIO NÃO EXISTIR NO DATABASE CRIA NOVO USUÁRIO

	    if (!temUsuarioAdmin()) {
		usuario = new Usuario();
		usuario.setNome("ADMIN");
		usuario.setSenha(Senha.criptografar("ADMIN"));
		usuario.setEmail("ADMIN");
		UsuarioFac.salvarRegistro(usuario);
		JOptionPane.showMessageDialog(null, "Usuário criado !\nUsuário:  ADMIN\nSenha: ADMIN", "Informação",
			JOptionPane.INFORMATION_MESSAGE);
		logger.warn("USUÁRIO ADMIN CRIADO");
	    }

	    // CARREGA O USUÁRIO DE ACORDO COM A ENTRADA NA TELA DE LOGIN

	    usuario = lerEntrada();

	    if (UsuarioFac.isRegistroValido(usuario)) {
		List<Usuario> list = (List<Usuario>) UsuarioFac.pesquisarRegistro(usuario);
		Sis.setUsuario(list.get(0));
		MainController.getLoginJan().setVisible(false);
		MainController.getMainJan().toFront();
		logger.info("LOGIN EFETUADO");

	    } else {
		Msg.avisoUsuarioInvalido();
		MainController.getLoginJan().toFront();
		logger.error("USUÁRIO INVÁLIDO");
		++tentativas;

		if (tentativas >= LoginControl.MAXIMO_LOGIN_TENTATIVAS) {
		    Msg.avisoFecharSistema();
		    System.exit(0);
		}
	    }
	}
    }

    public class ButtonEntrarTeclado extends KeyAdapter {

	@Override
	public void keyPressed(final KeyEvent evt) {
	    final int key = evt.getKeyCode();
	    if (key == KeyEvent.VK_ENTER) {
		evt.getComponent().requestFocus();
		entradaValida();
	    }
	}
    }

    public class Frame extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
	    MainController.getLoginJan().setVisible(false);
	}
    }

    public boolean entradaValida() {
	if (MainController.getLoginJan().getLoginPainelCad().getGUINome().getText().length() == 0) {
	    JOptionPane.showMessageDialog(null, "Preencha o campo usuário !");
	    MainController.getLoginJan().getLoginPainelCad().getGUINome().requestFocus();
	    return false;
	}

	if (new String(MainController.getLoginJan().getLoginPainelCad().getGUISenha().getPassword()).length() == 0) {
	    JOptionPane.showMessageDialog(null, "Preencha o campo senha !");
	    MainController.getLoginJan().getLoginPainelCad().getGUISenha().requestFocus();
	    return false;
	}

	return true;
    }

    public Usuario lerEntrada() {
	usuario = new Usuario();
	usuario.setNome(MainController.getLoginJan().getLoginPainelCad().getGUINome().getText());
	char[] passwordChars = MainController.getLoginJan().getLoginPainelCad().getGUISenha().getPassword();
	String password = new String(passwordChars); // Converte para String
	usuario.setSenha(Senha.criptografar(password));
	return usuario;
    }

    public boolean temUsuarioAdmin() {
	List<Usuario> list = (List<Usuario>) UsuarioFac.pesquisarRegistro(new Usuario());

	for (Usuario usuarioIterator : list) {
	    if (usuarioIterator.getNome().equals("ADMIN"))
		return true;
	}
	return false;
    }

    final static Logger logger = Logger.getLogger(Usuario.class);

    private static final int MAXIMO_LOGIN_TENTATIVAS = 3;

    public static int getMaximoLoginTentativas() {
	return LoginControl.MAXIMO_LOGIN_TENTATIVAS;
    }

    public static LoginJan getLoginJan() {
	return MainController.getLoginJan();
    }

    public LoginControl() {
	// logger.trace("Mensagem de TRACE");
	// logger.debug("Mensagem de DEBUG");
	// logger.info("Mensagem de INFO");
	// logger.warn("Mensagem de WARN");
	// logger.error("Mensagem de ERROR");
	// logger.fatal("Mensagem de FATAL");
    }

    private int tentativas = 0;
    private Usuario usuario = new Usuario();

}
