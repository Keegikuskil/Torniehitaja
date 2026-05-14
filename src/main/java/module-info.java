module org.example.torniehitaja {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens game to com.almasb.fxgl.all, javafx.fxml, javafx.base;
    exports game;
}