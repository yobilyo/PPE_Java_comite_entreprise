package vue;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BoutonJTable extends JButton implements TableCellRenderer {

	public BoutonJTable() {
		setOpaque(true);
	}
	
	
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText("Delete");
		return this;
	}

}
