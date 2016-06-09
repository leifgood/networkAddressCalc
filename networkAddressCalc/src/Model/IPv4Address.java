package Model;

import java.util.*;

public class IPv4Address {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bytes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPv4Address other = (IPv4Address) obj;
		if (!Arrays.equals(bytes, other.bytes))
			return false;
		return true;
	}

	private byte[] bytes;
	
	public IPv4Address(){
		bytes = null;
	}
	
	public IPv4Address( byte[] bytes){
		if( bytes.length != 4 )
			throw new IllegalArgumentException( "Nicht genau 4 Bytes");
		this.bytes = bytes;
	}
	
	public String toBinary(){
		String address = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			address = byteToBinaryString(bytes[i]);
			if( i != bytes.length - 1 ){
				address += ".";
			}
		}
		return address;
	}
	
	private String byteToBinaryString(byte b) {
		return Integer.toBinaryString(b);
	}

	public String toHex(){
		String address = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			address = byteToHexString(bytes[i]);
			if( i != bytes.length - 1 ){
				address += ".";
			}
		}
		return address;
	}
	
	private String byteToHexString(byte b) {
		return Integer.toHexString(b);
	}

	public String toDecimal(){
		String address = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			address += bytes[i];
			if( i != bytes.length - 1 ){
				address += ".";
			}
		}
		return address;
	}

	public static IPv4Address addOne(IPv4Address temp) {
		++temp.bytes[3];
		if( temp.bytes[3] == 0){
			++temp.bytes[2];
			if( temp.bytes[2] == 0){
				++temp.bytes[1];
				if( temp.bytes[1] == 0){
					++temp.bytes[0];
				}
			}
		}
		return temp;
	}
	
	public static IPv4Address generateFromString( String string ){
		String[] splitstring = string.split(".");
		if( splitstring.length != 4 )
			throw new IllegalArgumentException( "Keine gültige IPv4Adresse eingegeben");
		for (String str : splitstring) {
			if( Integer.parseInt(str) < 0 || Integer.parseInt(str) > 255 )
				throw new IllegalArgumentException( "Kein gültiges Byte angegeben. Eingabe: " + str);
		}
		byte[] bytes = new byte[4];
		for( int i = 0; i < 4; ++i){
			bytes[i] = Byte.parseByte(splitstring[i]);
		}
		return new IPv4Address(bytes);
	}
}
