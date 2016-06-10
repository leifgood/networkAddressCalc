package Model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

public class Data {
	private static final Path SERIALIZED_FILE_DIR = Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), "NetworkCalc");
	private static final Path SERIALIZED_FILE_NAME = Paths.get(SERIALIZED_FILE_DIR.toString(), "save.xml");
	
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
				throw new IllegalArgumentException("Die Netzwerkadresse " + network.getIpv4Networkmask().toDecimal() + " ist bereits belegt");
		}
		networks.add(network);
	}
	
	public void deleteNetwork( Network network){
		networks.remove(network);
	}
	
	@SuppressWarnings("resource")
	public void save() throws Exception{
		
		if( !Files.exists(SERIALIZED_FILE_DIR) )
			Files.createDirectory(SERIALIZED_FILE_DIR);
		if( !Files.exists(SERIALIZED_FILE_NAME) )
			Files.createFile(SERIALIZED_FILE_NAME);
		
		XMLEncoder encoder = null;
		encoder = new XMLEncoder( new BufferedOutputStream( new FileOutputStream(SERIALIZED_FILE_NAME.toString())));
		encoder.writeObject(this);
	}
	
	@SuppressWarnings("resource")
	public static Data load() throws Exception{
		XMLDecoder decoder=null;
		decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME.toString())));
		Data data=(Data)decoder.readObject();
		return data;
	}
	
	public Network getNetworkByIPv4( IPv4Address ip ){
		for( Network network : networks )
			if( network.getIpv4Networkmask().equals(ip) )
				return network;
		return null;
	}
}
