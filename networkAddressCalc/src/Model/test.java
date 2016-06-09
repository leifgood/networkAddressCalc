package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.filechooser.FileSystemView;

public class test {

	public static void main(String[] args) {
		Path dir = Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), "NetworkCalc" );
		Path path = Paths.get( dir.toString(), "save.xml");
		System.out.println(path);
		FileOutputStream out = null;
		if( !Files.exists(dir) )
			try {
				Files.createDirectory(dir);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if( !Files.exists(path) )
			try {
				Files.createFile(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			out = new FileOutputStream(path.toString());
			out.write(64);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
