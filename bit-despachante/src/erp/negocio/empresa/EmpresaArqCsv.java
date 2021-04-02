
package erp.negocio.empresa;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import erp.arquitetura.Data;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;

public class EmpresaArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[empresa]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public EmpresaArqCsv(final List<Empresa> listEmpresa) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("E-MAIL");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-BAIRRO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-CEP");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-CIDADE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-COMPLEMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-ESTADO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-LOGRADOURO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDERECO-PAIS");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FAX");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE1");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE2");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME-FANTASIA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("TIPO-EMPRESA");

			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Empresa empresa : listEmpresa) {
				StringBuffer linha = new StringBuffer();
				linha.append(empresa.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getCnpj());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEmail());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoBairro());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoCep());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoCidade());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoComplemento());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoEstado());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoLogradouro());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getEnderecoPais());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getFax());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getFone1());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getFone2());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getNomeFantasia());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getRazaoSocial());
				linha.append(CSV_SEPARATOR);
				linha.append(empresa.getTipoEmpresa());

				bufferedWriter.write(linha.toString());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Msg.erroCodificacao();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Msg.erroArquivoNaoEncontrado();
		} catch (IOException e) {
			e.printStackTrace();
			Msg.erroAbrirArquivo();
		}
	}

	public File retornarArquivo(boolean abrirArquivo) {

		try {
			Sis.abrirDiretorio(Sis.getCaminhoDadosCsv());
			file = new File(arquivo);
			if (abrirArquivo) {
				Desktop.getDesktop().open(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
}
