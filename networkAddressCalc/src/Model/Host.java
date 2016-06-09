package Model;

public class Host {
	private IPv4Address ipv4Address;
	private IPv6Address ipv6Address;
	private MACAddress macAddress;
	private String name;
	
	public Host(){
		ipv4Address = null;
		ipv6Address = null;
		macAddress = null;
		name = "";
	}
	
	public Host( IPv4Address ipv4Address, IPv6Address ipv6Address, MACAddress macAddress, String name ){
		this.setIpv4Address(ipv4Address);
		this.setIpv6Address(ipv6Address);
		this.setMacAddress(macAddress);
		this.setName(name);
	}

	public IPv4Address getIpv4Address() {
		return ipv4Address;
	}

	public void setIpv4Address(IPv4Address ipv4Address) {
		this.ipv4Address = ipv4Address;
	}

	public IPv6Address getIpv6Address() {
		return ipv6Address;
	}

	public void setIpv6Address(IPv6Address ipv6Address) {
		this.ipv6Address = ipv6Address;
	}

	public MACAddress getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(MACAddress macAddress) {
		this.macAddress = macAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
