package erp.negocio.fornecedor;

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

public class FornecedorRel {

	private String arquivo = Data.getHora() + "-fornecedor.pdf";
	private Document document = new Document();
	private Relatorio relatorio = new Relatorio();
	private String titulo = "FORNECEDORES";
	private PdfWriter writer = null;

	public FornecedorRel(List<Fornecedor> fornecedores) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Fornecedor fornecedor : fornecedores) {
				document.newPage();
				document.add(new Paragraph("NOME FANTASIA: " + fornecedor.getNomeFantasia()));
				document.add(new Paragraph("TIPO DE EMPRESA: " + fornecedor.getTipoEmpresa()));
				document.add(new Paragraph("CNPJ: " + fornecedor.getCnpj()));
				document.add(new Paragraph("CPF: " + fornecedor.getCpf()));
				document.add(new Paragraph("CAPITAL SOCIAL: " + fornecedor.getCapitalSocial()));
				document.add(new Paragraph("RAZÃO SOCIAL: " + fornecedor.getRazaoSocial()));
				document.add(new Paragraph("FAX: " + fornecedor.getFax()));
				document.add(new Paragraph("TELEFONE: " + fornecedor.getFone1()));
				document.add(new Paragraph("TELEFONE: " + fornecedor.getFone2()));
				document.add(new Paragraph("E-MAIL: " + fornecedor.getEmail()));
				document.add(new Paragraph("PAÍS: " + fornecedor.getEnderecoPais()));
				document.add(new Paragraph("ESTADO: " + fornecedor.getEnderecoEstado()));
				document.add(new Paragraph("CIDADE: " + fornecedor.getEnderecoCidade()));
				document.add(new Paragraph("BAIRRO: " + fornecedor.getEnderecoBairro()));
				document.add(new Paragraph("LOGRADOURO: " + fornecedor.getEnderecoLogradouro()));
				document.add(new Paragraph("COMPLEMENTO: " + fornecedor.getEnderecoComplemento()));
				document.add(new Paragraph("CEP: " + fornecedor.getEnderecoCep()));
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
