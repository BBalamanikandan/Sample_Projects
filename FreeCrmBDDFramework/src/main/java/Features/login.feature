Feature: Login Feature


Scenario Outline: Login Test Scenario

Given user is already on Login Page
When title of login page is QA Fake
Then user enters "<username>" and "<password>"
Then user checked rember me
Then user clicks on login button
Then user get the secret string on home screen
Then Close the browser


Examples:
	| username | password |
	| admin  | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InN1cnlhQHNraWxsZW56YS5jb20iLCJpYXQiOjE2MTEzMTc2Njh9.HVMcJZZJh8_EAhB_mcSeGLfZZarE5ZgnEgbbAxSu99g |
 