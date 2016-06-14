package Presenter;

import javax.swing.JOptionPane;

import GUI.ChangeDescriptionDialog;
import Model.Host;

public class ChangeDescriptionPresenter {
	
	private ChangeDescriptionDialog dialog;
	public ChangeDescriptionDialog getDialog() {
		return dialog;
	}

	public void setDialog(ChangeDescriptionDialog dialog) {
		this.dialog = dialog;
	}

	private Host host;
	
	public void verifySave() {
		host.setName(dialog.descriptionNameFromComponent());
		dialog.getHostPresenter().updateUI();
		dialog.dispose();
	}
	
	public void verifyCancel() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			dialog.dispose();
		}
	}

	public void setHost(Host hostByIPString) {
		host = hostByIPString;
	}

}
