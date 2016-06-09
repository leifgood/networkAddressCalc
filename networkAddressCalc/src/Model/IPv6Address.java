package Model;

import java.util.ArrayList;

public class IPv6Address {
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
	
//	public String toHex(){
//		String hex = isp.toHex() + ":" + subnetID.toHex() + ":";
//		for( int i = 0; i < NetworkID.length; ++i )
//		{
//			hex += NetworkID[i].toHex();
//			if( i != NetworkID.length - 1)
//				hex += ":";
//		}
//			return hex;
//		}
	
	public static IPv6Address generateFromString( String string ){
		if( string.contains("::"))
			string =  generateStringFromZeroCompression( string );
		String[] splitstring = string.split(":");
		if( splitstring.length != 8 )
			throw new IllegalArgumentException( "Syntaxfehler in der IPv6 Adresse" );
		ArrayList<IPv6AddressBlock> blocks = new ArrayList<IPv6AddressBlock>();
		for (String str : splitstring) {
			blocks.add(IPv6AddressBlock.generateFromString( str ) );
		}
		IPv6Address adr = new IPv6Address();
//		adr.setIsp( new ISP( (IPv6AddressBlock[]) blocks.subList(0, 2).toArray() ) );
//		adr.setSubnetID(blocks.get(3));
//		adr.setNetworkID( (IPv6AddressBlock[]) blocks.subList(4, blocks.size() - 1).toArray() );
		return adr;
	}

	private static String generateStringFromZeroCompression(String string) {
		String[] splitstring = string.split("::");
		if( splitstring.length != 2 )
			throw new IllegalArgumentException( "Syntaxfehler in der IPv6 Adresse" );
		String[] lhs;
		String[] rhs;
		
		if( splitstring[0].length() == 0 )
			lhs = null;
		else
			lhs = splitstring[0].split(":");
		
		if( splitstring[1].length() == 0 )
			rhs = null;
		else
			rhs = splitstring[1].split(":");
		
		int zeroBlockCount;
		String returnString = "";
		
		if( lhs == null ){
			zeroBlockCount = 8 - rhs.length;
			for( int i = 0; i < zeroBlockCount; ++i )
				returnString += "0:";
			returnString += splitstring[1];
		}
			
		else if( rhs == null ){
			zeroBlockCount = 8 - lhs.length;
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
		}
			
		else{
			zeroBlockCount = 8 - ( lhs.length + rhs.length );
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
			returnString += ":" + splitstring[1];
		}
		return returnString;
			
	}

	public static IPv6Address addOne(IPv6Address ipv6address) {
		// TODO Auto-generated method stub
		return null;
	}
}
