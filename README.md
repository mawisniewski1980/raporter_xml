
# Raporter XML example
## Jar to create html 'email.html' file raport with data from xml files junitHtmlReport 

# How to run - command line
## java -jar Raporter.jar parametr, parametr e.g. project\src\main\resources\test-results\test
## java -jar Raporter.jar C:\project\src\main\resources\test-results\test

# Result
## You find one 'email.html' file in C:\project\src\main\resources\test-results\raport

# For what?
# I used this raport file on Jenkins - Editable Email Notification plugin to add emdded content in email. e.g. 
## ${FILE,path="/raport/email.html"}

