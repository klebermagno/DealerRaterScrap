package com.klebermagno.scrape;


import com.klebermagno.scrape.crawler.DealerRaterCrawler;
import com.klebermagno.scrape.model.DealerRaterReview;
import com.klebermagno.scrape.model.ReviewSentiment;
import com.klebermagno.scrape.sentiment.ReviewAnalyzer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a web crawler.
 *
 * @author Kleber Magno Maciel Vieira
 * @author klebermagno@me.com
 * @version 1.0.0
 */

@Slf4j
@Data
public class App {

    private static String baseUrl = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/";
    private static String paginator = "page";
    private static String parameter = "/?filter=#link";
    private static int pages = 5;
    List<DealerRaterReview> reviews =Collections.synchronizedList(new ArrayList());



    public App() {
        log.info("Scrape started.");
        log.info("Scrape url: " + baseUrl);
    }


    /**
     * Fist step is scrap all reviews from page.
     * @return List of dealer rate review.
     */
    public List<DealerRaterReview> extractReviews( ) {

        IntStream.range(1, pages + 1).parallel().forEach(n -> {
                    DealerRaterCrawler dealerRaterCrawler = new DealerRaterCrawler(baseUrl, paginator+n, parameter);
                    log.debug("Scrape page: " + n);
                    List<DealerRaterReview>  review = dealerRaterCrawler.init();
                    log.debug("Scrape page: " + n+" scraped review:" +review.size());
                    reviews.addAll(review);
                }
        );
        return reviews;
    }

    /**
     * Analyze the review and calc total score.
     * @param reviews List of reviews.
     * @return List of Sentiment score.
     */
    public List<ReviewSentiment> analyzeReview(List<DealerRaterReview> reviews) {
        ReviewAnalyzer reviewAnalyzer = new ReviewAnalyzer();
        List<ReviewSentiment> sentimentsReviews = reviewAnalyzer.analyze(reviews);
        log.info("Total of reviews: " + sentimentsReviews.size());

        return reviewAnalyzer.removeCutLine(sentimentsReviews);
    }

    public static void main(String[] args) {
        App app = new App();
        List<DealerRaterReview> reviews = app.extractReviews();
        List<ReviewSentiment> sortedSentiment = app.analyzeReview(reviews);
        if (sortedSentiment.size()==0)
            log.info("Can't find any overly positive endorsements!");
        for (int i =0;i<3&&i<sortedSentiment.size();i++) {
            ReviewSentiment reviewSentiment = sortedSentiment.get(i);
            log.info("----------------------Overly positive endorsement---------------------------");
            log.info("User: " + reviewSentiment.getDealerRaterReview().getUser());
            log.info("TotalScore: " + reviewSentiment.getTotalScore());
            log.info("Title: " + reviewSentiment.getDealerRaterReview().getTitle());
        }


    }

}

