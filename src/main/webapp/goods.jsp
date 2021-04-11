<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="common/head.jsp" />
<body>
    <jsp:include page="common/nav.jsp" />

    <div id="app">
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-4 mb-4"  v-for="item in goods">
                    <div class="card border-0">
                        <div class="p-3">
                            <img class="img-fluid w-100"
                                 :src="'images/goods/' + item.gImage + '.gif'" alt="Sample">
                        </div>
                        <div class="text-center pt-4">
                            <h6>{{ item.gName }}</h6>
                            <p class="mb-2 text-muted text-uppercase small">{{ item.gCategory }}</p>
                            <hr>
                            <h6 class="mb-3">&#8361; {{ item.gPrice }}</h6>
                            <button type="button" @click="moveToDetail(item)" class="btn btn-primary btn-sm mr-1 mb-2">
                                <i class="material-icons pr-2" style="font-size: medium; vertical-align: middle; padding-bottom: 3px">info</i>
                                See Details
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="module">
        const goods = JSON.parse('<%= request.getAttribute("goods") %>');

        const app = new Vue({
            el: "#app",
            data: {
                goods: goods
            },
            methods: {
                moveToDetail: function (item) {
                    location.href = `/goods/detail?gCode=\${item.gCode}`;
                }
            }
        });
    </script>
    <script src="${pageContext.request.contextPath}/webjars/vue/2.6.12/vue.js"></script>
</body>
</html>
