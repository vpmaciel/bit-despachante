package erp.negocio.veiculo.documento;

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
import erp.negocio.veiculo.Veiculo;
import erp.negocio.veiculo.VeiculoFac;
import erp.sistema.main.MainControl;

final class VeiculoDocumentoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((veiculoDocumento == null) || (veiculoDocumento.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				VeiculoDocumentoFac.deletarRegistro(veiculoDocumento);
				getVeiculoDocumentoJanCad().limparGui();
				veiculoDocumento = new VeiculoDocumento();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getVeiculoDocumentoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoDocumento> listVeiculoDocumento = new LinkedList<>();

			try {
				listVeiculoDocumento = new LinkedList<>(VeiculoDocumentoFac.pesquisarRegistro(new VeiculoDocumento()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoDocumentoArqCsv VeiculoDocumentoArqCsv = new VeiculoDocumentoArqCsv(listVeiculoDocumento);
			VeiculoDocumentoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoDocumento> listVeiculoDocumento = new LinkedList<>();

			try {

				ArquivoJson<VeiculoDocumento> arquivoJson = new ArquivoJson<VeiculoDocumento>(veiculoDocumento,
						"VeiculoDocumento");
				arquivoJson.gravarArquivo(VeiculoDocumentoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoDocumentoArqCsv VeiculoDocumentoArqCsv = new VeiculoDocumentoArqCsv(listVeiculoDocumento);
			VeiculoDocumentoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVeiculoDocumentoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVeiculoDocumentoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			veiculoDocumento = new VeiculoDocumento();
			getVeiculoDocumentoPainelCad().getGuiVeiculo().requestFocus();
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
			List<VeiculoDocumento> veiculoDocumentos = new LinkedList<>();

			if (veiculoDocumento.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (veiculoDocumentos.add(VeiculoDocumentoFac.getRegistro(veiculoDocumento))) {
				VeiculoDocumentoRel veiculoDocumentoRel = new VeiculoDocumentoRel(veiculoDocumentos);
				veiculoDocumentoRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrame extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getSource() == getVeiculoDocumentoPainelCad().getLabelVeiculo()) {
				MainControl.mostrarFrame(MainControl.getVeiculoJanCad());
			} else {
				MainControl.getVeiculoDocumentoJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			veiculoDocumento = new VeiculoDocumento();
			getVeiculoDocumentoJanCad().limparGui();
			getVeiculoDocumentoPainelCad().getGuiVeiculo().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoDocumentoPainelPesq().pesquisarRegistro(veiculoDocumento);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoDocumentoJanPesq());
				getVeiculoDocumentoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVeiculoDocumentoPainelPesq().pesquisarRegistro(new VeiculoDocumento());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVeiculoDocumentoJanPesq());
				getVeiculoDocumentoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VeiculoDocumento> veiculoDocumentos = new LinkedList<>();

			try {
				veiculoDocumentos = new LinkedList<>(VeiculoDocumentoFac.pesquisarRegistro(new VeiculoDocumento()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoDocumentoRel veiculoDocumentoRel = new VeiculoDocumentoRel(veiculoDocumentos);
			veiculoDocumentoRel.retornarRelatorio(true);

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

				if (getVeiculoDocumentoPainelCad().getGuiVeiculo() == null) {
					getVeiculoDocumentoPainelCad().getGuiVeiculo().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VeiculoDocumentoFac.salvarRegistro(veiculoDocumento);
					veiculoDocumento = new VeiculoDocumento();
					getVeiculoDocumentoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getVeiculoDocumentoPainelCad().getGuiVeiculo().requestFocus();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private VeiculoDocumento veiculoDocumento;

	public void atualizarGui() {
		if (veiculoDocumento == null) {
			return;
		}
		getVeiculoDocumentoPainelCad().getGuiCNPJRecebedorVeiculoDocumento()
				.setText(veiculoDocumento.getCnpjRecebedorVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiCPFRecebedorVeiculoDocumento()
				.setText(veiculoDocumento.getCpfRecebedorVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiDiaDevolucaoVeiculoDocumento()
				.setText(veiculoDocumento.getDataDevolucaoVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiDiaRecebimentoVeiculoDocumento()
				.setText(veiculoDocumento.getDataRecebimentoVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiLocalVeiculoDocumento()
				.setSelectedItem(veiculoDocumento.getLocalVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiNomeProprietário()
				.setText(veiculoDocumento.getVeiculo().getProprietarioNome());
		getVeiculoDocumentoPainelCad().getGuiNomeRecebedorVeiculoDocumento()
				.setText(veiculoDocumento.getNomeRecebedorVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiVeiculo().setSelectedItem(veiculoDocumento.getVeiculo());
		getVeiculoDocumentoPainelCad().getGuiRGNumeroRecebedorVeiculoDocumento()
				.setText(veiculoDocumento.getRgNumeroRecebedorVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiRGOrgaoEmisssorRecebedorVeiculoDocumento()
				.setText(veiculoDocumento.getRgOrgaoEmisssorRecebedorVeiculoDocumento());
		getVeiculoDocumentoPainelCad().getGuiSituacaoVeiculoDocumento()
				.setSelectedItem(veiculoDocumento.getSituacaoVeiculoDocumento());
	}

	public void atualizarObjeto() {
		veiculoDocumento.setCnpjRecebedorVeiculoDocumento(
				getVeiculoDocumentoPainelCad().getGuiCNPJRecebedorVeiculoDocumento().getText());
		veiculoDocumento.setCpfRecebedorVeiculoDocumento(
				getVeiculoDocumentoPainelCad().getGuiCPFRecebedorVeiculoDocumento().getText());
		veiculoDocumento.setDataDevolucaoVeiculoDocumento(
				getVeiculoDocumentoPainelCad().getGuiDiaDevolucaoVeiculoDocumento().getText());
		veiculoDocumento.setDataRecebimentoVeiculoDocumento(
				getVeiculoDocumentoPainelCad().getGuiDiaRecebimentoVeiculoDocumento().getText());
		veiculoDocumento.setLocalVeiculoDocumento(
				(String) getVeiculoDocumentoPainelCad().getGuiLocalVeiculoDocumento().getSelectedItem());

		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca(((Veiculo) getVeiculoDocumentoPainelCad().getGuiVeiculo().getSelectedItem()).getPlaca());

		List<Veiculo> listVeiculo = (List<Veiculo>) VeiculoFac.pesquisarRegistro(veiculo);
		if (listVeiculo.size() > 0) {
			veiculo = listVeiculo.get(0);
			veiculoDocumento.setNomeRecebedorVeiculoDocumento(veiculo.getProprietarioNome());
			veiculoDocumento.setVeiculo(veiculo);
		}

		veiculoDocumento.setRgNumeroRecebedorVeiculoDocumento(
				getVeiculoDocumentoPainelCad().getGuiRGNumeroRecebedorVeiculoDocumento().getText());
		veiculoDocumento.setRgOrgaoEmisssorRecebedorVeiculoDocumento(
				getVeiculoDocumentoPainelCad().getGuiRGOrgaoEmisssorRecebedorVeiculoDocumento().getText());
		veiculoDocumento.setSituacaoVeiculoDocumento(
				(String) getVeiculoDocumentoPainelCad().getGuiSituacaoVeiculoDocumento().getSelectedItem());

		if (veiculoDocumento.getCnpjRecebedorVeiculoDocumento().length() == 0) {
			veiculoDocumento.setCnpjRecebedorVeiculoDocumento(null);
		}

		if (veiculoDocumento.getCpfRecebedorVeiculoDocumento().length() == 0) {
			veiculoDocumento.setCpfRecebedorVeiculoDocumento(null);
		}
	}

	public VeiculoDocumento getVeiculoDocumento() {
		return veiculoDocumento;
	}

	public VeiculoDocumentoJanCad getVeiculoDocumentoJanCad() {
		return MainControl.getVeiculoDocumentoJanCad();
	}

	public VeiculoDocumentoJanPesq getVeiculoDocumentoJanPesq() {
		return MainControl.getVeiculoDocumentoJanPesq();
	}

	public VeiculoDocumentoPainelCad getVeiculoDocumentoPainelCad() {
		return MainControl.getVeiculoDocumentoJanCad().getVeiculoDocumentoPainelCad();
	}

	public VeiculoDocumentoPainelPesq getVeiculoDocumentoPainelPesq() {
		return MainControl.getVeiculoDocumentoJanPesq().getVeiculoDocumentoPainelPesq();
	}

	public void setVeiculoDocumento(VeiculoDocumento veiculoDocumento) {
		this.veiculoDocumento = veiculoDocumento;
	}
}
