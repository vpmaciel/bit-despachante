package erp.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainController;

final class UsuarioControl {

    public class Exclui implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    if ((usuario == null) || (usuario.getNome() == null)
		    || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
		return;
	    }
	    try {
		UsuarioFac.deletarRegistro(usuario);
		getCienteJanCad().limparGui();
		usuario = new Usuario();
		Msg.sucessoExcluiRegistro();
	    } catch (Exception e) {
		Msg.erroExcluiRegistro();
	    }
	}
    }

    public class FechaJanela implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {
		getCienteJanCad().setVisible(false);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class FormatoCsv implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Usuario> listConta = new LinkedList<>();

	    try {
		listConta = new LinkedList<>(UsuarioFac.pesquisarRegistro(new Usuario()));
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    UsuarioArqCsv usuarioArqCsv = new UsuarioArqCsv(listConta);
	    usuarioArqCsv.retornarArquivo(true);
	    Sis.abrirDiretorio();

	}
    }

    public class FormatoJson implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		ArquivoJson<Usuario> arquivoJson = new ArquivoJson<>(usuario, "usuario");
		arquivoJson.gravarArquivo(UsuarioFac.getRegistro());
		arquivoJson.retornarArquivo(true);
		Sis.abrirDiretorio();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

    public class Frame extends WindowAdapter {

	@Override
	public void windowActivated(WindowEvent e) {
	    getCienteJanCad().reiniciarGui();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	    getCienteJanCad().setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	    usuario = new Usuario();
	}
    }

    public class Home implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {
		MainController.mostrarFrame(MainController.getMainJan());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class Imprime implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    List<Usuario> usuarios = new LinkedList<>();

	    if (usuario.getNome() == null) {
		Msg.avisoImprimiRegistroNaoCadastrado();
		return;
	    }
	    if (usuarios.add(UsuarioFac.getRegistro(usuario))) {
		UsuarioRel usuarioRel = new UsuarioRel(usuarios);
		usuarioRel.retornarRelatorio();
	    }
	}
    }

    public class MostraFrameConta extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent event) {
	    if (event.getButton() == MouseEvent.BUTTON1) {
		MainController.mostrarFrame(MainController.getUsuarioJan());
	    } else {
		MainController.getUsuarioJan().reiniciarGui();
	    }
	}
    }

    public class Novo implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    usuario = new Usuario();
	    getCienteJanCad().limparGui();
	    getUsuarioPainelCad().getGuiNome().requestFocus();
	}
    }

    public class Pesquisa implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    usuario = new Usuario();
	    atualizarObjeto();

	    long totalPesquisaRegistro = 0;

	    totalPesquisaRegistro = MainController.getUsuarioJan().getUsuarioPainelPesq().pesquisarRegistro(usuario);
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getUsuarioJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Registro implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    long totalPesquisaRegistro = 0;

	    totalPesquisaRegistro = MainController.getUsuarioJan().getUsuarioPainelPesq()
		    .pesquisarRegistro(new Usuario());
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getUsuarioJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Relatorio implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Usuario> usuarioList = new LinkedList<>();

	    try {
		usuarioList = new LinkedList<>(UsuarioFac.pesquisarRegistro(new Usuario()));
		if (usuarioList.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Sem registros para gerar relatório !", "Aviso",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    UsuarioRel usuarioRel = new UsuarioRel(usuarioList);
	    usuarioRel.retornarRelatorio();

	}
    }

    public class Salva implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		int mensagem = Msg.confirmarSalvarRegistro();

		if (((getUsuarioPainelCad().getGuiNome().getText()) == null)
			|| (getUsuarioPainelCad().getGuiNome().getText().length() == 0)) {
		    getUsuarioPainelCad().getGuiNome().requestFocus();
		    Msg.avisoCampoObrigatorio("NOME");
		    return;
		}

		if (mensagem == JOptionPane.YES_OPTION) {
		    atualizarObjeto();
		    UsuarioFac.salvarRegistro(usuario);
		    usuario = new Usuario();
		    MainController.getUsuarioJan().limparGui();
		    Msg.sucessoSalvarRegistro();
		    getUsuarioPainelCad().getGuiNome().requestFocus();
		}

	    } catch (Exception e) {
		Throwable throwable = e.getCause().getCause();
		String mensagem = throwable.toString();
		if (mensagem.contains("ConstraintViolationException")) {
		    if (mensagem.contains("INDEX_CLIENTE_CPF_CNPJ")) {
			Msg.avisoCampoDuplicado("CPF | CNPJ");
			getUsuarioPainelCad().getGuiNome().requestFocus();
		    }
		}
		e.printStackTrace();
		Msg.erroSalvarRegistro();
	    }
	}
    }

    private Usuario usuario;

    UsuarioControl() {
    }

    public void atualizarGui() {
	if (usuario == null) {
	    return;
	}
	getUsuarioPainelCad().getGuiEmail().setText(usuario.getEmail());
	getUsuarioPainelCad().getGuiSenha().setText(usuario.getSenha());
	getUsuarioPainelCad().getGuiNome().setText(usuario.getNome());

    }

    public void atualizarObjeto() {
	if (usuario == null) {
	    usuario = new Usuario();
	}
	usuario.setSenha(getUsuarioPainelCad().getGuiSenha().getText());
	usuario.setEmail(getUsuarioPainelCad().getGuiEmail().getText());
	usuario.setNome(getUsuarioPainelCad().getGuiNome().getText());

    }

    public UsuarioJan getCienteJanCad() {
	return MainController.getUsuarioJan();
    }

    public UsuarioPainelCad getUsuarioPainelCad() {
	return MainController.getUsuarioJan().getUsuarioPainelCad();
    }

    public Usuario getConta() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }
}