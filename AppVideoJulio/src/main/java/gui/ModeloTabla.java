package gui;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	Object[] col = { "Previsualizacion", "Titulo" };

	public ModeloTabla() {
		for (Object c : col) {
			this.addColumn(c);
		}

	}

	public void borrarFilas() {
		int numFilas = this.getRowCount();
		for (int i = 0; numFilas > i; i++) {
			this.removeRow(0);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Class<? extends Object> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return getValueAt(0, columnIndex).getClass();
		else
			return super.getColumnClass(columnIndex);
	}
}
