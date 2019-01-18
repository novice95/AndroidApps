# Quiz App

A Quiz app, where we can see a list of questions. By clicking on a question, details of question is shown.  
The quiz has 30 mcq questions.  
Each question has two answers true or false.  
RecyclerView is used to show the list of questions. Fragments are used to show detailed question part.  
In the question detail part, there is an option to save the answer. Save the answer to the database.  
Quiz questions are saved in a sqlite database on click of Save button.  
When you click on submit, the database is exported to a csv file and uploaded to a webserver.  
Async task is used for uploading csv file.  
