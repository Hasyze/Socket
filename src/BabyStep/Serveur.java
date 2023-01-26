package BabyStep;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.Date;

public class Serveur{
	static int port = 4320;
	static int backlog = 3;
	
	
	public static void main(String[] args)throws IOException {
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		// server loop
		while (true) {
			// wait for a connection request
			Socket soc = listenSoc.accept();
			
			// communicate with the client
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			InputStream is = soc.getInputStream();
			DataInputStream dis= new DataInputStream(is);
			String m = dis.readUTF();
			
			String msg = "Hello "+m;
			dos.writeUTF(msg.toString());
			is.close();
			dos.close();
		}
	}


}