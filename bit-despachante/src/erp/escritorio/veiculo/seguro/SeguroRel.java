package erp.escritorio.veiculo.seguro;

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

public class SeguroRel {

	private String arquivo = Data.getHora() + "-centro-de-custo.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "CENTROS DE CUSTO";
	private PdfWriter writer = null;

	public SeguroRel(List<Seguro> seguros) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Seguro seguro : seguros) {
				document.add(new Paragraph("NOME: " + seguro.getNome()));
				document.add(new Paragraph("\n"));
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
