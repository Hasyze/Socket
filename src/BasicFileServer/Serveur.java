package BasicFileServer;
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
	static int port = 4320;
	static int backlog = 3;
	
	public static void main(String[] args) throws IOException {
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		String path = "./files/";
		// server loop
		while (true) {
			// wait for a connection request
			Socket soc = listenSoc.accept();
			
			// communicate with the client
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			/*dos.writeInt(9999);
			dos.close();*/
			
			InputStream is = soc.getInputStream();
			DataInputStream dis= new DataInputStream(is);
			String namefile = dis.readUTF();
			
			System.out.println("read file: " + path + namefile);
			
			
				File f = new File(path+namefile);
				if(f.exists() && !f.isDirectory()){
					FileInputStream fis = new FileInputStream(f);
					
					byte[] data = new byte[(int) f.length()];
					dos.writeInt((int) f.length());
					data = fis.readAllBytes();
					dos.write(data);
					dos.flush();
				} else {
					dos.writeInt(-1);
				}
			
			dis.close();
			dos.close();
		}
	}


}