# tradeassignment

Write a Java program with all the JUNIT cases. TDD approach will be preferred. Time to complete the below assignment is 2 to 3 hrs.

Problem Statement
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
T1	1	CP-1	B1	20/05/2020	<today date>	N
T2	2	CP-2	B1	20/05/2021	<today date>	N
T2	1	CP-1	B1	20/05/2021	14/03/2015	N
T3	3	CP-3	B2	20/05/2014	<today date>	Y

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.


 
FAQ's
Can I use build management tool, is it mandatory?
Build management tool is not mandatory, but preference is that you should use any one of the build management tool (Gradle, Maven or Ant). This helps to build the code offline on interviewer’s machine, without worrying about any dependencies.
How can I share the code with interviewer?
1. In case you are going through offline code pairing session (asynchronous) i.e. you get the assignment from resourcing team 2 days before, then preferred option is to commit the code in your GitHub repository. Make the repository public in read only mode and share it with resourcing team. Interviewer will offline asses the code and at the time of discussion with discuss with you.
2. In case you are coming to office premises for code pairing round (synchronous) , then interviewer will be sitting with you for code pairing session.
In case I am not able to solve the whole problem, will I be rejected?
There is no right or wrong answer, and we are not looking for 100% right solution or perfect design. We are here to assess the thought process, the design pattern you use in your code, how you think about the problem statement etc. The only condition is that whatever the code you are committing should be in working condition either it contains all the feature or not is not mandatory.
Can I use open source framework (spring-boot) etc.?
Yes, it’s absolutely fine to use any open source framework, but please do ensure that with any framework you use proper build management tool (maven, Gradle) and it runs properly.
Do I really need to follow TDD?
The preferred option is to follow TDD. Your code should evolve with Red-Green-Refactor concept. In case you have not done TDD in past but writing test (JUnit) cases are mandatory.  What-ever the code you are writing should be properly covered with the Unit Test Cases or Integration test cases. Interviewer will be first checking the test cases to review the code.
Can I use any database or cache in this assignment?
You are open to use any database or cache, but remember it’s a test assignment not a project. Keeping technology stack simple will be helpful to build the code and will be self-explanatory.
Do I need to use CheckStyle or PMD in this assignment?
Absolutely your choice, there is no such preference. The code should be neat and self-documented with class names and method names.

How long I need to spend with Interviewer for reviewing or pairing the code?
If it’s an offline submission of the code Interviewer will spend 60 min to 90 min to review the code on phone or skype or any communication channel with the candidate
If its code pairing round Interviewer will be spending 90 to 120 min with the candidate and will be cutting code together.






