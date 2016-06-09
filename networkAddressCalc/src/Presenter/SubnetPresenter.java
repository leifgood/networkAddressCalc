//package Presenter;
//
//import javax.swing.JOptionPane;
//
//import Model.IPv4Address;
//import Model.IPv6Address;
//import Model.Network;
//import Model.Subnet;
//
//public class SubnetPresenter {
//	private Network network;
//	private SubnetDialog frame;
//
//	public SubnetPresenter(Network network) {
//		this.setNetwork(network);
//	}
//	
//	public void verifyAdd(){
//		Subnet subnet = new Subnet();
//		subnet.setNetwork(network);
//		subnet.setDepartment(frame.getDepartment());
//		int ipv4Praefix = Integer.parseInt(frame.getIpv4Praefix());
//		subnet.setIpv4Praefix(ipv4Praefix);
//		subnet.setIpv4SubnetID( IPv4Address.generateFromString(frame.getkIPv4SubnetID()));
//		subnet.setIpv6SubnetID( IPv6Address.generateFromString(frame.getIPv6SubnetID()));
//		
//	}
//	
//	public void verifyEdit(){
//		
//	}
//
//	public void verifyDelete(){
//		String selectedItem = frame.getSelectedName();
//		if( selectedItem != null ){
//			int dialogResult = JOptionPane.showConfirmDialog(null, "Wollen sie das Subnet wirklich löschen?", "Löschen", JOptionPane.YES_NO_OPTION);
//				network.DeleteSubnet( network.getSubnetByName(selectedItem));
//			}
//		}
//	}
//	
//	public Network getNetwork() {
//		return network;
//	}
//
//	public void setNetwork(Network network) {
//		this.network = network;
//	}
//	
//	public SubnetDialog getFrame() {
//		return frame;
//	}
//
//	public void setFrame(SubnetDialog frame) {
//		this.frame = frame;
//	}
//
//}
