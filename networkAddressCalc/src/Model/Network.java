package Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.activity.InvalidActivityException;

public class Network implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		if(ipv4Praefix < 1 || ipv4Praefix > 24)
			throw new IllegalArgumentException("No possible ipv4Praefix");
		this.ipv4Praefix = ipv4Praefix;
	}

	public boolean hasIpv6() {
		return ipv6ISP != null;
	}
	
	public boolean isOverlapping( Network other )
	{
		ArrayList<IPv4Address> possibleOtherIPs = other.calculatePossibleIPs();
		for (IPv4Address ipAddress : calculatePossibleIPs()) {
			if( possibleOtherIPs.contains(ipAddress));
			return true;
		}
		return false;
	}


	private ArrayList<IPv4Address> calculatePossibleIPs() {
		int size = (int) Math.pow(2, ( 32 - ipv4Praefix ));
		ArrayList<IPv4Address> possibleIPs = new ArrayList<IPv4Address>();
		IPv4Address temp = new IPv4Address(ipv4Networkmask);
		for( int i = 0; i < size; ++i ){
			possibleIPs.add(temp);
			temp = IPv4Address.addOne(temp);
		}
		return possibleIPs;
	}


	public IPv4Address createSubnetID( Subnet subnet ) throws InvalidActivityException {
		if( subnet.getIpv4Praefix() < this.ipv4Praefix )
			throw new IllegalArgumentException("Das Subnet ist größer als das Netzwerk");
		ArrayList<IPv4Address> possibleSubnetIDs = calculateIPv4SubnetIDs( subnet.getIpv4Praefix() );
		for (IPv4Address iPv4Address : possibleSubnetIDs) {
			Subnet temp = new Subnet( subnet );
			temp.setIpv4SubnetID(iPv4Address);
			boolean isUsed = false;
			for (Subnet other : subnets) {
				if( !isUsed && temp.isIPv4Overlapping(other)){
					isUsed = true;
					break;
				}	
			}
			if( !isUsed)
				return iPv4Address;
		}
		return null;
	}


	private ArrayList<IPv4Address> calculateIPv4SubnetIDs(int ipPraefix) {
		int diff = ipPraefix - this.ipv4Praefix;
		int subnetCount = (int) Math.pow(2, diff);
		int subnetSize = (int) Math.pow(2, ( 32 - ipPraefix ));
		ArrayList<IPv4Address> possibleSubnetIDs = new ArrayList<IPv4Address>();
		for( int i = 0; i < subnetCount; ++i ){
			possibleSubnetIDs.add(ipv4Networkmask.plus( subnetSize * i));
		}
		return possibleSubnetIDs;
	}


	public IPv6Address createIPv6Subnet( int hostCount ) throws InvalidActivityException {
		IPv6Address address = new IPv6Address();
		address.setIsp(this.ipv6ISP);
		address.setSubnetID( calculateNewIPv6SubnetID() );
		int zeroBlocks = 7 - ipv6ISP.getValues().length / 4;
		Short[] networkID = new Short[zeroBlocks * 4];
		for( int i = 0; i < networkID.length; ++i){
			networkID[i] = 0;
		}
		address.setNetworkID(networkID);
		return address;
	}

	private Short[] calculateNewIPv6SubnetID() throws InvalidActivityException {
		int size = this.subnets.size();
		if( size == 65536 )
			throw new InvalidActivityException( "No space for new subnet");
		Short[] subnetID = new Short[4];
		subnetID[0] = (short) (size / 4096);
		size = size % 4096;
		subnetID[1] = (short) (size / 256);
		size = size % 256;
		subnetID[2] = (short) (size / 16); 
		size = size % 16;
		subnetID[3] = (short) (size); 
		return subnetID;
	}
	
	
}
