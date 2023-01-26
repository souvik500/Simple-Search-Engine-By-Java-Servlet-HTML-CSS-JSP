<%@page import = "java.util.ArrayList"%>
<%@page import = "com.search.SearchResult"%>

<html>
<head>
<link rel = "stylesheet" type = "text/css" href = "styles.css">
</head>
    <body>
        <form action = "SearchBar">
            <input type = "text" name = "keyword">
            <button type = "submit">Search</button>
        </form>
        <div class = "resultTable">

        <table border = 2>
            <tr>
                <td>Title</td>
                <td>Link</td>
            </tr>
            <%
                //Get results from search servlet
                ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");

                //Iterate for every data present in results array
                for(SearchResult result:results)
                {
            %>
                <tr>
                    <!--response in search page, First showing Page Title-->
                    <td><%out.println(result.getPageTitle());%></td>

                    <!--response in search page, second showing Page Link clickable which is actually going main page-->
                    <td><a href="<%out.println(result.getPageLink());%>"><%out.println(result.getPageLink());%></a></td>
                </tr>
            <%
                }
            %>
        </table>

        </div>
    </body>
</html>