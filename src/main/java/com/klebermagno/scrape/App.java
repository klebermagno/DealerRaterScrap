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
    //private static String parameter = "/?filter=ONLY_NEGATIVE#link";
    private static String parameter = "/?filter=#link";
    private static int pages = 5;




    public App() {
        log.info("Scrape started.");
        log.info("Scrape url: " + baseUrl);
    }


    private List<DealerRaterReview> extractReviews( ) {
        List<DealerRaterReview> reviews =Collections.synchronizedList(new ArrayList());
        IntStream.range(1, pages + 1).parallel().forEach(n -> {
                    DealerRaterCrawler dealerRaterCrawler = new DealerRaterCrawler(baseUrl, paginator, parameter);
                    log.info("Scrape page: " + n);
                     reviews.addAll(dealerRaterCrawler.init(n));

                }
        );
        return reviews;
    }

    public List<ReviewSentiment> analyzeReview(List<DealerRaterReview> reviews) {
        ReviewAnalyzer reviewAnalyzer = new ReviewAnalyzer();
        List<ReviewSentiment> sentimentsReviews = reviewAnalyzer.analyze(reviews);
        log.info("Total of reviews: " + sentimentsReviews.size());

        return sentimentsReviews;
    }

    public static void main(String[] args) {
        App app = new App();
        List<DealerRaterReview> reviews = app.extractReviews();
        List<ReviewSentiment> sortedSentiment = app.analyzeReview(reviews);

        sortedSentiment.subList(0, 3).stream().forEach(reviewSentiment -> {
            System.out.println("User: " + reviewSentiment.getDealerRaterReview().getUser());
            System.out.println("TotalScore: " + reviewSentiment.getTotalScore());
            System.out.println("Title: " + reviewSentiment.getDealerRaterReview().getTitle());
        });
    }

}

