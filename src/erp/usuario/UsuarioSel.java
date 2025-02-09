package erp.usuario;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class UsuarioSel implements ListSelectionListener {

	JTable table;

	UsuarioSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Usuario usuarioPesquisaRegistro = new Usuario();
				usuarioPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], UsuarioTm.ID));

				if (table.getSelectedRow() != -1) {
					Usuario usuario = ((List<Usuario>) UsuarioFac.pesquisarRegistro(usuarioPesquisaRegistro))
							.get(table.getSelectedRow());
					UsuarioTm usuarioTm = (UsuarioTm) table.getModel();
					usuarioTm.getUsuario(table.getSelectedRow());

					MainControl.mostrarFrame(MainControl.getUsuarioJan());
					MainControl.getUsuarioJan().getUsuarioCont().setModelo(UsuarioFac.getRegistro(usuario));
					MainControl.getUsuarioJan().getUsuarioCont().atualizarGui();
					MainControl.getUsuarioJan().setFocusable(true);
					MainControl.getUsuarioJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
