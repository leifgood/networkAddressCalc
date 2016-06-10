package Presenter;

import javax.swing.JOptionPane;

import GUI.AddNetworkDialog;
import Model.Data;

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

	public void verifyOK() {
		// TODO abfrage ob Felder korrekt ausgefüllt wurden
		int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to create Subnets?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogresult == JOptionPane.YES_OPTION) {
//			SubnetPresenter subnetPresenter = new SubnetPresenter(data);
//			SubnetDialog subnetDialog = new SubnetDialog(subnetPresenter);
//			subnetPresenter.setDialog(subnetDialog);
//			subnetDialog.setVisible(true);
		}
		
	}
	
	public void verifyCancel() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			dialog.dispose();
		}
	}
	
}
