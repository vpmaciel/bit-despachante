package erp.utilitarios.agenda.evento.tipoevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class TipoEventoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((tipoEvento == null) || (tipoEvento.getId() == 0)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				TipoEventoFac.deletarRegistro(tipoEvento);
				getTipoEventoJanCad().limparGui();
				tipoEvento = new TipoEvento();
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
				getTipoEventoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getTipoEventoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getTipoEventoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			tipoEvento = new TipoEvento();
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
			List<TipoEvento> tipoEventos = new LinkedList<>();

			if (tipoEvento.getId() == 0) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (tipoEventos.add(TipoEventoFac.getRegistro(tipoEvento))) {
				TipoEventoRel tipoEventoRel = new TipoEventoRel(tipoEventos);
				tipoEventoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			tipoEvento = new TipoEvento();
			MainControl.getAgendaTipoEventoJanCad().limparGui();
			getTipoEventoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getTipoEventoPainelPesq().pesquisarRegistro(tipoEvento);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getTipoEventoJanPesq());
				getTipoEventoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getTipoEventoPainelPesq().pesquisarRegistro(new TipoEvento());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getTipoEventoJanPesq());
				getTipoEventoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<TipoEvento> tipoEventos = new LinkedList<>();

			try {
				tipoEventos = new LinkedList<>(TipoEventoFac.pesquisarRegistro(new TipoEvento()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			TipoEventoRel tipoEventoRel = new TipoEventoRel(tipoEventos);
			tipoEventoRel.retornarRelatorio(true);

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

				String nome = getTipoEventoPainelCad().getGuiNome().getText();

				if ((nome == null) || (nome.length() == 0)) {
					getTipoEventoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					TipoEventoFac.salvarRegistro(tipoEvento);
					tipoEvento = new TipoEvento();
					MainControl.getAgendaTipoEventoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getTipoEventoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getTipoEventoPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private TipoEvento tipoEvento;

	public void atualizarGui() {
		if (tipoEvento == null) {
			return;
		}
		getTipoEventoPainelCad().getGuiNome().setText(tipoEvento.getNome());
	}

	public void atualizarObjeto() {
		tipoEvento.setNome(getTipoEventoPainelCad().getGuiNome().getText());

		if (getTipoEventoPainelCad().getGuiNome().getText().length() == 0) {
			tipoEvento.setNome(null);
		}
	}

	public TipoEvento getEvento() {
		return tipoEvento;
	}

	public TipoEventoJanCad getTipoEventoJanCad() {
		return MainControl.getAgendaTipoEventoJanCad();
	}

	public TipoEventoJanPesq getTipoEventoJanPesq() {
		return MainControl.getAgendaTipoEventoJanPesq();
	}

	public TipoEventoPainelCad getTipoEventoPainelCad() {
		return MainControl.getAgendaTipoEventoJanCad().getTipoEventoPainelCad();
	}

	public TipoEventoPainelPesq getTipoEventoPainelPesq() {
		return MainControl.getAgendaTipoEventoJanPesq().getTipoEventoPainelPesq();
	}

	public void setTipoEvento(TipoEvento TipoEvento) {
		this.tipoEvento = TipoEvento;
	}
}
