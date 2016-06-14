package Presenter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import GUI.HostDialog;
import Model.Host;
import Model.Subnet;

public class HostPresenter {
	
	private HostDialog dialog;
	private Subnet subnet;
	
	public HostPresenter(Subnet subnet) {
		this.subnet = subnet;
	}
	
	public void setDialog(HostDialog dialog) {
		this.dialog = dialog;
	}
	
	public void verifyOK() {
		dialog.dispose();
	}
	
	public void verifyChange() {
		ChangeDescriptionPresenter presenter = new ChangeDescriptionPresenter();
		presenter.setHost(subnet.getHostByIPString(dialog.getSelectedItem()));
	}
	
	public void updateUI(){
		TableModel model = dialog.getTable().getModel();
		DefaultTableModel mod = (DefaultTableModel)model;
		for( int i = 0; i < mod.getRowCount();++i )
			mod.removeRow(i);
		for (Host host : subnet.getHosts() ) {
			mod.addRow( new Object[]{host.getName(), host.getIpv4Address().toDecimal(), host.getIpv4Address().toBinary()});
		}
	}
}
