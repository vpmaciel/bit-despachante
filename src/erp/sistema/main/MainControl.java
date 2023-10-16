package erp.sistema.main;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import erp.arquitetura.gui.Msg;
import erp.cliente.ClienteJan;
import erp.pedido.placa.PedidoPlacaJan;
import erp.servico.ServicoJan;
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
			if (actionEvent.getSource() == mainJan.getMenuItemAjudaSobreSistema()) {
				Msg.ajuda();
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoSair()) {
				if (Msg.confirmarSairDoSistema() == 0) {
					System.exit(0);
				}
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
		}
	}
	
	
	
	private static MainControl mainControl;
	private static MainJan mainJan;
	private static PedidoPlacaJan pedidoPlacaJan;
	private static ServicoJan servicoJan;
	private static ClienteJan clienteJan;
	private static VeiculoJan veiculoJan;


	public static synchronized MainControl getInstance(MainJan mainJan) {
		if (mainControl == null) {			
			return new MainControl(mainJan);
		}
		return mainControl;
	}	
	
	public static PedidoPlacaJan getPedidoPlacaJan() {
		return pedidoPlacaJan;
	}
	
	public static VeiculoJan getVeiculoJan() {
		return veiculoJan;
	}
	
	
	public static ClienteJan getClienteJan() {
		return clienteJan;
	}
	
	public static ServicoJan getServicoJan() {
		return servicoJan;
	}
	
		public static MainJan getMainJan() {
		return mainJan;
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
	}
}