package Presenter;

import javax.swing.JOptionPane;

import GUI.NetworkFrame;
import Model.Data;
import Model.IPv4Address;

public class NetworkPresenter {
	
	private NetworkFrame frame;
	private Data data;
	
	public void verifySave() {
		
	}
	
	public void verifyLoad() {
		
	}
	
	public void verifyAdd() {
//		AddNetworkPresenter addNetworkPresenter = new AddNetworkPresenter(data);
//		AddNetworkDialog addNetworkDialog = new AddNetworkDialog(addNetworkPresenter);
//		addNetworkPresenter.setDialog(addNetworkDialog);
//		addNetworkDialog.setVisible(true);
	}
	
	public void verifyDelete() {
		String selectedItem = frame.getSelectedItem();
		if (selectedItem != null) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "delete", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				IPv4Address ipv4 = IPv4Address.generateFromString(selectedItem);
				data.deleteNetwork(data.getNetworkByIPv4(ipv4));
			}
		}
	}
	
	public void verifySubnets() {
		
	}

}
