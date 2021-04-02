
package erp.escritorio.veiculo.seguro;

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

public class SeguroArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[seguro]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public SeguroArqCsv(final List<Seguro> listSeguro) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CORRETOR");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA-FIM-VIGENCIA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA-INICIO-VIGENCIA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SEGURADORA");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Seguro seguro : listSeguro) {
				StringBuffer linha = new StringBuffer();
				linha.append(seguro.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(seguro.getCorretor());
				linha.append(CSV_SEPARATOR);
				linha.append(seguro.getDataFimVigencia());
				linha.append(CSV_SEPARATOR);
				linha.append(seguro.getDataInicioVigencia());
				linha.append(CSV_SEPARATOR);
				linha.append(seguro.getNome());
				linha.append(CSV_SEPARATOR);
				linha.append(seguro.getSeguradora());

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
