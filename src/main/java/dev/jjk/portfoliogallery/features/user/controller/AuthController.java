package dev.jjk.portfoliogallery.features.user.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import dev.jjk.portfoliogallery.features.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	private FirebaseAuth firebaseAuth;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
		try {
			UserRecord.CreateRequest request = new UserRecord.CreateRequest().setEmail(userDTO.getEmail())
																																			 .setPassword(
																																					 userDTO.getPassword())
																																			 .setDisplayName(
																																					 userDTO.getDisplayName());

			UserRecord userRecord = firebaseAuth.createUser(request);
			return ResponseEntity.ok("User created successfully: " + userRecord.getUid());
		} catch (FirebaseAuthException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
													 .body(e.getMessage());
		}
	}

	//로그인 실패
	//    @PostMapping("/login")
	//    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
	//        try {
	//            SignInWithEmailAndPassword signInWithEmailAndPassword = FirebaseAuth.getInstance()
	//                    .signInWithEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
	//            return ResponseEntity.ok("Login successful. User ID: " + signInWithEmailAndPassword.getUserInfo().getUid());
	//        } catch (FirebaseAuthException e) {
	//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	//        }
	//    }
}
