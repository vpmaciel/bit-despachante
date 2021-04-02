package erp.negocio.veiculo;

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

public class VeiculoRel {

	private String arquivo = Data.getHora() + "-veiculo.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "VEÍCULOS";
	private PdfWriter writer = null;

	public VeiculoRel(List<Veiculo> veiculos) {
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Veiculo veiculo : veiculos) {
				document.newPage();
				document.add(new Paragraph("ANO FABRICAÇÃO: " + veiculo.getAnoFabricacao()));
				document.add(new Paragraph("ANO MODELO: " + veiculo.getAnoModelo()));
				document.add(new Paragraph("CHASSI: " + veiculo.getChassi()));
				document.add(new Paragraph("DATA COMPRA: " + veiculo.getDataCompra()));
				document.add(new Paragraph("DATA VENDA: " + veiculo.getDataVenda()));
				document.add(new Paragraph("FABRICAÇÃO: " + veiculo.getFabricacao()));
				document.add(new Paragraph("MARCA: " + veiculo.getMarca()));
				document.add(new Paragraph("MODELO: " + veiculo.getModelo()));
				document.add(new Paragraph("PLACA: " + veiculo.getPlaca()));
				document.add(new Paragraph("NOME DO PROPRIETÁRIO: " + veiculo.getProprietarioNome()));
				document.add(new Paragraph("RENAVAM: " + veiculo.getRenavam()));
				document.add(new Paragraph("RESTRIÇÃO FINANCEIRA: " + veiculo.getRestricoes()));
				document.add(new Paragraph("TIPO: " + veiculo.getTipo()));
				document.add(new Paragraph("VALOR DE COMPRA: " + veiculo.getValorCompra()));
				document.add(new Paragraph("VALOR DE VENDA: " + veiculo.getValorVenda()));
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
