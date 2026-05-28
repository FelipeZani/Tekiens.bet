// package com.SurveyApp.modelTest;

// import static org.junit.jupiter.api.Assertions.assertAll;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.HashSet;

// import org.junit.jupiter.api.Test;

// import com.SurveyApp.transactionsManager.commum.model.AppTransactionStatus;
// import com.SurveyApp.transactionsManager.dailyReward.model.DailyReward;

// public class DailyRewardTest {

//     @Test
//     public void constructorTest(){
//         DailyReward daily = new DailyReward(0l, 0l,0l,7500,AppTransactionStatus.COMPLETED);

//         assertAll("Daily Reward constructor test",
//             () -> assertTrue(Integer.max(daily.getCoolDown(), 0) >= 0),
//             () -> assertTrue(Integer.max(daily.getDailyRewardRate(), 0) >= 0)
            
//         );

//     }


    
// }
