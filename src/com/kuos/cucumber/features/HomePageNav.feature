Feature: Homepage Navigation
All Categories button on Home Page should list all categories
Swiping left goes to Just for You page
Search function produces valid results
Able to add flash deal product to cart

Scenario: User views All Categories
Given User is on Home Page
When the user taps the All Categories button
Then a list of all categories is displayed

Scenario: User adds flash deal item to cart 
Given User is on Home Page
When the user taps Flash Deals
And selects the first product
And fills desired conditions
And taps Add to Cart
Then Item is added to cart

Scenario Outline: User searches for product 
Given User is on home page
When the user searches for "<product>"
Then a list of relevant search results is displayed

Examples: 
|	product				|
|	makeup 				|
|	womens dress  |
|	electronics   |
      
Scenario: User swipes to Just for You page
Given User is on Home Page
When the user swipes left
Then the Just for You Page is displayed
