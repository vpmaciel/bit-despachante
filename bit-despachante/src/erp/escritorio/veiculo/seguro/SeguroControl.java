package erp.escritorio.veiculo.seguro;

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
import erp.negocio.seguradora.Seguradora;
import erp.sistema.main.MainControl;

final class SeguroControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((seguro == null) || (seguro.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				SeguroFac.deletarRegistro(seguro);
				getSeguroJanCad().limparGui();
				seguro = new Seguro();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getSeguroJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguro> listSeguro = new LinkedList<>();

			try {
				listSeguro = new LinkedList<>(SeguroFac.pesquisarRegistro(new Seguro()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SeguroArqCsv SeguroArqCsv = new SeguroArqCsv(listSeguro);
			SeguroArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguro> listSeguro = new LinkedList<>();

			try {

				ArquivoJson<Seguro> arquivoJson = new ArquivoJson<Seguro>(seguro, "Seguro");
				arquivoJson.gravarArquivo(SeguroFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			SeguroArqCsv SeguroArqCsv = new SeguroArqCsv(listSeguro);
			SeguroArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getSeguroJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getSeguroJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			seguro = new Seguro();
			getSeguroPainelCad().getGuiNome().requestFocus();
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
			List<Seguro> seguros = new LinkedList<>();

			if (seguro.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (seguros.add(SeguroFac.getRegistro(seguro))) {
				SeguroRel seguroRel = new SeguroRel(seguros);
				seguroRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			seguro = new Seguro();
			getSeguroJanCad().limparGui();
			getSeguroPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getSeguroPainelPesq().pesquisarRegistro(seguro);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getSeguroJanPesq());
				getSeguroJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getSeguroPainelPesq().pesquisarRegistro(new Seguro());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getSeguroJanPesq());
				getSeguroJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguro> seguros = new LinkedList<>();

			try {
				seguros = new LinkedList<>(SeguroFac.pesquisarRegistro(new Seguro()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SeguroRel seguroRel = new SeguroRel(seguros);
			seguroRel.retornarRelatorio(true);

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
				if (((getSeguroPainelCad().getGuiNome().getText()) == null)
						|| (getSeguroPainelCad().getGuiNome().getText().length() == 0)) {
					getSeguroPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getSeguroPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					SeguroFac.salvarRegistro(seguro);
					seguro = new Seguro();
					getSeguroJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getSeguroPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getSeguroPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Seguro seguro;

	public SeguroControl() {

	}

	public void atualizarGui() {
		if (seguro == null) {
			return;
		}
		getSeguroPainelCad().getGuiCorretor().setText(seguro.getNome());
		getSeguroPainelCad().getGuiDataFimVigencia().setText(seguro.getNome());
		getSeguroPainelCad().getGuiDataInicioVigencia().setText(seguro.getNome());
		getSeguroPainelCad().getGuiNome().setText(seguro.getNome());
		getSeguroPainelCad().getGuiSeguradora().setSelectedItem(seguro.getSeguradora());
	}

	public void atualizarObjeto() {
		seguro.setNome(getSeguroPainelCad().getGuiNome().getText());

		seguro.setCorretor(getSeguroPainelCad().getGuiCorretor().getText());
		seguro.setDataFimVigencia(getSeguroPainelCad().getGuiDataFimVigencia().getText());
		seguro.setDataInicioVigencia(getSeguroPainelCad().getGuiDataInicioVigencia().getText());
		seguro.setSeguradora((Seguradora) getSeguroPainelCad().getGuiSeguradora().getSelectedItem());

		if (getSeguroPainelCad().getGuiNome().getText().length() == 0) {
			seguro.setNome(null);
		}
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public SeguroJanCad getSeguroJanCad() {
		return MainControl.getSeguroJanCad();
	}

	public SeguroJanPesq getSeguroJanPesq() {
		return MainControl.getSeguroJanPesq();
	}

	public SeguroPainelCad getSeguroPainelCad() {
		return MainControl.getSeguroJanCad().getSeguroPainelCad();
	}

	public SeguroPainelPesq getSeguroPainelPesq() {
		return MainControl.getSeguroJanPesq().getSeguroPainelPesq();
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
}
