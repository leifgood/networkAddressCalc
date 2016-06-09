package Model;

import java.util.ArrayList;

public class SubnetMask {
	private byte[] bytes;

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		if( !isValid( bytes ) )
			throw new IllegalArgumentException("Keine gültige Subnetzmaske");
		this.bytes = bytes;
	}

	private boolean isValid(byte[] bytes) {
		String str = "";
		for (byte b : bytes) {
			str += byteToBinaryString(b);
		}
		return isValidSubnetMask(str);
	}

	public SubnetMask(int ipv4Praefix) {
		bytes = getBytesFromPraefix(ipv4Praefix);
	}
	
	public SubnetMask(byte[] bytes) {
		this.setBytes(bytes);
	}
	
	public SubnetMask(){
		bytes = null;
	}
	
	private byte[] getBytesFromPraefix(int ipv4Praefix) {
		String bin = "";
		ArrayList<String> blocks = new ArrayList<String>();
		int zeroCount = 32 - ipv4Praefix;
		while( ipv4Praefix > 0){
			bin += "1";
			--ipv4Praefix;
		}
		while( zeroCount > 0){
			bin += "0";
			--zeroCount;
		}
		int begin = 0;
		int end = 7;
		while( end < 32 ){
			blocks.add(bin.substring(begin, end));
			begin = end + 1;
			end = begin + 7;
		}
		byte[] tempBytes = new byte[4];
		for( int index = 0; index < blocks.size(); ++ index ){
			tempBytes[index] = Byte.parseByte(blocks.get(index), 2);
		}
		return tempBytes;
	}

	public String toBinary(){
		String mask = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			mask = byteToBinaryString(bytes[i]);
			if( i != bytes.length - 1 ){
				mask += ".";
			}
		}
		return mask;
	}
	
	private String byteToBinaryString(byte b) {
		return Integer.toBinaryString(b);
	}

	public String toHex(){
		String mask = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			mask = byteToHexString(bytes[i]);
			if( i != bytes.length - 1 ){
				mask += ".";
			}
		}
		return mask;
	}
	
	private String byteToHexString(byte b) {
		return Integer.toHexString(b);
	}

	public String toDecimal(){
		String mask = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			mask += bytes[i];
			if( i != bytes.length - 1 ){
				mask += ".";
			}
		}
		return mask;
	}
	
	public static SubnetMask generateFromString( String string ){
		String[] splitstring = string.split(".");
		if( splitstring.length != 4 )
			throw new IllegalArgumentException( "Keine gültige Subnetzmaske eingegeben");
		for (String str : splitstring) {
			if( Integer.parseInt(str) < 0 || Integer.parseInt(str) > 255 )
				throw new IllegalArgumentException( "Kein gültiges Byte angegeben. Eingabe: " + str);
		}
		byte[] bytes = new byte[4];
		
		for( int i = 0; i < 4; ++i){
			bytes[i] = Byte.parseByte(splitstring[i]);
		}
		SubnetMask mask = new SubnetMask();
		String checkString = "";
		for( int i = 0; i < 4; ++i){
			checkString += mask.byteToBinaryString(bytes[i]);
		}
		if( !isValidSubnetMask(checkString) )
			throw new IllegalArgumentException( "Keine gültige Subnetzmaske eingegeben");
		mask.setBytes(bytes);
		return mask;
	}

	private static boolean isValidSubnetMask(String checkString) {
		int index = 0;
		if( checkString.startsWith("1"))
		{
			while( index < checkString.length() && checkString.charAt(index) == '1')
				++index;
			while( index < checkString.length() && checkString.charAt(index) == '0')
				++index;

		}
		return index == checkString.length();
	}
}
