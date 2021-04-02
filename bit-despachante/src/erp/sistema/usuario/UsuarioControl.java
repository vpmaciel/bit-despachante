package erp.sistema.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.gui.Msg;
import erp.arquitetura.validacao.Entrada;
import erp.arquitetura.validacao.RegExp;
import erp.sistema.main.MainControl;

final class UsuarioControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((usuario == null) || (usuario.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				UsuarioFac.deletarRegistro(usuario);
				getUsuarioJanCad().limparGui();
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
				getUsuarioJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getUsuarioJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getUsuarioJanCad().setVisible(false);
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
				MainControl.mostrarFrame(MainControl.getMainJanCad());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Usuario> usuarios = new LinkedList<>();

			if (usuario.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (usuarios.add(UsuarioFac.getRegistro(usuario))) {
				UsuarioRel usuarioRel = new UsuarioRel(usuarios);
				usuarioRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrameUsuario extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getUsuarioJanCad());
			} else {
				MainControl.getUsuarioJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			usuario = new Usuario();
			getUsuarioJanCad().limparGui();
			getUsuarioPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getUsuarioPainelPesq().pesquisarRegistro(usuario);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getUsuarioJanPesq());
				getUsuarioJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getUsuarioPainelPesq().pesquisarRegistro(new Usuario());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getUsuarioJanPesq());
				getUsuarioJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Usuario> usuarios = new LinkedList<>();

			try {
				usuarios = new LinkedList<>(UsuarioFac.pesquisarRegistro(new Usuario()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			UsuarioRel usuarioRel = new UsuarioRel(usuarios);
			usuarioRel.retornarRelatorio(true);

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if (mensagem != JOptionPane.YES_OPTION) {
					return;
				}

				if (!Entrada.validar(getUsuarioPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (!Entrada.validar(getUsuarioPainelCad().getGuiSenha(), "CÓDIGO", RegExp.NUMERO_BANCO, false)) {
					return;
				}

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
					MainControl.getUsuarioJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getUsuarioPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_USUARIO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getUsuarioPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
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
		getUsuarioPainelCad().getGuiNome().setText(usuario.getNome());
		getUsuarioPainelCad().getGuiSenha().setText(usuario.getSenha());
	}

	public void atualizarObjeto() {

		usuario.setSenha(getUsuarioPainelCad().getGuiSenha().getText());
		usuario.setNome(getUsuarioPainelCad().getGuiNome().getText());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public UsuarioJanCad getUsuarioJanCad() {
		return MainControl.getUsuarioJanCad();
	}

	public UsuarioJanPesq getUsuarioJanPesq() {
		return MainControl.getUsuarioJanPesq();
	}

	public UsuarioPainelCad getUsuarioPainelCad() {
		return MainControl.getUsuarioJanCad().getUsuarioPainelCad();
	}

	public UsuarioPainelPesq getUsuarioPainelPesq() {
		return MainControl.getUsuarioJanPesq().getUsuarioPainelPesq();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}