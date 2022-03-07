package com.klebermagno.scrape.crawler;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.klebermagno.scrape.model.DealerRaterReview;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.isDigits;

@Slf4j
public class RatingExtractor {

    public static void extractGeralRating(HtmlElement review, DealerRaterReview dealerRaterReview, int position) {
        List<HtmlDivision> ratings = review.getByXPath(DealerRaterEnum.RATING_STATIC.label);
        HtmlDivision ratingStatic = ratings.get(position);
        dealerRaterReview.setRatingStatic(extractRater(ratingStatic.getAttribute("class")));
        log.debug("position: " + position + " ration: " + extractRater(ratingStatic.getAttribute("class")));

    }

    public static void extractSpecificRatings(HtmlElement review, DealerRaterReview dealerRaterReview, int position) {
        List<HtmlDivision> ratings = (List) review.getByXPath(DealerRaterEnum.RATINGS.label);
        HtmlDivision ratingTable = ratings.get(position);
        Iterable<HtmlElement> elements = ratingTable.getHtmlElementDescendants();
        List<HtmlElement> elementList = IteratorUtils.toList(elements.iterator());
        for (int i = 0; i < elementList.size(); i++) {
            HtmlElement element = elementList.get(i);
            switch (element.getVisibleText()) {
                case "Customer Service":
                    HtmlElement customerServiceRating = elementList.get(i + 1);
                    log.debug("customer class:" + customerServiceRating.getAttribute("class"));
                    dealerRaterReview.setCustomerService(extractRater(customerServiceRating.getAttribute("class")));
                    log.debug("customer rating: " + extractRater(customerServiceRating.getAttribute("class")));
                    break;
                case "Quality of Work":
                    HtmlElement qualityOfWorkRating = elementList.get(i + 1);
                    log.debug("Quality class:" + qualityOfWorkRating.getAttribute("class"));
                    dealerRaterReview.setQualityOfWork(extractRater(qualityOfWorkRating.getAttribute("class")));
                    log.debug("Quality rating: " + extractRater(qualityOfWorkRating.getAttribute("class")));
                    break;
                case "Friendliness":
                    HtmlElement friendlinessRating = elementList.get(i + 1);
                    log.debug("Friendliness class:" + friendlinessRating.getAttribute("class"));
                    dealerRaterReview.setFriendliness(extractRater(friendlinessRating.getAttribute("class")));
                    log.debug("Friendliness rating: " + extractRater(friendlinessRating.getAttribute("class")));
                    break;
                case "Pricing":
                    HtmlElement pricingRating = elementList.get(i + 1);
                    log.debug("Pricing class:" + pricingRating.getAttribute("class"));
                    dealerRaterReview.setPricing(extractRater(pricingRating.getAttribute("class")));
                    log.debug("Friendliness rating: " + extractRater(pricingRating.getAttribute("class")));
                    break;
                case "Overall Experience":
                    HtmlElement overallExperienceRating = elementList.get(i + 1);
                    log.debug("Overall Experience class:" + overallExperienceRating.getAttribute("class"));
                    dealerRaterReview.setOverallExperience(extractRater(overallExperienceRating.getAttribute("class")));
                    log.debug("Overall Experience rating: " + extractRater(overallExperienceRating.getAttribute("class")));
                    break;
                case "Recommend Dealer":
                    HtmlElement recommendDealerRating = elementList.get(i + 1);
                    log.debug("Recommend Dealer class:" + recommendDealerRating.getVisibleText());
                    dealerRaterReview.setRecommendDealer(recommendDealerRating.getVisibleText().equals("YES") ? true : false);
                    break;
                default:
                    log.debug("Can't find any rating");
            }
        }
    }

    public static int extractRater(String classes) {
        Optional<String> optional = Arrays.asList(classes.split(" ")).stream().filter(clas ->
                clas.contains("rating-5") ||
                        clas.contains("rating-4") ||
                        clas.contains("rating-3") ||
                        clas.contains("rating-2") ||
                        clas.contains("rating-1") ||
                        clas.contains("rating-0")).findAny();
        if (optional.isPresent()) {
            Optional<String> rating = Arrays.asList(optional.get().split("-")).stream().filter(s -> isDigits(s)).findAny();
            if (rating.isPresent()) {

                return Integer.parseInt(rating.get());
            }
        }
        return 0;
    }
}
