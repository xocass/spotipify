package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;


public class cCargando {
    @FXML
    private ProgressBar barraCarga;


    public void barraCargando() {
        barraCarga.setProgress(0.1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        barraCarga.setProgress(0.35);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        barraCarga.setProgress(0.67);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        barraCarga.setProgress(0.84);
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        barraCarga.setProgress(0.95);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        barraCarga.setProgress(1.0);


    }
}
