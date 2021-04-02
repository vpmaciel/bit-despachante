
package erp.negocio.veiculo;

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

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[veiculo]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = Sis.getCsvSeparador();
	private File file;

	public VeiculoArqCsv(final List<Veiculo> listVeiculo) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("ID");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-FABRICACAO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-MODELO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-REFERENCIA-CADASTRO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-REFERENCIA-COMPRA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ANO-REFERENCIA-VENDA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CENTROCUSTO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CHASSI");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("CMTTON");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATACOMPRA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DATAVENDA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FABRICACAO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MARCA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MES-REFERENCIA-CADASTRO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MES-REFERENCIA-COMPRA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MES-REFERENCIA-VENDA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("MODELO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NUMERO-MOTOR");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("NUMERO-PORTAS");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("PLACA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RENAVAM");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RESTRICOES");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("RODAS");
			cabecalho.append("TIPO");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VALOR-COMPRA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VALOR-VENDA");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("VALVULAS");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ZERO-KM");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Veiculo veiculo : listVeiculo) {
				StringBuffer linha = new StringBuffer();
				linha.append(veiculo.getId());
				linha.append(CSV_SEPARATOR);
				
				linha.append(veiculo.getAnoFabricacao());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getAnoModelo());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getAnoReferenciaCompra());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getAnoReferenciaVenda());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getCentroCusto());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getChassi());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getDataCompra());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getDataVenda());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getFabricacao());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getMarca());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getMesReferenciaCadastro());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getMesReferenciaCompra());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getMesReferenciaVenda());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getModelo());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getNumeroMotor());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getPlaca());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getRenavam());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getRestricoes());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getRodas());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getSubCategoria());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getTipo());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getValorCompra());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getValorVenda());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getValvulas());
				linha.append(CSV_SEPARATOR);
				linha.append(veiculo.getZeroKm());

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
