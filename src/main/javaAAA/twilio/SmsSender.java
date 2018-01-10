package twilio;
// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URISyntaxException;

public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC91d5708a9402e8fd541f1a44f97577a6";
    public static final String AUTH_TOKEN = "bbfac867f4b468e133681bd6aaba9022";

    public static void main(String [] args){
        sendToSimon();
    }
    public static void sendToSimon(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            Call call = Call.creator(new PhoneNumber("+16479721505"), new PhoneNumber("+16474927692"), new URI("http://simonguo.tech/warning.xml")).create();
            /*
            Message message = Message
                    .creator(new PhoneNumber("+16479721505"),  // to
                            new PhoneNumber("+16474927692"),  // from
                            "12345").create();
            Message message1 = Message.creator(new PhoneNumber("+16478680063"),
                                            new PhoneNumber("+16474927692"), "12345").create();
                                            */
        }catch(Exception e){
            System.out.println("You've done goofed Twilio...");
        }
    }
}