package erp.escritorio.produto.compra.lista;

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

public class CompraProdutoRel {

	private String arquivo = Data.getDataHoraArquivo() + "-centro-de-custo.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "CENTROS DE CUSTO";
	private PdfWriter writer = null;

	public CompraProdutoRel(List<CompraProduto> compraProdutos) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (CompraProduto compraProduto : compraProdutos) {
				document.add(new Paragraph("NOME: " + compraProduto.getNome()));
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
