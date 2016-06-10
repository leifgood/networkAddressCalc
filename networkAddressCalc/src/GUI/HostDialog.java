package GUI;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class HostDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private HostPresenter hostpresenter;
	
	

//	public HostDialog( HostPresenter hostpresenter )
//	{
//		this.hostpresenter = hostpresenter;
//		init();
//	}
	public static void main(String[] args) {
		try {
			HostDialog dialog = new HostDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() {
		setTitle("Hosts");
		setBounds(100, 100, 1175, 719);
		getContentPane().setLayout(null);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 1135, 600);
			getContentPane().add(scrollPane);
				table = new JTable();
				String[][] cells = {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
					};
				table.setModel(new DefaultTableModel(cells,new String[] 
						{"Description", "Hostaddress", "Binary"}
				));
				JComboBox comboBox = new JComboBox(cells[0]);
				comboBox.setMaximumRowCount(4);
				TableCellEditor editor = new DefaultCellEditor(comboBox);
				table.getColumnModel().getColumn(1).setCellEditor(editor);
				scrollPane.setViewportView(table);
		{
			JButton ok = new JButton("OK");
			ok.setBounds(925, 634, 220, 36);
			getContentPane().add(ok);
		}
	}
	
	public String getSelectedItem(){
		return (String) table.getModel().getValueAt(table.getSelectedRow(),1);
	}

}
