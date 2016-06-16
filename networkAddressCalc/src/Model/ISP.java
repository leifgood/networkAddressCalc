package Model;

import java.io.Serializable;

public class ISP implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Short[] values;
	private int ISPPraefix;
	public ISP( Short[] values ) {
		this.setValues(values);
	}
	
	public ISP(){
		values = null;
		ISPPraefix = -1;
	}

	public ISP(ISP isp) {
		values = new Short[isp.getValues().length];
		for( int i = 0; i < values.length; ++i ){
			values[i] = isp.getValues()[i];
		}
	}

	public Short[] getValues() {
		return values;
	}

	public void setValues( Short[] values) {
		if( values.length != ISPPraefix / 4 )
			throw new IllegalArgumentException( "No valid address space!");
		this.values = values;
	}
	
	public String toBinary(){
		String binary = "";
		for( int i = 0; i < values.length; ++i){
			if(i > 0 && i % 4 == 0)
				binary += ":";
			binary += Integer.toBinaryString(values[i]); 
		}
		return binary;
	}
	
	public String toHex(){
		String hex = "";
		for( int i = 0; i < values.length; ++i){
			hex += Integer.toHexString(values[i]); 
		}
		return hex.toUpperCase();
	}
	

	public String toDecimal(){
		String decimal = "";
		for( int i = 0; i < values.length; ++i){
			decimal += values[i];
		}
		return decimal;
	}
	
	public String toString(){
		String temp = toHex();
		String str = "";
		for( int i = 0; i < values.length; ++i){
			if( i > 0 && i % 4 == 0)
				str += ":";
			str += temp.charAt(i); 
		}
		str += "::";
		return str.toUpperCase();
	}
	
	public void generateFromString( String str ){
		if( str.contains("IPv6: ")){
			str = str.split("IPv6: ")[1];
		}
		if( str.contains("::") )
			str = generateFullString(str);
		String[] splitstring = str.split(":");
		if( splitstring.length != ISPPraefix / 16 )
			throw new IllegalArgumentException("Syntaxerror in IPv6 address space!");
		for (int i = 0; i < splitstring.length; ++i) {
			while( splitstring[i].length() < 4 )
				splitstring[i] = "0" + splitstring[i]; 
		}
		Short[] blocks = new Short[ISPPraefix / 4];
		for( int i = 0, j = 0; i < blocks.length; ++i ){
			if( i > 0 && i % 4 == 0 )
				++j;
			blocks[i] = Short.parseShort("" + splitstring[j].charAt(i % 4), 16);
		}
		this.setValues( blocks );
	}

	private String generateFullString(String str) {
		String[] splitstring = str.split("::");
		if( splitstring.length == 1 ){
			String temp = splitstring[0];
			splitstring = new String[]{ temp, ""};
		}
		if( splitstring.length != 2 )
			throw new IllegalArgumentException( "Syntaxerror in IPv6 address" );
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
			zeroBlockCount = ISPPraefix / 16 - rhs.length;
			for( int i = 0; i < zeroBlockCount; ++i )
				returnString += "0:";
			returnString += splitstring[1];
		}
			
		else if( rhs == null ){
			zeroBlockCount = ISPPraefix / 16 - lhs.length;
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
		}
			
		else{
			zeroBlockCount = ISPPraefix / 16 - ( lhs.length + rhs.length );
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
			returnString += ":" + splitstring[1];
		}
		return returnString;
	}

	public int getISPPraefix() {
		return ISPPraefix;
	}

	public void setISPPraefix(int iSPPraefix) {
		if( iSPPraefix <= 0 && iSPPraefix % 16 != 0 && iSPPraefix > 96)
			throw new IllegalArgumentException("No valid prefix!");
		ISPPraefix = iSPPraefix;
	}
	
}
