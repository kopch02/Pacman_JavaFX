package maps;

import java.io.IOException;
import javafx.fxml.FXML;

public class MapController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("map");
    }
}
