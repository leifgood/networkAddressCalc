package Presenter;

import javax.swing.JOptionPane;

import GUI.AddNetworkDialog;
import GUI.SubnetDialog;
import Model.Data;
import Model.IPv4Address;
import Model.ISP;
import Model.Network;

public class AddNetworkPresenter {
	
	private AddNetworkDialog dialog;
	@SuppressWarnings("unused")
	private Data data;
	
	public AddNetworkPresenter(Data data) {
		super();
		this.data = data;
	}
	
	public void setDialog(AddNetworkDialog dialog) {
		this.dialog = dialog;
	}

	public void verifySave() {
		Network network = new Network();
		
		network.setIpv4Networkmask(IPv4Address.generateFromString(ipv4FromComponent()));
		network.setIpv4Praefix(Integer.parseInt(ipv4PraefixFromComponent()));
		if (dialog.isCheckboxChecked()) {
			ISP isp = new ISP();
			isp.setISPPraefix(Integer.parseInt(ipv6PraefixFromComponent()));
			isp.generateFromString(ipv6FromComponent());
			network.setIpv6ISP(isp);
		}
		data.addNetwork(network);
		
		int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to create Subnets?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogresult == JOptionPane.YES_OPTION) {
			SubnetPresenter subnetPresenter = new SubnetPresenter(network);
			SubnetDialog subnetDialog = new SubnetDialog(subnetPresenter);
			subnetPresenter.setDialog(subnetDialog);
			subnetDialog.setVisible(true);
		}
	}
	
	public void verifyCancel() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			dialog.dispose();
		}
	}
}
