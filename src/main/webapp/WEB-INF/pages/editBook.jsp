<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Edit Book">
    <h1>Edit Book</h1>
    <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/EditBook">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="" value="${book.title}" required>
                <div class="invalid-feedback">
                    Title is Required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="author">Author</label>
                <input type="text" class="form-control" id="author" name="author" placeholder="" value="${book.author}" required>
                <div class="invalid-feedback">
                    Author is Required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="genre">Genre</label>
                <input type="text" class="form-control" id="genre" name="genre" placeholder="" value="${book.genre}" required>
                <div class="invalid-feedback">
                    Genre is Required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description">Description</label>
                <input type="text" class="form-control" id="description" name="description" placeholder="" value="${book.description}" required>
                <div class="invalid-feedback">
                    Description is Required
                </div>
            </div>
        </div>
        <div class="col-12">
            <label for="image" class="form-label">Book Image</label>
            <div class="input-group">
                <input type="file" class="form-control" id="image" name="image" accept="image/*" required="">
                <div class="invalid-feedback">
                    An image is required.
                </div>
            </div>
        </div>

        <button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
        <hr class="mb-4">
        <input type="hidden" name="book_id" value="${book.id}" />
    </form>

</t:pageTemplate>