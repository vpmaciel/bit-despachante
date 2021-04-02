
package erp.escritorio.veiculo.pedido.placa;

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

public class PedidoPlacaArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[pedido-placa]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public PedidoPlacaArqCsv(final List<PedidoPlaca> listPedidoPlaca) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CIDADE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("COR");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ESTADO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FAZER-PLACA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FAZER-TARJETA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("PLACA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RENAVAM");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (PedidoPlaca pedidoPlaca : listPedidoPlaca) {
				StringBuffer linha = new StringBuffer();
				linha.append(pedidoPlaca.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getCidade());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getCor());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getEstado());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getFazerPlaca());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getFazerTarjeta());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getNome());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getPlaca());
				linha.append(CSV_SEPARATOR);
				linha.append(pedidoPlaca.getRenavam());
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
