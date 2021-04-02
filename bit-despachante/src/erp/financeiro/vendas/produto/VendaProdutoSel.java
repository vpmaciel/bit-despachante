package erp.financeiro.vendas.produto;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class VendaProdutoSel implements ListSelectionListener {

	JTable table;

	VendaProdutoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				VendaProduto vendaProdutoPesquisaRegistro = new VendaProduto();
				vendaProdutoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], VendaProdutoTm.ID));

				if (table.getSelectedRow() != -1) {
					VendaProduto vendaProduto = VendaProdutoFac.getRegistro(vendaProdutoPesquisaRegistro);
					VendaProdutoTm vendaProdutoTm = (VendaProdutoTm) table.getModel();
					vendaProdutoTm.getVendaProduto(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getVendaProdutoJanCad());
					MainControl.getVendaProdutoJanCad().getVendaProdutoCont().setVendaProduto(vendaProduto);
					MainControl.getVendaProdutoJanCad().getVendaProdutoCont().atualizarGui();
					MainControl.getVendaProdutoJanCad().setFocusable(true);
					MainControl.getVendaProdutoJanPesq().setVisible(false);
				}
			}
		}
	}
}
