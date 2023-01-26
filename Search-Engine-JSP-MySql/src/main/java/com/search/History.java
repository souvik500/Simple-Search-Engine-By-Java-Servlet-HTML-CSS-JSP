package com.search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            //Connect with database
            Connection connection = DatabaseConnection.getConnection();

            /* Here History Table fetch data from database show in frontend page, that's why we use executeQuery*/
            /*If I write Query for delete data from history --> executeUpdate(Truncate table history)*/
            ResultSet resultSet = connection.createStatement().executeQuery("Select * from history");


            ArrayList<HistoryResult> results = new ArrayList<>();

            while(resultSet.next()){

                //In history database table, column name is keyword and pageLink
                String keyword = resultSet.getString("keyword");
                String link = resultSet.getString("pageLink");
                //create HistoryResult object with parameter and that parameter goes to HistoryResult.java class
                HistoryResult historyResult = new HistoryResult(keyword, link);

//                historyResult.setKeyword(resultSet.getString("keyword"));
//                historyResult.setLink(resultSet.getString("pageLink"));

                results.add(historyResult); //add to arraylist of each keyword as well as url;
            }


            //That arraylist is sending to jsp file
            request.setAttribute("results", results);
            //writer created for displaying history.jsp--> Go to frontend part for client
            request.getRequestDispatcher("/history.jsp").forward(request, response);
        }

        catch (SQLException | IOException | ServletException sqlException){
            sqlException.printStackTrace();
        }

    }
}









/*=======================Please Ignore That Part Only======================================*/
/*
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/com.search.MyServlet")
public class com.search.MyServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, @NotNull HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("This is my Own Servlet");
    }
}
*/