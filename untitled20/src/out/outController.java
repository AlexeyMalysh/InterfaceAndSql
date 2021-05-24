package out;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.ControllerSecond;

public class outController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text transmissionText;

    @FXML
    private Text EngineText;
/*Output of engine and transmission characteristics relative
            to the model received from the user in the first parent window */
    @FXML
    void initialize() {
        transmissionText.setText(ControllerSecond.textTransmission);
        EngineText.setText(ControllerSecond.engineText);

    }
}
