package BasicThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.Date;

public class Serveur {
	static int port = 8080;
	static int backlog = 3;
	
	public static void main(String[] args) throws IOException {
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		String path = "./files/";
		// server loop
		while (true) {
			// wait for a connection request
			Socket soc = listenSoc.accept();
			
			// communicate with the client
			
			InputStream is = soc.getInputStream();
			DataInputStream dis= new DataInputStream(is);
			String namefile = dis.readUTF();
			new Worker (path,namefile,soc);
			
		}
	}


}