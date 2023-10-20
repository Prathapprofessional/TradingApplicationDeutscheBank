# TradingApplicationDeutscheBank

#This is a springboot java application for Deutsche bank Trading. 

#Json signal files can be added more in required format for further increase of signals, please refer the howToCreateSignal_json_readme.md for more details.

#Json file defines the data and the actions to be performed. 

#Ideally Databases has to be used to store the data and retreive, here the json files are used to store and retreive data.

#The example local URL is http://localhost:8080/trading/signal/1 where the last integer refers to the signal. Here the scenario of signals 1 2 and 3 alone are considered and all other signals are considered as DEFAULT (As per given).

#Signal information was retreived from the JIRA tickets (Given).

#No front end display messages are popped up as per the requirement. Only the console performs and prints the data as per the code, from third party library-ALGO. This data is only read from the console for testing purpose.

#unit testing was performed and the result was successful.

