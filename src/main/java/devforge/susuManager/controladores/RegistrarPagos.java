package devforge.susuManager.controladores;

import devforge.susuManager.DTOs.SusuDTO;
import devforge.susuManager.DTOs.UsuarioDTO;
import devforge.susuManager.model.Pagos;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import devforge.susuManager.servicios.PagosServicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class RegistrarPagos implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(RegistrarPagos.class);
    @Autowired private PagosServicios pagosServicios;

    @FXML private TableView<Pagos> paymentTable;
    @FXML private TableColumn<Pagos,Integer> idColumn;
    @FXML private TableColumn<Pagos,String> userColumn;
    @FXML private TableColumn<Pagos,String> susuColumn;
    @FXML private TableColumn<Pagos,Double> amountColumn;
    @FXML private TableColumn<Pagos, LocalDate> paymentDateColumn;
    @FXML private ComboBox userComboBox;
    @FXML private ComboBox susuComboBox;
    @FXML private TextField amountField;
    @FXML private DatePicker paymentDatePicker;

    private ObservableList<UsuarioDTO> listaUsuarios = FXCollections.observableArrayList();
    private ObservableList<SusuDTO> listaSusu = FXCollections.observableArrayList();
    private ObservableList<Pagos> listaPagos = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarCeldas();
        listarUsuariosComboBox();
        listarSusu();
        listarPagos();
        click();

    }
    public void addPayment(ActionEvent actionEvent) {
        Pagos pagos = new Pagos();
        pagos.setUsuarioId(probar().getIdUsuario());
        pagos.setSusuId(probar1().getIdSusu());
        pagos.setUsuario(probar());
        pagos.setSusuPagos(probar1());
        pagos.setMonto(Integer.parseInt(amountField.getText()));
        pagos.setFechaPago(paymentDatePicker.getValue());
        pagosServicios.agregarPago(pagos);
        listarPagos();
        limpiarCampos();
    }
    public void editPayment(ActionEvent actionEvent) {
        Pagos pagos = paymentTable.getSelectionModel().getSelectedItem();
        pagos.setMonto(Integer.parseInt(amountField.getText()));
        pagos.setFechaPago(paymentDatePicker.getValue());
        pagosServicios.agregarPago(pagos);
        limpiarCampos();
        listarPagos();
    }
    public void deletePayment(ActionEvent actionEvent) {
        Pagos pagos = paymentTable.getSelectionModel().getSelectedItem();
        pagosServicios.eliminarPago(pagos);
        listarPagos();
        limpiarCampos();
    }
    public void listarUsuariosComboBox(){
        listaUsuarios.clear();
        listaUsuarios.addAll(pagosServicios.listarUsuarios());
        userComboBox.setItems(listaUsuarios);
    }
    public void listarSusu(){
        listaSusu.clear();
        listaSusu.addAll(pagosServicios.listarSusu());
        susuComboBox.setItems(listaSusu);
    }
    private void configurarCeldas() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idPago"));

        // Acceder al nombre del usuario desde la relación
        userColumn.setCellValueFactory(cellData -> {
            Pagos pago = cellData.getValue();
            if (pago.getUsuario() != null) {
                return new javafx.beans.value.ObservableValueBase<>() {
                    @Override
                    public String getValue() {
                        return pago.getUsuario().getNombreUsuario();
                    }
                };
            }
            return null;
        });

        // Acceder al nombre del susu desde la relación
        susuColumn.setCellValueFactory(cellData -> {
            Pagos pago = cellData.getValue();
            if (pago.getSusuPagos() != null) {
                return new javafx.beans.value.ObservableValueBase<>() {
                    @Override
                    public String getValue() {
                        return pago.getSusuPagos().getNombreSusu();
                    }
                };
            }
            return null;
        });

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("monto"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
    }
    private void listarPagos(){
        listaPagos.clear();
        listaPagos.addAll(pagosServicios.listarPagos());
        paymentTable.setItems(listaPagos);
    }
    private Usuario probar(){
        UsuarioDTO usuarioDTO = (UsuarioDTO) userComboBox.getValue();
        Usuario usuario = usuarioDTO.getUsuario();
        logger.info("----- El usuario es ------ " + usuario.getNombreUsuario() + " y su ID: " + usuario.getIdUsuario());
        return usuario;
    }
    private Susus probar1(){
        SusuDTO susuDTO = (SusuDTO) susuComboBox.getValue();
        Susus susus = susuDTO.getSusus();
        logger.info("----- El usuario es ------ " + susus.getNombreSusu() + " y su ID: " + susus.getIdSusu());
        return susus;
    }
    private void click(){
        paymentTable.setRowFactory(tv -> {
            TableRow<Pagos> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) { // Verifica si es un doble clic y la fila no está vacía
                    Pagos pagoSelecionado  = paymentTable.getSelectionModel().getSelectedItem();
                    userComboBox.setValue(pagoSelecionado.getUsuario().getNombreUsuario());
                    susuComboBox.setValue(pagoSelecionado.getSusuPagos().getNombreSusu());
                    amountField.setText(String.valueOf(pagoSelecionado.getMonto()));
                    paymentDatePicker.setValue(pagoSelecionado.getFechaPago());
                }
            });
            return row;
        });
    }
    private void limpiarCampos(){
        userComboBox.setValue(null);
        susuComboBox.setValue(null);
        amountField.clear();
        paymentDatePicker.setValue(null);
    }

}
