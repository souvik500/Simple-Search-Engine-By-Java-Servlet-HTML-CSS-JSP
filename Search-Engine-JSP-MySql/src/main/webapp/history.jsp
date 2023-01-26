<%@page import = "java.util.ArrayList"%>
<%@page import = "com.search.HistoryResult"%>

<html>
<head>
<link rel = "stylesheet" type = "text/css" href = "styles.css">
</head>
    <body>
        <form action = "History">
            <input type = "text" name = "keyword">
            <button type = "submit">Search</button>
        </form>

        <!-- In Styles.css resultTable class doing everything in middle of page -->
        <div class = "resultTable">

        <table border = 2>
            <tr>
                <td>keyword</td>
                <td>Link</td>
            </tr>
            <%
                //Get results from history servlet
                ArrayList<HistoryResult> results = (ArrayList<HistoryResult>)request.getAttribute("results");

                //Iterate for every data present in results array
                for(HistoryResult result:results)
                {         //===========loop starting hear only==========
            %>
                <tr>
                    <td><%out.println(result.getKeyword());%></td>
                    <td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
                </tr>
            <%
                }        //=============loop finish here=================
            %>
        </table>

        </div>
    </body>
</html>