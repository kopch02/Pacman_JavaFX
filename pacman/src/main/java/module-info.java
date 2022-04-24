module pacman {
    requires javafx.controls;
    requires javafx.fxml;

    opens maps to javafx.fxml;
    exports maps;
}
