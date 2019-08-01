package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.Administrateur;
import model.Salle;
import model.Serveur;

public class ClientController {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    @SuppressWarnings("unchecked")
    public void getListAdmin(Socket socket) {
	try {
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    oos.writeObject("listAdmin");
	    oos.flush();

	    ois = new ObjectInputStream(socket.getInputStream());

	    ArrayList<Administrateur> listeAdministrateur = (ArrayList<Administrateur>) ois.readObject();

	    Administrateur.listAdmins = listeAdministrateur;
	} catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @SuppressWarnings("unchecked")
    public void getListSalle(Socket socket) {
	try {
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    oos.writeObject("listSalle");
	    oos.flush();

	    ois = new ObjectInputStream(socket.getInputStream());

	    Salle.listSalles = (ArrayList<Salle>) ois.readObject();

	} catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @SuppressWarnings("unchecked")
    public void getListServeur(Socket socket) {
	try {
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    oos.writeObject("listServeur");
	    oos.flush();

	    ois = new ObjectInputStream(socket.getInputStream());

	    Serveur.listeServeurs = (ArrayList<Serveur>) ois.readObject();
	} catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }
}
