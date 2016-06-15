package Model;

import java.io.Serializable;

public class Host implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IPv4Address ipv4Address;
	private IPv6Address ipv6Address;
	private String name;
	
	public Host(){
		ipv4Address = null;
		ipv6Address = null;
		name = "";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
