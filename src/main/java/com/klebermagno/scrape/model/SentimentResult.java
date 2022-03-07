package com.klebermagno.scrape.model;

import lombok.Data;

/**
 * Represent sentiment result.
 */
@Data
public class SentimentResult {

    double sentimentScore;
    String sentimentType;
    SentimentClassification sentimentClass;

}