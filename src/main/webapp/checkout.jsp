<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <!-- Common Head -->
    <jsp:include page="common/head.jsp" />
<body>
    <jsp:include page="common/nav.jsp" />

    <div id="app" class="container p-4">
        <div class="row">
            <div class="col-md-4 order-md-2 mb-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Your cart</span>
                    <span class="badge badge-secondary badge-pill">{{ getTotalCarts }}</span>
                </h4>

                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-condensed" v-for="cart in carts">
                        <div>
                            <h6 class="my-0">{{ cart.gName }}</h6>
                            <small class="text-muted">Size - {{ cart.gSize }} / Color - {{ cart.gColor }} / Amount - {{ cart.gAmount }}</small>
                        </div>
                        <span class="text-muted">&#8361; {{ cart.gPrice }}</span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between">
                        <span>Total (KRW)</span>
                        <strong>&#8361; {{ getTotalPrice }}</strong>
                    </li>
                </ul>
            </div>

            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Billing address</h4>

                <div class="mb-3">
                    <label for="billingFirstName">Full name</label>
                    <input type="text" class="form-control" id="billingFirstName" v-model="billing.name" required>
                </div>

                <div class="mb-3">
                    <label for="billingPhone">Phone</label>
                    <input type="tel" class="form-control" id="billingPhone" v-model="billing.phone" required>
                </div>

                <div class="mb-3">
                    <label for="billingPost">Zip</label>
                    <div class="row col-5">
                        <input type="text" class="form-control col-7 mr-1" id="billingPost" v-model="billing.post" required>
                        <button class="btn btn-primary col-4" @click="findAddress(true)">
                            <i class="material-icons">search</i>
                        </button>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="billingAddr1">Address</label>
                    <input type="text" class="form-control" id="billingAddr1" v-model="billing.addr1" required>
                </div>

                <div class="mb-3">
                    <label for="billingAddr2">Address 2</label>
                    <input type="text" class="form-control" id="billingAddr2" v-model="billing.addr2" required>
                </div>

                <hr class="mb-4">

                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="same-address" v-model="isSameClicked" @change="copyBilling">
                    <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
                </div>

                <hr class="mb-4">

                <form class="needs-validation" @submit="order">
                    <h4 class="mb-3">Shipping address</h4>

                    <div class="mb-3">
                        <label for="orderName">Full name</label>
                        <input type="text" class="form-control" id="orderName" v-model="shipping.orderName" required>
                    </div>

                    <div class="mb-3">
                        <label for="phone">Phone</label>
                        <input type="tel" class="form-control" id="phone" v-model="shipping.phone" required>
                    </div>

                    <div class="mb-3">
                        <label for="post">Zip</label>
                        <div class="row col-5">
                            <input type="text" class="form-control col-7 mr-1" id="post" v-model="shipping.post" required>
                            <button class="btn btn-primary col-4" @click="findAddress(false)">
                                <i class="material-icons">search</i>
                            </button>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="addr1">Address</label>
                        <input type="text" class="form-control" id="addr1" v-model="shipping.addr1" required>
                    </div>

                    <div class="mb-3">
                        <label for="addr2">Address 2</label>
                        <input type="text" class="form-control" id="addr2" v-model="shipping.addr2" required>
                    </div>

                    <hr class="mb-4">

                    <h4 class="mb-3">Payment</h4>

                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="credit" name="payMethod" type="radio" class="custom-control-input" v-model="payMethod" value="credit" required>
                            <label class="custom-control-label" for="credit">Credit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="debit" name="payMethod" type="radio" class="custom-control-input" v-model="payMethod" value="debit" required>
                            <label class="custom-control-label" for="debit">Debit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="paypal" name="payMethod" type="radio" class="custom-control-input" v-model="payMethod" value="paypal" required>
                            <label class="custom-control-label" for="paypal">PayPal</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-name">Name on card</label>
                            <input type="text" class="form-control" id="cc-name" placeholder="" required>
                            <small class="text-muted">Full name as displayed on card</small>
                            <div class="invalid-feedback">
                                Name on card is required
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cc-number">Credit card number</label>
                            <input type="text" class="form-control" id="cc-number" placeholder="" required>
                            <div class="invalid-feedback">
                                Credit card number is required
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 mb-3">
                            <label for="cc-expiration">Expiration</label>
                            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cc-cvv">CVV</label>
                            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                            <div class="invalid-feedback">
                                Security code required
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
                </form>
            </div>
        </div>
    </div>

    <script type="module">
        const carts = JSON.parse('<%= session.getAttribute("carts") %>');
        const member = JSON.parse('<%= request.getAttribute("member") %>');

        const app = new Vue({
            el: "#app",
            data: {
                carts: carts,
                isSameClicked: true,
                billing: {
                    ...member,
                    name: member.username,
                    phone: member.phone1 + "" + member.phone2 + "" + member.phone3
                },
                shipping: {
                    ...member,
                    orderName: member.username,
                    phone: member.phone1 + "" + member.phone2 + "" + member.phone3
                },
                payMethod: "credit"
            },
            computed: {
                getTotalCarts: function () {
                    return (this.carts || 0) && this.carts.length;
                },
                getTotalPrice: function () {
                    return this.carts.map(cart => cart.gPrice * cart.gAmount).reduce((acc, cur) => acc + cur);
                }
            },
            methods: {
                findAddress: function (isBilling) {
                    new daum.Postcode({
                        oncomplete: (data) => {
                            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                            let addr = ''; // 주소 변수
                            let extraAddr = ''; // 참고항목 변수

                            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                addr = data.roadAddress;
                            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                addr = data.jibunAddress;
                            }

                            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                            if (data.userSelectedType === 'R') {
                                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                                    extraAddr += data.bname;
                                }
                                // 건물명이 있고, 공동주택일 경우 추가한다.
                                if (data.buildingName !== '' && data.apartment === 'Y') {
                                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                }
                                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                if (extraAddr !== '') {
                                    extraAddr = ' (' + extraAddr + ')';
                                }

                            } else {
                                extraAddr = '';
                            }

                            if (isBilling) {
                                this.billing.post = data.zonecode;
                                this.billing.addr1 = addr;
                                this.billing.addr2 = extraAddr;
                            } else {
                                this.shipping.post = data.zonecode;
                                this.shipping.addr1 = addr;
                                this.shipping.addr2 = extraAddr;
                            }
                        }
                    }).open();
                },
                copyBilling: function () {
                    this.shipping = {}

                    if (this.isSameClicked) {
                        this.shipping = {
                            ...this.billing
                        }
                    }
                },
                order: function () {
                    const orders = carts.map(cart => {
                        return {
                            ...cart,
                            ...this.shipping,
                            payMethod: this.payMethod,
                        }
                    });

                    $.post({
                        url: "/order.do",
                        data: {
                            orders: JSON.stringify(orders)
                        },
                        complete: ({responseText}) => {
                            alert(responseText);
                            location.href = '/order.do';
                        }
                    })
                }
            }
        })
    </script>

    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/vue/2.6.12/vue.min.js"></script>
</body>
</html>
