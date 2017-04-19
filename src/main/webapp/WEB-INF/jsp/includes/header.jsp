<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%--JQUERY--%>
        <c:url value="/webjars/jquery/2.1.4/jquery.min.js" var="jquery" />
            <script src="${jquery}"></script>

        <%--BOOTSTRAP--%>
        <c:url value="/webjars/bootstrap/3.3.4/js/bootstrap.min.js" var="bootstrapJS" />
            <script src="${bootstrapJS}"></script>

        <c:url value="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" var="bootstrapCSS" />
            <link href="${bootstrapCSS}" rel="stylesheet" media="screen" />

        <%--<c:url value="/static/css/bootswatch_paper.css" var="bootstrapCSS" />--%>
            <%--<link href="${bootstrapCSS}" rel="stylesheet" media="screen" />--%>

            <%--CUSTOM CSS--%>
            <c:url value="/static/CSS/custom.css" var="bootstrapCUSTOM" />
            <link href="${bootstrapCUSTOM}" rel="stylesheet" media="screen">

        <title>Squees Generator</title>

            <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
            <%--<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">--%>
            <%--<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>--%>
            <%--<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>--%>

    </head>
    <body>


