package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Vector;

public class FirstScenarioController {
    @FXML
    TextField BytesInput;
    @FXML
    Button EncodeButton;
    @FXML
    TextArea EncodedMessageField;
    @FXML
    Button EditButton;
    @FXML
    Button SendToChannelButton;
    @FXML
    TextArea ReceivedMessageFromChannelArea;
    @FXML
    TextArea ErrorField;
    @FXML
    TextArea DecodedMessageField;
    @FXML
    TextField probabilityField;
    @FXML
    Text errorText;

    @FXML
    public void initialize()
    {
        EncodedMessageField.setEditable(false);
        ReceivedMessageFromChannelArea.setEditable(false);
        ErrorField.setEditable(false);
        DecodedMessageField.setEditable(false);
        errorText.setVisible(false);
        EditButton.setDisable(true);
        SendToChannelButton.setDisable(true);
        probabilityField.setDisable(true);
        AddListeners();
    }

    public void AddListeners()
    {
        BytesInput.textProperty().addListener(((observableValue, s, t1) -> {
            if(!t1.matches("^[0-1]*$") )
            {
                BytesInput.setText(s);
            }
        }));

        EncodedMessageField.textProperty().addListener(((observableValue, s, t1) -> {
            if(!t1.matches("^[0-1]*$") )
            {
                EncodedMessageField.setText(s);
            }
        }));

        probabilityField.textProperty().addListener(((observableValue, s, t1) -> {
            if(!t1.matches("^(^$|1|0|([0][.][0-9]*))$") )
            {
                probabilityField.setText(s);
            }
            SendToChannelButton.setDisable(probabilityField.getLength() <= 0 || probabilityField.getLength() == 2);
        }));
    }


    @FXML
    public void Encode()
    {
        BinaryParser binaryParser = new BinaryParser();

        String input =  BytesInput.getText();
        Vector<Integer> bits = binaryParser.parseString(input);

        Encoder encoder = new Encoder();
        Vector<Integer> encodedBits = encoder.Encode(bits);
        SetTextAreaText(EncodedMessageField, encodedBits);

        EditButton.setDisable(false);
        probabilityField.setDisable(false);
    }

    @FXML
    public void Send()
    {
        EncodedMessageField.setEditable(false);

        String bitsInput = EncodedMessageField.getText();
        String probabilityInput = probabilityField.getText();

        BinaryParser binaryParser = new BinaryParser();
        Decoder decoder = new Decoder();
        Channel channel = new Channel();

        Vector<Integer> bits = binaryParser.parseString(bitsInput);
        Vector<Integer> bitsCopy = binaryParser.parseString(bitsInput);
        Vector<Integer> bitsSentTroughChannel = channel.SendBitsThroughChannel(Double.parseDouble(probabilityInput),bits);
        SetTextAreaText(ReceivedMessageFromChannelArea, bitsSentTroughChannel);
        checkAndDisplayErrors(bitsCopy,bitsSentTroughChannel);
        Vector<Integer> decodedBits = decoder.decode(bitsSentTroughChannel);
        SetTextAreaText(DecodedMessageField, decodedBits);
    }

    @FXML
    public void Edit()
    {
        EncodedMessageField.setEditable(true);
        int initialLength = EncodedMessageField.getLength();
        EncodedMessageField.textProperty().addListener(((observableValue, s, t1) -> {
            if(initialLength != t1.length())
            {
                errorText.setVisible(true);
            }
            else errorText.setVisible(false);
            if(initialLength - t1.length() > 1 || t1.length() - initialLength == 1)
            {
                EncodedMessageField.setText(s);
            }
        }));

    }


    public void checkAndDisplayErrors(Vector<Integer> initialBits, Vector<Integer> bitsSentTroughChannel)
    {
        ErrorChecker errorChecker = new ErrorChecker(initialBits,bitsSentTroughChannel);
        String errorString = errorChecker.checkForErrors();
        ErrorField.setText(errorString);
    }

    public void SetTextAreaText(TextArea textArea, Vector<Integer> bits)
    {
        BinaryParser binaryParser = new BinaryParser();
        textArea.setText(binaryParser.parseVector(bits));
    }

}
