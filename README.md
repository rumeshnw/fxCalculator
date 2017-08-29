#FX Calculator

This system uses Maven build system(version 3.1.1). In order to use this application or run any of the below commands, you should fulfill following requirements.

**Prerequisite**
 - Java 8 should be installed
 - Maven 3 or higher version & should be able to access mvn command from command line

**Commands**

1. Run unit tests: 
      - _mvn clean test_

2. Run test suite (unit and integration test): 
      - _mvn clean verify_

3. Run test suite with code coverage: 
      - _mvn clean verify jacoco:report_

4. Run system: 
      - mvn clean compile exec:java -Dexec.mainClass=au.com.rumesh.fxCalculator.ApplicationBootstrap
      
Once you execute the Run system command, system will boot up and show following option, 

`Enter currency conversion input as <base currency> <amount> in <term currency> or type EXIT to quit application.`

Then you can enter input as per the requested format & system will output the converted amount in following format.

`<base currency> <amount> = <term currency> <converted amount>`

Sample Input and output as follows,
        
     Input          ==> Output    

     AUD 100 in USD ==> AUD 100 = USD 83.71
     
     USD 200 in JPY ==> USD 200 = JPY 23990
     
If system cannot calculate the exchange rate for the give Base/Term currency combination, following message will be displayed.

`Unable to find Rate for <base currency>/<term currency>`
     
Test coverage report can be found under _/fxCalculator/target/site/jacoco/index.html_

**Assumptions**

1. Currency code input will be case sensitive. i.e: if user input currency codes as "aud 100 in usd", system will output invalid input format message.
2. System exit command "EXIT" is also case sensitive.