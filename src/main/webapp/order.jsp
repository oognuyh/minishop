<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="common/head.jsp" />
<body>
    <jsp:include page="common/nav.jsp" />

    <div id="app" class="container">
        <div class="mb-3">
            <div class="pt-4 wish-list">
                <h5 class="mb-4">
                    Order History (<span>{{ orders.length }}</span> items)
                </h5>

                <div class="row mb-4" v-for="(order, idx) in orders">
                    <div class="col-md-5 col-lg-3 col-xl-3">
                        <div class="mb-md-0 p-3">
                            <img class="img-fluid"
                                 :src=`/images/goods/\${order.gImage}.gif` alt="Sample">
                        </div>
                    </div>

                    <div class="col-md-7 col-lg-9 col-xl-9">
                        <div>
                            <div class="d-flex justify-content-between">
                                <div>
                                    <h5>{{ order.gName }}</h5>
                                    <p class="mb-2 text-muted text-uppercase small">Color: {{ order.gColor }}</p>
                                    <p class="mb-3 text-muted text-uppercase small">Size: {{ order.gSize }}</p>
                                    <p class="mb-3 text-muted text-uppercase small">Size: {{ order.gAmount }}</p>
                                    <p class="mb-3 text-muted text-uppercase small">Size: {{ order.orderday }}</p>
                                    <p class="mb-3 text-muted text-uppercase small">Size: {{ order.payMethod }}</p>
                                </div>
                            </div>
                            <p class="mb-0"><span><strong id="summary">&#8361; {{ order.gPrice * order.gAmount }}</strong></span></p>

                        </div>

                    </div>

                    <hr class="col-10" />
                </div>
            </div>
        </div>
    </div>

    <script type="module">
        const orders = JSON.parse('<%= request.getAttribute("orders") %>');

        const app = new Vue({
            el: "#app",
            data: {
                orders: orders
            }
        });
    </script>
    <script src="webjars/vue/2.6.12/vue.js"></script>
</body>
</html>
