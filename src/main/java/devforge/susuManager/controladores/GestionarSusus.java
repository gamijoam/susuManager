package devforge.susuManager.controladores;

import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import devforge.susuManager.servicios.SususServicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.TabableView;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Component
public class GestionarSusus implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(GestionarSusus.class);
    @Autowired SususServicios sususServicios;
    @FXML private TableView<Susus> susuTable;
    @FXML private TableColumn<Susus,Integer> idColumn;
    @FXML private TableColumn<Susus,String> nameColumn;
    @FXML private TableColumn<Susus,Integer> amountColumn;
    @FXML private TableColumn<Susus, LocalDate> startDateColumn;
    @FXML private TableColumn<Susus,String> frequencyColumn;

    @FXML private TextField nameField;
    @FXML private TextField amountField;
    @FXML private DatePicker startDatePicker;
    @FXML private ComboBox frequencyComboBox;

    private ObservableList<Susus> sususList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarCeldad();
        listarCeldas();
        Click();
    }

    public void addSusu(ActionEvent actionEvent) {
        Susus susus = new Susus();
        susus.setNombreSusu(nameField.getText());
        susus.setMontoFijo(Double.parseDouble(amountField.getText()));
        susus.setFechaInicio(startDatePicker.getValue());
        susus.setFrecuencia((String) frequencyComboBox.getValue());
        sususServicios.agregarSusu(susus);
        limpiarCampos();
        listarCeldas();
        /*
        * Manejar errores de insercion de datos*/
    }

    public void editSusu(ActionEvent actionEvent) {
        Susus susus = susuTable.getSelectionModel().getSelectedItem();
        susus.setNombreSusu(nameField.getText());
        susus.setMontoFijo(Double.parseDouble(amountField.getText()));
        susus.setFechaInicio(startDatePicker.getValue());
        susus.setFrecuencia((String) frequencyComboBox.getValue());
        sususServicios.agregarSusu(susus);
        limpiarCampos();
        listarCeldas();
        logger.info("---- Usuario Modificado -----");

    }

    public void deleteSusu(ActionEvent actionEvent) {
        Susus susus = susuTable.getSelectionModel().getSelectedItem();
        sususServicios.eliminarSusu(susus);
        listarCeldas();
        limpiarCampos();
    }

    private void limpiarCampos(){
        nameField.clear();
        amountField.clear();
        startDatePicker.setValue(null);
        frequencyComboBox.setValue(null);
    }
    private void configurarCeldad(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idSusu"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombreSusu"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("montoFijo"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frecuencia"));
    }
    private void listarCeldas(){
        sususList.clear();
        sususList.addAll(sususServicios.listarSusus());
        susuTable.setItems(sususList);
    }
    private void Click(){
        susuTable.setRowFactory(tv -> {
            TableRow<Susus> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) { // Verifica si es un doble clic y la fila no está vacía
                    Susus susuSelecionado  = susuTable.getSelectionModel().getSelectedItem();
                    nameField.setText(susuSelecionado.getNombreSusu());
                    amountField.setText(String.valueOf(susuSelecionado.getMontoFijo()));
                    startDatePicker.setValue(susuSelecionado.getFechaInicio());
                    frequencyComboBox.setValue(susuSelecionado.getFrecuencia());
                }
            });
            return row;
        });
    }


    /*
    * GESTIONAR POR SI EL USUARIO INGRESA DATOS INVALIDOS
    * GESTIONAR ALERTAS PARA QUE EL USUARIO SEPA QUE AÑADIO , ELIMINO O MODIFICO*/

}
