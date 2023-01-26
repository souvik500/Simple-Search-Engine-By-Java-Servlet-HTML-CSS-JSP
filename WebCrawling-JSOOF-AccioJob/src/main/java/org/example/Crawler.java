package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static java.sql.PreparedStatement.*;

public class Crawler {
    //Create HashSet for remove duplicate link
    public HashSet<String> urlHash;
    //limit 2 for visiting one page to another page and taking element
    public int maxDepth = 2;

    //Database Connection class, at first initialise as null
    public static Connection connection = null;

    Crawler()
    {
        //HashSet initialisation
        urlHash = new HashSet<>();
        //database connection class
        connection = DatabaseConnection_MySQL.getConnection();
    }

    public void getLinkTextandPage(String url, int depth)  {
        if(! urlHash.contains(url)){
            // Non duplicate URL print in Intellij Console
            if(urlHash.add(url)) System.out.println(url);

            try {
                Document document = Jsoup.connect(url).timeout(5000).get();
                //For not Overflow the text, that's why i have add 1000 limit
                String text = document.text().length() > 1000 ? document.text().substring(0,999) : document.text();
                String title = document.title();
                /*Instead of printing here(console) we are saving data in MySql database*/
                //System.out.println(title + "/n" + text);
                /*Here prepare data to send into `SearchPages` database with format like title then url, then text*/
                PreparedStatement preparedStatement = connection.prepareStatement("Insert into SearchPages values(?, ?, ?)");
                //First ? place, page title is coming
                preparedStatement.setString(1, title);
                //Second ? place, page Link is coming
                preparedStatement.setString(2, url);
                //Third ? place, page text is coming
                preparedStatement.setString(3, text);
                //After all the details set, we have to execute--> for final sending
                preparedStatement.executeUpdate();


                depth++;
                if (depth == 2) return;

                //page URL storing in Elements class under JSOUP Library
                Elements availableLinkOnPages = document.select("a[href]");
                for (Element currentLink : availableLinkOnPages) {
                    getLinkTextandPage(currentLink.attr("abs:href"), depth);
                }
            }
            catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /* we are taking example --> javaTPoint Website pages, link and text*/
    public static void main(String[] args) {
        Crawler cwl = new Crawler();
        cwl.getLinkTextandPage("https://www.javatpoint.com",0);
    }
}