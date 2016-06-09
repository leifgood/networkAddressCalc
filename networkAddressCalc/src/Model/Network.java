package Model;

import java.util.ArrayList;

public class Network {
	private IPv4Address ipv4Networkmask;
	private ISP ipv6ISP;
	private ArrayList<Subnet> subnets;
	private int ipv4Praefix;
	
	public Network(){
		ipv4Networkmask = null;
		ipv6ISP = null;
		subnets = new ArrayList<Subnet>();
		ipv4Praefix = -1;
	}
	
	
	public void AddSubnet( Subnet subnet ){
		for (Subnet sub : subnets) {
			if( subnet.getDepartment().equals(sub.getDepartment()) )
				throw new IllegalArgumentException("Es existiert bereits ein Subnet für die Abteilung " + sub.getDepartment());
		}
		subnets.add( subnet);
	}
	
	public void DeleteSubnet( Subnet subnet ){
		subnets.remove(subnet);
	}
	
	public IPv4Address getIpv4Networkmask() {
		return ipv4Networkmask;
	}
	public void setIpv4Networkmask(IPv4Address ipv4Networkmask) {
		this.ipv4Networkmask = ipv4Networkmask;
	}
	public ISP getIpv6ISP() {
		return ipv6ISP;
	}
	public void setIpv6ISP(ISP ipv6isp) {
		ipv6ISP = ipv6isp;
	}
	public ArrayList<Subnet> getSubnets() {
		return subnets;
	}
	public void setSubnets(ArrayList<Subnet> subnets) {
		this.subnets = subnets;
	}

	public Subnet getSubnetByName(String selectedItem) {
		for (Subnet subnet : subnets) {
			if( subnet.getDepartment().equals(selectedItem) )
				return subnet;
		}
		throw new IllegalArgumentException("Unerwarteter Fehler");
	}

	public int getIpv4Praefix() {
		return ipv4Praefix;
	}

	public void setIpv4Praefix(int ipv4Praefix) {
		this.ipv4Praefix = ipv4Praefix;
	}

	public boolean hasIpv6() {
		return ipv6ISP != null;
	}


	public IPv4Address createSubnetID(int ipv4Praefix2) {
		// TODO Auto-generated method stub
		return null;
	}


	public IPv6Address createIPv6Subnet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
