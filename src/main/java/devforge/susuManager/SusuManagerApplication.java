package devforge.susuManager;

import devforge.susuManager.presentacion.indexFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SusuManagerApplication {

	public static void main(String[] args) {
		Application.launch(indexFx.class,args);
	}

}
