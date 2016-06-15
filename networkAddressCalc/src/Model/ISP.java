package Model;

import java.io.Serializable;
import java.util.ArrayList;

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

	public Short[] getValues() {
		return values;
	}

	public void setValues( Short[] values) {
		if( values.length != ISPPraefix / 4 )
			throw new IllegalArgumentException( "Keine gültige ISP!");
		this.values = values;
	}
	
	public String toBinary(){
		String binary = "";
		for( int i = 0; i < values.length; ++i){
			binary += Integer.toBinaryString(values[i]); 
			if( i > 0 && i % 4 == 0 && i < values.length - 1 )
				binary += ":";
		}
		return binary;
	}
	
	public String toHex(){
		String hex = "";
		for( int i = 0; i < values.length; ++i){
			hex += Integer.toHexString(values[i]); 
			if( i > 0 && i % 4 == 0 && i < values.length - 1 )
				hex += ":";
		}
		return hex;
	}
	

	public String toDecimal(){
		String decimal = "";
		for( int i = 0; i < values.length; ++i){
			decimal += values[i];
			if( i > 0 && i % 4 == 0 && i < values.length - 1 )
				decimal += ":";
		}
		return decimal;
	}
	
	public void generateFromString( String str ){
		if( str.contains("::") )
			str = generateFullString(str);
		String[] splitstring = str.split(":");
		if( splitstring.length != ISPPraefix / 4 )
			throw new IllegalArgumentException("Syntaxfehler in der ISP");
		ArrayList<Short> blocks = new ArrayList<Short>();
		for (String s : splitstring) {
			for (char c : s.toCharArray()) {
				String temp = "" + c;
				Integer.parseInt(temp, 16);
			}
		}
		this.setValues( (Short[]) ( blocks.toArray() ) );
	}

	private String generateFullString(String str) {
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
			zeroBlockCount = ISPPraefix / 4 - rhs.length;
			for( int i = 0; i < zeroBlockCount; ++i )
				returnString += "0:";
			returnString += splitstring[1];
		}
			
		else if( rhs == null ){
			zeroBlockCount = ISPPraefix / 4 - lhs.length;
			returnString += splitstring[0];
			for( int i = 0; i < zeroBlockCount; ++i )
					returnString += ":0";
		}
			
		else{
			zeroBlockCount = ISPPraefix / 4 - ( lhs.length + rhs.length );
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
		if( iSPPraefix <= 0 && iSPPraefix % 4 != 0 && iSPPraefix > 96)
			throw new IllegalArgumentException("Kein gültiger Präfix");
		ISPPraefix = iSPPraefix;
	}
	
}
