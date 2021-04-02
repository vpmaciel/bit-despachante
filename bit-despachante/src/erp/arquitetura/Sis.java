package erp.arquitetura;

import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import erp.arquitetura.gui.Msg;
import erp.sistema.usuario.Usuario;

public class Sis {
	private static final String AppSeparador = System.getProperty("file.separator");
	private static final String caminhoAppRaiz =  System.getProperty("user.dir");
	private static final String caminhoAppSistema = caminhoAppRaiz + AppSeparador + "bit" + AppSeparador;
	private static final String caminhoDiretorioApp = caminhoAppSistema + AppSeparador + "despachante" + AppSeparador;
	private static final String caminhoDiretorioAppDados = caminhoDiretorioApp + "dados" + AppSeparador;
	private static final String caminhoDiretorioDadosCsv = caminhoDiretorioAppDados + "csv" + AppSeparador;
	private static final String caminhoDiretorioDadosJson = caminhoDiretorioAppDados + "json" + AppSeparador;
	private static final String caminhoDiretorioDadosPdf = caminhoDiretorioAppDados + "pdf" + AppSeparador;
	private static final String caminhoDiretorioDadosTxt = caminhoDiretorioAppDados + "txt" + AppSeparador;
	private static final String caminhoDiretorioDadosXml = caminhoDiretorioAppDados + "xml" + AppSeparador;
	private static final String caminhoDiretorioLog = caminhoDiretorioApp + "logs";
	private static final String caminhoDiretorioOds = caminhoDiretorioAppDados + "ods" + AppSeparador;
	private static final String caminhoDiretorioVideos = caminhoDiretorioApp + "videos";
	private static final String csvSeparador = String.format("%s", ";");
	private static Sis instancia;
	private static Locale locale;
	private static Dimension tamanhoTela;
	private static Usuario usuario;

	static {
		locale = new Locale("pt", "BR");
		Locale.setDefault(locale);
		tamanhoTela = new Dimension(800, 585);
		criarDiretorios();
	}

	public static void abrirDiretorio(String URL) {
		String text, text2;
		text = System.getProperty("os.name");
		text = text.toLowerCase();
		text2 = URL;

		if (text.contains("linux") && !text2.equals("")) {
			try {
				Runtime.getRuntime().exec("konkeror " + URL); // Seu gerenciador de arquivos: konkeror (KDE), dolphin,
																// nautilus (gnome) e etc
			} catch (IOException ex) {
				System.out.println("Gerenciador de arquivos não instalado.");
			}
		} else if (text.contains("windows") && !text2.equals("")) {
			try {
				Runtime.getRuntime().exec("explorer.exe " + URL); // A url, que no caso é C:/
			} catch (IOException ex) {
				System.out.println("Desculpe, falha na execução dessa função!");
			}
		}

	}

	public static void criarDiretorios() {
		try {
			File arquivo = new File(caminhoAppSistema);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioApp);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioAppDados);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioLog);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioOds);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosCsv);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosJson);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosPdf);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosTxt);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosXml);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioVideos);
			arquivo.mkdir();
		} catch (Exception exception) {
			exception.printStackTrace();
			Msg.erroCriarPasta();
		}
	}

	public static Border getBordaPainel() {
		return BorderFactory.createTitledBorder("DADOS");
	}

	public static String getCaminhoDadosCsv() {
		return caminhoDiretorioDadosCsv;
	}

	public static String getCaminhoDadosJson() {
		return caminhoDiretorioDadosJson;
	}

	public static String getCaminhoDadosPdf() {
		return caminhoDiretorioDadosPdf;
	}

	public static String getCaminhoDadosTxt() {
		return caminhoDiretorioDadosTxt;
	}

	public static String getCaminhoDadosXml() {
		return caminhoDiretorioDadosXml;
	}

	public static String getCaminhoDiretoriolog() {
		return caminhoDiretorioLog;
	}

	public static String getCaminhoDiretorioOds() {
		return caminhoDiretorioOds;
	}

	public static String getCaminhoDiretoriovideos() {
		return caminhoDiretorioVideos;
	}

	public static String getCsvSeparador() {
		return csvSeparador;
	}

	public static synchronized Sis getInstancia() {
		return instancia == null ? new Sis() : instancia;
	}

	public static Locale getLocale() {
		return new Locale("pt", "BR");
	}

	public static String getNomeHost() {
		try {
			return InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	public static String getNomeSistema() {
		return "BIT - DESPACHANTE";
	}

	public static Cursor getNovaJanelaCursor() {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}

	public static Format getNumeroFormatado() {

		return NumberFormat.getNumberInstance(Sis.locale);
	}

	public static String getSeparador() {
		return AppSeparador;
	}

	public static Dimension getTamanhoJanela() {
		return tamanhoTela;
	}

	public static Usuario getUsuario() {
		return Sis.usuario;
	}

	public static String getUsuarioFormatado() {
		if (Sis.usuario == null) {
			return " - [ ]";
		}
		return " - [ " + Sis.usuario + " ]";
	}

	public static void setUsuario(Usuario usuario) {
		Sis.usuario = usuario;
	}
}
