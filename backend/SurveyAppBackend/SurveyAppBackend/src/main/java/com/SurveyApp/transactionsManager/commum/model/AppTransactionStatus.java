package com.SurveyApp.transactionsManager.commum.model;

public enum AppTransactionStatus {
    COMPLETED,
    CANCELLED,
    FAILED;
    @Override
public String toString() {
    switch (this) {
        case COMPLETED:
            return "COMPLETED";
        case CANCELLED:
            return "CANCELLED";
        case FAILED:
            return "FAILED";
        default:
            return null;
    }
}

}
