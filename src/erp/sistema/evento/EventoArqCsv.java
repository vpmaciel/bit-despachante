package erp.sistema.evento;

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

public class EventoArqCsv {

	private final String arquivo = Sis.getCaminhoApp() + "[agenda-evento]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public EventoArqCsv(final List<Evento> listEvento) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SEXO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("E-MAIL");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FAX");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("EMPRESA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-BAIRRO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-CEP");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-CIDADE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-COMPLEMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-ESTADO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-LOGRADOURO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENDEREÇO-PAÍS");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CPF");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Evento evento : listEvento) {
				StringBuffer linha = new StringBuffer();
				linha.append(evento.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(evento.getData());
				linha.append(CSV_SEPARATOR);
				linha.append(evento.getDescricao());
				linha.append(CSV_SEPARATOR);
				linha.append(evento.getHoraInicio());
				linha.append(CSV_SEPARATOR);
				linha.append(evento.getHoraTermino());
				linha.append(CSV_SEPARATOR);
				linha.append(evento.getTipoEvento());
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

	public File retornarArquivo() {

		try {
			Sis.abrirDiretorio();
			file = new File(arquivo);
			Desktop.getDesktop().open(file);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
}
