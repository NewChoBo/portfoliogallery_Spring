package dev.jjk.util.firebase.firestrore.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service
public class FirestoreService {

	public void saveDataToFirestore(String collectionName, String documentId, Object data) {
		com.google.cloud.firestore.Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection(collectionName)
																				.document(documentId);
		docRef.set(data);
	}

	public Object getDataFromFirestore(String collectionName, String documentId) throws
			ExecutionException,
			InterruptedException {
		com.google.cloud.firestore.Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection(collectionName)
																				.document(documentId);
		return docRef.get()
								 .get()
								 .getData();
	}
}
