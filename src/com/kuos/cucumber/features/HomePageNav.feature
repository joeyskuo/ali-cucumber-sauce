Feature: Homepage Navigation
All Categories button on Home Page should list all categories
Swiping left goes to Just for You page
Search function produces valid results
Able to add flash deal product to cart

Scenario: User views All Categories
Given User starts app
When the user taps the All Categories button
Then a list of all categories is displayed

Scenario Outline: User searches for product 
Given User is on Home Page
When the user searches for "<product>"
Then a list of relevant search results is displayed

Examples: 
|	product				|
|	smartphone 				|

Scenario: User adds flash deal item to cart 
Given User is on Home Page
When the user taps Flash Deals
And selects the first product
And fills desired conditions
And taps Add to Cart
Then Item is added to cart


      
