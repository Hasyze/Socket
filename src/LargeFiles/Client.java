package LargeFiles;
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
		
		//int len = dis.readInt();
		long len = dis.readLong();
		long offset =0;
		if (len != -1) {
			byte[] data = new byte[(int) len];
			
			File f = new File (path  + "Client.txt");
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			while(offset < len) {
				dis.read(data, 0, 512);
				fos.write(data, 0, 512);
				offset += 512;
			}
			fos.flush();
			fos.close();
			
		}
		
		dis.close();
		dos.close();
	}
}



