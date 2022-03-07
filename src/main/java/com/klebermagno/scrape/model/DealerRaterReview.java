package com.klebermagno.scrape.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class DealerRaterReview {

    public String title;
    public String review;
    public String user;
    public int ratingStatic;
    public int customerService;
    public int friendliness;
    public int overallExperience;
    public int qualityOfWork;
    public int pricing;
    public Boolean recommendDealer;

    public DealerRaterReview(){

    }
    @Override
    public String toString() {
        return "DealerRaterReview{" +
                "\n title='" + title + '\'' +
                ",\n review='" + review + '\'' +
                ",\n user='" + user + '\'' +
                ",\n ratingStatic=" + ratingStatic +
                ",\n customerService=" + customerService +
                ",\n friendliness=" + friendliness +
                ",\n overallExperience=" + overallExperience +
                ",\n qualityOfWork=" + qualityOfWork +
                ",\n pricing=" + pricing +
                ",\n recommendDealer=" + recommendDealer +
                "}\n\n\n";
    }
}
