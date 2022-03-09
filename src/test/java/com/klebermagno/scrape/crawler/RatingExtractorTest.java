package com.klebermagno.scrape.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.klebermagno.scrape.model.DealerRaterReview;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * RatingExtractorTest Tester.
 *
 * @author Kleber
 * @version 1.0
 */
public class RatingExtractorTest {

    private DealerRaterReview dealerRaterReview;
    private HtmlPage htmlPage;
    private WebClient client;
    private HtmlElement reviewSection;
    private  List<HtmlElement> reviewWarpper;

    @Before
    public void before() throws Exception {
        this.dealerRaterReview = new DealerRaterReview();
        client= new WebClient();
        this.htmlPage = client.getPage("file:./target/test-classes/dealerrater.html");
        reviewSection = htmlPage.getFirstByXPath(DealerRaterEnum.REVIEW_SECTION.label);
        reviewWarpper = reviewSection.getByXPath(DealerRaterEnum.REVIEW_WARPPER.label);

    }


    @Test
    public void shouldExtractGeralRating() {

        RatingExtractor.extractGeralRating(reviewSection, dealerRaterReview, 1);
        assertEquals(38,dealerRaterReview.getRatingStatic());
    }
    @Test
    public void shouldExtractSpecificRatingsCustomerService() {
        RatingExtractor.extractSpecificRatings(reviewSection, dealerRaterReview, 1);
        assertEquals(34,dealerRaterReview.getCustomerService());



    }
    @Test
    public void shouldExtractSpecificRatingsQualityOfWork() {
        RatingExtractor.extractSpecificRatings(reviewSection, dealerRaterReview, 1);
        assertEquals(0,dealerRaterReview.getQualityOfWork());
    }
    @Test
    public void shouldExtractSpecificRatingsFriendliness() {
        HtmlElement reviewSection = htmlPage.getFirstByXPath(DealerRaterEnum.REVIEW_SECTION.label);
        RatingExtractor.extractSpecificRatings(reviewSection, dealerRaterReview, 1);
        assertEquals(24,dealerRaterReview.getFriendliness());
    }
    @Test
    public void shouldExtractSpecificRatingsPricing() {
        RatingExtractor.extractSpecificRatings(reviewSection, dealerRaterReview, 1);
        assertEquals(27,dealerRaterReview.getPricing());

    }
    @Test
    public void shouldExtractSpecificOverallExperience() {
        RatingExtractor.extractSpecificRatings(reviewSection, dealerRaterReview, 1);
        assertEquals(31,dealerRaterReview.getOverallExperience());
    }
    @Test
    public void shouldExtractSpecificRecommendDealer() {
        RatingExtractor.extractSpecificRatings(reviewSection, dealerRaterReview, 1);
        assertEquals(true,dealerRaterReview.getRecommendDealer());
    }

    @Test
    public void shouldExtractRater(){
        int rater = RatingExtractor.extractRater("rating-static rating-49 margin-top-none pull-right margin-right-none");
        assertEquals(49,rater);
    }

    @Test
    public void shouldExtractRaterWithoutRating(){

        int rater = RatingExtractor.extractRater("rating-static margin-top-none pull-right margin-right-none");
        assertEquals(0,rater);
    }
    @Test
    public void shouldExtractTitle() {

        RatingExtractor.extractTitle(reviewWarpper.get(0), dealerRaterReview);
        assertEquals("He was very driven and motivated, polite and caring! I",dealerRaterReview.getTitle());
    }
    @Test
    public void shouldExtractReview() {
        RatingExtractor.extractReview(reviewWarpper.get(0), dealerRaterReview);
        assertEquals(" will recommend him!! Very satisfied!!!",dealerRaterReview.getReview());
    }
    @Test
    public void shouldExtractUser() {
        RatingExtractor.extractUser(reviewWarpper.get(0), dealerRaterReview);
        assertEquals("Tonya.griffin903",dealerRaterReview.getUser());
    }
}
