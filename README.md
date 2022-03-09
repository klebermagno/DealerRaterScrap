# Dealer Rater Scrape
The KGB has noticed a resurgence of overly excited reviews for the McKaig Chevrolet Buick, a dealership they have planted in the United States. In order to avoid attracting unwanted attention, you've been enlisted to scrape reviews for this dealership from DealerRater.com and uncover the top three worst offenders of these overly positive endorsements.

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
