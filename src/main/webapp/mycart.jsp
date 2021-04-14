<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="common/head.jsp" />
<body>
    <jsp:include page="common/nav.jsp" />
    <%
        if (request.getAttribute("carts") == null)
            response.sendRedirect("mycart.do");
    %>
    <div id="app">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="mb-3">
                        <div class="pt-4 wish-list">

                            <h5 class="mb-4">
                                Cart (<span>{{ carts.length }}</span> items)
                            </h5>

                            <div class="row mb-4" v-for="(cart, idx) in carts">
                                <div class="col-md-5 col-lg-3 col-xl-3">
                                    <div class="mb-md-0 p-3">
                                        <img class="img-fluid w-100"
                                             :src=`/images/goods/\${cart.gImage}.gif` alt="Sample">
                                    </div>
                                </div>

                                <div class="col-md-7 col-lg-9 col-xl-9">
                                    <div>
                                        <div class="d-flex justify-content-between">
                                            <div>
                                                <h5>{{ cart.gName }}</h5>
                                                <p class="mb-2 text-muted text-uppercase small">Color: {{ cart.gColor }}</p>
                                                <p class="mb-3 text-muted text-uppercase small">Size: {{ cart.gSize }}</p>
                                            </div>
                                            <div>
                                                <div class="def-number-input number-input safari_only mb-0 w-100">
                                                    <button @click="increaseGAmount(idx)" class="btn btn-light btn-sm">
                                                        <i class="material-icons">arrow_drop_up</i>
                                                    </button>
                                                    <input style="width: 50px" min="1" name="quantity" :value="cart.gAmount">
                                                    <button @click="decreaseGAmount(idx)" class="btn btn-light btn-sm">
                                                        <i class="material-icons">arrow_drop_down</i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div>
                                                <button class="btn btn-danger btn-sm text-uppercase mr-3" @click="removeCart(cart)">
                                                    <i class="material-icons" style="vertical-align: middle; padding-bottom: 3px">delete</i> Remove item </button>
                                            </div>
                                            <p class="mb-0"><span><strong id="summary">&#8361; {{ cart.gPrice }}</strong></span></p>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <hr class="mb-4">

                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="pt-4">

                            <h5 class="mb-4">We accept</h5>

                            <img class="mr-2" width="45px"
                                 src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                                 alt="Visa">
                            <img class="mr-2" width="45px"
                                 src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                                 alt="American Express">
                            <img class="mr-2" width="45px"
                                 src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                                 alt="Mastercard">
                            <img class="mr-2" width="45px"
                                 src="https://mdbootstrap.com/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.png"
                                 alt="PayPal acceptance mark">
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="mb-3">
                        <div class="pt-4">

                            <h5 class="mb-3">The total amount of</h5>

                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                    Temporary amount
                                    <span>&#8361; {{ getTotalAmount }}</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Shipping
                                    <span>Free</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                    <div>
                                        <strong>The total amount of</strong>
                                        <strong>
                                            <p class="mb-0">(including VAT)</p>
                                        </strong>
                                    </div>
                                    <span><strong>&#8361; {{ getTotalAmount }}</strong></span>
                                </li>
                            </ul>

                            <button type="button" class="btn btn-primary btn-block text-uppercase" @click="moveToCheckOut">
                                <strong>go to checkout</strong>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="module">
        const carts = JSON.parse('<%= request.getAttribute("carts") %>')

        const app = new Vue({
            el: "#app",
            data: {
                carts: carts
            },
            computed: {
                getTotalAmount: function () {
                    return this.carts.length === 0 ? 0 : this.carts.map((cart) => cart.gPrice * cart.gAmount).reduce((acc, cur) => acc + cur);
                }
            },
            methods: { // TODO - should update database
                increaseGAmount: function (idx) {
                    this.carts[idx].gAmount++;
                    $.post({
                        url: "/mycart.do",
                        data: {
                            cart: JSON.stringify(this.carts[idx]),
                            action: "update"
                        },
                        completed: ({responseText}) => {
                            alert(responseText);
                        }
                    })
                },
                decreaseGAmount: function (idx) {
                    if (this.carts[idx].gAmount >  1) {
                        this.carts[idx].gAmount--;
                        $.post({
                            url: "/mycart.do",
                            data: {
                                cart: JSON.stringify(this.carts[idx]),
                                action: "update"
                            },
                            completed: ({responseText}) => {
                                alert(responseText);
                            }
                        })
                    }
                },
                removeCart: function (cart) {
                    this.carts = this.carts.filter((c) => c !== cart);
                    $.post({
                        url: "/mycart.do",
                        data: {
                            cart: JSON.stringify(cart),
                            action: "delete"
                        },
                        completed: ({responseText}) => {
                            alert(responseText);
                        }
                    })
                },
                moveToCheckOut: function () {
                    if (this.carts.length > 0) {
                        $.post({
                            url: "/checkout.do",
                            data: {
                                carts: JSON.stringify(carts)
                            },
                            complete: ({responseText}) => {
                                if (responseText === "success")
                                    location.href = "/checkout.do";
                            }
                        })
                    } else {
                        alert("Your cart is empty.")
                    }
                }
            }
        })
    </script>
    <script src="${pageContext.request.contextPath}/webjars/vue/2.6.12/vue.min.js"></script>
</body>
</html>
