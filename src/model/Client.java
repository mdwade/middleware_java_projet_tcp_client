package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {

    DataOutputStream out;
    DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public void addAdmin(Administrateur admin, OutputStream outputStream) {
	try {
	    oos = new ObjectOutputStream(outputStream);
	    oos.writeObject("addAdmin");
	    oos.flush();

	    oos.writeObject(admin);
	    oos.flush();
	} catch (IOException e) {
	    System.out.println("Probléme d'ajout" + e.getMessage());
	    e.printStackTrace();
	}

    }

    public ArrayList<Administrateur> ListAdmin() {
	try {
	    oos.writeObject("listAdmin");
	    oos.flush();
	} catch (IOException e) {

	}
	return null;

    }

    public void removeAdmin(String pseudo) {

    }

    public void updateAdmin(String pseudo) {

    }

    // public void actionPerformed(ActionEvent e)
    // {
    // if (e.getSource()==rec)
    // {
    // try
    // {
    // oos.writeObject("rechercher");
    // oos.flush();
    // String num = chmatr.getText();
    // int matr =Integer.parseInt(num);
    // out.writeInt(matr);
    // out.flush();
    // Employe p =(Employe)ois.readObject();
    // if (p==null)
    // JOptionPane.showMessageDialog(null, "Employe inexistant!!!");
    // else
    // {
    // chnom.setText(p.getNom());
    // chprenom.setText(p.getPrenom());
    // chtel.setText(p.getPhone());
    // }
    //
    // }
    //
    // catch(Exception ex)
    // {
    // System.out.println(ex.getMessage());
    // }
    //
    // }
    // else
    // if (e.getSource()==mod)
    // {
    // try
    // {
    // oos.writeObject("modifier");
    // oos.flush();
    // String num = chmatr.getText();
    // int matr =Integer.parseInt(num);
    // String nom = chnom.getText();
    // String prenom = chprenom.getText();
    // String tel = chtel.getText();
    // Employe p = new Employe(matr,nom,prenom,tel);
    // oos.writeObject(p);
    // oos.flush();
    // chmatr.setText("");
    // chnom.setText("");
    // chprenom.setText("");
    // chtel.setText("");
    //
    //
    // }
    // catch(Exception ex)
    // {
    // System.out.println(ex.getMessage());
    // }
    // }
    // else
    // if (e.getSource()==sup)
    // {
    // try
    // {
    // oos.writeObject("supprimer");
    // oos.flush();
    // String num = chmatr.getText();
    // int matr =Integer.parseInt(num);
    // out.writeInt(matr);
    // out.flush();
    // chmatr.setText("");
    // chnom.setText("");
    // chprenom.setText("");
    // chtel.setText("");
    //
    // }
    // catch(Exception ex)
    // {
    // System.out.println(ex.getMessage());
    // }
    // }
    // else
    // if (e.getSource()==aj)
    // {
    //
    // Employe p;
    // try
    // {
    //
    //
    // oos.writeObject("ajout");
    // oos.flush();
    // String smatr=chmatr.getText();
    // int matr = Integer.parseInt(smatr);
    // String nom=chnom.getText();
    // String prenom=chprenom.getText();
    // String phone=chtel.getText();
    // p = new Employe(matr,nom,prenom,phone);
    // oos.writeObject(p);
    // oos.flush();
    // chmatr.setText("");
    // chnom.setText("");
    // chprenom.setText("");
    // chtel.setText("");
    // }
    // catch(Exception ex)
    // {
    // System.out.println(ex.getMessage());
    // }
    // }
    //
    // else
    //
    // if (e.getSource()==aff)
    // {
    // try
    // {
    // oos.writeObject("lister");
    // oos.flush();
    // ArrayList<Employe> liste=(ArrayList<Employe>)ois.readObject();
    // new ListeEmployes(liste);
    //
    // }
    // catch(Exception ex)
    // {
    // System.out.println(ex.getMessage());
    // }
    // }
    //
    // else
    // if (e.getSource()==qt)
    // {
    // try
    // {
    // oos.writeObject("fin");
    // oos.flush();
    // socket.close();
    // dispose();
    // System.exit(0);
    // }
    // catch(Exception ex)
    // {
    // System.out.println(ex.getMessage());
    // }
    // }
    // }
    //
    // public static void main(String args[])
    // {
    // new Client();
    //
    // }
}