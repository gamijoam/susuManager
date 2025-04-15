package devforge.susuManager.presentacion;

import devforge.susuManager.SusuManagerApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class indexFx extends Application {
    private ConfigurableApplicationContext context;
//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void init(){
        this.context =
                new SpringApplicationBuilder(SusuManagerApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SusuManagerApplication.class.getResource("/fxml/index.fxml"));
        loader.setControllerFactory(context::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void stop(){
        context.close();
        Platform.exit();
    }
}
