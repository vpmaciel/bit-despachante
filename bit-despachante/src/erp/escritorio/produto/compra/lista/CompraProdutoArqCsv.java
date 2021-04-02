
package erp.escritorio.produto.compra.lista;

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

public class CompraProdutoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[compra-produto]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public CompraProdutoArqCsv(final List<CompraProduto> listCompraProduto) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("QUANTIDADE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("UNIDADE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MARCA");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (CompraProduto compraProduto : listCompraProduto) {
				StringBuffer linha = new StringBuffer();
				linha.append(compraProduto.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(compraProduto.getNome());
				linha.append(CSV_SEPARATOR);
				linha.append(compraProduto.getQuantidade());
				linha.append(CSV_SEPARATOR);
				linha.append(compraProduto.getProdutoUnidade());
				bufferedWriter.write(linha.toString());
				linha.append(compraProduto.getProdutoMarca());
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
