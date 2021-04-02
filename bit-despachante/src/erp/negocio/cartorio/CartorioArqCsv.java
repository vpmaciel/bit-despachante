package erp.negocio.cartorio;

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

public class CartorioArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[cartorio]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public CartorioArqCsv(final List<Cartorio> listCartorio) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CNPJ");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("COMARCA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DISTRITO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("E-MAIL");
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
			cabecalho.append("FAX");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE1");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FONE2");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MUNICÍPIO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NOME FANTASIA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RAZÃO SOCIAL");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SUBSTITUTO");

			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Cartorio cartorio : listCartorio) {
				StringBuffer linha = new StringBuffer();
				linha.append(cartorio.getId());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getCnpj());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getComarca());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getDistrito());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEmail());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoBairro());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoCep());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoCidade());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoComplemento());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoEstado());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoLogradouro());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getEnderecoPais());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getFax());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getFone1());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getFone2());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getMunicipio());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getNomeFantasia());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getRazaoSocial());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getSubstituto());
				linha.append(CSV_SEPARATOR);
				linha.append(cartorio.getTitular());
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
