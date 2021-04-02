package erp.sistema.dados;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class DadosSel implements ListSelectionListener {

	JTable table;

	DadosSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Dados contadorPesquisaRegistro = new Dados();
				contadorPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], DadosTm.ID));

				if (table.getSelectedRow() != -1) {
					Dados contador = DadosFac.getRegistro(contadorPesquisaRegistro);
					DadosTm contadorTm = (DadosTm) table.getModel();
					contadorTm.getDados(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getDadosJan());
					MainControl.getDadosJan().getDadosCont().setDados(contador);
					MainControl.getDadosJan().getDadosCont().atualizarGui();
					MainControl.getDadosJan().setFocusable(true);
					// MainControl.getDadosJanPesq().setVisible(false);
				}
			}
		}
	}
}
