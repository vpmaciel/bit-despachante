package erp.escritorio.documento;

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

public class DocumentoRel {

	private String arquivo = Data.getDataHoraArquivo() + "-documento.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "CLIENTES";
	private PdfWriter writer = null;

	public DocumentoRel(List<Documento> documentos) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Documento documento : documentos) {
				document.newPage();
				document.add(new Paragraph("NÚMERO DO PROTOCOLO: " + documento.getId()));
				document.add(new Paragraph("DESCRIÇÃO DO DOCUMENTO: " + documento.getDescricao()));
				document.add(new Paragraph("NOME DO PROPRIETÁRIO DO DOCUMENTO: " + documento.getNomeProprietario()));
				document.add(new Paragraph("SITUAÇÃO DO DOCUMENTO: " + documento.getSituacaoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(
						new Paragraph("ANO DE RECEBIMENTO DO DOCUMENTO: " + documento.getAnoRecebimentoDocumento()));
				document.add(
						new Paragraph("MÊS DE RECEBIMENTO DO DOCUMENTO: " + documento.getMesRecebimentoDocumento()));
				document.add(
						new Paragraph("DATA DE RECEBIMENTO DO DOCUMENTO: " + documento.getDiaRecebimentoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph("ANO DE DEVOLUÇÃO DO DOCUMENTO: " + documento.getAnoDevolucaoDocumento()));
				document.add(new Paragraph("MÊS DE DEVOLUÇÃO DO DOCUMENTO: " + documento.getMesDevolucaoDocumento()));
				document.add(new Paragraph("DATA DE DEVOLUÇÃO DO DOCUMENTO: " + documento.getDiaDevolucaoDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph("NOME DO RECEBEDOR DO DOCUMENTO: " + documento.getNomeRecebedorDocumento()));
				document.add(new Paragraph("IDENTIDADE DO RECEBEDOR: " + documento.getRgNumeroRecebedorDocumento()));
				document.add(new Paragraph("ORGÃO EMISSOR DE IDENTIDADE DO RECEBEDOR: "
						+ documento.getRgOrgaoEmisssorRecebedorDocumento()));
				document.add(new Paragraph("CNPJ DO RECEBEDOR DO DOCUMENTO: " + documento.getCnpjRecebedorDocumento()));
				document.add(new Paragraph("CPF DO RECEBEDOR DO DOCUMENTO: " + documento.getCpfRecebedorDocumento()));
				document.add(new Paragraph("DOCUMENTO ESTÁ EM POSSE COM: " + documento.getLocalDocumento()));
				document.add(new Paragraph("------------------------------"));
				document.add(new Paragraph(
						"ASSINATURA DO RECEBEDOR DO DOCUMENTO:_____________________________________________________________ "));
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
