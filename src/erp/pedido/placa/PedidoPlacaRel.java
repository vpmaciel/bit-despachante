package erp.pedido.placa;

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

public class PedidoPlacaRel {

    private String arquivo = Sis.getCaminhoApp() + Data.getDataHoraArquivo() + "pedido-de-placa.pdf";
    private Document document = new Document();
    private Relatorio relatorio = new Relatorio();
    private PdfWriter writer = null;
    private Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);

    public PedidoPlacaRel(List<PedidoPlaca> pedidoList) {

	try {
	    writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
	    relatorio.criarRelatorio(writer, document);

	    long contador = 1;

	    for (PedidoPlaca pedido : pedidoList) {
		relatorio.getCabecalho(writer, document, "PEDIDO DE PLACA");
		document.add(new Paragraph("DATA: " + pedido.getData(), font));
		document.add(new Paragraph("QUANTIDADE: " + pedido.getQuantidade(), font));
		document.add(new Paragraph("PLACA DO VEÍCULO: " + pedido.getPlaca(), font));
		document.add(new Paragraph("COR DA PLACA: " + pedido.getCorPlaca(), font));
		document.add(new Paragraph("TIPO DE PLACA: " + pedido.getTipoPlaca(), font));
		document.add(new Paragraph("CPF | CNPJ DO PROPRIETÁRIO: " + pedido.getCpfCnpjProprietario(), font));
		document.add(new Paragraph("RENAVAM: " + pedido.getRenavam(), font));
		relatorio.getRodape(writer, document);
		if (contador < pedidoList.size()) {
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
