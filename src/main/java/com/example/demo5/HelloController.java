package com.example.demo5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
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
    private List<TextField> textfield6;

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
        List<List<TextField>> CellValues = new ArrayList<>();
        CellValues.add(textfield1);
        CellValues.add(textfield2);
        CellValues.add(textfield3);
        CellValues.add(textfield4);
        CellValues.add(textfield5);
        CellValues.add(textfield6);

        AtomicReference<String> prefinalWordle = new AtomicReference<>("");

        AtomicInteger DidYouGuessTheWord = new AtomicInteger();
        AtomicInteger j = new AtomicInteger();
        AtomicBoolean ClearAllCells = new AtomicBoolean(false);
        AtomicBoolean DoesThisWordExist = new AtomicBoolean(false);
        AtomicBoolean GenerateNewWord = new AtomicBoolean(true);
        getData.setOnAction(event -> {
            word.setPromptText("?????????????? ??????????");
            if (ClearAllCells.get()){
                for (int k = 0; k < 6; k++) {
                    for (TextField text : CellValues.get(j.get())) {
                        text.clear();
                        text.setStyle("");
                    }
                    j.getAndIncrement();
                }
                j.set(0);
                ClearAllCells.set(false);
            }
            getData.setText("?????????????????? ??????????");

            String guess = word.getText();

            try {
                File file = new File("words.txt");
                //?????????????? ???????????? FileReader ?????? ?????????????? File
                FileReader fr = new FileReader(file);
                //?????????????? BufferedReader ?? ?????????????????????????? FileReader ?????? ?????????????????????? ????????????????????
                BufferedReader reader = new BufferedReader(fr);
                String line;

                for (int p = 0; p < 3483; p++) {
                    // ?????????????????? ???????????? ?? ??????????
                    line = reader.readLine();
                    if (guess.equals(line)){
                        DoesThisWordExist.set(true);
                        break;
                    }
                    DoesThisWordExist.set(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            int count = 0;
            if (GenerateNewWord.get()) {
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
                GenerateNewWord.set(false);
            }
            final String finalWordle = prefinalWordle.get();
            for (TextField text : CellValues.get(DidYouGuessTheWord.get())) {
                if (guess.length() != 5) {
                    info.setText("???????????? ???????? 5 ????????!");
                    DidYouGuessTheWord.getAndDecrement();
                    break;
                }
                if (!DoesThisWordExist.get()) {
                    info.setText("???????????? ?????????? ??????!");
                    DidYouGuessTheWord.getAndDecrement();
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
                info.setText("?????????????? ?????????? ???? 5 ????????:");
            }
            DidYouGuessTheWord.getAndIncrement();
            if (guess.equals(finalWordle)) {
                info.setText("???? ??????????????!");
                word.setPromptText("???????????????????? ??????????: " + finalWordle);
                getData.setText("???????????? ????????????");
                DidYouGuessTheWord.set(0);
                GenerateNewWord.set(true);
                ClearAllCells.set(true);
            }
            if (DidYouGuessTheWord.get() == 6) {
                if (!guess.equals(finalWordle)){
                    info.setText("???? ??????????????????!");
                    word.setPromptText("???????????????????? ??????????: " + finalWordle);
                    getData.setText("???????????? ????????????");
                    DidYouGuessTheWord.set(0);
                    GenerateNewWord.set(true);
                    ClearAllCells.set(true);
                }
            }
            word.clear();
        });
    }
}