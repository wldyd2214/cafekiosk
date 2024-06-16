package sample.cafekiosk.spring.api.controller.fcm;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.spring.api.ApiResponse;
import sample.cafekiosk.spring.api.service.fcm.FcmService;

@RequiredArgsConstructor
@RestController
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/api/v1/fcm")
    public ApiResponse<Object> postFcmMessage() throws FirebaseMessagingException {
        return ApiResponse.ok(fcmService.fcmMessagePush());
    }
}
