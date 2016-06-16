package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Path SERIALIZED_FILE_DIR = Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), "NetworkCalc");
	private static final Path SERIALIZED_FILE_NAME = Paths.get(SERIALIZED_FILE_DIR.toString(), "save.dat");
	
	private ArrayList<Network> networks;
	
	public Data(){
		networks = new ArrayList<Network>();
	}
	
	public ArrayList<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<Network> networks) {
		this.networks = networks;
	}

	public void addNetwork( Network network){
		for (Network net : networks) {
			if( network.getIpv4Networkmask().equals(net.getIpv4Networkmask()))
				throw new IllegalArgumentException("The adressspace " + network.getIpv4Networkmask().toDecimal() + " is already used!");
		}
		networks.add(network);
	}
	
	public void deleteNetwork( Network network){
		networks.remove(network);
	}
	
	public void save() throws Exception{
		
		if( !Files.exists(SERIALIZED_FILE_DIR) )
			Files.createDirectory(SERIALIZED_FILE_DIR);
		if( !Files.exists(SERIALIZED_FILE_NAME) )
			Files.createFile(SERIALIZED_FILE_NAME);
		
		OutputStream fos = null;

		  fos = new FileOutputStream( SERIALIZED_FILE_NAME.toString() );
		  ObjectOutputStream o = new ObjectOutputStream( fos );
		  o.writeObject( this );
		  fos.close();
		
	}
	
	public static Data load() throws Exception{
		
		InputStream fis = new FileInputStream( SERIALIZED_FILE_NAME.toString() );
		@SuppressWarnings("resource")
		ObjectInputStream o = new ObjectInputStream( fis );
		Data data = (Data)o.readObject();

		return data;
	}
	
	public Network getNetworkByIPv4( IPv4Address ip ){
		for( Network network : networks )
			if( network.getIpv4Networkmask().equals(ip) )
				return network;
		return null;
	}
}
