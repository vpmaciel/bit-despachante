package erp.sistema.empresa;

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

public class EmpresaRel {

	private String arquivo = Sis.getCaminhoApp() + "usuario.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "USUÁRIOS";
	private PdfWriter writer = null;

	public EmpresaRel(List<Empresa> empresas) {

		try {			
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Empresa empresa : empresas) {
				document.add(new Paragraph("USUÁRIO: " + empresa.getNome()));
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
