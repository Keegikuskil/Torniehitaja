module org.example.torniehitaja {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens game to com.almasb.fxgl.core, javafx.fxml;
    exports game;
}