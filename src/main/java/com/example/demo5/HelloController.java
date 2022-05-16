package com.example.demo5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
    void initialize() {
        List<List<TextField>> a = new ArrayList<>();
        a.add(textfield1);
        a.add(textfield2);
        a.add(textfield3);
        a.add(textfield4);
        a.add(textfield5);
        AtomicReference<String> prefinalWordle = new AtomicReference<>("");
        AtomicInteger i = new AtomicInteger();
        AtomicInteger t = new AtomicInteger();
        AtomicInteger x = new AtomicInteger();
        AtomicInteger j = new AtomicInteger();
        AtomicInteger jk = new AtomicInteger();
        getData.setOnAction(event -> {
            word.setPromptText("Введите слово");
            if (x.get() == 1){
                for (int k = 0; k < 5; k++) {
                    for (TextField text : a.get(j.get())) {
                        text.clear();
                        text.setStyle("");
                    }
                    j.getAndIncrement();
                }
                j.set(0);
                x.set(0);
            }
            getData.setText("Проверить слово");

            String guess = word.getText();

            try {
                File file = new File("words.txt");
                //создаем объект FileReader для объекта File
                FileReader fr = new FileReader(file);
                //создаем BufferedReader с существующего FileReader для построчного считывания
                BufferedReader reader = new BufferedReader(fr);
                String line;

                for (int p = 0; p < 3483; p++) {
                    // считываем строки в цикле
                    line = reader.readLine();
                    if (guess.equals(line)){
                        jk.set(1);
                        break;
                    }
                    jk.set(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            int count = 0;
            if (t.get() == 0) {
                FileReader reader = null;
                try {
                    reader = new FileReader("words.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Scanner scan = new Scanner(reader);
                AtomicReference<String> wordle = new AtomicReference<>("");
                int random_number = 0 + (int) (Math.random() * 3483);
                for (int p = 0; p < random_number; p++){
                    wordle.set(scan.nextLine());
                }
                prefinalWordle.set(wordle.get());
                t.set(1);
            }
            final String finalWordle = prefinalWordle.get();
            for (TextField text : a.get(i.get())) {
                if (guess.length() != 5) {
                    info.setText("Должно быть 5 букв!");
                    i.getAndDecrement();
                    break;
                }
                if (jk.get()==0) {
                    info.setText("Такого слова нет!");
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
            if (guess.equals(finalWordle)) {
                info.setText("Вы угадали!");
                word.setPromptText("Правильное слово: " + finalWordle);
                getData.setText("Начать заново");
                i.set(0);
                t.set(0);
                x.set(1);
            }
            if (i.get() == 5) {
                if (!guess.equals(finalWordle)){
                    info.setText("Вы проиграли!");
                    word.setPromptText("Правильное слово: " + finalWordle);
                    getData.setText("Начать заново");
                    i.set(0);
                    t.set(0);
                    x.set(1);
                }
            }
            word.clear();
        });
    }
}