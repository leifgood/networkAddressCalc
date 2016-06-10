package GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Presenter.HostPresenter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HostDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private HostPresenter hostpresenter;

	public HostDialog( HostPresenter hostpresenter )
	{
		this.hostpresenter = hostpresenter;
		init();
	}

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
//			table.setCellEditable()
			scrollPane.setViewportView(table);
			JButton ok = new JButton("OK");
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					hostpresenter.verifyOK();
				}
			});
			ok.setBounds(925, 634, 220, 36);
			getContentPane().add(ok);
	}
	
	public String getSelectedItem(){
		return (String) table.getModel().getValueAt(table.getSelectedRow(),1);
	}

}
