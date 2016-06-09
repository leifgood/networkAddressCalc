package Model;

import java.util.ArrayList;

public class IPv6Address {
	private ISP isp;
	private IPv6AddressBlock subnetID;
	private IPv6AddressBlock[] NetworkID;
	
	public static IPv6Address createIPv6ByMAC(ISP isp, IPv6AddressBlock subnetID, MACAddress mac){
		IPv6Address address = new IPv6Address();
		address.isp = isp;
		address.subnetID = subnetID;
		address.NetworkID = calculateNetID( mac );
		return address;
	}
	
	private static IPv6AddressBlock[] calculateNetID(MACAddress mac) {
		ArrayList<IPv6AddressBlock> blockList = new ArrayList<IPv6AddressBlock>();
		byte[] bytes = mac.getBytes();
		blockList.add(new IPv6AddressBlock((byte) (bytes[0] + 2) , bytes[1]));
		blockList.add(new IPv6AddressBlock(bytes[3], Byte.parseByte("FF", 16) ) );
		blockList.add(new IPv6AddressBlock(Byte.parseByte("FE", 16), bytes[4] ) );
		blockList.add(new IPv6AddressBlock(bytes[5], bytes[6] ) );
		return (IPv6AddressBlock[]) blockList.toArray();
	}

	public IPv6Address(){
		isp = null;
		subnetID = null;
		NetworkID = null;
	}

	public ISP getIsp() {
		return isp;
	}

	public void setIsp(ISP isp) {
		this.isp = isp;
	}

	public IPv6AddressBlock getSubnetID() {
		return subnetID;
	}

	public void setSubnetID(IPv6AddressBlock subnetID) {
		this.subnetID = subnetID;
	}

	public IPv6AddressBlock[] getNetworkID() {
		return NetworkID;
	}

	public void setNetworkID(IPv6AddressBlock[] networkID) {
		NetworkID = networkID;
	}
	
	public String toBinary(){
		String binary = isp.toBinary() + ":" + subnetID.toBinary() + ":";
		for( int i = 0; i < NetworkID.length; ++i )
		{
			binary += NetworkID[i].toBinary();
			if( i != NetworkID.length - 1)
				binary += ":";
		}
		return binary;
	}
	
	public String toHex(){
		String hex = isp.toHex() + ":" + subnetID.toHex() + ":";
		for( int i = 0; i < NetworkID.length; ++i )
		{
			hex += NetworkID[i].toHex();
			if( i != NetworkID.length - 1)
				hex += ":";
		}
			return hex;
		}
	public String toDecimal(){
		String decimal = isp.toDecimal() + ":" + subnetID.toDecimal() + ":";
		for( int i = 0; i < NetworkID.length; ++i )
		{
			decimal += NetworkID[i].toDecimal();
			if( i != NetworkID.length - 1)
				decimal += ":";
		}
			return decimal;
		}
	
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
		adr.setIsp( new ISP( (IPv6AddressBlock[]) blocks.subList(0, 2).toArray() ) );
		adr.setSubnetID(blocks.get(3));
		adr.setNetworkID( (IPv6AddressBlock[]) blocks.subList(4, blocks.size() - 1).toArray() );
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
}
