package erp.arquitetura.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public final class EntradaMinuscula extends PlainDocument {

    private final int iMaxLength;

    public EntradaMinuscula(int maxlen) {
	this.iMaxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	if (str == null) {
	    return;
	}
	str = str.replaceAll("\\s+", " ");
	if (this.iMaxLength <= 0) {
	    super.insertString(offset, str.toLowerCase(), attr);
	    return;
	}
	int ilen = this.getLength() + str.length();
	if (ilen <= this.iMaxLength) {
	    super.insertString(offset, str.toLowerCase(), attr);
	}
    }
}
