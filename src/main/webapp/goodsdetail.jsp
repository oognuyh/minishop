<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="common/head.jsp" />
<body>
    <jsp:include page="common/nav.jsp" />

    <div id="app">
        <div class="container">
            <div class="row p-4">
                <div class="col-md-6 mb-4 mb-md-0">
                    <div class="row product-gallery mx-1">
                        <div class="col-12">
                            <img :src=`/images/goods/\${goods.gImage}.gif`
                                 class="img-fluid z-depth-1 w-100">
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <h6>{{ goods.gName }}</h6>
                    <p class="mb-2 text-muted text-uppercase small">{{ goods.gCategory }}</p>
                    <p><span class="mr-1"><strong>&#8361; {{ goods.gPrice }}</strong></span></p>
                    <p class="pt-1">{{ goods.gContent }}</p>
                    <div class="table-responsive">
                        <table class="table table-sm table-borderless mb-0">
                            <tbody>
                            <tr>
                                <th class="pl-0 w-25" scope="row"><strong>Code</strong></th>
                                <td>{{ goods.gCode }}</td>
                            </tr>
                            <tr>
                                <th class="pl-0 w-25" scope="row"><strong>Color</strong></th>
                                <td>Black / Navy / Ivory / White / Gray</td>
                            </tr>
                            <tr>
                                <th class="pl-0 w-25" scope="row"><strong>Delivery</strong></th>
                                <td>South korea Only</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <hr>
                    <div class="col-12 mb-2">
                        <table class="table table-sm table-borderless">
                            <tbody>
                                <tr>
                                    <td class="pl-0 pb-0">Quantity</td>
                                    <td class="pb-0">Select Color</td>
                                    <td class="pb-0">Select size</td>
                                </tr>
                                <tr>
                                    <td class="pl-0">
                                        <div class="col-8 def-number-input number-input safari_only mb-0 text-center">
                                            <button class="btn btn-light btn-sm" @click="increaseGAmount">
                                                <i class="material-icons">arrow_drop_up</i>
                                            </button>
                                            <input style="width: 45px; padding-left: 4px" min="0" name="quantity" :value="goods.gAmount">
                                            <button class="btn btn-light btn-sm" @click="decreaseGAmount">
                                                <i class="material-icons">arrow_drop_down</i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="mt-1">
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="black" name="color" v-model="goods.gColor" value="black">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="black">black</label>
                                            </div>
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="navy" name="color" v-model="goods.gColor" value="navy">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="navy">navy</label>
                                            </div>
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="ivory" name="color" v-model="goods.gColor" value="ivory">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="ivory">ivory</label>
                                            </div>
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="white" name="color" v-model="goods.gColor" value="white">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="white">white</label>
                                            </div>
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="gray" name="color" v-model="goods.gColor" value="gray">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="gray">gray</label>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="mt-1">
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="small" name="size" value="S"
                                                       v-model="goods.gSize">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="small">Small</label>
                                            </div>
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="medium" name="size" value="M"
                                                       v-model="goods.gSize">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="medium">Medium</label>
                                            </div>
                                            <div class="form-check form-check-inline pl-0">
                                                <input type="radio" class="form-check-input" id="large" name="size" value="L"
                                                       v-model="goods.gSize">
                                                <label class="form-check-label small text-uppercase card-link-secondary"
                                                       for="large">Large</label>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <button type="button" class="btn btn-primary btn-md mr-1 mb-2" @click="buy">
                        <i class="material-icons pr-2" style="vertical-align: middle; padding-bottom: 3px">paid</i>Buy
                    </button>
                    <button type="button" class="btn btn-light btn-md mr-1 mb-2" @click="addToCart">
                        <i class="material-icons pr-2" style="vertical-align: middle; padding-bottom: 3px">add_shopping_cart</i>Add to cart
                    </button>
                </div>
            </div>

        </div>
    </div>

    <script type="module">
        const goods = JSON.parse('<%= request.getAttribute("goods") %>');

        const app = new Vue({
            el: "#app",
            data: {
                goods: {
                    ...goods,
                    gAmount: 1,
                    gSize: "S",
                    gColor: "black"
                }
            },
            methods: {
                increaseGAmount: function () {
                    this.goods.gAmount++;
                },
                decreaseGAmount: function () {
                    this.goods.gAmount > 0 && this.goods.gAmount--;
                },
                addToCart: function () {
                    $.post({
                        url: "/mycart.do",
                        data: {
                            action: "add",
                            cart: JSON.stringify(this.goods),
                        },
                        complete: ({responseText}) => {
                            if (responseText === "signin")
                                location.replace("/signin.do");
                            else
                                alert(responseText);
                        }
                    })
                },
                buy: function () {
                    $.post({
                        url: "/checkout.do",
                        data: {
                            carts: JSON.stringify([this.goods])
                        },
                        complete: ({responseText}) => {
                            if (responseText === "success")
                                location.href = '/checkout.do';
                        }
                    })
                }
            }
        })
    </script>
    <script src="${pageContext.request.contextPath}/webjars/vue/2.6.12/vue.min.js"></script>
</body>
</html>
