package erp.usuario;

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

public class UsuarioRel {

	private String arquivo = Sis.getCaminhoApp() + Data.getDataHoraArquivo() + "usuario.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private PdfWriter writer = null;
	private Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);

	public UsuarioRel(List<Usuario> usuarioList) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document);

			long contador = 1;

			for (Usuario usuario : usuarioList) {
				relatorio.getCabecalho(writer, document, "CLIENTE");
				document.add(new Paragraph("NOME: " + usuario.getNome(), font));				
				document.add(new Paragraph("EMAIL: " + usuario.getEmail(), font));				
				relatorio.getRodape(writer, document);
				if (contador < usuarioList.size()) {
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
