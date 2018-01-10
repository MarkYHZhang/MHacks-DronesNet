package twilio;
// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;

public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC91d5708a9402e8fd541f1a44f97577a6";
    public static final String AUTH_TOKEN = "bbfac867f4b468e133681bd6aaba9022";

    public static void sendToSimon(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            Call call = Call.creator(new PhoneNumber("+16479721505"), new PhoneNumber("+16474927692"), new URI("http://callummoseley.net/warning.xml")).create();

            Message message = Message
                    .creator(new PhoneNumber("+16479721505"),  // to
                            new PhoneNumber("+16474927692"),  // from
                            "Some drone have entered your air space").create();
            Message message1 = Message.creator(new PhoneNumber("+16478680063"),
                                            new PhoneNumber("+16474927692"), "Your drone have entered a drone regulatory area!!!").create();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("You've done goofed Twilio...");
        }
    }
}