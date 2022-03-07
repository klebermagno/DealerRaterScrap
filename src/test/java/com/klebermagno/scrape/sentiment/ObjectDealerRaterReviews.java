package com.klebermagno.scrape.sentiment;

import com.klebermagno.scrape.model.DealerRaterReview;

import java.util.ArrayList;
import java.util.List;

public class ObjectDealerRaterReviews {

    public static  List<DealerRaterReview>  objectDealerRaterReviews(){
        List<DealerRaterReview> dealerRaterReviews = new ArrayList<>();
        DealerRaterReview dealerRaterReview = DealerRaterReview.builder()
                .title("Always have a great experience been going there for 6").
                review(" years and will always go back great service great attitude when you go in .").
                user("by johnkim2005webb").
                ratingStatic(50).
                customerService(50).
                friendliness(50).
                overallExperience(50).
                qualityOfWork(50).
                pricing(50).
                recommendDealer(true).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        dealerRaterReview = DealerRaterReview.builder()
                .title("Came from Longview to look at a new car. Jeannie was very").
                review(" bad polite and effective. She was able to get me exactly what I needed. ").
                user("by laurazieglertwins").
                ratingStatic(10).
                customerService(20).
                friendliness(30).
                overallExperience(10).
                qualityOfWork(20).
                pricing(30).
                recommendDealer(false).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        dealerRaterReview = DealerRaterReview.builder()
                .title("Very helpful made sure I got a newer truck Brandon was").
                review(" great with getting the deal done thanks to everyone for help super nice guys can’t wait to buy my first new vehicle next!!!!").
                user("by Bobbyphillips1981").
                ratingStatic(50).
                customerService(50).
                friendliness(50).
                overallExperience(50).
                qualityOfWork(50).
                pricing(50).
                recommendDealer(true).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        dealerRaterReview = DealerRaterReview.builder()
                .title("Extreme helpful!!").
                review(" great Best Best!!!!").
                user("by Johny Walker").
                ratingStatic(50).
                customerService(50).
                friendliness(50).
                overallExperience(50).
                qualityOfWork(50).
                pricing(50).
                recommendDealer(true).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        dealerRaterReview = DealerRaterReview.builder()
                .title("Extreme bad!!").
                review(" Poor!!!").
                user("by Nobody").
                ratingStatic(10).
                customerService(50).
                friendliness(20).
                overallExperience(20).
                qualityOfWork(30).
                pricing(30).
                recommendDealer(false).
                build();
        dealerRaterReviews.add(dealerRaterReview);
        return dealerRaterReviews;
    }
}
