package devforge.susuManager.controladores;

import devforge.susuManager.SusuManagerApplication;
import devforge.susuManager.model.Pagos;
import devforge.susuManager.servicios.PagosServicios;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class IndexControlador {
   @Autowired
   private ConfigurableApplicationContext context;

    public void openUserManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(SusuManagerApplication.class.getResource("/fxml/gestionarUsuarios.fxml"));
        Stage stage = new Stage();
        loader.setControllerFactory(context::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    public void openSusuManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(SusuManagerApplication.class.getResource("/fxml/gestionarSusus.fxml"));
        Stage stage = new Stage();
        loader.setControllerFactory(context::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    public void openPaymentRegistration(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(SusuManagerApplication.class.getResource("/fxml/registrarPagos.fxml"));
        Stage stage = new Stage();
        loader.setControllerFactory(context::getBean);
        Scene escena = new Scene(loader.load());
        Pagos pagos = new Pagos();
        // Inicializar la colección de tareas
        Hibernate.initialize(pagos);

        stage.setScene(escena);
        stage.show();
    }

    public void openReports(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(SusuManagerApplication.class.getResource("/fxml/verReportes.fxml"));
        Stage stage = new Stage();
        loader.setControllerFactory(context::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

}
