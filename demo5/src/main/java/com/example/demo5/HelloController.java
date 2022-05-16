package com.example.demo5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    @FXML
    private List<TextField> textfield1;

    @FXML
    private List<TextField> textfield2;

    @FXML
    private List<TextField> textfield3;

    @FXML
    private List<TextField> textfield4;

    @FXML
    private List<TextField> textfield5;

    @FXML
    private Button getData;

    @FXML
    private Text info;

    @FXML
    private TextField t11;

    @FXML
    private TextField t12;

    @FXML
    private TextField t13;

    @FXML
    private TextField t14;

    @FXML
    private TextField t15;

    @FXML
    private TextField t21;

    @FXML
    private TextField t22;

    @FXML
    private TextField t23;

    @FXML
    private TextField t24;

    @FXML
    private TextField t25;

    @FXML
    private TextField t31;

    @FXML
    private TextField t32;

    @FXML
    private TextField t33;

    @FXML
    private TextField t34;

    @FXML
    private TextField t35;

    @FXML
    private TextField t41;

    @FXML
    private TextField t42;

    @FXML
    private TextField t43;

    @FXML
    private TextField t44;

    @FXML
    private TextField t45;

    @FXML
    private TextField t51;

    @FXML
    private TextField t52;

    @FXML
    private TextField t53;

    @FXML
    private TextField t54;

    @FXML
    private TextField t55;

    @FXML
    private TextField word;

    @FXML
    void initialize() throws Exception {
        FileReader reader = new FileReader("words.txt");
        Scanner scan = new Scanner(reader);
        String wordle = "";
        int random_number = 0 + (int) (Math.random() * 3483);
        for (int  i = 0; i < random_number; i++) {
            wordle = scan.nextLine();
        }
        reader.close();
        List<List<TextField>> a = new ArrayList<>();
        a.add(textfield1);
        a.add(textfield2);
        a.add(textfield3);
        a.add(textfield4);
        a.add(textfield5);
        AtomicInteger i = new AtomicInteger();
        String finalWordle = wordle;
        System.out.println(finalWordle);
        getData.setOnAction(event -> {
            String guess = word.getText();
            int count = 0;
            for (TextField text : a.get(i.get())) {
                if (guess.length() != 5) {
                    info.setText("Должно быть 5 букв!");
                    i.getAndDecrement();
                    break;
                }
                text.setText(String.valueOf(guess.charAt(count)));
                if (guess.charAt(count) == finalWordle.charAt(count)) {
                    text.setStyle("-fx-background-color:#ADFF2F; -fx-border-color:#ADFF2F;");

                }
                if (guess.charAt(count) != finalWordle.charAt(count)) {
                    text.setStyle("-fx-background-color:#FFD700; -fx-border-color:#FFD700;");
                }
                if (finalWordle.indexOf(guess.charAt(count)) == -1) {
                    text.setStyle("-fx-background-color:#D3D3D3; -fx-border-color:#D3D3D3;");
                }
                count++;
                info.setText("Введите слово из 5 букв:");
            }
            i.getAndIncrement();
            if (guess.equals(finalWordle) == true){
                info.setText("Вы угадали!");
                getData.setText("Выйти");
                getData.setOnAction(e -> {
                    System.out.println("Победа!");
                    System.exit(0);
                });
            }
            if (i.get() == 5){
                info.setText("Ответ: " + finalWordle);
                getData.setText("Выйти");
                word.setPromptText("Вы проиграли!");
                getData.setOnAction(e -> {
                    System.out.println("Поражение!");
                    System.exit(0);
                });
            }
            word.clear();
        });
    }
}