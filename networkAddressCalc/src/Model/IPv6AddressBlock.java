package Model;

public class IPv6AddressBlock {
	private byte byte1;
	private byte byte2;
	
	
	public byte getByte1() {
		return byte1;
	}

	public void setByte1(byte byte1) {
		this.byte1 = byte1;
	}

	public byte getByte2() {
		return byte2;
	}

	public void setByte2(byte byte2) {
		this.byte2 = byte2;
	}

	public IPv6AddressBlock( byte byte1, byte byte2 ){
		this.byte1 = byte1;
		this.byte2 = byte2;
	}
	
	public IPv6AddressBlock(){
		this.byte1 = 0;
		this.byte2 = 0;
	}
	
	public String toBinary(){
		String binary = Integer.toBinaryString(byte1);
		binary += Integer.toBinaryString(byte2);
		return binary;
	}
	
	public String toHex(){
		String hex = Integer.toHexString(byte1);
		hex += Integer.toHexString(byte2);
		return hex;
	}
	
	public String toDecimal(){
		return Integer.toString(byte1 * 256 + byte2);
	}
	
	public void setBytes( byte byte1, byte byte2 ){
		this.byte1 = byte1;
		this.byte2 = byte2;
	}

	public static IPv6AddressBlock generateFromString(String str) {
		if( str.length() > 5 || str.length() < 1 )
			throw new IllegalArgumentException( "Syntaxfehler in der IPv6 Adresse" );
		if( str.length() < 4 ){
			for( int i = str.length(); i < 4; ++i )
				str = "0" + str;
		}
		String byteString1 = str.substring(0, 1);
		String byteString2 = str.substring(2, 3);
		IPv6AddressBlock block = new IPv6AddressBlock();
		block.byte1 = Byte.parseByte( byteString1, 16 );
		block.byte2 = Byte.parseByte( byteString2, 16 );
		return block;
	}
}
