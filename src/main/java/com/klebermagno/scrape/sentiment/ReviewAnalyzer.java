package com.klebermagno.scrape.sentiment;

import com.klebermagno.scrape.model.DealerRaterReview;
import com.klebermagno.scrape.model.ReviewSentiment;
import com.klebermagno.scrape.model.SentimentResult;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ReviewAnalyzer {

    private double scoreCutLine = 300;

    public  List<ReviewSentiment> analyze(List<DealerRaterReview> reviews) {
        log.info("Analyze " +reviews.size()+" review sentiment. ");
        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
        sentimentAnalyzer.initialize();
        List<ReviewSentiment> reviewSentimentList = new ArrayList<>();
        for (DealerRaterReview review : reviews) {
            ReviewSentiment rs = new ReviewSentiment();
            rs.setDealerRaterReview(review);
            reviewSentimentList.add(rs);
            rs.setTitlePositiveScore(analyzeTextSentiment(rs,sentimentAnalyzer));
            analyzeTotalScore(rs);
        }
        return descendingSort(reviewSentimentList);
    }

    public List<ReviewSentiment> removeCutLine(List<ReviewSentiment> descendingSort) {
        ArrayList<ReviewSentiment> descendingSortLineCut = new ArrayList<>(descendingSort);
        descendingSortLineCut.removeIf(reviewSentiment -> reviewSentiment.getTotalScore()<this.scoreCutLine);
        return descendingSortLineCut;
    }

    private List<ReviewSentiment> descendingSort(List<ReviewSentiment> reviewSentmentList) {
        List<ReviewSentiment> sortedSentiment = reviewSentmentList.stream().sorted().collect(Collectors.toList());
        Collections.reverse(sortedSentiment);
        return sortedSentiment;
    }

    private void analyzeTotalScore(ReviewSentiment rs) {
       double wholeScore = rs.getTitlePositiveScore();
       int cs = rs.getDealerRaterReview().getCustomerService();
       int fl =rs.getDealerRaterReview().getFriendliness();
       int pr = rs.getDealerRaterReview().getPricing();
       int qw =rs.getDealerRaterReview().getQualityOfWork();
       int ra =rs.getDealerRaterReview().getRatingStatic();
       int oa = rs.getDealerRaterReview().getOverallExperience();
       int rc = rs.getDealerRaterReview().getRecommendDealer()?1:-1;
       rs.setTotalScore((cs+fl+pr+qw+ra+oa+wholeScore*10)*rc);
    }

    private double analyzeTextSentiment(ReviewSentiment rs, SentimentAnalyzer sentimentAnalyzer) {
        SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(
                rs.getDealerRaterReview().getTitle() + " " + rs.getDealerRaterReview().getReview());
        log.debug(sentimentResult.toString());
        double sentimentScore = sentimentResult.getSentimentScore();
        return sentimentScore;
    }
}
