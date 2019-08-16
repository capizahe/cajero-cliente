package GUI;

import Client.Cliente;
import Proceso.Proceso;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

	private static final double WIDTH = 500;
	private static final double HEIGHT = 500;
	private String account_number_user= null;
	private Integer user_option;
	private Long value;
	private Stage stage;
	public  Proceso proceso;
	public Cliente cliente;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		firstScene();
		this.stage.setFullScreen(false);
		this.stage.setResizable(false);
		this.stage.setTitle("Cajero");
		this.stage.show();
	}

	public  void firstScene() {

		Label account_number = new Label("INGRESE SU NUMERO DE CUENTA");
		account_number.setStyle("-fx-font-weight:bold");
		TextField account_field = new TextField();
		account_field.setAlignment(Pos.BASELINE_CENTER);
		VBox account_info = new VBox(account_number,account_field);
		account_info.setAlignment(Pos.BASELINE_CENTER);
		account_info.setLayoutX(WIDTH/3);
		account_info.setLayoutY(HEIGHT/3);
		account_info.setPadding(new Insets(10, 10, 10, 10));

		ToggleButton accept = new ToggleButton("Acceder");
		accept.setStyle("-fx-background-color:green;-fx-font-weight:bold");
		ToggleButton erase = new ToggleButton(" Borrar ");
		erase.setStyle("-fx-background-color:red;-fx-font-weight:bold");

		accept.setLayoutY(HEIGHT/2);
		erase.setLayoutY(HEIGHT/2);
		accept.setLayoutX(WIDTH/2 - 50);
		erase.setLayoutX(WIDTH/2 + 40);

		erase.setOnAction(e -> {
			account_field.setText("");
			account_number_user = "";
		});

		accept.setOnAction(e -> {
			account_number_user= account_field.getText();
			System.out.println("El numero de cuenta es: "+ account_number_user);
			check(account_number_user);
		});

		this.stage.setScene(new Scene(new Pane(account_info, accept, erase),WIDTH,HEIGHT));
	}

	private void check(String value) {
		secondView();
	}

	private void secondView() {

		ToggleButton insertar = new ToggleButton (" INSERTAR ");
		ToggleButton modificar = new ToggleButton(" MODIFICAR");
		ToggleButton consultar = new ToggleButton(" CONSULTAR");

		insertar.setLayoutX(200);
		modificar.setLayoutX(200);
		consultar.setLayoutX(200);

		buttonStye(insertar);
		buttonStye(consultar);
		buttonStye(modificar);

		insertar.setLayoutY(150);
		modificar.setLayoutY(250);
		consultar.setLayoutY(350);

		insertar.setOnAction(e -> {
			user_option = 1;
			System.out.println(account_number_user+","+user_option);
			thirdView();
		});
		modificar.setOnAction(e -> {
			user_option = 2;
			System.out.println(account_number_user+","+user_option);
			thirdView();
		});
		consultar.setOnAction(e -> {
			user_option = 3;
			thirdView();
			System.out.println(account_number_user+","+user_option);
		});


		this.stage.setScene(new Scene(new Pane(insertar,modificar,consultar),WIDTH,HEIGHT));
	}

	private void thirdView() {

		Label cantidad_accion_label = new Label();
		final TextField accion_field = new TextField();
		VBox accion_box = new VBox(cantidad_accion_label,accion_field);

		cantidad_accion_label.setStyle("-fx-font-weight:bold");
		accion_field.setAlignment(Pos.BASELINE_CENTER);
		accion_box.setAlignment(Pos.BASELINE_CENTER);
		accion_box.setLayoutX(WIDTH/3);
		accion_box.setLayoutY(HEIGHT/3);
		accion_box.setPadding(new Insets(10, 10, 20, 10));

		ToggleButton accept = new ToggleButton("Acceder");
		accept.setStyle("-fx-background-color:green;-fx-font-weight:bold");
		ToggleButton erase = new ToggleButton(" Borrar ");
		erase.setStyle("-fx-background-color:red;-fx-font-weight:bold");

		accept.setLayoutY(HEIGHT/2);
		erase.setLayoutY(HEIGHT/2);
		accept.setLayoutX(WIDTH/2 - 70);
		erase.setLayoutX(WIDTH/2 + 20);

		if(user_option == 1) {

			cantidad_accion_label.setText("CANTIDAD A INSERTAR");

			erase.setOnAction(e -> {
				accion_field.setText("");
				value = (long) 0;		
				cliente.exit = true;

			});

			accept.setOnAction(e -> {
				value = (!accion_field.getText().isEmpty())?Long.parseLong(accion_field.getText()):0;
				Proceso proceso = new Proceso(user_option,account_number_user,value);
				procesarOperacion(proceso);
				System.out.println("proceso enviado -> "+ proceso.toString());
			});
			this.stage.setScene(new Scene(new Pane(accept,erase,accion_box),WIDTH,HEIGHT));
		}
		if(user_option == 2) {

			cantidad_accion_label.setText("CANTIDAD A RETIRAR");
			erase.setOnAction(e -> {
				accion_field.setText("");
				value = (long) 0;
				cliente.exit = true;
			});

			accept.setOnAction(e -> {

				value = (!accion_field.getText().isEmpty())?Long.parseLong(accion_field.getText()):0;
				Proceso proceso = new Proceso(user_option,account_number_user,value);
				procesarOperacion(proceso);
				System.out.println("proceso enviado -> "+ proceso.toString());
			});
			this.stage.setScene(new Scene(new Pane(accept,erase,accion_box),WIDTH,HEIGHT));
		}

		if(user_option == 3) {

			proceso = new Proceso(user_option,account_number_user,(long)0);
			procesarOperacion(proceso);

			cantidad_accion_label.setText("INFORMACION");
			Label info = new Label("No. cuenta: "+account_number_user);
			cantidad_accion_label.setStyle("-fx-font-weight:bold");

			Label saldo = new Label(cliente.saldo);
			saldo.setStyle("-fx-font-weight:bold");

			accion_box = new VBox(cantidad_accion_label,info,saldo);
			accion_box.setAlignment(Pos.BASELINE_CENTER);
			accion_box.setLayoutX(WIDTH/3);
			accion_box.setLayoutY(HEIGHT/3);
			accion_box.setPadding(new Insets(10, 10, 20, 10));

			ToggleButton back = new ToggleButton("Volver");
			back.setStyle("-fx-background-color:white;-fx-font-weight:bold");
			back.setLayoutX(WIDTH-60);
			back.setLayoutY(HEIGHT/8);

			back.setOnAction(e -> {
				user_option = 0;
				secondView();
			});

			this.stage.setScene(new Scene(new Pane(accion_box,back),WIDTH,HEIGHT));

		}
	}

	private void procesarOperacion(Proceso proceso) {
		cliente = new Cliente(proceso);
		this.stage.setOnCloseRequest(e -> cliente.exit = true );

		cliente.run();
		if(cliente.exit) {
			firstScene();
		}
	}

	private void restart() {
		user_option = 0;
		account_number_user="";
		value = (long) 0;
	}

	private void buttonStye(ToggleButton button) {
		button.setOnMouseEntered(e -> button.setCursor(javafx.scene.Cursor.HAND));
		button.setOnMouseExited(e -> button.setCursor(javafx.scene.Cursor.DEFAULT));
		button.setStyle("-fx-background-color:green;-fx-font-weight:bold");
	}
}
