package erp.negocio.veiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.negocio.centrocusto.CentroCusto;
import erp.negocio.veiculo.marca.VeiculoMarca;
import erp.negocio.veiculo.modelo.VeiculoModelo;
import erp.sistema.main.MainControl;

final class VeiculoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((veiculo == null) || (veiculo.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				VeiculoFac.deletarRegistro(veiculo);
				getVeiculoJanCad().limparGui();
				veiculo = new Veiculo();
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
				getVeiculoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Veiculo> listVeiculo = new LinkedList<>();

			try {
				listVeiculo = new LinkedList<>(VeiculoFac.pesquisarRegistro(new Veiculo()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoArqCsv VeiculoArqCsv = new VeiculoArqCsv(listVeiculo);
			VeiculoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Veiculo> listVeiculo = new LinkedList<>();

			try {

				ArquivoJson<Veiculo> arquivoJson = new ArquivoJson<Veiculo>(veiculo, "Veiculo");
				arquivoJson.gravarArquivo(VeiculoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoArqCsv VeiculoArqCsv = new VeiculoArqCsv(listVeiculo);
			VeiculoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVeiculoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVeiculoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			veiculo = new Veiculo();
			getVeiculoPainelCad().getGuiPlaca().requestFocus();
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
			List<Veiculo> veiculos = new LinkedList<>();

			if (veiculo.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (veiculos.add(VeiculoFac.getRegistro(veiculo))) {
				VeiculoRel veiculoRel = new VeiculoRel(veiculos);
				veiculoRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrame extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getSource() == getVeiculoPainelCad().getLabelCentroCusto()) {
				MainControl.mostrarFrame(MainControl.getCentroCustoJanCad());
			} else if (event.getSource() == getVeiculoPainelCad().getLabelVeiculoMarca()) {
				MainControl.mostrarFrame(MainControl.getVeiculoMarcaJanCad());
			} else if (event.getSource() == getVeiculoPainelCad().getLabelVeiculoModelo()) {
				MainControl.mostrarFrame(MainControl.getVeiculoModeloJanCad());
			} else {
				MainControl.getCentroCustoJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			veiculo = new Veiculo();
			getVeiculoJanCad().limparGui();
			getVeiculoPainelCad().getGuiPlaca().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoPainelPesq().pesquisarRegistro(veiculo);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoJanPesq());
				getVeiculoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoPainelPesq().pesquisarRegistro(new Veiculo());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoJanPesq());
				getVeiculoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Veiculo> veiculos = new LinkedList<>();

			try {
				veiculos = new LinkedList<>(VeiculoFac.pesquisarRegistro(new Veiculo()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoRel veiculoRel = new VeiculoRel(veiculos);
			veiculoRel.retornarRelatorio(true);

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
				String placa = getVeiculoPainelCad().getGuiPlaca().getText();
				if ((placa == null) || (placa.length() == 0)) {
					getVeiculoPainelCad().getGuiPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("PLACA");
					return;
				}
				if ((VeiculoMarca) getVeiculoPainelCad().getGuiVeiculoMarca().getSelectedItem() == null) {
					getVeiculoPainelCad().getGuiPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("MARCA");
					return;
				}
				if (((VeiculoModelo) getVeiculoPainelCad().getGuiVeiculoModelo().getSelectedItem()) == null) {
					getVeiculoPainelCad().getGuiPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("MODELO");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VeiculoFac.salvarRegistro(veiculo);
					veiculo = new Veiculo();
					MainControl.getVeiculoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getVeiculoPainelCad().getGuiPlaca().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause();
				if (throwable != null) {
					throwable = e.getCause().getCause();
				}
				String mensagem = "";
				if (throwable != null) {
					mensagem = throwable.toString();
				}

				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_VEICULO_PLACA")) {
						Msg.avisoCampoDuplicado("PLACA");
						getVeiculoPainelCad().getGuiPlaca().requestFocus();
					} else if (mensagem.contains("INDEX_VEICULO_RENAVAM")) {
						Msg.avisoCampoDuplicado("RENAVAM");
						getVeiculoPainelCad().getGuiRenavam().requestFocus();
					} else if (mensagem.contains("INDEX_VEICULO_CHASSI")) {
						Msg.avisoCampoDuplicado("CHASSI");
						getVeiculoPainelCad().getGuiChassi().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private static Veiculo veiculo;

	public void atualizarGui() {
		if (veiculo == null) {
			return;
		}
		
		getVeiculoPainelCad().getGuiFabricacao().setSelectedItem(veiculo.getFabricacao());
		getVeiculoPainelCad().getGuiAnoFabricacao().setText(veiculo.getAnoFabricacao());
		getVeiculoPainelCad().getGuiAnoModelo().setText(veiculo.getAnoModelo());
		getVeiculoPainelCad().getGuiChassi().setText(veiculo.getChassi());
		getVeiculoPainelCad().getGuiDataCompra().setText(veiculo.getDataCompra());
		getVeiculoPainelCad().getGuiDataVenda().setText(veiculo.getDataVenda());
		getVeiculoPainelCad().getGuiVeiculoMarca().setSelectedItem(veiculo.getMarca());
		getVeiculoPainelCad().getGuiVeiculoModelo().setSelectedItem(veiculo.getModelo());
		getVeiculoPainelCad().getGuiPlaca().setText(veiculo.getPlaca());
		getVeiculoPainelCad().getGuiProprietarioNome().setText(veiculo.getProprietarioNome());
		getVeiculoPainelCad().getGuiValorCompra().setText(String.valueOf(veiculo.getValorCompra()));
		getVeiculoPainelCad().getGuiValorVenda().setText(String.valueOf(veiculo.getValorVenda()));
		getVeiculoPainelCad().getGuiRenavam().setText(veiculo.getRenavam());
		getVeiculoPainelCad().getGuiNumeroMotor().setText(veiculo.getNumeroMotor());
		getVeiculoPainelCad().getGuiMesReferenciaCompra().setSelectedItem(veiculo.getMesReferenciaCompra());
		getVeiculoPainelCad().getGuiMesReferenciaVenda().setSelectedItem(veiculo.getMesReferenciaVenda());
		getVeiculoPainelCad().getGuiAnoReferenciaCompra().setText(veiculo.getAnoReferenciaCompra());
		getVeiculoPainelCad().getGuiAnoReferenciaVenda().setText(veiculo.getAnoReferenciaVenda());
		getVeiculoPainelCad().getGuiCentroCusto().setSelectedItem(veiculo.getCentroCusto());
	}

	public void atualizarObjeto() {
		veiculo.setAnoFabricacao(getVeiculoPainelCad().getGuiAnoFabricacao().getText());
		veiculo.setAnoModelo(getVeiculoPainelCad().getGuiAnoModelo().getText());		
		veiculo.setChassi(getVeiculoPainelCad().getGuiChassi().getText());
		if (veiculo.getChassi().length() == 0) {
			veiculo.setChassi(null);
		}		
		veiculo.setDataCompra(getVeiculoPainelCad().getGuiDataCompra().getText());
		veiculo.setDataVenda(getVeiculoPainelCad().getGuiDataVenda().getText());
		veiculo.setFabricacao((String) getVeiculoPainelCad().getGuiFabricacao().getSelectedItem());
		veiculo.setMarca((VeiculoMarca) getVeiculoPainelCad().getGuiVeiculoMarca().getSelectedItem());
		veiculo.setModelo((VeiculoModelo) getVeiculoPainelCad().getGuiVeiculoModelo().getSelectedItem());
		veiculo.setPlaca(getVeiculoPainelCad().getGuiPlaca().getText());
		veiculo.setRenavam(getVeiculoPainelCad().getGuiRenavam().getText());
		if (veiculo.getRenavam().length() == 0) {
			veiculo.setRenavam(null);
		}

		try {
			veiculo.setValorCompra(Double.parseDouble(getVeiculoPainelCad().getGuiValorCompra().getText()));
		} catch (Exception e) {
			veiculo.setValorCompra(0);
		}
		try {
			veiculo.setValorVenda(Double.parseDouble(getVeiculoPainelCad().getGuiValorVenda().getText()));
		} catch (Exception e) {
			veiculo.setValorVenda(0);
		}
		veiculo.setNumeroMotor(getVeiculoPainelCad().getGuiNumeroMotor().getText());
		veiculo.setMesReferenciaCompra((String) getVeiculoPainelCad().getGuiMesReferenciaCompra().getSelectedItem());
		veiculo.setMesReferenciaVenda((String) getVeiculoPainelCad().getGuiMesReferenciaVenda().getSelectedItem());
		veiculo.setAnoReferenciaCompra(getVeiculoPainelCad().getGuiAnoReferenciaCompra().getText());
		veiculo.setAnoReferenciaVenda(getVeiculoPainelCad().getGuiAnoReferenciaVenda().getText());
		veiculo.setCentroCusto((CentroCusto) getVeiculoPainelCad().getGuiCentroCusto().getSelectedItem());
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public VeiculoJanCad getVeiculoJanCad() {
		return MainControl.getVeiculoJanCad();
	}

	public VeiculoJanPesq getVeiculoJanPesq() {
		return MainControl.getVeiculoJanPesq();
	}

	public VeiculoPainelCad getVeiculoPainelCad() {
		return MainControl.getVeiculoJanCad().getVeiculoPainelCad();
	}

	public VeiculoPainelPesq getVeiculoPainelPesq() {
		return MainControl.getVeiculoJanPesq().getVeiculoPainelPesq();
	}

	public void setVeiculo(Veiculo veiculo) {
		VeiculoControl.veiculo = veiculo;
	}
}
