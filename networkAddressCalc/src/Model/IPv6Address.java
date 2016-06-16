package Model;

import java.io.Serializable;

import javax.activity.InvalidActivityException;

public class IPv6Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ISP isp;
	private Short[] subnetID;
	private Short[] networkID;
	
	public IPv6Address(){
		isp = null;
		subnetID = null;
		networkID = null;
	}

	public IPv6Address(IPv6Address clone) {
		this.isp = new ISP( clone.isp );
		this.subnetID = new Short[4];
		this.subnetID[0] = clone.subnetID[0];
		this.subnetID[1] = clone.subnetID[1];
		this.subnetID[2] = clone.subnetID[2];
		this.subnetID[3] = clone.subnetID[3];
		this.networkID = new Short[clone.networkID.length];
		for( int i = 0; i < this.networkID.length; ++i )
			networkID[i] = clone.networkID[i];
	}

	public ISP getIsp() {
		return isp;
	}

	public void setIsp(ISP isp) {
		this.isp = isp;
	}

	public Short[] getSubnetID() {
		return subnetID;
	}

	public void setSubnetID(Short[] subnetID) {
		if( subnetID.length != 4 )
			throw new IllegalArgumentException( "no correct subnetID ");
		this.subnetID = subnetID;
	}

	public Short[] getNetworkID() {
		return networkID;
	}

	public void setNetworkID(Short[] networkID) {
		if( networkID.length % 4 != 0 || networkID.length <= 0 )
			throw new IllegalArgumentException("No correct networkID");
		this.networkID = networkID;
	}
	
	@Override
	public String toString(){
		String hex = isp.toHex();
		for (Short sh : subnetID) {
			hex += Integer.toHexString(sh);
		}
		for (Short sh : networkID) {
			hex += Integer.toHexString(sh);
		}
		String result = "";
		for( int i = 0; i < hex.length(); ++i ){
			if( i > 0 && i % 4 == 0 )
				result += ":";
			result += hex.charAt(i);
		}
		return result.toUpperCase();
		}

	public static IPv6Address addOne(IPv6Address ipv6address) throws InvalidActivityException {
		int i = ipv6address.networkID.length -1;
		boolean abort = false;
		while( !abort ){
			if( i < 0 )
				throw new InvalidActivityException("The maximal number of hosts in this network is reached!");
			ipv6address.networkID[i]++;
			if( ipv6address.networkID[i] == 16 ){
				ipv6address.networkID[i] = 0;
				--i;
			}
			else
				abort = true;
		}
		return ipv6address;
	}
}
