package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Subnet;
import Presenter.HostPresenter;

public class HostDialog extends JDialog{
	
	//variables
	private static final long serialVersionUID = 1L;
	private HostPresenter hostpresenter;
	MyTableModel model = new MyTableModel();
	JTable table = new JTable(model);

	public JTable getTable() {
		return table;
	}
	// MAIN, temporär zur GUI exklusiven ausführung, später entfernen
	public static void main(String[] args) {
		Subnet subnet = new Subnet();
		HostPresenter presenter = new HostPresenter( subnet );
		HostDialog dialog = new HostDialog(presenter);
		presenter.setDialog(dialog);
		dialog.setVisible(true);
	}
	//Konstruktor
	public HostDialog( HostPresenter hostpresenter )
	{
		this.hostpresenter = hostpresenter;
		init();
	}
	//Hauptaufruf
	public void init() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Hosts");
		setBounds(100, 100, 1175, 719);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1135, 600);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);		
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hostpresenter.verifyOK();
			}
		});
		ok.setBounds(925, 634, 220, 36);
		getContentPane().add(ok);
		
		JButton changeDescription = new JButton("Change Description");
		changeDescription.setBounds(695, 634, 220, 36);
		getContentPane().add(changeDescription);
	}
	
	//MyTableModel
	public class MyTableModel extends DefaultTableModel{
		private static final long serialVersionUID = 1L;
	
		public MyTableModel(){
			super(new Object[][] {},new String[]{ 
				"Description", "Hostaddress", "Binary"
			});
		}
	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	return false;
	    }
	}
		public String getSelectedItem(){
			return (String) table.getModel().getValueAt(table.getSelectedRow(),1);
		}
}
