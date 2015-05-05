package Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Socket socket;
	private Counter counter;
	private OutputStreamWriter osw;
	
	public ClientHandler(Socket accept, Counter counter) {
		socket = accept;
		this.counter = counter;
		try {
			osw = new OutputStreamWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if(osw==null) return;
		while(!socket.isClosed()){
				long number = counter.count();
				//System.out.println(Thread.currentThread().getName()+" : "+number);
				try {
					osw.write(number+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

}
