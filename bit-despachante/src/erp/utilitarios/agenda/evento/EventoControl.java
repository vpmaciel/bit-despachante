package erp.utilitarios.agenda.evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;
import erp.utilitarios.agenda.evento.tipoevento.TipoEvento;
import erp.utilitarios.agenda.evento.tipoevento.TipoEventoComp;
import erp.utilitarios.agenda.evento.tipoevento.TipoEventoFac;

final class EventoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((evento == null) || (evento.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				EventoFac.deletarRegistro(evento);
				getEventoJanCad().limparGui();
				evento = new Evento();
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
				getEventoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getEventoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getEventoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			evento = new Evento();
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
			List<Evento> eventos = new LinkedList<>();

			if (evento.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (eventos.add(EventoFac.getRegistro(evento))) {
				EventoRel eventoRel = new EventoRel(eventos);
				eventoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<TipoEvento> tipoEventoList = (List<TipoEvento>) TipoEventoFac.getRegistro();
			Collections.sort(tipoEventoList, new TipoEventoComp().new Nome());

			getEventoPainelCad().getTipoEventoGui().removeAllItems();
			getEventoPainelCad().getTipoEventoGui().addItem(new TipoEvento());

			for (TipoEvento v : tipoEventoList) {
				getEventoPainelCad().getTipoEventoGui().addItem(v);
			}

			evento = new Evento();
			getEventoJanCad().limparGui();
			getEventoPainelCad().getGuiDescricao().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getEventoPainelPesq().pesquisarRegistro(evento);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getEventoJanPesq());
				getEventoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getEventoPainelPesq().pesquisarRegistro(new Evento());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getEventoJanPesq());
				getEventoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Evento> eventos = new LinkedList<>();

			try {
				eventos = new LinkedList<>(EventoFac.pesquisarRegistro(new Evento()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			EventoRel eventoRel = new EventoRel(eventos);
			eventoRel.retornarRelatorio(true);

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
				String descricao = getEventoPainelCad().getGuiDescricao().getText();
				if ((descricao == null) || (descricao.length() == 0)) {
					getEventoPainelCad().getGuiDescricao().requestFocus();
					Msg.avisoCampoObrigatorio("DESCRIÇÃO");
					return;
				}

				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					EventoFac.salvarRegistro(evento);
					evento = new Evento();
					MainControl.getAgendaEventoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getEventoPainelCad().getGuiDescricao().requestFocus();
				}
			} catch (Exception e) {
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Evento evento;

	public void atualizarGui() {
		if (evento == null) {
			return;
		}

		getEventoPainelCad().getTipoEventoGui().setSelectedItem(evento.getTipoEvento());
		getEventoPainelCad().getDataGui().setText(evento.getData());
		getEventoPainelCad().getGuiDescricao().setText(evento.getDescricao());
		getEventoPainelCad().getGuiHoraInicio().setText(evento.getHoraInicio());
		getEventoPainelCad().getGuiHoraTermino().setText(evento.getHoraTermino());
	}

	public void atualizarObjeto() {
		evento.setDescricao(getEventoPainelCad().getGuiDescricao().getText());
		evento.setHoraTermino(getEventoPainelCad().getGuiHoraTermino().getText());
		evento.setHoraInicio(getEventoPainelCad().getGuiHoraInicio().getText());
		evento.setData(getEventoPainelCad().getDataGui().getText());
		evento.setTipoEvento((TipoEvento) getEventoPainelCad().getTipoEventoGui().getSelectedItem());
	}

	public Evento getEvento() {
		return evento;
	}

	public EventoJanCad getEventoJanCad() {
		return MainControl.getAgendaEventoJanCad();
	}

	public EventoJanPesq getEventoJanPesq() {
		return MainControl.getAgendaEventoJanPesq();
	}

	public EventoPainelCad getEventoPainelCad() {
		return MainControl.getAgendaEventoJanCad().getEventoPainelCad();
	}

	public EventoPainelPesq getEventoPainelPesq() {
		return MainControl.getAgendaEventoJanPesq().getEventoPainelPesq();
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
