package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import model.Administrateur;
import model.Salle;
import model.Serveur;

public class ClientViewController1 implements Initializable {

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private ClientController clientController = new ClientController();

    @FXML
    private Pane principalPane;

    @FXML
    private StackPane stackPane;
    // ---------------------------------------------------------------------------------------

    // Admin Variables Declaration
    @FXML
    private Region addAdminRegion;

    @FXML
    private JFXTextField adminLastName;

    @FXML
    private JFXTextField adminFirstName;

    @FXML
    private JFXTextField adminPseudo;

    @FXML
    private Region listAdminRegion;

    @FXML
    private TableView<Administrateur> adminListTable;

    @FXML
    private TableColumn<?, ?> adminIDColumn;

    @FXML
    private TableColumn<?, ?> adminPseudoColumn;

    @FXML
    private TableColumn<?, ?> adminLastNameColumn;

    @FXML
    private TableColumn<?, ?> adminFirstNameColumn;

    @FXML
    private Button btnAddAmin;

    @FXML
    private Button removeAdminBtn;

    @FXML
    private Button updateAdminBtn;

    // ---------------------------------------------------------------------------------------

    // Server Variables Declaration
    @FXML
    private Region addServerRegion;

    @FXML
    private JFXTextField serverNumber;

    @FXML
    private JFXTextField serverName;

    @FXML
    private JFXComboBox<String> salleName;

    @FXML
    private JFXComboBox<String> adminServerPseudo;

    @FXML
    private Region listServerRegion;

    @FXML
    private TableView<?> serverListTable;

    @FXML
    private TableColumn<?, ?> serverNumberColumn;

    @FXML
    private TableColumn<?, ?> serverNameColumn;

    @FXML
    private TableColumn<?, ?> salleNameColumn;

    @FXML
    private TableColumn<?, ?> adminServerPseudoColumn;

    @FXML
    private Button btnAddServer;

    @FXML
    private Button removeServerBtn;

    @FXML
    private Button updateServerBtn;

    ObservableList data = FXCollections.observableArrayList();

    // -------------------------------------------------------------------------------------

    // Salle Variables Declaration
    @FXML
    private Region salleRegion;

    @FXML
    private JFXTextField salleNumber;

    @FXML
    private JFXTextField salleNames;

    @FXML
    private TableView<Salle> sallesListTable;

    @FXML
    private TableColumn<?, ?> sallesNumberColumn;

    @FXML
    private TableColumn<?, ?> sallesNameColumn;

    @FXML
    private Button btnAddSalle;

    // ---------------------------------------------------------------------------------------

    @FXML
    void closeClient(ActionEvent event) {
	System.exit(0);
    }

    @FXML
    void showAddAdminPane(ActionEvent event) {
	stackPane.getChildren().setAll(addAdminRegion);
    }

    @FXML
    void showListAdminPane(ActionEvent event) {
	Administrateur.listAdmins.forEach(admin -> data.add(admin));
	stackPane.getChildren().setAll(listAdminRegion);
    }

    @FXML
    void addAdmin(ActionEvent event) {
	try {
	    String pseudo = adminPseudo.getText();
	    String nom = adminLastName.getText();
	    String prenom = adminFirstName.getText();

	    Administrateur admin = new Administrateur(pseudo, nom, prenom);
	    ObjectOutputStream os = new ObjectOutputStream(this.socket.getOutputStream());

	    os.writeObject("addAdmin");
	    os.flush();

	    os.writeObject(admin);
	    os.flush();

	    Administrateur.listAdmins.add(admin);

	    adminPseudo.setText("");
	    adminLastName.setText("");
	    adminFirstName.setText("");
	} catch (IOException e) {
	    System.out.println("Probléme d'ajout");
	}
    }

    @FXML
    void removeAdmin(ActionEvent event) {

    }

    @FXML
    void updateAdmin(ActionEvent event) {
    }

    @FXML
    void showAddServerPane(ActionEvent event) {
	salleName.setPromptText("Salle du Serveur");
	adminServerPseudo.setPromptText("Admin du Serveur");

	ObservableList<String> listSallesObserver = FXCollections.observableArrayList();
	ObservableList<String> listAdminsObserver = FXCollections.observableArrayList();

	Salle.listSalles.forEach(salle -> listSallesObserver.add(salle.getNom()));
	salleName.setItems(listSallesObserver);

	Administrateur.listAdmins.forEach(admin -> listAdminsObserver.add(admin.getPseudo()));
	adminServerPseudo.setItems(listAdminsObserver);

	stackPane.getChildren().setAll(addServerRegion);
    }

    @SuppressWarnings("unchecked")
    @FXML
    void showListServerPane(ActionEvent event) {
	// this.getListServeur();
	ObservableList serveurObservable = FXCollections.observableArrayList();

	Serveur.listeServeurs.forEach(s -> serveurObservable.add(s));

	serverNumberColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
	serverNameColumn.setCellValueFactory(new PropertyValueFactory<>("nomServeur"));
	salleNameColumn.setCellValueFactory(new PropertyValueFactory<>("nomSalle"));

	adminServerPseudoColumn.setCellValueFactory(new PropertyValueFactory<>("pseudoAdmin"));

	serverListTable.setItems(serveurObservable);

	stackPane.getChildren().setAll(listServerRegion);
    }

    @FXML
    void addServer(ActionEvent event) {
	String nomSalle = salleName.getValue();
	String pseudoAdmin = adminServerPseudo.getValue();
	String nomServeur = serverName.getText();
	int numeroServeur = Integer.parseInt(serverNumber.getText());

	Serveur serveur = new Serveur(numeroServeur, nomServeur, nomSalle, pseudoAdmin);

	try {
	    oos = new ObjectOutputStream(socket.getOutputStream());

	    oos.writeObject("addServeur");
	    oos.flush();

	    oos.writeObject(serveur);
	    oos.flush();

	    Serveur.listeServeurs.add(serveur);
	    serverName.setText("");
	    serverNumber.setText("");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @FXML
    void removeServer(ActionEvent event) {

    }

    @FXML
    void updateServer(ActionEvent event) {

    }

    @SuppressWarnings("unchecked")
    @FXML
    void showAddSallePane(ActionEvent event) {
	ObservableList dataSalle = FXCollections.observableArrayList();

	Salle.listSalles.forEach(salle -> dataSalle.add(salle));

	sallesNumberColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
	sallesNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
	sallesListTable.setItems(dataSalle);

	stackPane.getChildren().setAll(salleRegion);
    }

    @FXML
    void addSalle(ActionEvent event) {
	int numSall = Integer.parseInt(salleNumber.getText());
	String nomSall = salleNames.getText();

	Salle salle = new Salle(numSall, nomSall);

	sallesNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Numero"));
	sallesNameColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));

	try {
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    oos.writeObject("addSalle");
	    oos.flush();

	    oos.writeObject(salle);
	    oos.flush();
	    Salle.listSalles.add(salle);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // -------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	try {
	    socket = new Socket("localhost", 8888);

	    adminPseudoColumn.setCellValueFactory(new PropertyValueFactory<>("Pseudo"));
	    adminFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
	    adminLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));

	    adminListTable.setItems(data);

	    clientController.getListAdmin(this.socket);
	    clientController.getListSalle(this.socket);
	    clientController.getListServeur(this.socket);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void showAlert(String message) {
	Alert alert = new Alert(AlertType.WARNING);
	alert.setTitle("Enregistrement Administrateur");
	alert.setContentText(message);

	alert.showAndWait();
    }

    // public void getListAdmin() {
    // try {
    // oos = new ObjectOutputStream(socket.getOutputStream());
    // oos.writeObject("listAdmin");
    // oos.flush();
    //
    // ois = new ObjectInputStream(socket.getInputStream());
    //
    // ArrayList<Administrateur> listeAdministrateur = (ArrayList<Administrateur>)
    // ois.readObject();
    //
    // Administrateur.listAdmins.clear();
    // Administrateur.listAdmins = listeAdministrateur;
    //
    // listeAdministrateur.forEach(admin -> data.add(admin));
    // } catch (IOException e) {
    // e.printStackTrace();
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // public void getListSalle() {
    // try {
    // oos = new ObjectOutputStream(socket.getOutputStream());
    // oos.writeObject("listSalle");
    // oos.flush();
    //
    // ois = new ObjectInputStream(socket.getInputStream());
    //
    // ArrayList<Salle> listeSalles = (ArrayList<Salle>) ois.readObject();
    //
    // ObservableList dataSalle = FXCollections.observableArrayList();
    //
    // Salle.listSalles = listeSalles;
    //
    // listeSalles.forEach(salle -> dataSalle.add(salle));
    //
    // sallesListTable.setItems(dataSalle);
    // } catch (IOException | ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // public void getListServeur() {
    // try {
    // oos = new ObjectOutputStream(socket.getOutputStream());
    // oos.writeObject("listServeur");
    // oos.flush();
    //
    // ois = new ObjectInputStream(socket.getInputStream());
    //
    // Serveur.listeServeurs = (ArrayList<Serveur>) ois.readObject();
    // } catch (IOException | ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    // }
}
