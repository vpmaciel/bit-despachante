package erp.negocio.veiculo.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class VeiculoModeloControl {

	public class ExcluiRegistro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((veiculoModelo == null) || (veiculoModelo.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				VeiculoModeloFac.deletarRegistro(veiculoModelo);
				getVeiculoModeloJanCad().limparGui();
				veiculoModelo = new VeiculoModelo();
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
				getVeiculoModeloJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoModelo> listVeiculoModelo = new LinkedList<>();

			try {
				listVeiculoModelo = new LinkedList<>(VeiculoModeloFac.pesquisarRegistro(new VeiculoModelo()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoModeloArqCsv VeiculoModeloArqCsv = new VeiculoModeloArqCsv(listVeiculoModelo);
			VeiculoModeloArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoModelo> listVeiculoModelo = new LinkedList<>();

			try {

				ArquivoJson<VeiculoModelo> arquivoJson = new ArquivoJson<VeiculoModelo>(veiculoModelo, "VeiculoModelo");
				arquivoJson.gravarArquivo(VeiculoModeloFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoModeloArqCsv VeiculoModeloArqCsv = new VeiculoModeloArqCsv(listVeiculoModelo);
			VeiculoModeloArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVeiculoModeloJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVeiculoModeloJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			veiculoModelo = new VeiculoModelo();
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
			List<VeiculoModelo> veiculoModelos = new LinkedList<>();

			if (veiculoModelo.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (veiculoModelos.add(VeiculoModeloFac.getRegistro(veiculoModelo))) {
				VeiculoModeloRel veiculoModeloRel = new VeiculoModeloRel(veiculoModelos);
				veiculoModeloRel.retornarRelatorio(true);
			}
		}
	}

	public class NovoFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			veiculoModelo = new VeiculoModelo();
			getVeiculoModeloJanCad().limparGui();
			getVeiculoModeloPainelCad().getGuiModelo().requestFocus();
		}
	}

	public class PesquisaRegistro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoModeloPainelPesq().pesquisarRegistro(veiculoModelo);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoModeloJanPesq());
				getVeiculoModeloJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoModeloPainelPesq().pesquisarRegistro(new VeiculoModelo());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoModeloJanPesq());
				getVeiculoModeloJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoModelo> veiculoModelos = new LinkedList<>();

			try {
				veiculoModelos = new LinkedList<>(VeiculoModeloFac.pesquisarRegistro(new VeiculoModelo()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoModeloRel veiculoModeloRel = new VeiculoModeloRel(veiculoModelos);
			veiculoModeloRel.retornarRelatorio(true);

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
				String placa = getVeiculoModeloPainelCad().getGuiModelo().getText();
				if ((placa == null) || (placa.length() == 0)) {
					getVeiculoModeloPainelCad().getGuiModelo().requestFocus();
					Msg.avisoCampoObrigatorio("MODELO");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VeiculoModeloFac.salvarRegistro(veiculoModelo);
					veiculoModelo = new VeiculoModelo();
					getVeiculoModeloJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getVeiculoModeloPainelCad().getGuiModelo().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_VEICULO_MODELO_MODELO")) {
						Msg.avisoCampoDuplicado("MODELO");
						getVeiculoModeloPainelCad().getGuiModelo().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private VeiculoModelo veiculoModelo;

	public void atualizarGui() {
		if (veiculoModelo == null) {
			return;
		}
		getVeiculoModeloPainelCad().getGuiModelo().setText(veiculoModelo.getModelo());
	}

	public void atualizarObjeto() {
		veiculoModelo.setModelo(getVeiculoModeloPainelCad().getGuiModelo().getText());

		if (getVeiculoModeloPainelCad().getGuiModelo().getText().length() == 0) {
			veiculoModelo.setModelo(null);
		}
	}

	public VeiculoModelo getVeiculoModelo() {
		return veiculoModelo;
	}

	public VeiculoModeloJanCad getVeiculoModeloJanCad() {
		return MainControl.getVeiculoModeloJanCad();
	}

	public VeiculoModeloJanPesq getVeiculoModeloJanPesq() {
		return MainControl.getVeiculoModeloJanPesq();
	}

	public VeiculoModeloPainelCad getVeiculoModeloPainelCad() {
		return MainControl.getVeiculoModeloJanCad().getVeiculoModeloPainelCad();
	}

	public VeiculoModeloPainelPesq getVeiculoModeloPainelPesq() {
		return MainControl.getVeiculoModeloJanPesq().getVeiculoModeloPainelPesq();
	}

	public void setVeiculoModelo(VeiculoModelo veiculoModelo) {
		this.veiculoModelo = veiculoModelo;
	}
}