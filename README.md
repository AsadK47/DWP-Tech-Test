# DWP-Tech-Test

## Instructions for the tech test
Please complete the online test following the link: https://bpdts-test-app.herokuapp.com

The link contains a swagger spec for an API.

Using the language of your choice please build your own API which calls the API at 
https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either 
living in London, or whose current coordinates are within 50 miles (ca. 80 km) of London.

## Opening project in an IDE
If you are opening the project in an IDE such as intellij, please do so by opening the build.gradle file
which will enable intellij to open the files as a project and enable the correct structure, download dependencies, etc.

## How to run the unit tests
### For all of the following operations please make sure you have JDK 8 installed and gradle version 5 or above

There are 2 ways to be able to run the test:
1. Run ./gradlew test in the terminal
2. (Recommended) Open the APITests in an IDE such as Intellij and click the run button

## How to run the service
There are 2 ways to be able to run this api:  
1. From the terminal run ./gradlew bootRun  
2. Run the file RestServiceApplication from an IDE such as Intellij

At this point SpringBoot should start up which will take a couple of seconds so please be patient.

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
something, rather than nothing because it could potential trick users into thinking that 
the server was not up because they failed to read the instructions properly. 
Therefore, I decided to return all of the users in this instance as this shows what 
data the user is dealing with and makes it easier to consume the api.

The second feature added was the ability to search users by id. The reason for this was because it made it a 
much easier starting point in both terms of testing the application and developing for it. I left the feature in there 
because I believe being able to search any user via their id is very useful.

#### Did you follow a TDD approach?
If you look at my commit history you will see that I tried to follow a TDD approach which was quite successful.
However, eventually I started to run into unknown territory and had to write the functionality first and tests right after
to cover the scenario.

#### Is your code clean and maintainable?
I believe it is, however some more improvements could be made with further refactoring and refining. It is at the very least
quite maintainable in my opinion as I was easily able to add new routes and more functionality and an example of this being
adding an all user routes.

#### How could you improve this in the future?
I would consider changing the code to retrieve all london users within 50 miles as I believe this can be made more
sophisticated, even though my current solution works. My thinking on how I've implemented it is, finding out 
London latitude and checking the closest longitudes within reason. Thus, they could only vary between -1 and 1 which is
the basis for the algorithm used to calculate that.

Latitudes can help you get the vertical axis correct and so has a limited range, but the 
longitude can vary much more heavily and so finding a rough estimate for London was necessary to start with. 

#### Running the App itself without SpringBoot
There is a main method that will print out the first user and the rest of the contents in the instrucitons for this test.
This is easily removable, however I left it in there if a console version wanted to be printed without having to 
start SpringBoot and check via the api.
