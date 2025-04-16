package devforge.susuManager.controladores;

import devforge.susuManager.model.Usuario;
import devforge.susuManager.servicios.UsuariosServicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GestionarUsuarios  implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(GestionarUsuarios.class);
    @Autowired private UsuariosServicios usuariosServicios;
    @FXML private TableView<Usuario> userTable;
    @FXML private TableColumn<Usuario,Integer> idColumn;
    @FXML private TableColumn<Usuario,String> nameColumn;
    @FXML private TableColumn<Usuario,String> phoneColumn;
    @FXML private TableColumn<Usuario,String> emailColumn;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;


    private ObservableList<Usuario> userList = FXCollections.observableArrayList();
    public void addUser(ActionEvent actionEvent) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nameField.getText());
        usuario.setTelefonoUsuario(phoneField.getText());
        usuario.setEmailUsuario(emailField.getText());
        usuariosServicios.agregarUsuario(usuario);
        limpiarCampos();
        listarCeldas();
        logger.info("----- Usuario registrado exitosamente -----");
        /*
        * Manejar por si el usuario ingresa datos invalidos
        * colocar alertas por para que el usuario sepa que se realizo la accion
        * */
    }
    public void editUser(ActionEvent actionEvent) {
        Usuario usuario = userTable.getSelectionModel().getSelectedItem();
        usuario.setNombreUsuario(nameField.getText());
        usuario.setTelefonoUsuario(phoneField.getText());
        usuario.setEmailUsuario(emailField.getText());
        usuariosServicios.agregarUsuario(usuario);
        listarCeldas();
        limpiarCampos();
        logger.info("----- Usuario actualizado -----");
        /*
         * Manejar por si el usuario ingresa datos invalidos
         * colocar alertas por para que el usuario sepa que se realizo la accion
         * */
    }
    public void deleteUser(ActionEvent actionEvent) {
        Usuario usuario = userTable.getSelectionModel().getSelectedItem();
        usuariosServicios.eliminarUsuario(usuario);
        listarCeldas();
        limpiarCampos();
        /*
         *
         * colocar alertas por para que el usuario sepa que se realizo la accion
         * */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarCeldad();
        listarCeldas();
        Click();
        /*
        * SEGUIR CON EL OBSERVABLE DE LOS USUARIOS DE LA TABLE VIEW
        * */
    }
    private void limpiarCampos(){
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }
    private void configurarCeldad(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefonoUsuario"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailUsuario"));
    }
    private void listarCeldas(){
        userList.clear();
        userList.addAll(usuariosServicios.listarUsuarios());
        userTable.setItems(userList);
    }
    private void Click(){
        userTable.setRowFactory(tv -> {
            TableRow<Usuario> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) { // Verifica si es un doble clic y la fila no está vacía
                    Usuario usuarioSeleccionado  = userTable.getSelectionModel().getSelectedItem();
                    nameField.setText(usuarioSeleccionado.getNombreUsuario());
                    phoneField.setText(usuarioSeleccionado.getTelefonoUsuario());
                    emailField.setText(usuarioSeleccionado.getEmailUsuario());
                }
            });
            return row;
        });
    }
}
