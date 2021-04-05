<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<!-- Common Head -->
<jsp:include page="common/head.jsp" />
<body>
<jsp:include page="common/nav.jsp" />

<div class="container">
    <div class="row py-5 mt-4 align-items-center">
        <div class="col-md-5 pr-lg-5 mb-5 mb-md-0">
            <img src="https://res.cloudinary.com/mhmd/image/upload/v1569543678/form_d9sh6m.svg" alt="" class="img-fluid mb-3 d-none d-md-block">
            <h1>Create an Account</h1>
            <p class="font-italic text-muted mb-0">Create a minimal registeration page using Bootstrap 4 HTML form elements.</p>
            <p class="font-italic text-muted">Snippet By <a href="https://bootstrapious.com" class="text-muted">
                <u>Bootstrapious</u></a>
            </p>
        </div>

        <!-- Registeration Form -->
        <div class="col-md-7 col-lg-6 ml-auto">
            <form action="#" method="post">
                <div class="row">

                    <!-- ID -->
                    <div class="input-group col-lg-12 mb-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-white border-md border-right-0">
                                <i class="material-icons text-muted">person</i>
                            </span>
                        </div>
                        <input id="id" type="text" name="id" placeholder="ID" class="form-control bg-white border-left-0 border-md">
                    </div>

                    <!-- Password -->
                    <div class="input-group col-lg-6 mb-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-white border-md border-right-0">
                                <i class="material-icons text-muted">lock</i>
                            </span>
                        </div>
                        <input id="password" type="password" name="password" placeholder="Password" class="form-control bg-white border-left-0 border-md">
                    </div>

                    <!-- Password Confirmation -->
                    <div class="input-group col-lg-6 mb-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-white border-md border-right-0">
                                <i class="material-icons text-muted">lock</i>
                            </span>
                        </div>
                        <input id="passwordConfirmation" type="text" name="passwordConfirmation" placeholder="Confirm Password" class="form-control bg-white border-left-0 border-md">
                    </div>

                    <!-- Name -->
                    <div class="input-group col-lg-12 mb-4">
                        <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">person</i>
                                </span>
                        </div>
                        <input id="name" type="text" name="name" placeholder="Name" class="form-control bg-white border-left-0 border-md">
                    </div>

                    <!-- Email Address -->
                    <div class="input-group col-lg-12 mb-4">
                        <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">email</i>
                                </span>
                        </div>
                        <input id="email" type="email" name="email" placeholder="Email Address" class="form-control bg-white border-left-0 border-right-0 border-md">

                        <span class="input-group-text bg-white border-left-0 border-right-0 border-md">
                                <i class="material-icons text-muted">alternate_email</i>
                            </span>

                        <select id="c" name="countryCode" class="custom-select form-control bg-white border-left-0 border-md h-100 font-weight-bold text-muted">
                            <option value="@naver.com">naver.com</option>
                            <option value="@gmail.com">gmail.com</option>
                            <option value="@daum.net">daum.net</option>
                            <option value="@nate.com">nate.com</option>
                        </select>
                    </div>

                    <!-- Phone Number -->
                    <div class="input-group col-lg-12 mb-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-white border-md border-right-0">
                                <i class="material-icons text-muted">phone</i>
                            </span>
                        </div>
                        <select id="countryCode" name="countryCode" style="max-width: 80px" class="custom-select form-control bg-white border-left-0 border-md h-100 font-weight-bold text-muted">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                        </select>
                        <input id="phoneNumber" type="tel" name="phone" placeholder="Phone Number" class="form-control bg-white border-md border-left-0 pl-3">
                    </div>

                    <!-- ZIP -->
                    <div class="input-group col-lg-12 mb-4">
                        <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">home</i>
                                </span>
                        </div>
                        <input id="zip" type="text" name="zip" placeholder="ZIP" class="form-control col-3 bg-white border-md border-left-0 border-right-0 pl-3">
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
                        <input id="address1" type="text" name="address1" placeholder="Address 1" class="form-control bg-white border-md border-left-0 pl-3">
                    </div>

                    <!-- Address 2 -->
                    <div class="input-group col-lg-6 mb-4">
                        <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md">
                                    <i class="material-icons text-muted">home</i>
                                </span>
                        </div>
                        <input id="address2" type="text" name="address2" placeholder="Address 2" class="form-control bg-white border-md border-left-0 pl-3">
                    </div>

                    <!-- Submit Button -->
                    <div class="form-group col-lg-12 mx-auto mb-0">
                        <button type="submit" class="btn btn-primary btn-block py-2">
                            <span class="font-weight-bold">Create your account</span>
                        </button>
                    </div>

                    <!-- Divider Text -->
                    <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                        <div class="border-bottom w-100 ml-5"></div>
                        <span class="px-2 small text-muted font-weight-bold text-muted">OR</span>
                        <div class="border-bottom w-100 mr-5"></div>
                    </div>

                    <!-- Already Registered -->
                    <div class="text-center w-100">
                        <p class="text-muted font-weight-bold">Already Registered? <a href="signin.jsp" class="text-primary ml-2">Sign in</a></p>
                    </div>

                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(() => {
        $("#findAddress").on("click", (e) => {
            console.log("findAddress btn is clicked")
            new daum.Postcode({
                oncomplete: (data) => {
                    const zip = $("#zip");
                    const address1 = $("#address1");
                    const address2 = $("#address2");

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

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    zip.val(data.zonecode);
                    address1.val(addr);
                    address2.val(extraAddr);
                    // 커서를 상세주소 필드로 이동한다.
                    address2.focus();
                }
            }).open();
        })
    })
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>