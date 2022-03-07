package com.klebermagno.scrape.crawler;


public enum DealerRaterEnum {

    REVIEW_SECTION(".//div[(@id='reviewsSection')]"),
    REVIEW_WARPPER("//div[contains(@class,'review-wrapper')]"),
    REVIEW_TITLE(".//span[@class='review-title bolder font-18 italic']"),
    REVIEW_WHOLE(".//span[@class='review-whole display-none']"),
    REVIEW_USER(".//span[@class='italic font-16 bolder notranslate']"),
    RATING_STATIC( "//div[contains(@class,'rating-static hidden-xs rating')]"),
    RATINGS("//div[contains(@class,'table width-100 pad-left-none pad-right-none margin-bottom-md')]");
    public final String label;

    private DealerRaterEnum(String label) {
        this.label = label;
    }

}
