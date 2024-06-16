package sample.cafekiosk.spring.api.service.fcm;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FcmService {
    public Object fcmMessagePush() throws FirebaseMessagingException {
        // This registration token comes from the client FCM SDKs.
        List<String> registrationTokens =
                Arrays.asList("sampleToken1", "sampleToken2");

        // See documentation on defining a message payload.
        MulticastMessage message = MulticastMessage.builder()
                                                   .putData("title", "제목")
                                                   .putData("content", "내용")
                                                   .addAllTokens(registrationTokens)
                                                   .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
        System.out.println("Successfully Count: " + response.getSuccessCount());
        System.out.println("Successfully Failure: " + response.getFailureCount());

        return response;
    }
}
