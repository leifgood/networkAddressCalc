package Presenter;

import GUI.AddNetworkDialog;
import Model.Data;

public class AddNetworkPresenter {
	
	@SuppressWarnings("unused")
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
		
	}
	
	public void verifyCancel() {
		
	}
	
}
