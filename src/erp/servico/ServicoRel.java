package erp.servico;

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

public class ServicoRel {

    private String arquivo = Sis.getCaminhoApp() + Data.getDataHoraArquivo() + "pedido-de-placa.pdf";
    private Document document = new Document();
    private Relatorio relatorio = new Relatorio();
    private PdfWriter writer = null;
    private Font font = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);

    public ServicoRel(List<Servico> servicos) {

	try {
	    writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
	    relatorio.criarRelatorio(writer, document);

	    long contador = 1;

	    for (Servico servico : servicos) {
		relatorio.getCabecalho(writer, document, "PEDIDO DE PLACA");
		document.add(new Paragraph("DATA: " + servico.getData(), font));
		document.add(new Paragraph("PLACA DO VEÍCULO: " + servico.getPlaca(), font));
		document.add(new Paragraph("DESCRIÇÃO DO SERVIÇO: " + servico.getDescricao(), font));
		document.add(new Paragraph("VALOR DO SERVIÇO: " + servico.getValor(), font));
		document.add(new Paragraph("CPF | CNPJ DO CLIENTE: " + servico.getCpfCnpjCliente(), font));
		document.add(new Paragraph("NOME DO CLIENTE: " + servico.getNomeCliente(), font));
		document.add(new Paragraph("TELEFONE DO CLIENTE: " + servico.getTelefoneCliente(), font));
		relatorio.getRodape(writer, document);
		if (contador < servicos.size()) {
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
