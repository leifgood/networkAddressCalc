package Presenter;

import GUI.HostDialog;
import Model.Host;
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
	
	public verifyChange() {
		String prevSelectedIP = getPreviousSelectedIP();
		String prevSelectedName = getPreviousSelectedIP();
		Host host = subnet.getHostByIPString(prevSelectedIP);
		host.setName(prevSelectedName);
	}
}
