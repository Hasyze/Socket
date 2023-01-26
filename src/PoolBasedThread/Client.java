package PoolBasedThread;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Client {
	static String serverHost = "localhost";
	static int serverPort = 8080;

	
	public static void main(String[] args) throws IOException  {
		Socket soc = new Socket(serverHost, serverPort);
		String path = "./files/";
		
		// communicate with the server
		InputStream is = soc.getInputStream();
		DataInputStream dis= new DataInputStream(is);
		
		
		OutputStream os = soc.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		String filename = "NomFichier.txt";
		
		dos.writeUTF(filename);
		
		int len = dis.readInt();
		
		if (len != -1) {
			byte[] data = new byte[len];
			dis.readFully(data);
			
			
			File f = new File (path  + "Client.txt");
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(data);
			fos.flush();
			fos.close();
			
		}
		
		dis.close();
		dos.close();
	}
}



