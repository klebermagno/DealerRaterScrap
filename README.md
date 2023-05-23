# Dealer Rater Scrape
The Dealer Rater Crawler is a robust and highly sophisticated back-end system, primarily built with Java 8. This application serves as a targeted web crawler for car dealer reviews, effectively scraping the first five pages of customer feedback.

The crawler is designed with advanced analytics capabilities, recognizing and highlighting the top three most "overly positive" reviews based on the severity of their endorsement. These reviews are then neatly displayed in the console, providing users with immediate, crucial insights.

The system has been crafted with a keen emphasis on testing, using JUnit for comprehensive unit testing to guarantee reliable operation and robustness. To enhance the crawling capabilities, the project employs HtmlUnit, allowing for efficient page navigation and data extraction.

One of the standout features is the integration of Stanford NLP for text sentiment analysis. This sophisticated language processing tool enables the crawler to accurately assess and categorize the sentiment behind each review, thus ensuring the "overly positive" reviews are correctly identified.

The Dealer Rater Crawler offers a unique blend of web crawling and sentiment analysis, presenting a useful tool for car dealers and consumers alike.


## Your mission, should you choose to accept it, is to write a tool that:

1 scrapes the first five pages of reviews

2 identifies the top three most “overly positive” endorsements (using criteria of your choosing, documented in the README)

3 outputs these three reviews to the console, in order of severity



## Analytics Criteria


* Use Stanford NLP to analyse text sentiment, this value is from 0 to 4, so have weight 10.

* Sum customer service score, friendliness, pricing quality of work, rating static and overall experience with weight 1.

* If user don't recommend dealer score are multiply to -1 to ignore this review.

* All score are added.

* Reviews with score over 290 is considered overly positive.


# How setup the project:

1. To build the project need have installed docker, docker-compose,  openjdk 8 and maven.
   Run maven to build and package the project:

       mvn clean install package

2. To create the docker image:

       docker-compose build

3. To run docker container:

       docker-compose up 

4. See the result in console.

# How to test

To run all test you need.
   Run maven to build and package the project:

       mvn clean test
