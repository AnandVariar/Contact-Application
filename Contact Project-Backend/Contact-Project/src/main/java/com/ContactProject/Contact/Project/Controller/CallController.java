//package com.ContactProject.Contact.Project.Controller;
//
//import com.ContactProject.Contact.Project.service.CallService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CallController {
//
//    private final CallService callService;
//
//    @Autowired
//    public CallController(CallService callService) {
//        this.callService = callService;
//    }
//
//    @PostMapping("/api/call")
//    public ResponseEntity<String> makeCall(@RequestParam String phoneNumber) {
//        // Validate phoneNumber format if necessary
//        if (phoneNumber == null || phoneNumber.isEmpty()) {
//            return new ResponseEntity<>("Phone number is required.", HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            callService.makeCall(phoneNumber);
//            return new ResponseEntity<>("Call initiated successfully!", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to initiate call: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
