package Model;

import java.util.ArrayList;

public class ISP {
	private IPv6AddressBlock[] blocks;
	
	public ISP( IPv6AddressBlock[] blocks ) {
		this.setBlocks(blocks);
	}
	
	public ISP(){
		blocks = null;
	}

	public IPv6AddressBlock[] getBlocks() {
		return blocks;
	}

	public void setBlocks(IPv6AddressBlock[] blocks) {
		if( blocks.length != 3 )
			throw new IllegalArgumentException( "Keine gültige ISP!");
		this.blocks = blocks;
	}
	
	public String toBinary(){
		String binary = "";
		for( int i = 0; i < blocks.length; ++i){
			binary += blocks[i].toBinary(); 
			if( i < blocks.length - 1)
				binary += ":";
		}
		return binary;
	}
	
	public String toHex(){
		String hex = "";
		for( int i = 0; i < blocks.length; ++i){
			hex += blocks[i].toHex(); 
			if( i < blocks.length - 1)
				hex += ":";
		}
		return hex;
	}
	

	public String toDecimal(){
		String decimal = "";
		for( int i = 0; i < blocks.length; ++i){
			decimal += blocks[i].toDecimal(); 
			if( i < blocks.length - 1)
				decimal += ":";
		}
		return decimal;
	}
	
	public static ISP generateFromString( String str ){
		if( str.contains("::") )
			str = generateFullString(str);
		String[] splitstring = str.split(":");
		if( splitstring.length != 3 )
			throw new IllegalArgumentException("Syntaxfehler in der ISP");
		ArrayList<IPv6AddressBlock> blocks = new ArrayList<IPv6AddressBlock>();
		for (String s : splitstring) {
			blocks.add(IPv6AddressBlock.generateFromString( s ) );
		}
		return new ISP( (IPv6AddressBlock[]) blocks.toArray());
	}

	private static String generateFullString(String str) {
		String[] splitstring = str.split("::");
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
			zeroBlockCount = 3 - rhs.length;
			for( int i = 0; i < zeroBlockCount; ++i )
				returnString += "0:";
			returnString += splitstring[1];
		}
			
		else if( rhs == null ){
			zeroBlockCount = 3 - lhs.length;
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
		}
			
		else{
			zeroBlockCount = 3 - ( lhs.length + rhs.length );
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
			returnString += ":" + splitstring[1];
		}
		return returnString;
	}
	
}
