# DWP-Tech-Test

## Instructions on the test
Please complete the online test following the link: https://bpdts-test-app.herokuapp.com

The link contains a swagger spec for an API.

Using the language of your choice please build your own API which calls the API at 
https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either 
living in London, or whose current coordinates are within 50 miles (ca. 80 km) of London.

## How to run the tests
There are 2 ways to be able to run the test:
1. Run ./gradlew test in the terminal
2. Open the APITests in an IDE such as Intellij and click the run button

## How to run the service
There are 2 ways to be able to run this api:  
1. From the terminal run ./gradlew bootRun  
2. Run the file RestServiceApplication from an IDE such as Intellij

At this point SpringBoot should start up which will take a couple of seconds so please be patient

## How to use the api
Once the service is running you can now visit 3 different URL's all prepended with 
http://localhost:8080/

1. Hitting the base url will return all users
2. userid/{id}
3. city/{cityName}
4. userswithinfiftymilesoflondon

So for example going to http://localhost:8080/city/london and returns people who are listed as either 
living in London. However, this search is agnostic and is not limited to london but all of the cities
that are available from the consumed api (https://bpdts-test-app.herokuapp.com) - therefore another example
would be http://localhost:8080/city/kax

## Explanation for certain decisions
#### Why has this been over engineered instead of strictly following the exact instructions?

While the instructions were quite clear on what I had to do, I believed this extra functionality
would add value to the api without ever having to tax the system much harder.

The first being, if localhost is hit without any parameters then it should still return 
something, rather than nothing. Therefore, I decided to return all of the users in this instance 
as this shows what data the user is dealing with and makes it easier to consume the api.

The second feature added was the ability to search users by id. The reason for this was because it made it a 
much easier starting point in both terms of testing the application and developing for it. I left the feature in there 
because I believe being able to search any user via their id is very useful.


