package erp.arquitetura;

import java.awt.Desktop;
import java.io.File;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Relatorio {

	public void criarRelatorio(PdfWriter writer, Document document) {
		try {

			document.open();
			document.setPageSize(PageSize.A4);

		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao criar o relatório", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void getCabecalho(PdfWriter writer, Document document, String titulo) {
		try {
			Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
			Rectangle page = document.getPageSize();

			PdfPTable head = new PdfPTable(1);
			PdfPCell cell = new PdfPCell(new Paragraph(titulo, font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			head.addCell(cell);
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(),
					page.getHeight() - document.topMargin() + head.getTotalHeight(), writer.getDirectContent());

		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao definir o cabeçalho do relatório !", "Erro",
					JOptionPane.ERROR_MESSAGE);

			JOptionPane.showMessageDialog(null, "O arquivo já está sendo usado por outro processo !", "Erro",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	public void getRodape(PdfWriter writer, Document document) {
		try {
			Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
			Rectangle page = document.getPageSize();
			PdfPTable foot = new PdfPTable(1);
			PdfPCell cell = new PdfPCell(new Paragraph(Data.getDataHoraCompleta(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(0);
			foot.addCell(cell);
			foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());

		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao definir o rodapé do relatório !", "Erro",
					JOptionPane.ERROR_MESSAGE);

			JOptionPane.showMessageDialog(null, "O arquivo já está sendo usado por outro processo !", "Erro",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	public File retornarRelatorio(String caminho) {

		File arquivo = new File(caminho);

		try {
			Sis.abrirDiretorio();
			Desktop.getDesktop().open(arquivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arquivo;
	}
}
