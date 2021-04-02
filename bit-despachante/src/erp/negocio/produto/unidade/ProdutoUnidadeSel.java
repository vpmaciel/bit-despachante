package erp.negocio.produto.unidade;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ProdutoUnidadeSel implements ListSelectionListener {

	JTable table;

	ProdutoUnidadeSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				ProdutoUnidade produtoUnidadePesquisaRegistro = new ProdutoUnidade();
				produtoUnidadePesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ProdutoUnidadeTm.ID));

				if (table.getSelectedRow() != -1) {
					ProdutoUnidade produtoUnidade = ProdutoUnidadeFac.getRegistro(produtoUnidadePesquisaRegistro);
					ProdutoUnidadeTm produtoUnidadeTm = (ProdutoUnidadeTm) table.getModel();
					produtoUnidadeTm.getProdutoUnidade(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getProdutoUnidadeJanCad());
					MainControl.getProdutoUnidadeJanCad().getProdutoUnidadeCont().setProdutoUnidade(produtoUnidade);
					MainControl.getProdutoUnidadeJanCad().getProdutoUnidadeCont().atualizarGui();
					MainControl.getProdutoUnidadeJanCad().setFocusable(true);
					MainControl.getProdutoUnidadeJanPesq().setVisible(false);
				}
			}
		}
	}
}
