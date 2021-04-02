
package erp.financeiro.contasreceber;

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

public class ContasReceberArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[contas-receber]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public ContasReceberArqCsv(final List<ContasReceber> listContasReceber) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA-VENCIMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DESCRICAO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("STATUS");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VALOR");

			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (ContasReceber contasReceber : listContasReceber) {
				StringBuffer linha = new StringBuffer();
				linha.append(contasReceber.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(contasReceber.getDataVencimento());
				linha.append(CSV_SEPARATOR);
				linha.append(contasReceber.getDescricao());
				linha.append(CSV_SEPARATOR);
				linha.append(contasReceber.getStatus());
				linha.append(CSV_SEPARATOR);
				linha.append(contasReceber.getValor());
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
