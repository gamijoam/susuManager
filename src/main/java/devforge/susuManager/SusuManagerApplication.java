package devforge.susuManager;

import devforge.susuManager.presentacion.indexFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SusuManagerApplication {

	public static void main(String[] args) {
		Application.launch(indexFx.class,args);
	}

}
