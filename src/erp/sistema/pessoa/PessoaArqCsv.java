package erp.sistema.pessoa;

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

public class PessoaArqCsv {

	private final String arquivo = "usuario.csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public PessoaArqCsv(final List<Pessoa> listPessoa) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME");			
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Pessoa pessoa : listPessoa) {
				StringBuffer linha = new StringBuffer();
				linha.append(pessoa.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(pessoa.getNome());
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
