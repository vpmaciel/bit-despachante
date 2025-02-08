package erp.arquitetura.gui;

import java.text.Normalizer;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public final class EntradaMaiuscula extends PlainDocument {

	private final int iMaxLength;

	public EntradaMaiuscula(int maxlen) {
		this.iMaxLength = maxlen;

	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}

		str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		str = str.replaceAll("\\s+", " ");

		if (this.iMaxLength <= 0) {
			super.insertString(offset, str.toUpperCase(), attr);
			return;
		}
		int ilen = this.getLength() + str.length();
		if (ilen <= this.iMaxLength) {
			super.insertString(offset, str.toUpperCase(), attr);
		} else {

		}
	}
}
