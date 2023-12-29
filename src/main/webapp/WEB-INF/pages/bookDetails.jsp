<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Book Details">
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}${book.imagePath}" class="img-fluid" alt="Book Image">

            </div>
            <div class="col-md-6">
                <h2>${book.title}</h2>
                <p><strong>Author:</strong> ${book.author}</p>
                <p><strong>Genre:</strong> ${book.genre}</p>
                <p><strong>Description:</strong> ${book.description}</p>
            </div>
        </div>
    </div>
</t:pageTemplate>
