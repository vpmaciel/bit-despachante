package erp.negocio.veiculo.documento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import erp.arquitetura.Data;
import erp.arquitetura.Relatorio;

public class VeiculoDocumentoRel {

	private String arquivo = Data.getHora() + "-veiculoDocumento.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "CLIENTES";
	private PdfWriter writer = null;

	public VeiculoDocumentoRel(List<VeiculoDocumento> veiculoDocumentos) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (VeiculoDocumento veiculoDocumento : veiculoDocumentos) {
				document.newPage();
				document.add(new Paragraph("NÚMERO DO PROTOCOLO: " + veiculoDocumento.getId()));
				document.add(new Paragraph("PLACA DO VEÍCULO: " + veiculoDocumento.getVeiculo().getPlaca()));
				document.add(new Paragraph("RENAVAM: " + veiculoDocumento.getVeiculo().getRenavam()));
				document.add(new Paragraph("CHASSI: " + veiculoDocumento.getVeiculo().getChassi()));
				document.add(new Paragraph(
						"NOME DO PROPRIETÁRIO DO VEÍCULO: " + veiculoDocumento.getVeiculo().getProprietarioNome()));
				document.add(new Paragraph("MARCA: " + veiculoDocumento.getVeiculo().getMarca()));
				document.add(new Paragraph("MODELO: " + veiculoDocumento.getVeiculo().getModelo()));
				document.add(new Paragraph("SITUAÇÃO DO DOCUMENTO: " + veiculoDocumento.getSituacaoVeiculoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph("DATA DE RECEBIMENTO DO DOCUMENTO (CRV E CRLV): "
						+ veiculoDocumento.getDataRecebimentoVeiculoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph("DATA DE DEVOLUÇÃO DO DOCUMENTO (CRV E CRLV): "
						+ veiculoDocumento.getDataDevolucaoVeiculoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph("NOME DO RECEBEDOR DO DOCUMENTO (CRV E CRLV): "
						+ veiculoDocumento.getNomeRecebedorVeiculoDocumento()));
				document.add(new Paragraph(
						"IDENTIDADE DO RECEBEDOR: " + veiculoDocumento.getRgNumeroRecebedorVeiculoDocumento()));
				document.add(new Paragraph("ORGÃO EMISSOR DE IDENTIDADE DO RECEBEDOR: "
						+ veiculoDocumento.getRgOrgaoEmisssorRecebedorVeiculoDocumento()));
				document.add(new Paragraph("CNPJ DO RECEBEDOR DO DOCUMENTO (CRV E CRLV): "
						+ veiculoDocumento.getCnpjRecebedorVeiculoDocumento()));
				document.add(new Paragraph("CPF DO RECEBEDOR DO DOCUMENTO (CRV E CRLV): "
						+ veiculoDocumento.getCpfRecebedorVeiculoDocumento()));
				document.add(new Paragraph(
						"DOCUMENTO ESTÁ EM POSSE COM (CRV E CRLV): " + veiculoDocumento.getLocalVeiculoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph(
						"ASSINATURA DO RECEBEDOR DO DOCUMENTO (CRV E CRLV):_____________________________________________________________ "));
			}
		} catch (DocumentException | FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		relatorio.getRodape(writer, document);
		document.close();
		relatorio.retornarRelatorio(arquivo, false);
	}

	public File retornarRelatorio(boolean abrirArquivo) {
		return relatorio.retornarRelatorio(arquivo, abrirArquivo);
	}
}
