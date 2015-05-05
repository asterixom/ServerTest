package Server;

import java.io.IOException;
import java.net.ServerSocket;

import Client.ClientWriter;

public class Acceptor implements Runnable {

	public static void main(String[] args) {
		new Thread(new Acceptor(8080)).start();
		new ClientWriter(System.out);
		new ClientWriter(System.err);
	}
	
	private ServerSocket ss;
	private Counter counter;

	public Acceptor(int port){
		counter = new Counter();
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try {
				new Thread(new ClientHandler(ss.accept(),counter)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
