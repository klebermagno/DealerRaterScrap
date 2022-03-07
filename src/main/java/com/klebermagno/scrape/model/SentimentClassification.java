package com.klebermagno.scrape.model;

import lombok.Data;

/**
 * Represent Sentiment Classiftion.
 */
@Data
public class SentimentClassification {

    double veryPositive;
    double positive;
    double neutral;
    double negative;
    double veryNegative;


}