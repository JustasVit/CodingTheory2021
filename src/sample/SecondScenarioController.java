package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Vector;

public class SecondScenarioController {
    @FXML
    TextField stringInput;
    @FXML
    Button encodeButton;
    @FXML
    TextArea messageInBitsField;
    @FXML
    TextArea encodedMessageInBitsField;
    @FXML
    Button sendToChannelButton;
    @FXML
    TextField probabilityField;
    @FXML
    TextArea receivedMessageFromChannelField;
    @FXML
    TextArea receivedEncodedMessageFromChannelField;
    @FXML
    TextArea restoredMessageField;
    @FXML
    TextArea restoredEncodedMessageField;

//    @FXML
//    public void initialize()
//    {
//        EncodedMessageField.setEditable(false);
//        ReceivedMessageFromChannelArea.setEditable(false);
//        ErrorField.setEditable(false);
//        DecodedMessageField.setEditable(false);
//        errorText.setVisible(false);
//        EditButton.setDisable(true);
//        SendToChannelButton.setDisable(true);
//        probabilityField.setDisable(true);

//        BytesInput.textProperty().addListener(((observableValue, s, t1) -> {
//            if(!t1.matches("^[0-1]*$") )
//            {
//                BytesInput.setText(s);
//            }
//        }));

//        EncodedMessageField.textProperty().addListener(((observableValue, s, t1) -> {
//            if(!t1.matches("^[0-1]*$") )
//            {
//                EncodedMessageField.setText(s);
//            }
//        }));
//
//        probabilityField.textProperty().addListener(((observableValue, s, t1) -> {
//            if(!t1.matches("^(^$|1|0|([0][.][0-9]*))$") )
//            {
//                probabilityField.setText(s);
//            }
//            if(probabilityField.getLength() > 0 && probabilityField.getLength() != 2)
//            {
//                SendToChannelButton.setDisable(false);
//            }else SendToChannelButton.setDisable(true);
//        }));
//
//    }


    @FXML
    public void EncodeWords()
    {
        String originalMessage = stringInput.getText();

        BinaryConverter binaryConverter = new BinaryConverter();
        Encoder encoder = new Encoder();

        Vector<Integer> originalMessageBits = binaryConverter.ConvertStringToBits(originalMessage);
        SetTextAreaText(messageInBitsField, originalMessageBits);
        Vector<Integer> encodedMessageBits = encoder.Encode(originalMessageBits);
        SetTextAreaText(encodedMessageInBitsField, encodedMessageBits);

        probabilityField.setDisable(false);
    }

    @FXML
    public void SendMessagesTroughChannel()
    {
        String originalMessage = messageInBitsField.getText();
        String encodedMessage = encodedMessageInBitsField.getText();
        double probability = Double.parseDouble(probabilityField.getText());

        BinaryParser binaryParser = new BinaryParser();
        Channel channel = new Channel();

        Vector<Integer> originalMessageBits = binaryParser.parseString(originalMessage);
        Vector<Integer> bitsReturnedFromChannel =  channel.SendBitsThroughChannel(probability,originalMessageBits);
        Vector<Integer> encodedMessageBits = binaryParser.parseString(encodedMessage);
        Vector<Integer> encodedBitsReturnedFromChannel =  channel.SendBitsThroughChannel(probability,encodedMessageBits);

        SetTextAreaText(receivedMessageFromChannelField, bitsReturnedFromChannel);
        SetTextAreaText(receivedEncodedMessageFromChannelField, encodedBitsReturnedFromChannel);

        RestoreMessages(bitsReturnedFromChannel,encodedBitsReturnedFromChannel);
    }

    public void RestoreMessages(Vector<Integer> originalMessageBits, Vector<Integer> encodedMessageBits)
    {
        BinaryConverter binaryConverter = new BinaryConverter();

        SetTextAreaText(restoredMessageField,binaryConverter.ConvertBitsToString(originalMessageBits));

        Decoder decoder = new Decoder();
        SetTextAreaText(restoredEncodedMessageField,binaryConverter.ConvertBitsToString(decoder.decode(encodedMessageBits)));
    }


    public void SetTextAreaText(TextArea textArea, Vector<Integer> bits)
    {
        BinaryParser binaryParser = new BinaryParser();
        textArea.setText(binaryParser.parseVector(bits));
    }
    public void SetTextAreaText(TextArea textArea, String text)
    {
        textArea.setText(text);
    }
}
