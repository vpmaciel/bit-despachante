package erp.arquitetura;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import erp.usuario.Usuario;

public class Sis {
    private static String caminhoApp = System.getProperty("user.dir");
    private static String csvSeparador = String.format("%s", ";");
    private static Sis instancia;
    private static String separadorApp = System.getProperty("file.separator");
    private static Dimension tamanhoTela;
    @SuppressWarnings("unused")
    private static Usuario usuario;

    static {
	caminhoApp = caminhoApp + separadorApp;
	tamanhoTela = new Dimension(700, 555);
    }

    public static void abrirDiretorio() {
	File arquivo = new File(caminhoApp);
	try {
	    Desktop.getDesktop().open(arquivo);
	} catch (IOException ioException) {
	    ioException.printStackTrace();
	}
    }

    public static Border getBordaPainel() {
	Border bordaLinha = BorderFactory.createRaisedBevelBorder();
	Border margem = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	return BorderFactory.createCompoundBorder(bordaLinha, margem);
    }

    public static String getCaminhoApp() {
	return caminhoApp;
    }

    public static String getCsvSeparador() {
	return csvSeparador;
    }

    public static synchronized Sis getInstancia() {
	return instancia == null ? new Sis() : instancia;
    }

    public static Locale getLocale() {
	return new Locale.Builder().setLanguage("pt").setRegion("BR").build();
    }

    public static String getNomeHost() {
	try {
	    return InetAddress.getLocalHost().getCanonicalHostName();
	} catch (UnknownHostException e) {
	    return null;
	}
    }

    public static String getNomeSistema() {
	return "BIT - DESPACHANTE DE VEÍCULOS";
    }

    public static Cursor getNovaJanelaCursor() {
	return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    public static String getSeparador() {
	return separadorApp;
    }

    public static Dimension getTamanhoJanela() {
	return tamanhoTela;
    }

    public static void setUsuario(Usuario usuario) {
	Sis.usuario = usuario;
    }

    public static Usuario getUsuario() {
	return Sis.usuario;
    }
}
