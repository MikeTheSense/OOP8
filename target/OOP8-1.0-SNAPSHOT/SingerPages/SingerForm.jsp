<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://res.cloudinary.com/teepublic/image/private/s--Y8hcLCvM--/c_crop,x_10,y_10/c_fit,h_1692/c_crop,g_north_west,h_1038,w_1038,x_130,y_317/l_upload:v1565806151:production:blanks:vdbwo35fw6qtflw9kezw/fl_layer_apply,g_north_west,x_-111,y_-111/b_rgb:55ff21/c_limit,f_jpg,h_630,q_90,w_630/v1566106989/production/designs/5644249_1.jpg" alt="" width="30" height="24">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn dropdown-toggle btn-lg" style="margin-left: 9px; margin-right: 9px" type="button" id="dropdownMenuButtonAuthors" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Authors
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="allsingers">All Authors</a>
                            <a class="dropdown-item" href="addsinger">Add new author</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn dropdown-toggle btn-lg" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Albums
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuAlbums">
                            <a class="dropdown-item" href="albums">All albums</a>
                            <a class="dropdown-item" href="addalbum">Add new album</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn dropdown-toggle btn-lg" style="margin-left: 9px;margin-right: 9px" type="button" id="dropdownMenuButton1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Compositions
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="compositions">All compositions</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn dropdown-toggle btn-lg" style="margin-left: 9px;margin-right: 9px" type="button" id="dropdownMenuButton3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Statistic
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="singertop">Top authors who have written the largest number of albums</a>
                            <a class="dropdown-item" href="topalbumwithsong">Top albums with the most songs </a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class = "container" style="width: 600px; margin-top: 150px;">
    <form class="row g-3"  action="insertsinger" method="post">
        <div class="col-md-12" id = "errorPresented" style="margin-bottom: 9px; color:#f52342">
            <small class="text-muted"></small>
        </div>
            <div class="col-md-12">
                <input type="text" style = "visibility: hidden" class="form-control" id="singerId" readonly name = "singerId"
                <c:choose>
                    <c:when test="${singer!=null}">
                       value = "<c:out value='${singer.getId()}'/>">
                    </c:when>
                    <c:otherwise>
                        value = "0">
                    </c:otherwise>
                </c:choose>
            </div>

        <div class="col-md-12">
            <label for="SingerName">Имя</label>
            <input type="text" class="form-control" id="SingerName" name = "SingerName"  value = "<c:out value='${singer.getName()}'/>">
        </div>
            <c:choose>
                <c:when test="${singer!=null}">
                    <script>
                        var input = document.getElementById("SingerName")
                        input.onblur = function()
                        {
                            lockSubmit(${singer.getId()})
                        };
                    </script>
                </c:when>
                <c:otherwise>
                    <script>
                        var input = document.getElementById("SingerName")
                        input.onblur = function()
                        {
                            lockSubmit(0);
                        };
                    </script>
                </c:otherwise>
            </c:choose>
        <div class="col-md-3" style="margin-top: 10px; margin-bottom: 10px">
            <button type="submit" class="btn btn-primary" id = "submit">Submit</button>
        </div>
        </form>
</div>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/singer.js"></script>
</body>
</html>
