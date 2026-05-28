package com.SurveyApp.modelTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import com.SurveyApp.transactionsManager.commum.model.AppTransaction;
import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;


  
 
public class TransactionTest {
	
	//   Id pk constraint
	//   amount >= 1
	//   srcId not null not updatable
	//   dstId not null not updatable
	//   acc not null
	 
	 @Test
	 public void transactionConstructorTest() throws Exception {

	 	AppTransaction transaction = new AppTransaction(
			1L,
			2L,
			3000L,
			20,
			AppTransactionStatus.COMPLETED
		);
	 			

	 	assertAll("Transactions asserts",
	 			() -> assertTrue(transaction.getAmount() >= 1,
	 					"Points amount shouldn't be smaller than one"),
	 			() -> assertNotEquals(Long.max(transaction.getDstId(), 0), 0L,
	 					"Destionation id format is not correct"),
	 			() -> assertNotEquals(Long.max(transaction.getSrcId(), 0), 0L,
	 					"Source id format is not correct"),
	 			() -> assertNotNull(transaction.getStatus(), "Transaction status shouldn't be null"));

	 }

}
