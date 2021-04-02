
package erp.negocio.veiculo.documento;

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

public class VeiculoDocumentoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[veiculoDocumento]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public VeiculoDocumentoArqCsv(final List<VeiculoDocumento> listVeiculoDocumento) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ-RECEBEDOR-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CPF-RECEBEDOR-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA-DEVOLUCAO-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA-RECEBIMENTO-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("LOCAL-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME-RECEBEDOR-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RG-NUMERO-RECEBEDOR-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RG-ORGAO-EMISSSOR-RECEBEDOR-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SITUACAO-VEICULO-DOCUMENTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VEICULO");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (VeiculoDocumento veiculoDocumento : listVeiculoDocumento) {
				StringBuffer linha = new StringBuffer();
				linha.append(veiculoDocumento.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getCnpjRecebedorVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getCpfRecebedorVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getDataDevolucaoVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getDataRecebimentoVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getLocalVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getNomeRecebedorVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getRgNumeroRecebedorVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getRgOrgaoEmisssorRecebedorVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getSituacaoVeiculoDocumento());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculoDocumento.getVeiculo());

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
