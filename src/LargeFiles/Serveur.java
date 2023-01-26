package LargeFiles;
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
import java.util.ArrayList;

public class Serveur {
	static int port = 8080;
	static int backlog = 3;
	
	public static void main(String[] args) throws IOException {
		ServerSocket listenSoc = new ServerSocket(port, backlog);
		String path = "./files/";
		ProdConsBuffer buff = new ProdConsBuffer(3);
		ArrayList<Worker> tableau = new ArrayList<Worker>();
		for (int i = 0; i < 3; i++) {
			tableau.add(new Worker(buff));
		}
		// server loop
		while (true) {
			// wait for a connection request
			Socket soc = listenSoc.accept();
			
			try {
				buff.put(soc);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}


}