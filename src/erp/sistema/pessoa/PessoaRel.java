package erp.sistema.pessoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import erp.arquitetura.Relatorio;
import erp.arquitetura.Sis;

public class PessoaRel {

	private String arquivo = Sis.getCaminhoApp() + "pessoa.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "USUÁRIOS";
	private PdfWriter writer = null;

	public PessoaRel(List<Pessoa> pessoas) {

		try {			
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Pessoa pessoa : pessoas) {
				document.add(new Paragraph("USUÁRIO: " + pessoa.getNome()));
			}
		} catch (DocumentException | FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		relatorio.getRodape(writer, document);
		document.close();
		relatorio.retornarRelatorio(arquivo);
	}

	public File retornarRelatorio() {
		return relatorio.retornarRelatorio(arquivo);
	}
}
