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
	private Short[] NetworkID;
	
	public IPv6Address(){
		isp = null;
		subnetID = null;
		NetworkID = null;
	}

	public IPv6Address(IPv6Address clone) {
		this.isp = clone.isp;
		this.subnetID = clone.subnetID;
		this.NetworkID = clone.NetworkID;
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
		this.subnetID = subnetID;
	}

	public Short[] getNetworkID() {
		return NetworkID;
	}

	public void setNetworkID(Short[] networkID) {
		NetworkID = networkID;
	}
	
	@Override
	public String toString(){
		String hex = isp.toHex();
		for (Short sh : subnetID) {
			hex += Integer.toHexString(sh);
		}
		for (Short sh : NetworkID) {
			hex += Integer.toHexString(sh);
		}
		String result = "";
		for( int i = 0; i < hex.length(); ++i ){
			result += hex.charAt(i);
			if( i > 0 && i % 4 == 0 && i < hex.length() - 1 )
				result += ":";
		}
		return result;
		}

	public static IPv6Address addOne(IPv6Address ipv6address) throws InvalidActivityException {
		int i = ipv6address.NetworkID.length -1;
		boolean abort = false;
		while( !abort ){
			if( i < 0 )
				throw new InvalidActivityException("Die IPv6 Adresse kann nicht erhöht werden, da dies maximale Anzahl Hosts erreicht wurde.");
			ipv6address.NetworkID[i]++;
			if( ipv6address.NetworkID[i] == 16 ){
				ipv6address.NetworkID[i] = 0;
				--i;
			}
			else
				abort = true;
		}
		return ipv6address;
	}
}
