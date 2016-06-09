//package Presenter;
//
//import java.util.ArrayList;
//
//import javax.swing.JOptionPane;
//
//import Model.Data;
//import Model.IPv4Address;
//import Model.ISP;
//import Model.Network;
//import Model.NetworkClass;
//import Model.Subnet;
//
//public class EditNetworkPresenter {
//	private Data data;
//	private EditNetworkDialog frame;
//	
//	public EditNetworkPresenter(Data data) {
//		this.data = data;
//	}
//			
//	public void verifySave(){
//		try{
//			Network network = new Network();
//			network.setName( frame.getName() );
//			network.setNetworkClass( stringToEnum( frame.getClass() ) );
//			network.setSubnets(subnets);
//			network.setIpv6ISP( ISP.generateFromString( frame.getISP() ) );
//			network.setIpv4Networkmask( IPv4Address.generateFromString( frame.getNetworkMask ) );
//			data.addNetwork(network);
//			frame.close();
//		}
//		catch( Exception e){
//			JOptionPane.showMessageDialog(null, "Es ist ein Fehler aufgetreten:\n" + e.getMessage(), "Übernehmen", JOptionPane.INFORMATION_MESSAGE);
//		}
//	}
//	
//	private NetworkClass stringToEnum( String str ){
//		switch( str ){
//		case "A": return NetworkClass.A; break;
//		case "B": return NetworkClass.B; break;
//		case "C": return NetworkClass.C; break;
//		default: throw new IllegalArgumentException("Unbekannter Fehler in NetworkClass aufgetreten");
//		}
//	}
//	
//	public void verifyCancel(){
//		int result = JOptionPane.showConfirmDialog(null, "Möchten Sie ihre Änderungen speichern?", "Abbrechen", JOptionPane.YES_NO_CANCEL_OPTION);
//		if( result == JOptionPane.CANCEL_OPTION )
//			;
//		else if( result == JOptionPane.YES_OPTION )
//			verifySave();
//		else
//			frame.close();
//	}
//	
//	public Data getData() {
//		return data;
//	}
//
//	public void setData(Data data) {
//		this.data = data;
//	}
//
//	public EditNetworkDialog getFrame() {
//		return frame;
//	}
//
//	public void setFrame(EditNetworkDialog frame) {
//		this.frame = frame;
//	}
//
//	
//
//}
