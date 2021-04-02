package erp.escritorio.produto.compra.lista;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class CompraProdutoSel implements ListSelectionListener {

	JTable table;

	CompraProdutoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				CompraProduto compraProdutoPesquisaRegistro = new CompraProduto();
				compraProdutoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], CompraProdutoTm.ID));

				if (table.getSelectedRow() != -1) {
					CompraProduto compraProduto = CompraProdutoFac.getRegistro(compraProdutoPesquisaRegistro);
					CompraProdutoTm compraProdutoTm = (CompraProdutoTm) table.getModel();
					compraProdutoTm.getCompraProduto(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getCompraProdutoJanCad());
					MainControl.getCompraProdutoJanCad().getCompraProdutoCont().setCompraProduto(compraProduto);
					MainControl.getCompraProdutoJanCad().getCompraProdutoCont().atualizarGui();
					MainControl.getCompraProdutoJanCad().setFocusable(true);
					MainControl.getCompraProdutoJanPesq().setVisible(false);
				}
			}
		}
	}
}
