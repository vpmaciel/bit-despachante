package erp.cliente;

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

public class ClienteRel {

	private String arquivo = Sis.getCaminhoApp() + "marca-de-veiculo.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "CONTAS";
	private PdfWriter writer = null;

	public ClienteRel(List<Cliente> marcas) {

		try {			
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Cliente marca : marcas) {
				document.add(new Paragraph("MARCA: " + marca.getCpfCnpj()));
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
