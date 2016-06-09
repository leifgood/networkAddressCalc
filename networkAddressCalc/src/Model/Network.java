package Model;

import java.util.ArrayList;

public class Network {
	private IPv4Address ipv4Networkmask;
	private NetworkClass networkClass;
	private ISP ipv6ISP;
	private ArrayList<Subnet> subnets;
	private String name;
	
	public Network(){
		ipv4Networkmask = null;
		networkClass = null;
		ipv6ISP = null;
		subnets = new ArrayList<Subnet>();
		name = "";
	}
	
	public Network(IPv4Address ipv4Networkmask, NetworkClass networkClass,
			ISP ipv6isp, ArrayList<Subnet> subnets, String name) {
		this.ipv4Networkmask = ipv4Networkmask;
		this.networkClass = networkClass;
		ipv6ISP = ipv6isp;
		this.subnets = subnets;
		this.name = name;
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
	public NetworkClass getNetworkClass() {
		return networkClass;
	}
	public void setNetworkClass(NetworkClass networkClass) {
		this.networkClass = networkClass;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subnet getSubnetByName(String selectedItem) {
		for (Subnet subnet : subnets) {
			if( subnet.getDepartment().equals(selectedItem) )
				return subnet;
		}
		throw new IllegalArgumentException("Unerwarteter Fehler");
	}

	public int getPraefixByClass() {
		switch( this.networkClass )
		{
		case A: return 8; 
		case B: return 16;
		case C: return 24;
		default: throw new IllegalArgumentException("WTF has happend here!"); 
		}
	}
	
	
}
