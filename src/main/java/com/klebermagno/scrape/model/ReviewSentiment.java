package com.klebermagno.scrape.model;

import lombok.Data;

/**
 * Represent review sentiment.
 */
@Data
public class ReviewSentiment implements  Comparable<ReviewSentiment>{

    private double titlePositiveScore;
    private double totalScore;
    private DealerRaterReview dealerRaterReview;

    @Override
    public int compareTo(ReviewSentiment rs) {
        return totalScore < rs.getTotalScore() ? -1 : (totalScore == rs.getTotalScore() ? 0 : 1);

    }
}
