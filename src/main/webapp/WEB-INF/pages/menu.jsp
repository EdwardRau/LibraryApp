<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}" class="d-flex align-items-center mb-2 mb-lg-0 text-danger text-decoration-none">
                    LibraryApp
                </a></li>
                <li><a href="${pageContext.request.contextPath}/books" class="nav-link px-2 text-white">Books</a></li>
                <li><a href="${pageContext.request.contextPath}/users" class="nav-link px-2 text-white">Users</a></li>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
            </form>

            <div class="text-end">
                <button type="button" class="btn btn-outline-light me-2">Login</button>
                <button type="button" class="btn btn-warning">Sign-up</button>
            </div>
        </div>
    </div>
</header>