
package erp.negocio.sindicato;

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

public class SindicatoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sindicato]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public SindicatoArqCsv(final List<Sindicato> listSindicato) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("EMAIL");
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
			cabecalho.append("RAZAO-SOCIAL");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("TIPO-SINDICATO");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Sindicato sindicato : listSindicato) {
				StringBuffer linha = new StringBuffer();
				linha.append(sindicato.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getCnpj());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEmail());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoBairro());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoCep());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoCidade());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoComplemento());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoEstado());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoLogradouro());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getEnderecoPais());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getFax());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getFone1());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getFone2());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getNomeFantasia());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getRazaoSocial());
				linha.append(CSV_SEPARATOR);
				linha.append(sindicato.getTipoSindicato());
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
