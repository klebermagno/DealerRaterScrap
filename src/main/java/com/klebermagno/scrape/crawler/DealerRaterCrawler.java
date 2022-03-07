package com.klebermagno.scrape.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.klebermagno.scrape.model.DealerRaterReview;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.klebermagno.scrape.crawler.RatingExtractor.extractGeralRating;
import static com.klebermagno.scrape.crawler.RatingExtractor.extractSpecificRatings;

@Slf4j
public class DealerRaterCrawler extends Thread{

    private String url;
    private String page;
    private String parameters;
    private WebClient client;
    private HtmlPage htmlPage;



    public DealerRaterCrawler(String url, String page,String parameters) {
        this.url = url;
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        this.page = page;
        this.parameters = parameters;
    }



    public List<DealerRaterReview> init(int pageNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.url);
        sb.append(this.page + pageNumber);
        sb.append(this.parameters);
        try {

            log.debug("Start crawler "+ pageNumber+ " in: " +sb.toString());
            this.htmlPage = client.getPage(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
       return  extract();
    }

    private List<DealerRaterReview> extract() {
        List<DealerRaterReview> dealerRaterReviews = null;
        HtmlElement reviewSection = htmlPage.getFirstByXPath(DealerRaterEnum.REVIEW_SECTION.label);
        List<HtmlElement> reviewWarpper = reviewSection.getByXPath(DealerRaterEnum.REVIEW_WARPPER.label);
        if (reviewWarpper.isEmpty()) {
            log.debug("No items found !");
        } else {
            for (int i = 0; i < reviewWarpper.size(); i++) {
                HtmlElement review = reviewWarpper.get(i);
                dealerRaterReviews =mapper(review, i);
            }
        }

        return dealerRaterReviews;
    }

    private  List<DealerRaterReview> mapper(HtmlElement review, int position) {
        List<DealerRaterReview> dealerRaterReviews  = new ArrayList<>();;
        DealerRaterReview dealerRaterReview = new DealerRaterReview();

        HtmlSpan reviewTitle = review.getFirstByXPath(DealerRaterEnum.REVIEW_TITLE.label);
        log.debug("Review title: " +reviewTitle.getTextContent());
        dealerRaterReview.setTitle(reviewTitle.getTextContent());

        HtmlSpan reviewWhole = review.getFirstByXPath(DealerRaterEnum.REVIEW_WHOLE.label);
        log.debug("Review whole: " +reviewWhole.getTextContent());
        dealerRaterReview.setReview(reviewWhole.getTextContent());

        HtmlSpan reviewUser = review.getFirstByXPath(DealerRaterEnum.REVIEW_USER.label);
        log.debug("Review user: " +reviewUser.getTextContent());
        dealerRaterReview.setUser(reviewUser.getTextContent());

        extractGeralRating(review,dealerRaterReview,position);

        extractSpecificRatings(review,dealerRaterReview,position);

        dealerRaterReviews.add(dealerRaterReview);
        return dealerRaterReviews;

    }






}