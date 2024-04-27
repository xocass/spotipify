package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateSponsor {
    @FXML
    private Label text;

    public void setText(String text) {
        this.text.setText(text);
    }
}
