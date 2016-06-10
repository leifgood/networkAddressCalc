package Presenter;

import GUI.HostDialog;
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
}
