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

import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;

public class ClienteArqCsv {

	private final String arquivo = "marca-de-veiculo.csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public ClienteArqCsv(final List<Cliente> listUsuario) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("DESCRIÇÃO");						
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();
			for (Cliente marca : listUsuario) {
				StringBuffer linha = new StringBuffer();
				linha.append(marca.getCpfCnpj());
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
