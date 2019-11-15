<%--
  Created by IntelliJ IDEA.
  User: NotNaturalSelection
  Date: 14.11.2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="errors" class="beans.ErrorBean" scope="request"/>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            align-self: center;
            background-color: azure;
            font-size: 20px;
            border: 1px solid grey;
            border-radius: 7px;
            column-gap: 2px;
            column-rule: 7px grey;
            width: 50%;
            margin-left: 25%;
            margin-right: 25%;
            margin-top: 20%;
        }

        td, th {
            text-align: center;
            background-color: lightcoral;
            width: 5%;
            border-radius: 5px;
        }

        th {
            background-color: gold;
        }

        tr {
            background-color: lightcoral;
        }

        p {
            margin-left: 25%;
            margin-right: 25%;
            width: 50%;
            text-align: center;
            background-color: lightblue;
            border-radius: 5px;
            border: solid grey 1px;
        }
    </style>
</head>
<body>
<table>
    <%
        StringBuilder builder = new StringBuilder();
        builder.append("<tr><th>Errors</th></tr>");
        for (String error : errors.getErrors()) {
            builder.append("<tr>").append("<td>").append(error).append("</td>").append("</tr>");
        }
        out.write(builder.toString());
        errors.clearErrors();
    %>
</table>
<p><a type='text/html' href='https://github.com/NotNaturalSelection/Web2/' target='_blank'>Code on GitHub</a></p>
<p><a type='text/html' href='#' onclick="history.back()">Go back</a></p>
</body>
</html>
