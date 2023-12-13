<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Books">
    <div class="container">
        <main>
            <div class="py-5 text-center">
                <h2>Add Book</h2>
                <p class="lead">In order to add a new book please fill the fields below</p>
            </div>
            <div class="col-md col-lg">
                <form class="needs-validation" novalidate="" method="POST"
                      action="${pageContext.request.contextPath}/addBook">

                    <div class="col-12">
                        <label for="title" class="form-label">Title</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="title" name="title"
                                   placeholder="The Cherry guys" required="">
                            <div class="invalid-feedback">
                                A book title is mandatory!
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <label for="author" class="form-label">Author</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="author" name="author" placeholder="Jhon Branch"
                                   required="">
                            <div class="invalid-feedback">
                                An author is required.
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <label for="genre" class="form-label">Genre</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="genre" name="genre" placeholder="Si-Fi"
                                   required="">
                            <div class="invalid-feedback">
                                A genre is required.
                            </div>
                        </div>
                    </div>
                    <hr class="my-4">
                    <button class="w-100 btn btn-primary btn-lg" type="submit">Save</button>
                </form>
            </div>
        </main>
    </div>

</t:pageTemplate>