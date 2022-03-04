# jacoco-bug
There's a bug crawling around in here.

Just run `./gradlew clean build` and then open the  
`build/reports/jacoco/index.html` file in the browser.  
Navigate to the `RedisCrudRepositoryImpl` class coverage results.  
You will see the "1 of 2 branches missed" on the return statement.
