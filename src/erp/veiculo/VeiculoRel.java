package erp.veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import erp.arquitetura.Data;
import erp.arquitetura.Relatorio;
import erp.arquitetura.Sis;

public class VeiculoRel {
	private String arquivo = Sis.getCaminhoApp() + Data.getDataHoraArquivo() + "pedido-de-placa.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private PdfWriter writer = null;
	private Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);

	public VeiculoRel(List<Veiculo> veiculoList) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document);

			long contador = 1;

			for (Veiculo veiculo : veiculoList) {
				relatorio.getCabecalho(writer, document, "VEICULO");
				document.add(new Paragraph("PLACA DO VEÍCULO: " + veiculo.getPlaca(), font));
				document.add(new Paragraph("MARCA DO VEÍCULO: " + veiculo.getMarca(), font));
				document.add(new Paragraph("MODELO DO VEÍCULO: " + veiculo.getModelo(), font));
				document.add(new Paragraph("CPF | CNPJ DO PROPRIETÁRIO: " + veiculo.getCpfCnpjProprietario(), font));
				document.add(new Paragraph("NOME DO PROPRIETÁRIO: " + veiculo.getNomeProprietario(), font));
				relatorio.getRodape(writer, document);
				if (contador < veiculoList.size()) {
					document.newPage();
				}
				contador++;

			}
			document.close();
			relatorio.retornarRelatorio(arquivo);
		} catch (DocumentException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado !", "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Relatório não pode ser gerado !", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	public File retornarRelatorio() {
		return relatorio.retornarRelatorio(arquivo);
	}
}
