package erp.utilitarios.agenda.recado;

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

public class RecadoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[agenda-recado]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public RecadoArqCsv(final List<Recado> listRecado) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DESTINATÁRIO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RECADO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("REMETENTE");

			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Recado recado : listRecado) {
				StringBuffer linha = new StringBuffer();
				linha.append(recado.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(recado.getData());
				linha.append(CSV_SEPARATOR);
				linha.append(recado.getDestinatario());
				linha.append(CSV_SEPARATOR);
				linha.append(recado.getRecado());
				linha.append(CSV_SEPARATOR);
				linha.append(recado.getRemetente());
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
