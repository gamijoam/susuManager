package devforge.susuManager.controladores;

import devforge.susuManager.DTOs.SusuDTOagg;
import devforge.susuManager.DTOs.UsuarioDTOagg;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import devforge.susuManager.servicios.IUsuarioServicios;
import devforge.susuManager.servicios.ISususServicios;
import devforge.susuManager.servicios.SususServicios;
import devforge.susuManager.servicios.UsuariosServicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AsociarUsuarioASusuControlador {

    @Autowired
    private UsuariosServicios usuarioService;

    @Autowired
    private SususServicios susuService;

    @FXML
    private ComboBox<UsuarioDTOagg> usuarioComboBox;

    @FXML
    private ComboBox<SusuDTOagg> susuComboBox;

    ObservableList<UsuarioDTOagg> usuarios = FXCollections.observableArrayList();
    ObservableList<SusuDTOagg> susuDTOaggs = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        listarUsuarios();
        listarSusus();

    }

    @FXML
    private void asociarUsuario() {
        // Obtener el usuario y el susu seleccionados
        UsuarioDTOagg usuario = usuarioComboBox.getValue();
        SusuDTOagg susu = susuComboBox.getValue();

        if (usuario == null || susu == null) {
            showAlert("Error", "Debes seleccionar un usuario y un susu.");
            return;
        }

        try {
            // Llamar al servicio para asociar el usuario al susu
            usuarioService.asociarUsuarioASusu(usuario.getIdUsuario(), susu.getIdSusu());

            // Mostrar mensaje de éxito
            showAlert("Éxito", "El usuario ha sido asociado al susu correctamente.");
        } catch (Exception e) {
            // Manejar errores
            showAlert("Error", "Ocurrió un error al asociar el usuario al susu: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void listarUsuarios(){
        usuarios.clear();
        usuarios.addAll(usuarioService.listarUsuario());
        usuarioComboBox.setItems(usuarios);
    }
    private void listarSusus(){
        susuDTOaggs.clear();
        susuDTOaggs.addAll(susuService.listaSususs());
        susuComboBox.setItems(susuDTOaggs);
    }

}