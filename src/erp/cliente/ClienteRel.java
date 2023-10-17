package erp.cliente;

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

public class ClienteRel {

	private String arquivo = Sis.getCaminhoApp() + Data.getDataHoraArquivo() + "cliente.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private PdfWriter writer = null;
	private Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);

	public ClienteRel(List<Cliente> clienteList) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document);

			long contador = 1;

			for (Cliente cliente : clienteList) {
				relatorio.getCabecalho(writer, document, "CLIENTE");
				document.add(new Paragraph("NOME: " + cliente.getNome(), font));
				document.add(new Paragraph("CPF | CNPJ: " + cliente.getCpfCnpj(), font));
				document.add(new Paragraph("EMAIL: " + cliente.getEmail(), font));
				document.add(new Paragraph("TELEFONE: " + cliente.getTelefone(), font));
				relatorio.getRodape(writer, document);
				if (contador < clienteList.size()) {
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
