package BasicThread;
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
		System.out.println(len);
		if (len != -1) {
			byte[] data = new byte[len];
			dis.readFully(data);
			
			FileOutputStream fos = new FileOutputStream(path+filename);
			fos.write(data);
			fos.close();
			
			File f = new File (path  + "client");
			f.createNewFile();
			FileOutputStream fos2 = new FileOutputStream(f);
			fos2.write(data);
			fos2.flush();
			fos2.close();
			
		}
		
		dis.close();
		dos.close();
	}
}



