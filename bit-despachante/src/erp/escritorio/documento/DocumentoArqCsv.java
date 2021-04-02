
package erp.escritorio.documento;

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

public class DocumentoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[documento]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public DocumentoArqCsv(final List<Documento> listDocumento) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));
			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-DEVOLUCAO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-RECEBIMENTO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ-RECEBEDOR-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CPF-RECEBEDOR-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DESCRICAO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DIA-DEVOLUCAO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DIA-RECEBIMENTO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("LOCAL-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MES-DEVOLUCAO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MES-RECEBIMENTO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME-PROPRIETARIO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME-RECEBEDOR-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RG-NUMERO-RECEBEDOR-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RG-ORGAO-EMISSSOR-RECEBEDOR-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SITUACAO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("TIPO-DOCUMENTO");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Documento documento : listDocumento) {
				StringBuffer linha = new StringBuffer();
				linha.append(documento.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getAnoDevolucaoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getAnoRecebimentoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getCnpjRecebedorDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getCpfRecebedorDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getDescricao());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getDiaDevolucaoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getDiaRecebimentoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getLocalDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getMesDevolucaoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getMesRecebimentoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getNomeProprietario());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getNomeRecebedorDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getRgNumeroRecebedorDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getRgOrgaoEmisssorRecebedorDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getSituacaoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(documento.getTipoDocumento());
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
