package erp.negocio.contador;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ContadorSel implements ListSelectionListener {

	JTable table;

	ContadorSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Contador contadorPesquisaRegistro = new Contador();
				contadorPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ContadorTm.ID));

				if (table.getSelectedRow() != -1) {
					Contador contador = ContadorFac.getRegistro(contadorPesquisaRegistro);
					ContadorTm contadorTm = (ContadorTm) table.getModel();
					contadorTm.getContador(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getContadorJanCad());
					MainControl.getContadorJanCad().getContadorCont().setContador(contador);
					MainControl.getContadorJanCad().getContadorCont().atualizarGui();
					MainControl.getContadorJanCad().setFocusable(true);
					MainControl.getContadorJanPesq().setVisible(false);
				}
			}
		}
	}
}
