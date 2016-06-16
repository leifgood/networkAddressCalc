package Model;

import java.io.Serializable;
import java.util.*;

public class IPv4Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private int[] bytes;
	
	public IPv4Address(){
		bytes = null;
	}
	
	public IPv4Address( int[] bytes){
		if( bytes.length != 4 )
			throw new IllegalArgumentException( "Nicht genau 4 Bytes");
		this.bytes = bytes;
	}
	
	public IPv4Address(IPv4Address address) {
		bytes = new int[4];
		bytes[0] = address.bytes[0];
		bytes[1] = address.bytes[1];
		bytes[2] = address.bytes[2];
		bytes[3] = address.bytes[3];
	}

	public String toBinary(){
		String address = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			address += Integer.toBinaryString(bytes[i]);
			if( i != bytes.length - 1 ){
				address += ".";
			}
		}
		return address;
	}

	public String toHex(){
		String address = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			address += Integer.toHexString(bytes[i]);
			if( i != bytes.length - 1 ){
				address += ".";
			}
		}
		return address;
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
		if( temp.bytes[3] == 256){
			temp.bytes[3] = 0;
			++temp.bytes[2];
			if( temp.bytes[2] == 256){
				temp.bytes[2] = 0;
				++temp.bytes[1];
				if( temp.bytes[1] == 256){
					temp.bytes[1] = 0;
					++temp.bytes[0];
				}
			}
		}
		return temp;
	}
	
	public static IPv4Address generateFromString( String string ) throws Exception {
		if( string.startsWith("IPv4:")){
			string = string.replace("IPv4: ", "");
			if( string.contains("IPv6:")){
				string = string.split("IPv6:")[0].trim();
			}
			if( string.endsWith(";")){
				string = string.substring(0, string.length() - 1);
			}
		}
		String[] splitstring = string.trim().split("\\.");
		if( splitstring.length != 4 )
			throw new IllegalArgumentException( "Keine gültige IPv4Adresse eingegeben");
		for (String str : splitstring) {
			if( Integer.parseInt(str.trim()) < 0 || Integer.parseInt(str.trim()) > 255 )
				throw new IllegalArgumentException( "Kein gültiges Byte angegeben. Eingabe: " + str);
		}
		int[] bytes = new int[4];
		for( int i = 0; i < 4; ++i){
			bytes[i] = Integer.parseInt(splitstring[i].trim());
		}
		return new IPv4Address(bytes);
	}

	public IPv4Address plus(int diff) {
		IPv4Address address = new IPv4Address(this);
		for( int i = 0; i < diff; ++i){
			address = addOne(address);
		}
		return address;
	}
}
