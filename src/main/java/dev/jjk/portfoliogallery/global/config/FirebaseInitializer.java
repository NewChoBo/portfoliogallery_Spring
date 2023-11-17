package dev.jjk.portfoliogallery.global.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

@Configuration
public class FirebaseInitializer {
	private static final Logger log = LoggerFactory.getLogger(FirebaseInitializer.class);

	@Bean
	public FirebaseApp firebaseApp() throws IOException {
		log.info("Firebase 초기화 중.");
		FileInputStream serviceAccount = new FileInputStream("firebase.json");

		FirebaseOptions options = FirebaseOptions.builder().setCredentials(
			GoogleCredentials.fromStream(serviceAccount)).setStorageBucket("heroku-sample.appspot.com").build();

		FirebaseApp app = FirebaseApp.initializeApp(options);
		log.info("FirebaseApp 초기화됨: " + app.getName());
		return app;
	}

	@Bean
	public FirebaseAuth getFirebaseAuth() throws IOException {
		return FirebaseAuth.getInstance(firebaseApp());
	}
}
