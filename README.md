
# Search Engine in java

The purpose of this project is to find any word very quickly. Here i use only one particular website and i search any keyword from that website's pages

First Algo for searching next link-->  `DFS(Depth First Search) or Depth Crawling Search`

Second Algo for finding specific keyWord: -->  

`Ranking Algorithm` --> 

Lets, n1 = First calculate langth of whole text, n2 = length of searching keyword replace to lower case

`Formula is --> (n1 - n2) / n2`
## Documentation

`JSOUP 1.15.3 library` --> It provides a very convenient API for fetching URLs and extracting and manipulating data,

Server: `Apache Tomcat`

Install `JDBC Connector/J` jar file attach inside of project Structure --> library --> click `+` sign --> upload into java section

`Javax.serlet-api.4.0.1` version used in this project

TechStack Used for `FrontEnd` --> HTML,CSS, JSP

TechStack Used for `BackEnd` --> Java Servlet

AtFirst paste any URL and take all pages title as well as page link and text and save to MySQL database.

Secondly any user search any keyword from that searchBar, It will find top 50 most occurance results with nevigate Link. and that link also save to another table in same database name, In my project 

Database Name: search

Table Name: searchPages

Table Name: history (for saving search history)
## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`API_KEY` Java Servlet api 4.0.1 `[Import to Maven]`

`ANOTHER_API_KEY`   `JSOUP 1.15.3`


## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  src/main/java/com.search --->backend searching and crawling
  src/main/java/webapp ----> frontend 
```

Install dependencies

```bash
  JDBC Connector/J 

  Jsoup 1.15.3
```

Start the server

```bash
  Apecha Tomcat 9 Edit Configration Tab searching Smart Tomcat and add there
```

