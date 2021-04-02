package erp.negocio.empresa;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class EmpresaSel implements ListSelectionListener {

	JTable table;

	EmpresaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Empresa empresaPesquisaRegistro = new Empresa();
				empresaPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], EmpresaTm.ID));

				if (table.getSelectedRow() != -1) {
					Empresa empresa = EmpresaFac.getRegistro(empresaPesquisaRegistro);
					EmpresaTm empresaTm = (EmpresaTm) table.getModel();
					empresaTm.getEmpresa(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getEmpresaJanCad());
					MainControl.getEmpresaJanCad().getEmpresaCont().setEmpresa(empresa);
					MainControl.getEmpresaJanCad().getEmpresaCont().atualizarGui();
					MainControl.getEmpresaJanCad().setFocusable(true);
					MainControl.getEmpresaJanPesq().setVisible(false);
				}
			}
		}
	}
}
