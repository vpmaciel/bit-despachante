package erp.pedido.placa;

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
    private final String arquivo = Data.getDataHoraArquivo() + "pedido-de-placa.csv";

    private BufferedWriter bufferedWriter = null;
    private final String CSV_SEPARATOR = Sis.getCsvSeparador();
    private File file;

    public PedidoPlacaArqCsv(final List<PedidoPlaca> listPedidoPlaca) {
	try {
	    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

	    StringBuffer cabecalho = new StringBuffer();
	    cabecalho.append("PEDIDO_DE_PLACA_ID");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_DATA");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_PLACA_VEICULO");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_QUANTIDADE");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_RENAVAM");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_CPF_CNPJ_PROPRIETARIO");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_COR_PLACA");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("PEDIDO_DE_PLACA_TIPO_PLACA");
	    cabecalho.append(CSV_SEPARATOR);
	    bufferedWriter.write(cabecalho.toString());

	    bufferedWriter.newLine();
	    for (PedidoPlaca pedidoPlaca : listPedidoPlaca) {
		StringBuffer linha = new StringBuffer();
		linha.append(pedidoPlaca.getId());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getData());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getPlaca());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getQuantidade());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getRenavam());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getCpfCnpjProprietario());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getCorPlaca());
		linha.append(CSV_SEPARATOR);
		linha.append(pedidoPlaca.getTipoPlaca());
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
