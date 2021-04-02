package erp.negocio.seguradora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.arquitetura.validacao.Entrada;
import erp.arquitetura.validacao.RegExp;
import erp.sistema.main.MainControl;

final class SeguradoraControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((seguradora == null) || (seguradora.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				SeguradoraFac.deletarRegistro(seguradora);
				getSeguradoraJanCad().limparGui();
				seguradora = new Seguradora();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getSeguradoraJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguradora> listSeguradora = new LinkedList<>();

			try {
				listSeguradora = new LinkedList<>(SeguradoraFac.pesquisarRegistro(new Seguradora()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SeguradoraArqCsv SeguradoraArqCsv = new SeguradoraArqCsv(listSeguradora);
			SeguradoraArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguradora> listSeguradora = new LinkedList<>();

			try {

				ArquivoJson<Seguradora> arquivoJson = new ArquivoJson<Seguradora>(seguradora, "Seguradora");
				arquivoJson.gravarArquivo(SeguradoraFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			SeguradoraArqCsv SeguradoraArqCsv = new SeguradoraArqCsv(listSeguradora);
			SeguradoraArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getSeguradoraJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getSeguradoraJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			seguradora = new Seguradora();
			getSeguradoraPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainControl.getMainJanCad());
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Seguradora> seguradoras = new LinkedList<>();

			if (seguradora.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (seguradoras.add(SeguradoraFac.getRegistro(seguradora))) {
				SeguradoraRel seguradoraRel = new SeguradoraRel(seguradoras);
				seguradoraRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			seguradora = new Seguradora();
			getSeguradoraJanCad().limparGui();
			getSeguradoraPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getSeguradoraPainelPesq().pesquisarRegistro(seguradora);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getSeguradoraJanPesq());
				getSeguradoraJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getSeguradoraPainelPesq().pesquisarRegistro(new Seguradora());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getSeguradoraJanPesq());
				getSeguradoraJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguradora> seguradoras = new LinkedList<>();

			try {
				seguradoras = new LinkedList<>(SeguradoraFac.pesquisarRegistro(new Seguradora()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SeguradoraRel seguradoraRel = new SeguradoraRel(seguradoras);
			seguradoraRel.retornarRelatorio(true);

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
				if (((getSeguradoraPainelCad().getGuiNome().getText()) == null)
						|| (getSeguradoraPainelCad().getGuiNome().getText().length() == 0)) {
					getSeguradoraPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getSeguradoraPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					SeguradoraFac.salvarRegistro(seguradora);
					seguradora = new Seguradora();
					getSeguradoraJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getSeguradoraPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getSeguradoraPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Seguradora seguradora;

	public SeguradoraControl() {

	}

	public void atualizarGui() {
		if (seguradora == null) {
			return;
		}
		getSeguradoraPainelCad().getGuiNome().setText(seguradora.getNome());
	}

	public void atualizarObjeto() {
		seguradora.setNome(getSeguradoraPainelCad().getGuiNome().getText());

		if (getSeguradoraPainelCad().getGuiNome().getText().length() == 0) {
			seguradora.setNome(null);
		}
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public SeguradoraJanCad getSeguradoraJanCad() {
		return MainControl.getSeguradoraJanCad();
	}

	public SeguradoraJanPesq getSeguradoraJanPesq() {
		return MainControl.getSeguradoraJanPesq();
	}

	public SeguradoraPainelCad getSeguradoraPainelCad() {
		return MainControl.getSeguradoraJanCad().getSeguradoraPainelCad();
	}

	public SeguradoraPainelPesq getSeguradoraPainelPesq() {
		return MainControl.getSeguradoraJanPesq().getSeguradoraPainelPesq();
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
}
