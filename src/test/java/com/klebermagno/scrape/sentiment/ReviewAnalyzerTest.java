package com.klebermagno.scrape.sentiment;

import com.klebermagno.scrape.model.DealerRaterReview;
import com.klebermagno.scrape.model.ReviewSentiment;
import org.junit.Test;

import java.util.List;

import static com.klebermagno.scrape.sentiment.ObjectDealerRaterReviews.objectDealerRaterReviews;
import static org.junit.Assert.assertEquals;

public class ReviewAnalyzerTest {


    @Test
    public void shouldAnalyze() {
        List<DealerRaterReview> dealerRaterReviews = objectDealerRaterReviews();
        ReviewAnalyzer analyzer = new ReviewAnalyzer();
        List<ReviewSentiment> reviewSentiments = analyzer.analyze(dealerRaterReviews);
        assertEquals(340.0, reviewSentiments.get(0).getTotalScore(), 0);
    }

    @Test
    public void shouldDontRecommend() {
        List<DealerRaterReview> dealerRaterReviews = objectDealerRaterReviews();
        ReviewAnalyzer analyzer = new ReviewAnalyzer();
        List<ReviewSentiment> reviewSentiments = analyzer.analyze(dealerRaterReviews);
        assertEquals(-150.0, reviewSentiments.get(3).getTotalScore(), 0);
    }

    @Test
    public void shouldSort() {
        List<DealerRaterReview> dealerRaterReviews = objectDealerRaterReviews();
        ReviewAnalyzer analyzer = new ReviewAnalyzer();
        List<ReviewSentiment> reviewSentiments = analyzer.analyze(dealerRaterReviews);
        assertEquals(340.0, reviewSentiments.get(0).getTotalScore(), 0);
        assertEquals(330.0, reviewSentiments.get(1).getTotalScore(), 0);
        assertEquals(330.0, reviewSentiments.get(2).getTotalScore(), 0);
        assertEquals(-150.0, reviewSentiments.get(3).getTotalScore(), 0);
        assertEquals(-180.0, reviewSentiments.get(8).getTotalScore(), 0);
    }

    @Test
    public void shouldCutLineRule() {
        List<DealerRaterReview> dealerRaterReviews = objectDealerRaterReviews();
        ReviewAnalyzer analyzer = new ReviewAnalyzer();
        List<ReviewSentiment> reviewSentiments = analyzer.analyze(dealerRaterReviews);
        List<ReviewSentiment> reviewSentimentsCutLine = analyzer.removeCutLine(reviewSentiments);
        assertEquals(3, reviewSentimentsCutLine.size());
    }
}
