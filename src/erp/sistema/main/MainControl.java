package erp.sistema.main;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.cliente.ClienteJan;
import erp.pedido.placa.PedidoPlacaJan;
import erp.servico.ServicoJan;
import erp.sistema.login.LoginJan;
import erp.usuario.UsuarioJan;
import erp.veiculo.VeiculoJan;

public final class MainControl {

	public class FrameGerenteEventos extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if (Msg.confirmarSairDoSistema() == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}
	}

	public class MenuGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if(Sis.getUsuario() == null) {
				mostrarFrame(loginJan);
				return;
			}
			
			if (actionEvent.getSource() == mainJan.getMenuItemAjudaSobreSistema()) {
				Msg.ajuda();
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoSair()) {
				if (Msg.confirmarSairDoSistema() == 0) {
					System.exit(0);
				}
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoUsuario()) {
				mostrarFrame(usuarioJan);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroPedidoPlaca()) {
				mostrarFrame(pedidoPlacaJan);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroServico()) {
				mostrarFrame(servicoJan);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroCliente()) {
				mostrarFrame(clienteJan);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroVeiculo()) {
				mostrarFrame(veiculoJan);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoLogin()) {
				mostrarFrame(loginJan);
			}
		}
	}

	private static MainControl mainControl;
	private static MainJan mainJan;
	private static PedidoPlacaJan pedidoPlacaJan;
	private static ServicoJan servicoJan;
	private static ClienteJan clienteJan;
	private static VeiculoJan veiculoJan;
	private static LoginJan loginJan;
	private static UsuarioJan usuarioJan;

	public static ClienteJan getClienteJan() {
		return clienteJan;
	}

	public static synchronized MainControl getInstance(MainJan mainJan) {
		if (mainControl == null) {
			return new MainControl(mainJan);
		}
		return mainControl;
	}

	public static MainJan getMainJan() {
		return mainJan;
	}

	public static PedidoPlacaJan getPedidoPlacaJan() {
		return pedidoPlacaJan;
	}

	public static ServicoJan getServicoJan() {
		return servicoJan;
	}

	public static VeiculoJan getVeiculoJan() {
		return veiculoJan;
	}
	
	public static LoginJan getLoginJan() {
		return loginJan;
	}
	
	public static UsuarioJan getUsuarioJan() {		// 
		return usuarioJan;
	}

	public static void mostrarFrame(JFrame frame) {
		frame.setVisible(true);
		frame.toFront();
		frame.setLocationRelativeTo(null);
		mainJan.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	private MainControl(MainJan mainJan) {
		MainControl.mainJan = mainJan;
		criarFrames();
	}

	private void criarFrame(JFrame frame) {
		frame.pack();
		frame.setVisible(false);
	}

	private void criarFrames() {
		pedidoPlacaJan = new PedidoPlacaJan();
		criarFrame(pedidoPlacaJan);

		servicoJan = new ServicoJan();
		criarFrame(pedidoPlacaJan);

		clienteJan = new ClienteJan();
		criarFrame(clienteJan);

		veiculoJan = new VeiculoJan();
		criarFrame(veiculoJan);
		
		loginJan = new LoginJan();
		criarFrame(loginJan);
		
		usuarioJan = new UsuarioJan();
		criarFrame(usuarioJan);
	}
}