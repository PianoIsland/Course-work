package com.example.demo5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
//!!!!!!!!!!!!!!!!!Рекомендуется запускать на версии Java Amazon Corretto 17.0.3, т.к на последних версиях Oracle OpenJDK у меня почему то
// поломана кодировка русских символов в консоли, не исправляется никакими способами
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 502, 767);
        stage.setTitle("Wordle!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}