package BasicThread;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Worker extends Thread {
	String path="./files/";
	String namefile;
	Socket soc;
	
	public Worker (String path, String namefile, Socket soc) {
		this.path = path;
		this.namefile = namefile;
		this.soc =soc;
		this.start();
	}
	
	public void run() {
		OutputStream os;
		try {
			os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
		
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
		
		dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
