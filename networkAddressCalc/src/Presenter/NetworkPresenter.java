package Presenter;

import javax.swing.JOptionPane;

import GUI.AddNetworkDialog;
import GUI.NetworkFrame;
import GUI.SubnetDialog;
import Model.Data;
import Model.IPv4Address;
import Model.Network;

public class NetworkPresenter {
	
	private NetworkFrame frame;
	private Data data;
	
	public NetworkPresenter(Data data) {
		this.data = data;
	}
	
	public void verifySave() {
		try {
			data.save();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.OK_OPTION);
		}
	}
	
	public void verifyLoad() {
		try {
			Data data = Data.load();
			this.data = data;
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.OK_OPTION);
		}
	}
	
	public void verifyAdd() {
		AddNetworkPresenter addNetworkPresenter = new AddNetworkPresenter(data);
		AddNetworkDialog addNetworkDialog = new AddNetworkDialog(addNetworkPresenter);
		addNetworkPresenter.setDialog(addNetworkDialog);
		addNetworkDialog.setVisible(true);
	}
	
	public void verifyDelete() {
		String selectedItem = frame.getSelectedItem();
		if (selectedItem != null) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				IPv4Address ipv4 = IPv4Address.generateFromString(selectedItem);
				data.deleteNetwork(data.getNetworkByIPv4(ipv4));
			}
		}
	}
	
	public void verifySubnets() {
		String selectedItem = frame.getSelectedItem();
		if (selectedItem != null) {
			IPv4Address ipv4 = IPv4Address.generateFromString(selectedItem);
			Network network = data.getNetworkByIPv4(ipv4);
			SubnetPresenter subnetPresenter = new SubnetPresenter(network);
			SubnetDialog subnetDialog = new SubnetDialog(subnetPresenter);
			subnetPresenter.setDialog(subnetDialog);
			subnetDialog.setVisible(true);
		}
	}

	public void setFrame(NetworkFrame frame) {
		this.frame = frame;
	}
}
