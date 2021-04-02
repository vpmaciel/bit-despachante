package erp.negocio.veiculo.marca;

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

final class VeiculoMarcaControl {

	public class ExcluiRegistro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((veiculoMarca == null) || (veiculoMarca.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				VeiculoMarcaFac.deletarRegistro(veiculoMarca);
				getVeiculoMarcaJanCad().limparGui();
				veiculoMarca = new VeiculoMarca();
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
				getVeiculoMarcaJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoMarca> listVeiculoMarca = new LinkedList<>();

			try {
				listVeiculoMarca = new LinkedList<>(VeiculoMarcaFac.pesquisarRegistro(new VeiculoMarca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoMarcaArqCsv VeiculoMarcaArqCsv = new VeiculoMarcaArqCsv(listVeiculoMarca);
			VeiculoMarcaArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoMarca> listVeiculoMarca = new LinkedList<>();

			try {

				ArquivoJson<VeiculoMarca> arquivoJson = new ArquivoJson<VeiculoMarca>(veiculoMarca, "VeiculoMarca");
				arquivoJson.gravarArquivo(VeiculoMarcaFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoMarcaArqCsv VeiculoMarcaArqCsv = new VeiculoMarcaArqCsv(listVeiculoMarca);
			VeiculoMarcaArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVeiculoMarcaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVeiculoMarcaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			veiculoMarca = new VeiculoMarca();
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
			List<VeiculoMarca> veiculoMarcas = new LinkedList<>();

			if (veiculoMarca.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (veiculoMarcas.add(VeiculoMarcaFac.getRegistro(veiculoMarca))) {
				VeiculoMarcaRel veiculoMarcaRel = new VeiculoMarcaRel(veiculoMarcas);
				veiculoMarcaRel.retornarRelatorio(true);
			}
		}
	}

	public class NovoFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			veiculoMarca = new VeiculoMarca();
			getVeiculoMarcaJanCad().limparGui();
			getVeiculoMarcaPainelCad().getGuiMarca().requestFocus();
		}
	}

	public class PesquisaRegistro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoMarcaPainelPesq().pesquisarRegistro(veiculoMarca);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoMarcaJanPesq());
				getVeiculoMarcaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoMarcaPainelPesq().pesquisarRegistro(new VeiculoMarca());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoMarcaJanPesq());
				getVeiculoMarcaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoMarca> veiculoMarcas = new LinkedList<>();

			try {
				veiculoMarcas = new LinkedList<>(VeiculoMarcaFac.pesquisarRegistro(new VeiculoMarca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoMarcaRel veiculoMarcaRel = new VeiculoMarcaRel(veiculoMarcas);
			veiculoMarcaRel.retornarRelatorio(true);

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
				String placa = getVeiculoMarcaPainelCad().getGuiMarca().getText();
				if ((placa == null) || (placa.length() == 0)) {
					getVeiculoMarcaPainelCad().getGuiMarca().requestFocus();
					Msg.avisoCampoObrigatorio("MARCA");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VeiculoMarcaFac.salvarRegistro(veiculoMarca);
					veiculoMarca = new VeiculoMarca();
					getVeiculoMarcaJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getVeiculoMarcaPainelCad().getGuiMarca().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_VEICULO_MARCA_MARCA")) {
						Msg.avisoCampoDuplicado("MARCA");
						getVeiculoMarcaPainelCad().getGuiMarca().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private VeiculoMarca veiculoMarca;

	public void atualizarGui() {
		if (veiculoMarca == null) {
			return;
		}
		getVeiculoMarcaPainelCad().getGuiMarca().setText(veiculoMarca.getMarca());
	}

	public void atualizarObjeto() {
		veiculoMarca.setMarca(getVeiculoMarcaPainelCad().getGuiMarca().getText());

		if (getVeiculoMarcaPainelCad().getGuiMarca().getText().length() == 0) {
			veiculoMarca.setMarca(null);
		}

	}

	public VeiculoMarca getVeiculoMarca() {
		return veiculoMarca;
	}

	public VeiculoMarcaJanCad getVeiculoMarcaJanCad() {
		return MainControl.getVeiculoMarcaJanCad();
	}

	public VeiculoMarcaJanPesq getVeiculoMarcaJanPesq() {
		return MainControl.getVeiculoMarcaJanPesq();
	}

	public VeiculoMarcaPainelCad getVeiculoMarcaPainelCad() {
		return MainControl.getVeiculoMarcaJanCad().getVeiculoMarcaPainelCad();
	}

	public VeiculoMarcaPainelPesq getVeiculoMarcaPainelPesq() {
		return MainControl.getVeiculoMarcaJanPesq().getVeiculoMarcaPainelPesq();
	}

	public void setVeiculoMarca(VeiculoMarca veiculoMarca) {
		this.veiculoMarca = veiculoMarca;
	}
}
