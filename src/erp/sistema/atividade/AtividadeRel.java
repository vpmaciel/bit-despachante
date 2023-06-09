package erp.sistema.atividade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import erp.arquitetura.Relatorio;
import erp.arquitetura.Sis;

public class AtividadeRel {

	private String arquivo = Sis.getCaminhoApp() + "usuario.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "USUÁRIOS";
	private PdfWriter writer = null;

	public AtividadeRel(List<Atividade> atividades) {

		try {			
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Atividade atividade : atividades) {
				document.add(new Paragraph("USUÁRIO: " + atividade.getNome()));
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
