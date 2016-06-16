package Presenter;

import javax.swing.JOptionPane;

import GUI.AddNetworkDialog;
import GUI.NetworkFrame;
import GUI.SubnetDialog;
import Model.Data;
import Model.IPv4Address;
import Model.Network;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
			updateUI();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.OK_OPTION);
		}
	}
	
	public void verifyAdd() {
		AddNetworkPresenter addNetworkPresenter = new AddNetworkPresenter(data);
		AddNetworkDialog addNetworkDialog = new AddNetworkDialog(this, addNetworkPresenter);
		addNetworkPresenter.setDialog(addNetworkDialog);
		addNetworkDialog.setModal(true);
		addNetworkDialog.setVisible(true);
	}

	public void updateUI() {
		TableModel model = frame.getTable().getModel();
		DefaultTableModel mod = (DefaultTableModel) model;
		mod.setRowCount(0);
		frame.getTable().revalidate();
		for (Network net : data.getNetworks()) {
			String ipString = "IPv4: " + net.getIpv4Networkmask().toDecimal();
			String ipStringBinary = "IPv4: " + net.getIpv4Networkmask().toBinary();
			if( net.hasIpv6() )
			{
				ipString += "; IPv6: " + net.getIpv6ISP().toString();
				ipStringBinary += "; IPv6: " + net.getIpv6ISP().toBinary();
			}
			mod.addRow( new Object[] {ipString, ipStringBinary} );
		}
		frame.getTable().updateUI();
	}
	
	public void verifyDelete() {
		try {
			String selectedItem = frame.getSelectedItem();
			if (selectedItem != null) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					IPv4Address ipv4 = IPv4Address.generateFromString(selectedItem);
					data.deleteNetwork(data.getNetworkByIPv4(ipv4));
					updateUI();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void verifySubnets() {
		try {
			String selectedItem = frame.getSelectedItem();
			if (selectedItem != null) {
				IPv4Address ipv4 = IPv4Address.generateFromString(selectedItem);
				Network network = data.getNetworkByIPv4(ipv4);
				SubnetPresenter subnetPresenter = new SubnetPresenter(network);
				SubnetDialog subnetDialog = new SubnetDialog(subnetPresenter);
				subnetPresenter.setDialog(subnetDialog);
				subnetPresenter.updateUI();
				subnetDialog.setModal(true);
				subnetDialog.setVisible(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setFrame(NetworkFrame frame) {
		this.frame = frame;
	}
}
