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
	private Data data;
	
	public AddNetworkPresenter(Data data) {
		super();
		this.data = data;
	}
	
	public void setDialog(AddNetworkDialog dialog) {
		this.dialog = dialog;
	}

	public void verifySave() {
		try {
			Network network = new Network();
			
			network.setIpv4Networkmask(IPv4Address.generateFromString(dialog.ipv4FromComponent()));
			network.setIpv4Praefix(Integer.parseInt(dialog.ipv4PraefixFromComponent()));
			if (dialog.isCheckboxChecked()) {
				ISP isp = new ISP();
				isp.setISPPraefix(Integer.parseInt(dialog.ipv6PraefixFromComponent()));
				isp.generateFromString(dialog.ipv6FromComponent());
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
			dialog.getNetworkPresenter().updateUI();
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
