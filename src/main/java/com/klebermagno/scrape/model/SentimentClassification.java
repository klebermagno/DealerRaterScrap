package com.klebermagno.scrape.model;

import lombok.Data;

@Data
public class SentimentClassification {

    double veryPositive;
    double positive;
    double neutral;
    double negative;
    double veryNegative;


}