//package Presenter;
//
//import javax.swing.JOptionPane;
//
//import Model.Data;
//import Model.IPv4Address;
//import Model.Network;
//
//public class NetworkPresenter {
//	private Data data;
//	private NetworkFrame frame;
//	
//	public void verifySave(){
//		try {
//			data.save();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Es ist ein Fehler beim Speichern aufgetreten\n" + e.getMessage(), "Speichern", JOptionPane.OK_OPTION);
//		}
//	}
//	public void verifyLoad(){
//		try {
//			data = Data.load();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Es ist ein Fehler beim Speichern aufgetreten\n" + e.getMessage() , "Laden", JOptionPane.OK_OPTION);
//		}
//	}
//	
//	public void verifyAdd(){
//		EditNetworkPresenter presenter = new EditNetworkPresenter( data );
//		EditNetworkDialog dialog = new EditNetworkDialog(presenter);
//		presenter.setFrame( dialog );
//		dialog.setVisible(true);
//	}
//	
//	public void verifyEdit(){
//		String selectedItem = frame.getSelectedIP();
//		if( selectedItem != null ){
//			IPv4Address ip = IPv4Address.generateFromString(selectedItem);
//			Network network = data.getNetworkByIPv4(ip);
//			SubnetPresenter presenter = new SubnetPresenter( network );
//			SubnetDialog dialog = new SubnetDialog(presenter, network);
//			presenter.setFrame( dialog );
//			dialog.setVisible(true);
//			}
//		}
//	
//	public void verifyDelete(){
//		String selectedItem = frame.getSelectedIP();
//		if( selectedItem != null ){
//			int dialogResult = JOptionPane.showConfirmDialog(null, "Wollen sie dsas Netzwerk wirklich löschen?", "Löschen", JOptionPane.YES_NO_OPTION);
//			if( dialogResult == JOptionPane.YES_OPTION){
//				IPv4Address ip = IPv4Address.generateFromString(selectedItem);
//				data.deleteNetwork( data.getNetworkByIPv4(ip));
//			}
//		}
//			
//	}
//	
//	public Data getData() {
//		return data;
//	}
//	public void setData(Data data) {
//		this.data = data;
//	}
//	public NetworkFrame getFrame() {
//		return frame;
//	}
//	public void setFrame(NetworkFrame frame) {
//		this.frame = frame;
//	}
//}
