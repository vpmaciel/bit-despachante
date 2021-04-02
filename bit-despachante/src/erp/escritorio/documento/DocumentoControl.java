package erp.escritorio.documento;

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
import erp.sistema.main.MainControl;

final class DocumentoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((documento == null) || (documento.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				DocumentoFac.deletarRegistro(documento);
				getDocumentoJanCad().limparGui();
				documento = new Documento();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getDocumentoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Documento> listDocumento = new LinkedList<>();

			try {
				listDocumento = new LinkedList<>(DocumentoFac.pesquisarRegistro(new Documento()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			DocumentoArqCsv DocumentoArqCsv = new DocumentoArqCsv(listDocumento);
			DocumentoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Documento> listDocumento = new LinkedList<>();

			try {

				ArquivoJson<Documento> arquivoJson = new ArquivoJson<Documento>(documento, "Documento");
				arquivoJson.gravarArquivo(DocumentoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			DocumentoArqCsv DocumentoArqCsv = new DocumentoArqCsv(listDocumento);
			DocumentoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getDocumentoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getDocumentoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			documento = new Documento();
			getDocumentoPainelCad().getGuiVeiculo().requestFocus();
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
			List<Documento> documentos = new LinkedList<>();

			if (documento.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (documentos.add(DocumentoFac.getRegistro(documento))) {
				DocumentoRel documentoRel = new DocumentoRel(documentos);
				documentoRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrame extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			documento = new Documento();
			getDocumentoJanCad().limparGui();
			getDocumentoPainelCad().getGuiVeiculo().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getDocumentoPainelPesq().pesquisarRegistro(documento);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getDocumentoJanPesq());
				getDocumentoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getDocumentoPainelPesq().pesquisarRegistro(new Documento());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getDocumentoJanPesq());
				getDocumentoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Documento> documentos = new LinkedList<>();

			try {
				documentos = new LinkedList<>(DocumentoFac.pesquisarRegistro(new Documento()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			DocumentoRel documentoRel = new DocumentoRel(documentos);
			documentoRel.retornarRelatorio(true);

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

				if (getDocumentoPainelCad().getGuiVeiculo() == null) {
					getDocumentoPainelCad().getGuiVeiculo().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					DocumentoFac.salvarRegistro(documento);
					documento = new Documento();
					getDocumentoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getDocumentoPainelCad().getGuiVeiculo().requestFocus();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Documento documento;

	public void atualizarGui() {
		if (documento == null) {
			return;
		}
		getDocumentoPainelCad().getGuiAnoDevolucaoDocumento().setText(documento.getAnoDevolucaoDocumento());
		getDocumentoPainelCad().getGuiAnoRecebimentoDocumento().setText(documento.getAnoRecebimentoDocumento());
		getDocumentoPainelCad().getGuiCNPJRecebedorDocumento().setText(documento.getCnpjRecebedorDocumento());
		getDocumentoPainelCad().getGuiCPFRecebedorDocumento().setText(documento.getCpfRecebedorDocumento());
		getDocumentoPainelCad().getGuiDiaDevolucaoDocumento().setText(documento.getDiaDevolucaoDocumento());
		getDocumentoPainelCad().getGuiDiaRecebimentoDocumento().setText(documento.getDiaRecebimentoDocumento());
		getDocumentoPainelCad().getGuiLocalDocumento().setText(documento.getLocalDocumento());
		getDocumentoPainelCad().getGuiMesDevolucaoDocumento().setSelectedItem(documento.getMesDevolucaoDocumento());
		getDocumentoPainelCad().getGuiMesRecebimentoDocumento().setSelectedItem(documento.getMesRecebimentoDocumento());
		getDocumentoPainelCad().getGuiNomeProprietário().setText(documento.getNomeProprietario());
		getDocumentoPainelCad().getGuiNomeRecebedorDocumento().setText(documento.getNomeRecebedorDocumento());
		getDocumentoPainelCad().getGuiVeiculo().setText(documento.getDescricao());
		getDocumentoPainelCad().getGuiRGNumeroRecebedorDocumento().setText(documento.getRgNumeroRecebedorDocumento());
		getDocumentoPainelCad().getGuiRGOrgaoEmisssorRecebedorDocumento()
				.setText(documento.getRgOrgaoEmisssorRecebedorDocumento());
		getDocumentoPainelCad().getGuiSituacaoDocumento().setSelectedItem(documento.getSituacaoDocumento());
		getDocumentoPainelCad().getGuiTipoDocumento().setSelectedItem(documento.getTipoDocumento());
	}

	public void atualizarObjeto() {
		documento.setAnoDevolucaoDocumento(getDocumentoPainelCad().getGuiAnoDevolucaoDocumento().getText());
		documento.setAnoRecebimentoDocumento(getDocumentoPainelCad().getGuiAnoRecebimentoDocumento().getText());
		documento.setCnpjRecebedorDocumento(getDocumentoPainelCad().getGuiCNPJRecebedorDocumento().getText());
		documento.setCpfRecebedorDocumento(getDocumentoPainelCad().getGuiCPFRecebedorDocumento().getText());
		documento.setDiaDevolucaoDocumento(getDocumentoPainelCad().getGuiDiaDevolucaoDocumento().getText());
		documento.setDiaRecebimentoDocumento(getDocumentoPainelCad().getGuiDiaRecebimentoDocumento().getText());
		documento.setLocalDocumento(getDocumentoPainelCad().getGuiLocalDocumento().getText());
		documento.setMesDevolucaoDocumento(
				(String) getDocumentoPainelCad().getGuiMesDevolucaoDocumento().getSelectedItem());
		documento.setMesRecebimentoDocumento(
				(String) getDocumentoPainelCad().getGuiMesRecebimentoDocumento().getSelectedItem());
		documento.setNomeRecebedorDocumento(getDocumentoPainelCad().getGuiNomeRecebedorDocumento().getText());
		documento.setDescricao(getDocumentoPainelCad().getGuiNomeProprietário().getText());
		documento.setRgNumeroRecebedorDocumento(getDocumentoPainelCad().getGuiRGNumeroRecebedorDocumento().getText());
		documento.setRgOrgaoEmisssorRecebedorDocumento(
				getDocumentoPainelCad().getGuiRGOrgaoEmisssorRecebedorDocumento().getText());
		documento.setSituacaoDocumento((String) getDocumentoPainelCad().getGuiSituacaoDocumento().getSelectedItem());
		documento.setTipoDocumento((String) getDocumentoPainelCad().getGuiTipoDocumento().getSelectedItem());
	}

	public Documento getDocumento() {
		return documento;
	}

	public DocumentoJanCad getDocumentoJanCad() {
		return MainControl.getDocumentoJanCad();
	}

	public DocumentoJanPesq getDocumentoJanPesq() {
		return MainControl.getDocumentoJanPesq();
	}

	public DocumentoPainelCad getDocumentoPainelCad() {
		return MainControl.getDocumentoJanCad().getDocumentoPainelCad();
	}

	public DocumentoPainelPesq getDocumentoPainelPesq() {
		return MainControl.getDocumentoJanPesq().getDocumentoPainelPesq();
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
}
