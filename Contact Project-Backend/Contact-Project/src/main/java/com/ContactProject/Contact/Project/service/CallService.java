// package com.ContactProject.Contact.Project.service;
//
// import com.twilio.Twilio;
// import com.twilio.rest.api.v2010.account.Call;
// import com.twilio.type.PhoneNumber;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
//
// @Service
// public class CallService {
//
// phone number
//
//// public CallService() {
//// // Initialize Twilio with your Account SID and Auth Token
//// Twilio.init(accountSid, authToken);
//// }
////
//// public void makeCall(String toPhoneNumber) {
//// Call call = Call.creator(
//// new PhoneNumber(toPhoneNumber), // The phone number to call
//// new PhoneNumber(twilioPhoneNumber), // Your Twilio number
//// new com.twilio.type.Twiml("<Response><Say>Hello, this is a call from your
// Spring Boot application!</Say></Response>")
//// ).create();
//
// //System.out.println("Call SID: " + call.getSid());
// }
// }
