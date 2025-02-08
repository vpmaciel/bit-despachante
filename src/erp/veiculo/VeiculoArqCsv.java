package erp.veiculo;

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

public class VeiculoArqCsv {

	private final String arquivo = Data.getDataHoraArquivo() + "veiculo.csv";
	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public VeiculoArqCsv(final List<Veiculo> veiculoList) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("VEICULO_ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VEICULO_PLACA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VEICULO_MARCA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VEICULO_CPF_CNPJ_PROPRIETARIO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VEICULO_NOME_PROPRIETARIO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VEICULO_MODELO");
			cabecalho.append(CSV_SEPARATOR);
			bufferedWriter.write(cabecalho.toString());

			bufferedWriter.newLine();
			for (Veiculo pedidoPlaca : veiculoList) {
				StringBuffer linha = new StringBuffer();
				linha.append(pedidoPlaca.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getPlaca());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getMarca());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getCpfCnpjProprietario());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getNomeProprietario());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getCpfCnpjProprietario());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getModelo());
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
