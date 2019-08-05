<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
        <style>
          body {background-color: powderblue;}
          h1 {color: red;}
          p {color: blue;}
        </style>
    </head>
    <body>
    <center>
        <h1>Searching Results:</h1>
        <%
        String[] count = {"First", "Second", "Third"};

        List address = (List)request.getAttribute("address");
        List openHours = (List)request.getAttribute("openHours");
        List ratings = (List)request.getAttribute("ratings");
        List name = (List)request.getAttribute("name");

        if(address!=null){
            for(int i =0; i<address.size(); i++){
                out.println("<br>"+ count[i]+" result is :<br><br>"
                            +"Name:    "+name.get(i) +"<br>"
                            +"Address:    "+address.get(i) +"<br>"
                            +"Opening Hours:    ");
                for(String s: (List<String>)openHours.get(i)){
                    s = s.replaceAll("\u2013","-");
                    out.println(s+"<br>");
            }
                out.println("Ratings: "+ratings.get(i)+"<br>");
            }
        }else{
        out.println("Please input your address!");
    }
        %>
    </center>
    </body>
</html>