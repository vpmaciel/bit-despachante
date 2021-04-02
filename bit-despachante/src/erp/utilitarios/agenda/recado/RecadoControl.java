package erp.utilitarios.agenda.recado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class RecadoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((recado == null) || (recado.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				RecadoFac.deletarRegistro(recado);
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
				getRecadoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getRecadoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getRecadoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			recado = new Recado();
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
			List<Recado> recados = new LinkedList<>();

			if (recado.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (recados.add(RecadoFac.getRegistro(recado))) {
				RecadoRel recadoRel = new RecadoRel(recados);
				recadoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			recado = new Recado();
			getRecadoJanCad().limparGui();
			getRecadoPainelCad().getGuiData().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getRecadoPainelPesq().pesquisarRegistro(recado);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getRecadoJanPesq());
				getRecadoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getRecadoPainelPesq().pesquisarRegistro(new Recado());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getRecadoJanPesq());
				getRecadoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Recado> recados = new LinkedList<>();

			try {
				recados = new LinkedList<>(RecadoFac.pesquisarRegistro(new Recado()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			RecadoRel recadoRel = new RecadoRel(recados);
			recadoRel.retornarRelatorio(true);

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
				String data = getRecadoPainelCad().getGuiData().getText();
				if ((data == null) || (data.length() == 0)) {
					getRecadoPainelCad().getGuiData().requestFocus();
					Msg.avisoCampoObrigatorio("Data");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					RecadoFac.salvarRegistro(recado);
					recado = new Recado();
					MainControl.getAgendaRecadoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getRecadoPainelCad().getGuiData().requestFocus();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Recado recado;

	public void atualizarGui() {
		if (recado == null) {
			return;
		}
		getRecadoPainelCad().getGuiRemetente().setText(recado.getRemetente());
		getRecadoPainelCad().getGuiData().setText(recado.getData());
		getRecadoPainelCad().getGuiRecado().setText(recado.getRecado());
		getRecadoPainelCad().getGuiDestinatario().setText(recado.getDestinatario());
	}

	public void atualizarObjeto() {
		recado.setRemetente(getRecadoPainelCad().getGuiRemetente().getText());
		recado.setData(getRecadoPainelCad().getGuiData().getText());
		recado.setRecado(getRecadoPainelCad().getGuiRecado().getText());
		recado.setDestinatario(getRecadoPainelCad().getGuiDestinatario().getText());
	}

	public Recado getRecado() {
		return recado;
	}

	public RecadoJanCad getRecadoJanCad() {
		return MainControl.getAgendaRecadoJanCad();
	}

	public RecadoJanPesq getRecadoJanPesq() {
		return MainControl.getAgendaRecadoJanPesq();
	}

	public RecadoPainelCad getRecadoPainelCad() {
		return MainControl.getAgendaRecadoJanCad().getRecadoPainelCad();
	}

	public RecadoPainelPesq getRecadoPainelPesq() {
		return MainControl.getAgendaRecadoJanPesq().getRecadoPainelPesq();
	}

	public void setRecado(Recado recado) {
		this.recado = recado;
	}
}
