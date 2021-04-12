<%@ page import="com.example.minishop.model.Member" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!-- brand -->
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">MiniShop</a>

    <!-- right fixed menus -->
    <div class="d-flex order-lg-1 ml-auto pr-2">
        <span id="greeting" class="navbar-text">
            Hello, <span id="userName">Unknown</span>
        </span>
        <ul class="navbar-nav flex-row">
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/signin.do"><i id="btnSiginInOut" class="material-icons">login</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/mypage.do"><i class="material-icons">account_circle</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/order.do"><i class="material-icons">receipt</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/mycart.do"><i class="material-icons">shopping_cart</i></a>
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
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/goods.do?gCategory=outer">Outer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/goods.do?gCategory=top">Top</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/goods.do?gCategory=dress">Dress</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/goods.do?gCategory=bottom">Bottom</a>
            </li>
        </ul>
    </div>
</nav>

<!-- Check -->
<script>
    $(() => {
        <% Member member = (Member) session.getAttribute("member"); %>
        <% if (member != null) { %>
        $("#userName").text("<%= member.getUsername() %>");
        $("#btnSiginInOut").text("logout").parent().attr("href", "/signout.do");
        <% } %>
    })
</script>