package it.polito.tdp.porto;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.db.PortoDAO;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIDMap;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<?> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	this.txtResult.clear();
    	Author a = this.boxPrimo.getValue();
    	if(a!=null) {
    	this.txtResult.appendText(model.toStringCoatori(a));
    	}
    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		model.creaGrafo();
		AuthorIDMap map = new AuthorIDMap();
		PortoDAO dao = new PortoDAO();
		this.model=model;
		this.boxPrimo.getItems().addAll(dao.getAllAutori(map));
		
	}
}
