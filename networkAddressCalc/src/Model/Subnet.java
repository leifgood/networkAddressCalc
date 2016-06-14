package Model;

import java.util.ArrayList;

import javax.activity.InvalidActivityException;
import javax.management.InstanceNotFoundException;
import javax.swing.JOptionPane;

public class Subnet {
	private String department;
	private int ipv4Praefix;
	private ArrayList<Host> hosts;
	private Network network;
	private IPv4Address ipv4SubnetID;
	private IPv6Address ipv6SubnetID;
		
	public Subnet(){
		department = "";
		ipv4Praefix = -1;
		hosts = new ArrayList<Host>();
		network = null;
		ipv4SubnetID = null;
		ipv6SubnetID = null;
	}
	
	public Subnet(Subnet subnet) {
		department = subnet.department;
		ipv4Praefix = subnet.ipv4Praefix;
		hosts = subnet.hosts;
		network = subnet.network;
		ipv4SubnetID = subnet.ipv4SubnetID;
		ipv6SubnetID = subnet.ipv6SubnetID;
	}

	public void setByHostCount( int hostCount, String department ) throws Exception{
		setDepartment(department);
		this.setIpv4Praefix(createPraefix(hostCount));
		this.setIpv4SubnetID( network.createSubnetID(this) );
		if ( ipv4SubnetID == null )
			throw new IllegalArgumentException( "Das Subnet passt nicht mehr ins Netzwerk");
		if( network.hasIpv6() )
			this.setIpv6SubnetID( network.createIPv6Subnet( hostCount ) );
		createHosts( hostCount );
	}
	
	private void createHosts(int hostCount) throws InvalidActivityException{
		IPv4Address ipv4address = new IPv4Address( ipv4SubnetID );
		IPv6Address ipv6address = network.hasIpv6() ? new IPv6Address( ipv6SubnetID ) : null;
		for( int i = 0; i < hostCount; ++i )
		{
			Host host = new Host();
			host.setName("Host" + i);
			IPv4Address ip = new IPv4Address(IPv4Address.addOne(ipv4address));
			host.setIpv4Address( ip );
			host.setIpv6Address( network.hasIpv6() ? IPv6Address.addOne(ipv6address) : null );
			hosts.add(host);
		}
	}

	private int createPraefix(int hostCount) {
		int i = 0;
		while( Math.pow(2, i) - 2 < hostCount )
			i++;
		return 32 - i;
	}

	public void addHost( Host host ){
		hosts.add(host);
	}

	public void deleteHost( Host host ){
		hosts.remove(host);
	}
	
	public Host getHostByIPString( String ipString ){
		try {
			IPv4Address ip = IPv4Address.generateFromString(ipString);
			for (Host host : hosts) {
				if( host.getIpv4Address().equals(ip) )
					return host;
			}
			return null;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public ArrayList<IPv4Address> getAllIPv4Addresses(){
		int power = 32 - ipv4Praefix;
		int hostCount = (int) (Math.pow(2, power) - 2);
		if( hostCount <= 0 )
			return null;
		IPv4Address temp = new IPv4Address(ipv4SubnetID);
		ArrayList<IPv4Address> addresses = new ArrayList<IPv4Address>();
		do
			{
			addresses.add(IPv4Address.addOne(temp));
			--hostCount;
			}while( hostCount > 0);
		return addresses;
		}
	
	public ArrayList<IPv4Address> getAllFreeIPv4Addresses(){
		ArrayList<IPv4Address> alladdr = getAllIPv4Addresses();
		ArrayList<IPv4Address> usedaddr = getAllUsedIPv4Addresses();
		if( alladdr == null )
			return null;
		ArrayList<IPv4Address> freeaddr = new ArrayList<IPv4Address>();
		for( IPv4Address addr : alladdr ){
			if( !usedaddr.contains(addr))
				freeaddr.add(addr);
		}
		return freeaddr;
	}
	
	public ArrayList<IPv4Address> getAllUsedIPv4Addresses(){
		ArrayList<IPv4Address> usedAddresses = new ArrayList<IPv4Address>();
		for( Host host : hosts){
			usedAddresses.add(host.getIpv4Address());
		}
		return usedAddresses;
	} 
	
	private IPv4Address createBroadcastID() throws InvalidActivityException{
		int power = 32 - ipv4Praefix;
		if( power <= 0 )
			throw new InvalidActivityException("This Subnet is illegal (has less than 2 Addresses!)");
		int diff = (int) (Math.pow(2, power) - 1);
		IPv4Address broadcastID = ipv4SubnetID.plus(diff);
		return broadcastID;
	}
	
	public SubnetMask GetSubnetMask(){
		return new SubnetMask( ipv4Praefix );
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) throws Exception {
		if( network == null )
			throw new InstanceNotFoundException("Das Subnet ist noch keinem Netzwerk zugeordnet");
		for (Subnet net : network.getSubnets()) {
			if( department.equals(net.getDepartment() ) )
				throw new IllegalArgumentException("Das Netzwerk beinhaltet bereits die Abteilung " + department);
		}
		this.department = department;
	}

	public int getIpv4Praefix() {
		return ipv4Praefix;
	}

	public void setIpv4Praefix(int ipv4Praefix) throws Exception {
		if( network == null )
			throw new InstanceNotFoundException("Das Subnetz ist noch keinem Netzwerk zugeordnet");
		if( ipv4Praefix < network.getIpv4Praefix() )
			throw new IllegalArgumentException("Das Subnetz ist größer als das zugeordnete Netzwerk");
		this.ipv4Praefix = ipv4Praefix;
	}

	public ArrayList<Host> getHosts() {
		return hosts;
	}

	public void setHosts(ArrayList<Host> hosts) {
		this.hosts = hosts;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public IPv4Address getIpv4SubnetID() {
		return ipv4SubnetID;
	}

	public void setIpv4SubnetID(IPv4Address ipv4SubnetID) {
		this.ipv4SubnetID = ipv4SubnetID;
	}

	public IPv6Address getIpv6SubnetID() {
		return ipv6SubnetID;
	}

	public void setIpv6SubnetID(IPv6Address ipv6SubnetID) {
		this.ipv6SubnetID = ipv6SubnetID;
	}
	
	public boolean isIPv4Overlapping( Subnet other ) throws InvalidActivityException{
		if( other.getAllIPv4Addresses().contains(this.ipv4SubnetID) )
			return true;
		for (IPv4Address address : this.getAllIPv4Addresses() ) {
			if( other.getAllIPv4Addresses().contains(address) )
				return true;
		}
		if( other.getAllIPv4Addresses().contains(this.createBroadcastID()) )
			return true;
		return false;
	}
	
	public int getMaxHostCount(){
		int i = 0;
		while( Math.pow(2, i) - 2 < hosts.size() )
			i++;
		return (int) (Math.pow(2, i) - 2);
	}
}
