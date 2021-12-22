package com.infilos.sampler;

import javafx.application.Application;
import javafx.stage.Stage;

public class Bootstrap {

    public static void main(String[] args) {
        Sampler.launch(Sampler.class, args);
    }

    public static class Sampler extends Application {

        @Override
        public void start(Stage stage) throws Exception {
            Populator.apply(stage);
            stage.setTitle("FX Sampler");
            stage.show();
        }
    }
}
