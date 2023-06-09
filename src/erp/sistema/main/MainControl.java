package erp.sistema.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.atividade.AtividadeJan;
import erp.sistema.empresa.EmpresaJanCad;
import erp.sistema.evento.EventoJanCad;
import erp.sistema.evento.EventoJanPesq;
import erp.sistema.pessoa.PessoaJanCad;
import erp.veiculo.marca.MarcaJanCad;

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
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroEmpresa()) {
				mostrarFrame(eventoJanCad);
			}
			
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroConta()) {
				mostrarFrame(marcaJanCad);
			}
		}
	}
	
	private static AtividadeJan atividadeJan;
	private static EventoJanCad eventoJanCad;
	private static EmpresaJanCad empresaJanCad;
	private static EventoJanPesq eventoJanPesq;
	private static PessoaJanCad pessoaJanCad;
	private static MainControl mainControl;
	private static MainJan mainJan;
	private static MarcaJanCad marcaJanCad;
	
	public static MarcaJanCad getMarcaJan() {
		return marcaJanCad;
	}

	public static EventoJanCad getAgendaEventoJanCad() {
		return eventoJanCad;
	}
	
	public static EmpresaJanCad getEmpresaJan() {
		return empresaJanCad;
	}

	public static EventoJanPesq getAgendaEventoJanPesq() {
		return eventoJanPesq;
	}
	
	public static PessoaJanCad getPessoaJan() {
		return pessoaJanCad;
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

	public static AtividadeJan getAtividadeJan() {
		return atividadeJan;
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
		setTitulo();
	}

	private void criarFrame(JFrame frame) {
		frame.pack();
		frame.setVisible(false);
	}

	private void criarFrames() {
		atividadeJan = new AtividadeJan();
		criarFrame(atividadeJan);

		eventoJanCad = new EventoJanCad();
		criarFrame(eventoJanCad);

		eventoJanPesq = new EventoJanPesq();
		criarFrame(eventoJanPesq);
		
		marcaJanCad = new MarcaJanCad();
		criarFrame(marcaJanCad);
	}

	private String getTitulo(String titulo) {
		return Sis.getNomeSistema() + " - " + titulo;
	}
	
	private void setTitulo() {
		MainControl.getAgendaEventoJanCad().setTitle(getTitulo("EVENTO"));
		MainControl.getAgendaEventoJanPesq().setTitle(getTitulo("EVENTO"));
		MainControl.getMainJan().setTitle(getTitulo("PRINCIPAL"));
		MainControl.getMarcaJan().setTitle(getTitulo("MARCA DE VEÍCULO"));
	}
}