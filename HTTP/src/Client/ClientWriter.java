package Client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientWriter implements Runnable {

	PrintStream os;
	
	public ClientWriter(PrintStream os) {
		this.os = os;
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket("localhost", 8080);
			InputStreamReader isr = new InputStreamReader(
					socket.getInputStream());
			while (true) {
				try {
					int r = isr.read();
					if(r>=0){
						os.print((char)r);
						os.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
