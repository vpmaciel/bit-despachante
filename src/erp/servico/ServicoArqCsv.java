package erp.servico;

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

public class ServicoArqCsv {
    private final String arquivo = Data.getDataHoraArquivo() + "servico-de-placa.csv";

    private BufferedWriter bufferedWriter = null;
    private final String CSV_SEPARATOR = Sis.getCsvSeparador();
    private File file;

    public ServicoArqCsv(final List<Servico> listServico) {
	try {
	    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

	    StringBuffer cabecalho = new StringBuffer();
	    cabecalho.append("SERVICO_ID");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_DATA");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_PLACA_VEICULO");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_VALOR");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_DESCRICAO");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_CPF_CNPJ_CLIENTE");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_NOME_CLIENTE");
	    cabecalho.append(CSV_SEPARATOR);
	    cabecalho.append("SERVICO_TELEFONE_CLIENTE");
	    cabecalho.append(CSV_SEPARATOR);
	    bufferedWriter.write(cabecalho.toString());

	    bufferedWriter.newLine();
	    for (Servico servicoPlaca : listServico) {
		StringBuffer linha = new StringBuffer();
		linha.append(servicoPlaca.getId());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getData());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getPlaca());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getValor());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getDescricao());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getCpfCnpjCliente());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getNomeCliente());
		linha.append(CSV_SEPARATOR);
		linha.append(servicoPlaca.getTelefoneCliente());
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
