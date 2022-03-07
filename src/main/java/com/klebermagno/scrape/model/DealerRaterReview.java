package com.klebermagno.scrape.model;

import lombok.Data;

@Data
public class DealerRaterReview {
    enum REASON_FOR_VISIT { NEW , USED , SERVICE};

    public String title;
    public String review;
    public String user;
    public int ratingStatic;
    public int customerService;
    public int friendliness;
    public int overallExperience;
    public int qualityOfWork;
    public int pricing;
    public boolean recommendDealer;
    public REASON_FOR_VISIT reasonForVisit;
    public String date;
    public String helpfulCount;

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
                ",\n reasonForVisit=" + reasonForVisit +
                ",\n date='" + date + '\'' +
                ",\n helpfulCount='" + helpfulCount + '\'' +
                "}\n\n\n";
    }
}
