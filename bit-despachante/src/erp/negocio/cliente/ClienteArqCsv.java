
package erp.negocio.cliente;

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

public class ClienteArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[cliente]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public ClienteArqCsv(final List<Cliente> listCliente) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CARGO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CPF");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DEFICIENCIA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("EMAIL");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("EMPRESA");
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
			cabecalho.append("FONE1");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE2");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NACIONALIDADE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RG-NUMERO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RGORGAO-EMISSOR");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SALARIO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SEXO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("STATUS");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Cliente cliente : listCliente) {
				StringBuffer linha = new StringBuffer();
				linha.append(cliente.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getCargo());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getCnpj());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getCpf());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getDataNascimento());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getDeficiencia());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEmail());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoBairro());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoCep());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoCidade());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoComplemento());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoEstado());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoLogradouro());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEnderecoPais());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEscolaridade());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEstadoCivil());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEmpresa());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getFone1());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getFone2());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getNome());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getSalario());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getSexo());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getStatus());

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
