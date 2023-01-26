package PoolBasedThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Worker extends Thread {
	String path="./files/";
	String namefile;
	Socket soc;
	ProdConsBuffer buff;
	
	public Worker (String path, String namefile, Socket soc) {
		this.path = path;
		this.namefile = namefile;
		this.soc =soc;
		this.start();
	}
	
	public Worker(ProdConsBuffer buff) {
		this.buff= buff;
		this.start();
	}
	
	public void run() {
		try {
			soc = buff.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			InputStream is = soc.getInputStream();
			DataInputStream dis= new DataInputStream(is);
			
			String namefile = dis.readUTF();
		
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
