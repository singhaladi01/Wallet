Feature: WalletHub technical assignment

@MainTest
Scenario Outline: login to WalletHub to post review

Given user is already logged in with "<username>" and "<password>" to WalletHub
When user hovers on fourth star
Then starts should get lit up
When user clicks on fifth star
And select policy as Health 
And submit review
Then user should see a confirmation screen
And user should see review in "<username>" profile


Examples:
	|username| password|
	|singhaladi01@gmail.com|WalletHub@1|

