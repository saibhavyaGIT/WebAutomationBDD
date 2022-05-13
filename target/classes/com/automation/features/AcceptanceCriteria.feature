Feature: Web Automation

Background Provided a Task to add only lowest Price Item

Scenario: Acceptance Criteria
Given The user login in to Test Demo application
Given I add four different products to my wish list
When I view my wishlist table
Then I find total four selected items inthe wishlist
When I search for lowest price product
And I am able to addthe lowest price item to my cart
Then I am able to verify the item in my cart