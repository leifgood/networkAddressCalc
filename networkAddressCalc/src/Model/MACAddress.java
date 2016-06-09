package Model;

public class MACAddress {
	private byte[] bytes;
	
	public MACAddress(){
		bytes = new byte[6];
	}
	
	public byte[] getBytes(){
		return bytes;
	}
	
	public void setBytes( byte[] bytes) throws Exception{
		if( bytes.length != 6 )
			throw new Exception( "No MAC");
		this.bytes = bytes;
	}
	
	public String toBinary(){
		String address = "";
		for( int i = 0; i < bytes.length; ++i )
		{
			address = byteToBinaryString(bytes[i]);
			if( i != bytes.length - 1 ){
				address += ":";
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
				address += ":";
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
				address += ":";
			}
		}
		return address;
	}
	
	
	public static MACAddress generateFromString( String str ) throws Exception{
		String[] splitstring = str.split(":");
		if( splitstring.length != 6 )
			throw new IllegalArgumentException("Syntaxfehler in der MAC Adresse");
		byte[] bytes = new byte[6];
		for( int i = 0; i < 6; ++i){
			bytes[i] = Byte.parseByte(splitstring[i]);
		}
		MACAddress adr = new MACAddress();
		adr.setBytes( bytes );
		return adr;
	}
}
