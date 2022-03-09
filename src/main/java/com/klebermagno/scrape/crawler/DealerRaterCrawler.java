package com.klebermagno.scrape.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.klebermagno.scrape.model.DealerRaterReview;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.klebermagno.scrape.crawler.RatingExtractor.*;

/**
 * Represt a Dealer Rater Crawler.
 *
 * @version 1.0.0
 * @Author Kleber Magno Maciel Vieir.
 */
@Slf4j
public class DealerRaterCrawler  {

    private String url;
    private String page;
    private String parameters;
    private WebClient client;
    private HtmlPage htmlPage;
    private String pageNumber;


    /**
     * Create a DealerRaterCrawler
     *
     * @param url        Url
     * @param page       paginator prefix.
     * @param parameters page number.
     */
    public DealerRaterCrawler(String url, String page, String parameters) {
        this.url = url;
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        this.page = page;
        this.parameters = parameters;
    }


    /**
     * Init crawler.
     *

     * @return List of all DealerRaterReview from this page.
     */
    public List<DealerRaterReview> init() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.url);
        sb.append(this.page );
        sb.append(this.parameters);
        try {

            log.debug("Start crawler " + pageNumber + " in: " + sb.toString());
            this.htmlPage = client.getPage(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extract();
    }


    public List<DealerRaterReview> extract() {
        List<DealerRaterReview> dealerRaterReviews = new ArrayList<>();
        HtmlElement reviewSection = htmlPage.getFirstByXPath(DealerRaterEnum.REVIEW_SECTION.label);
        List<HtmlElement> reviewWarpper = reviewSection.getByXPath(DealerRaterEnum.REVIEW_WARPPER.label);
        if (reviewWarpper.isEmpty()) {
            log.debug("No items found !");
        } else {
            for (int i = 0; i < reviewWarpper.size(); i++) {
                HtmlElement review = reviewWarpper.get(i);
                dealerRaterReviews.add(mapper(review, i));
            }
        }

        return dealerRaterReviews;
    }

    public DealerRaterReview mapper(HtmlElement review, int position) {

        DealerRaterReview dealerRaterReview = new DealerRaterReview();

        extractTitle(review, dealerRaterReview);

        extractReview(review, dealerRaterReview);

        extractUser(review, dealerRaterReview);

        extractGeralRating(review, dealerRaterReview, position);

        extractSpecificRatings(review, dealerRaterReview, position);


        return dealerRaterReview;

    }



}