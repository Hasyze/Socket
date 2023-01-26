package PoolBasedThread;

import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ProdConsBuffer {

	int bufferSz;
	Socket[] buffer;
	int totmsg = 0;
	Semaphore notFull;
	Semaphore notEmpty;
	Semaphore mutexIn, mutexOut;
	int get, put;
	boolean done;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Socket[bufferSz];
		get = 0;
		put = 0;
		notFull = new Semaphore(bufferSz, true);
		notEmpty = new Semaphore(0, true);
		mutexIn = new Semaphore(1, true);
		mutexOut = new Semaphore(1, true);
		done = false;
	}

	public void put(Socket m) throws InterruptedException {
		notFull.acquire();
		mutexIn.acquire();
		buffer[put % bufferSz] = m;
		//String name = Thread.currentThread().getName();
		//System.out.println("Producteur " + name.charAt(name.length() - 1) + " met : " + m.id());
		put++;
		totmsg++;
		mutexIn.release();
		notEmpty.release();
	}

	public Socket get() throws InterruptedException {
		notEmpty.acquire();
		mutexOut.acquire();
		Socket msg;
		msg = buffer[get % bufferSz];
		//String name = Thread.currentThread().getName();
		//System.out.println("Consommateur " + name.charAt(name.length() - 1) + " prend: " + msg.id());
		if (msg != null)
			get++;
		mutexOut.release();
		notFull.release();
		return msg;
	}

	public int nmsg() {
		return put - get;
	}

	public int totmsg() {
		return totmsg;
	}

	public void Done() {
		done = true;
	}

	public boolean isDone() {
		return done;
	}
}
