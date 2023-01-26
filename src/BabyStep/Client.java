package BabyStep;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Client {
	static String serverHost = "localhost";
	static int serverPort = 4320;

	
	public static void main(String[] args) throws IOException  {
		Socket soc = new Socket(serverHost, serverPort);
		
		// communicate with the server
		InputStream is = soc.getInputStream();
		DataInputStream dis= new DataInputStream(is);
		
		OutputStream os = soc.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		String name = serverHost;
		
		dos.writeUTF(name);
		String msg = dis.readUTF();
		System.out.println(msg);
		is.close();
		dos.close();
	}
}



