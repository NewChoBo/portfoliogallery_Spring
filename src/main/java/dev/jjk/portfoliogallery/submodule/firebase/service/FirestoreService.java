package dev.jjk.portfoliogallery.submodule.firebase.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service
public class FirestoreService {

	public void saveDataToFirestore(String collectionName, String documentId, Object data) {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection(collectionName).document(documentId);
		docRef.set(data);
	}

	public Object getDataFromFirestore(String collectionName, String documentId) throws
			ExecutionException,
			InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection(collectionName)
																				.document(documentId);
		return docRef.get()
								 .get()
								 .getData();
	}
}
