package erp.negocio.produto;

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
import erp.arquitetura.Sis;

public final class ProdutoRel {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[Produto]-" + Data.getDataHoraArquivo() + ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "ProdutoS";
	private PdfWriter writer = null;

	public ProdutoRel(List<Produto> listProduto) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Produto produto : listProduto) {
				document.add(new Paragraph("Produto: " + produto.getNome()));
				document.add(new Paragraph("CÓDIGO DO Produto: " + produto.getPreco()));
				document.add(new Paragraph("\n"));
			}
		} catch (DocumentException | FileNotFoundException exception) {
			exception.printStackTrace();
		}
		relatorio.getRodape(writer, document);
		document.close();
		Sis.abrirDiretorio(Sis.getCaminhoDadosPdf());
		relatorio.retornarRelatorio(arquivo, false);
	}

	public File retornarRelatorio(boolean abrirArquivo) {
		return relatorio.retornarRelatorio(arquivo, abrirArquivo);
	}
}
