package Presenter;

import javax.swing.JOptionPane;

import GUI.AddSubnetDialog;
import Model.Network;
import Model.Subnet;

public class AddSubnetPresenter {
	
	private AddSubnetDialog dialog;
	private Network network;
	
	public AddSubnetPresenter(Network network) {
		this.network = network;
	}

	public void setDialog(AddSubnetDialog dialog) {
		this.dialog = dialog;
	}
	
	public void verifySave() {
		// TODO speichere neues Subnet in Liste
		Subnet subnet = new Subnet();
		subnet.setByHostCount(hostCountFromComponent(), departmentFromComponent());
		network.AddSubnet(subnet);
		dialog.dispose();
	}
	
	public void verifyCancel() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			dialog.dispose();
		}
	}

	
}
