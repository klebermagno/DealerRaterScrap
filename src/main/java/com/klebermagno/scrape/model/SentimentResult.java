package com.klebermagno.scrape.model;

import com.klebermagno.scrape.model.SentimentClassification;
import lombok.Data;

@Data
public class SentimentResult {

    double sentimentScore;
    String sentimentType;
    SentimentClassification sentimentClass;

}