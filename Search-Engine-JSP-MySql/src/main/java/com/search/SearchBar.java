package com.search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//WbServlet name should be similar with class name otherwise its compile different files
@WebServlet("/SearchBar")

public class SearchBar extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //request from user --> whatever type in searchBar like Java, React, MongoDB
        String keyword = request.getParameter("keyword");
        //Print that keyword in Console
        System.out.println(keyword);

        try{
            //connect with MySQL database
            Connection connection = DatabaseConnection.getConnection();

            //add keyword or save keyword and url, into history table to MySQL -->that's why we use prepareStatement class
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?, ?)");
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, "http://localhost:8080/Search-Engine-JSP-MySql/SearchBar?keyword="+keyword);
            preparedStatement.executeUpdate();

            //Execute Query from search table for top 50 descending order results
            ResultSet resultSet = connection.createStatement().executeQuery("select page_title, page_link, (length(lower(page_text))-length(replace(lower(page_text), '" + keyword + "', '')))/length('" + keyword + "') as countoccurence from SearchPages order by countoccurence desc limit 50;");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            while (resultSet.next()){
                SearchResult searchResult = new SearchResult();
                searchResult.setPageTitle(resultSet.getString("page_title"));
                searchResult.setPageLink(resultSet.getString("page_link"));
                results.add(searchResult);
            }
            //display result in console
            for (SearchResult result:results){
                System.out.println(result.getPageLink()+" "+result.getPageTitle()+"\n");
            }
            request.setAttribute("results", results);
            //Forward the request with respective response to Search.jsp --->frontend Part
            request.getRequestDispatcher("/Search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }

        catch (SQLException | ServletException | IOException sqlException){
            sqlException.printStackTrace();
        }
    }
}





/* ========================Please Ignore Down Portion========================*/
/*
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/com.search.SearchBar")
public class com.search.SearchBar extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        try {
            Connection connection = com.search.DatabaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select page_title, (length(lower(page_text)) - length(replace(lower(page_text), '\"+keyword+\"' ,'')) )/ length('\"+keyword+\"') as countJava from search_pages order by countJava DESC limit 60;");

            ArrayList<com.search.SearchResult> result = new ArrayList<>();

            while (resultSet.next()) {
                com.search.SearchResult searchResult = new com.search.SearchResult();
                searchResult.setPageLink(resultSet.getString("page_link"));
                searchResult.setPageTitle(resultSet.getString("page_title"));
                result.add(searchResult);
            }

            for(com.search.SearchResult searchResult : result)
                System.out.println(searchResult.getPageLink()+" "+searchResult.getPageTitle()+"\n");

            request.setAttribute("Results: " , result);
            request.getRequestDispatcher("/Search.jsp").forward(request, response);
        }
        catch (SQLException e) {e.printStackTrace();}
     }
}
*/