$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AcceptanceCriteria.feature");
formatter.feature({
  "line": 1,
  "name": "Web Automation",
  "description": "\r\nBackground Provided a Task to add only lowest Price Item",
  "id": "web-automation",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Acceptance Criteria",
  "description": "",
  "id": "web-automation;acceptance-criteria",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "The user login in to Test Demo application",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I add four different products to my wish list",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I view my wishlist table",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I find total four selected items inthe wishlist",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "I search for lowest price product",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I am able to addthe lowest price item to my cart",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I am able to verify the item in my cart",
  "keyword": "Then "
});
formatter.match({
  "location": "AcceptanceCritera.open_browser_and_Enter_url()"
});
formatter.result({
  "duration": 13717123400,
  "status": "passed"
});
formatter.match({
  "location": "AcceptanceCritera.validate_add_fourProducts_InWishList()"
});
formatter.result({
  "duration": 10335613700,
  "status": "passed"
});
formatter.match({
  "location": "AcceptanceCritera.view_wishList_Table()"
});
formatter.result({
  "duration": 3134484300,
  "status": "passed"
});
formatter.match({
  "location": "AcceptanceCritera.validate_Number_ofItems_in_the_Cart()"
});
formatter.result({
  "duration": 59431100,
  "status": "passed"
});
formatter.match({
  "location": "AcceptanceCritera.find_lowest_Price_Item_InCart()"
});
formatter.result({
  "duration": 288672400,
  "status": "passed"
});
formatter.match({
  "location": "AcceptanceCritera.add_the_Lowest_Price_Item()"
});
formatter.result({
  "duration": 6704505600,
  "status": "passed"
});
formatter.match({
  "location": "AcceptanceCritera.verify_the_item_myCart()"
});
formatter.result({
  "duration": 1173248700,
  "status": "passed"
});
});