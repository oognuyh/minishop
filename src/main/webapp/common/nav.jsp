<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!-- brand -->
    <a class="navbar-brand" href="../index.jsp">MiniShop</a>

    <!-- right fixed menus -->
    <div class="d-flex order-lg-1 ml-auto pr-2">
        <span id="greeting" class="navbar-text">
            Hello, <span id="userName">Unknown</span>
        </span>
        <ul class="navbar-nav flex-row">
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="../signin.jsp"><i id="btn-login-out" class="material-icons">login</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="../mypage.jsp"><i class="material-icons">account_circle</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="../mycart.jsp"><i class="material-icons">shopping_cart</i></a>
            </li>
        </ul>
    </div>

    <!-- hamburger menu button -->
    <button class="navbar-toggler" style="border: none" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <i class="material-icons">menu</i>
    </button>

    <!-- left menus -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="../index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Outer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Top</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Dress</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Bottom</a>
            </li>
        </ul>
    </div>
</nav>