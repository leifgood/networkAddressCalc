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
		Subnet subnet = new Subnet();
		try {
			subnet.setNetwork(network);
			subnet.setByHostCount(Integer.valueOf(dialog.hostCountFromComponent()), dialog.departmentFromComponent());
			network.AddSubnet(subnet);
			dialog.getSubnetPresenter().updateUI();
			dialog.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void verifyCancel() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			dialog.dispose();
		}
	}

	
}
