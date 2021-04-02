package erp.sistema.dados;

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

public class DadosRel {

	private String arquivo = Data.getHora() + "-contador.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "contadores";
	private PdfWriter writer = null;

	public DadosRel(List<Dados> contadores) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Dados contador : contadores) {
				document.newPage();
				document.add(new Paragraph("NOME: " + contador.getNome()));
				document.add(new Paragraph("CNPJ: " + contador.getCnpj()));
				document.add(new Paragraph("TELEFONE: " + contador.getFone1()));
				document.add(new Paragraph("E-MAIL: " + contador.getEmail()));
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
