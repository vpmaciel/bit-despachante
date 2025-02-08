package erp.cliente;

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

	private final String arquivo = Data.getDataHoraArquivo() + "cliente.csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public ClienteArqCsv(final List<Cliente> listCliente) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("CLIENTE_IDENTIFICADOR");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CLIENTE_NOME");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CLIENTE_CPF_CNPJ");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CLIENTE_EMAIL");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CLIENTE_TELEFONE");
			cabecalho.append(CSV_SEPARATOR);
			bufferedWriter.write(cabecalho.toString());

			bufferedWriter.newLine();
			for (Cliente cliente : listCliente) {
				StringBuffer linha = new StringBuffer();
				linha.append(cliente.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getNome());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getCpfCnpj());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getEmail());
				linha.append(CSV_SEPARATOR);
				linha.append(cliente.getTelefone());
				linha.append(CSV_SEPARATOR);
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
			Sis.abrirDiretorio();
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
