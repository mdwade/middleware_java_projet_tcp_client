package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Service extends Thread {
    private DataOutputStream out;
    private DataInputStream in;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;

    public Service() {
	System.out.println("Client ");
	try {
	    socket = new Socket("localhost", 8888);

	    this.out = new DataOutputStream(socket.getOutputStream());
	    this.in = new DataInputStream(socket.getInputStream());
	    this.oos = new ObjectOutputStream(socket.getOutputStream());
	    this.ois = new ObjectInputStream(socket.getInputStream());

	    // oos.writeObject("Je m'appelle Diadji");
	    // oos.flush();

	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}
    }

    public void send(Object obj, String action) throws IOException {

	switch (action) {
	case "cs":
	    oos.writeObject("cs");
	    oos.flush();
	    break;
	case "ca":
	    oos.writeObject("ca");
	    oos.flush();
	    break;
	default:
	    break;
	}
    }

    public void sayHello() {
	try {
	    oos.writeObject("hello");
	    oos.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}