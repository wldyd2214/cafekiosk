package sample.cafekiosk.spring.api.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

//@Configuration
//public class FirebaseConfiguration {
//
//    @Value("classpath:keystore/service-account.json")
//    private Resource resource;
//
//    private FirebaseApp firebaseApp;
//
//    @PostConstruct
//    public void initializeFCM() {
//        try {
//            // Service Account를 이용하여 Fireabse Admin SDK 초기화
//            FileInputStream serviceAccount = new FileInputStream(resource.getFile());
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://fcm.googleapis.com/v1/projects/uplus-mobvas/messages:send")
//                    .build();
//            FirebaseApp.initializeApp(options);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
