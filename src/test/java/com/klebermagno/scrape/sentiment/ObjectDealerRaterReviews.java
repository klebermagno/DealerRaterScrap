package com.klebermagno.scrape.sentiment;

import com.klebermagno.scrape.model.DealerRaterReview;

import java.util.ArrayList;
import java.util.List;

public class ObjectDealerRaterReviews {

    public static  List<DealerRaterReview>  objectDealerRaterReviews(){
        List<DealerRaterReview> dealerRaterReviews = new ArrayList<>();
        DealerRaterReview dealerRaterReview = DealerRaterReview.builder()
                .title("I came in for an appointment Thursday morning for a").
                review(" transmission flush service. Mariela Hernandez got my truck checked in pretty quickly. When they where all done she gave me a call to come pick it up when I had time.  Overall great job she did at getting me in and great job on the transmission flush I can tell that it made a big difference bc it shifts an runs a lot smoother than previously.  Thanks you guys again for taking care of me and my vehicle.").
                user("by Richard Irvine").
                ratingStatic(50).
                customerService(50).
                friendliness(50).
                overallExperience(50).
                qualityOfWork(50).
                pricing(50).
                recommendDealer(false).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        dealerRaterReview = DealerRaterReview.builder()
                .title("I came in for an appointment Thursday morning for a").
                review(" transmission flush service. Mariela Hernandez got my truck checked in pretty quickly. When they where all done she gave me a call to come pick it up when I had time.  Overall great job she did at getting me in and great job on the transmission flush I can tell that it made a big difference bc it shifts an runs a lot smoother than previously.  Thanks you guys again for taking care of me and my vehicle.").
                user("by Richard Irvine").
                ratingStatic(50).
                customerService(50).
                friendliness(50).
                overallExperience(50).
                qualityOfWork(50).
                pricing(50).
                recommendDealer(false).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        dealerRaterReview = DealerRaterReview.builder()
                .title("I came in for an appointment Thursday morning for a").
                review(" transmission flush service. Mariela Hernandez got my truck checked in pretty quickly. When they where all done she gave me a call to come pick it up when I had time.  Overall great job she did at getting me in and great job on the transmission flush I can tell that it made a big difference bc it shifts an runs a lot smoother than previously.  Thanks you guys again for taking care of me and my vehicle.").
                user("by Richard Irvine").
                ratingStatic(50).
                customerService(50).
                friendliness(50).
                overallExperience(50).
                qualityOfWork(50).
                pricing(50).
                recommendDealer(false).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        return dealerRaterReviews;
    }
}
