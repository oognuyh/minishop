<%@ page import="com.example.minishop.model.Member" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="common/head.jsp" />
<body>
    <jsp:include page="common/nav.jsp" />

    <%
        Member member = (Member) session.getAttribute("member");
        Gson gson = new Gson();
    %>

    <div id="app">
        <div class="container">
            <div class="row py-5 mt-4 align-items-center justify-content-center">

                <!-- Registeration Form -->
                <div class="col-md-12 col-lg-8">
                    <form class="needs-validation" action="${pageContext.request.contextPath}/mypage.do" method="post"
                          oninput="passwdConfirmation.setCustomValidity(passwd.value !== passwdConfirmation.value ? '패스워드가 일치하지 않습니다' : '')">
                        <div class="row">
                            <!-- ID -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-white border-md border-right-0">
                                        <i class="material-icons text-muted">person</i>
                                    </span>
                                </div>
                                <input id="userid" type="text" name="userid" :value="member.userid" class="form-control bg-white border-left-0 border-md" readonly />
                            </div>

                            <!-- Password -->
                            <div class="input-group col-lg-6 mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-white border-md border-right-0">
                                        <i class="material-icons text-muted">lock</i>
                                    </span>
                                </div>
                                <input id="passwd" type="password" name="passwd" :value="member.passwd" placeholder="Password" class="form-control bg-white border-left-0 border-md" required />
                            </div>

                            <!-- Password Confirmation -->
                            <div class="input-group col-lg-6 mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-white border-md border-right-0">
                                        <i class="material-icons text-muted">lock</i>
                                    </span>
                                </div>
                                <input id="passwdConfirmation" type="password" name="passwdConfirmation" :value="member.passwd" placeholder="Confirm Password" class="form-control bg-white border-left-0 border-md" required />
                            </div>

                            <!-- Name -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">person</i>
                                        </span>
                                </div>
                                <input id="username" type="text" name="username" :value="member.username" class="form-control bg-white border-left-0 border-md" readonly />
                            </div>

                            <!-- Email Address -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">email</i>
                                        </span>
                                </div>
                                <input id="email1" type="text" name="email1" v-model="member.email1" class="form-control bg-white border-left-0 border-right-0 border-md" required />

                                <span class="input-group-text bg-white border-left-0 border-right-0 border-md">
                                        <i class="material-icons text-muted">alternate_email</i>
                                </span>

                                <select id="email2" name="email2" v-model="member.email2" class="custom-select form-control bg-white border-left-0 border-md h-100 font-weight-bold text-muted" required>
                                    <option value="naver.com">naver.com</option>
                                    <option value="gmail.com">gmail.com</option>
                                    <option value="daum.net">daum.net</option>
                                    <option value="nate.com">nate.com</option>
                                </select>
                            </div>

                            <!-- Phone Number -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-white border-md border-right-0">
                                        <i class="material-icons text-muted">phone</i>
                                    </span>
                                </div>
                                <select id="phone1" name="phone1" style="max-width: 80px" v-model="member.phone1" class="custom-select form-control bg-white border-left-0 border-md h-100 font-weight-bold text-muted">
                                    <option value="010">010</option>
                                    <option value="011">011</option>
                                    <option value="016">016</option>
                                    <option value="017">017</option>
                                </select>
                                <input id="phone2" type="tel" name="phone2" style="max-width: 100px" v-model="member.phone2" class="form-control bg-white border-md border-left-0 border-right-0 pl-3" required />
                                <span class="input-group-text bg-white border-left-0 border-right-0 border-md">
                                        <strong>-</strong>
                                </span>
                                <input id="phone3" type="tel" name="phone3" style="max-width: 100px" v-model="member.phone3" class="form-control bg-white border-md border-left-0 pl-3" required />
                            </div>

                            <!-- ZIP -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-white border-md border-right-0">
                                        <i class="material-icons text-muted">home</i>
                                    </span>
                                </div>
                                <input id="post" type="text" name="post" v-model="member.post" class="form-control col-3 bg-white border-md border-left-0 border-right-0 pl-3" required />
                                <a id="findAddress" class="text-decoration-none" href="#">
                                    <span class="input-group-text bg-white border-md border-left-0">
                                        <i class="material-icons text-muted">search</i>
                                    </span>
                                </a>
                            </div>

                            <!-- Address 1 -->
                            <div class="input-group col-lg-6 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md">
                                            <i class="material-icons text-muted">home</i>
                                        </span>
                                </div>
                                <input id="addr1" type="text" name="addr1" v-model="member.addr1" class="form-control bg-white border-md border-left-0 pl-3" required />
                            </div>

                            <!-- Address 2 -->
                            <div class="input-group col-lg-6 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md">
                                            <i class="material-icons text-muted">home</i>
                                        </span>
                                </div>
                                <input id="addr2" type="text" name="addr2" v-model="member.addr2" class="form-control bg-white border-md border-left-0 pl-3" required />
                            </div>

                            <!-- Submit Button -->
                            <div class="form-group col-lg-12 mx-auto mb-0">
                                <button type="submit" class="btn btn-primary btn-block py-2">
                                    <span class="font-weight-bold">Update your account</span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script type="module">
        const member = JSON.parse('<%= gson.toJson(member) %>');
        const app = new Vue({
            el: "#app",
            data: {
                member: member
            }
        });
    </script>
    <script src="webjars/vue/2.6.12/vue.js"></script>
</body>
</html>
