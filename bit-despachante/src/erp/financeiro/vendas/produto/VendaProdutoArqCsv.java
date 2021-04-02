
package erp.financeiro.vendas.produto;

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

public class VendaProdutoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[vendaProduto]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public VendaProdutoArqCsv(final List<VendaProduto> listVendaProduto) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CLIENTE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ENTRADA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ESTORNO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("HORA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("QTDE");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("PRODUTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("USUARIO");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (VendaProduto vendaProduto : listVendaProduto) {
				StringBuffer linha = new StringBuffer();
				linha.append(vendaProduto.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getCliente());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getData());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getEntrada());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getEstorno());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getHora());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getQtde());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getProduto());
				linha.append(CSV_SEPARATOR);
				linha.append(vendaProduto.getUsuario());
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
