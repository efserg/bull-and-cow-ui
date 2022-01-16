package ru.gb.bcg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CowAndBullController {

    private Game game;
    private int step;

    @FXML
    private TextArea tipArea;
    @FXML
    private TextField answerField;

    public CowAndBullController() {
        game = new Game();
        step = 1;
    }

    public void onClickCheckButton(ActionEvent actionEvent) {
        final String answer = answerField.getText();
        if (answer != null && !answer.isEmpty()) {
            final Game.CowBullCount cowBullCount = game.calc(answer);
            final String tip = String.format("%d. Введено число %s: быков %d, коров %d", step, answer, cowBullCount.getBulls(), cowBullCount.getCows());
            step++;
            tipArea.appendText(tip + "\n");
            answerField.clear();
            if (cowBullCount.getBulls() == 4) {
                if (isPlayerWantPlayAgain()) {
                    runNewGame();
                } else {
                    System.exit(0);
                }
            }
        }
    }

    private void runNewGame() {
        step = 1;
        tipArea.clear();
        this.game = new Game();
    }

    private boolean isPlayerWantPlayAgain() {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Поздравляю, вы выиграли!\n" +
                        "Загаданное число: " + game.getGuessNumber() + "\n" +
                        "\n" +
                        "Желаете сыграть еще?",
                new ButtonType("Да", ButtonBar.ButtonData.YES),
                new ButtonType("Нет", ButtonBar.ButtonData.NO));
        alert.setTitle("Ю вона плей?");
        final ButtonType buttonType = alert.showAndWait().get();
        return buttonType.getButtonData() == ButtonBar.ButtonData.YES;
    }

    public void onNewGameSelect(ActionEvent actionEvent) {
        runNewGame();
    }

    public void onExitSelect(ActionEvent actionEvent) {
        System.exit(0);
    }
}