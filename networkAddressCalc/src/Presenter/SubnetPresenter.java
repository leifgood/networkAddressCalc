package Presenter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import GUI.AddSubnetDialog;
import GUI.HostDialog;
import GUI.SubnetDialog;
import Model.Network;
import Model.Subnet;

public class SubnetPresenter {

	private SubnetDialog dialog;
	private Network network;

	public SubnetPresenter(Network network) {
		this.network = network;
	}
	
	public void setDialog(SubnetDialog dialog) {
		this.dialog = dialog;
	}

	public void verifyAdd() {
		AddSubnetPresenter addSubnetPresenter = new AddSubnetPresenter(network);
		AddSubnetDialog addSubnetDialog = new AddSubnetDialog(this, addSubnetPresenter);
		addSubnetPresenter.setDialog(addSubnetDialog);
		addSubnetDialog.setVisible(true);
	}
	
	public void verifyDelete() {
		String selectedItem = dialog.getSelectedItem();
		if (selectedItem != null) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "delete", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				network.DeleteSubnet(network.getSubnetByName(selectedItem));
			}
		}
	}
	
	public void verifyHosts() {
		String selectedItem = dialog.getSelectedItem();
		if (selectedItem != null) {
			Subnet subnet = network.getSubnetByName(selectedItem);
			HostPresenter hostPresenter = new HostPresenter(subnet);
			HostDialog hostDialog = new HostDialog(hostPresenter);
			hostPresenter.setDialog(hostDialog);
			hostPresenter.updateUI();
			hostDialog.setVisible(true);
		}
	}
	
	public void updateUI(){
		TableModel model = dialog.getTable().getModel();
		DefaultTableModel mod = (DefaultTableModel)model;
		for( int i = 0; i < mod.getRowCount();++i )
			mod.removeRow(i);
		for (Subnet subnet : network.getSubnets()) {
			mod.addRow( new Object[]{ subnet.getIpv4SubnetID().toDecimal(), subnet.getDepartment(), subnet.getMaxHostCount() });
		}
	}
}
