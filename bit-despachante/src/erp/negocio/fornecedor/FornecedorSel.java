package erp.negocio.fornecedor;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class FornecedorSel implements ListSelectionListener {

	JTable table;

	FornecedorSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Fornecedor fornecedorPesquisaRegistro = new Fornecedor();
				fornecedorPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], FornecedorTm.ID));

				if (table.getSelectedRow() != -1) {
					Fornecedor fornecedor = FornecedorFac.getRegistro(fornecedorPesquisaRegistro);
					FornecedorTm fornecedorTm = (FornecedorTm) table.getModel();
					fornecedorTm.getFornecedor(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getFornecedorJanCad());
					MainControl.getFornecedorJanCad().getFornecedorCont().setFornecedor(fornecedor);
					MainControl.getFornecedorJanCad().getFornecedorCont().atualizarGui();
					MainControl.getFornecedorJanCad().setFocusable(true);
					MainControl.getFornecedorJanPesq().setVisible(false);
				}
			}
		}
	}
}
