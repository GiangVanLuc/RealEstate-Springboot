<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Đăng kí</title>
</head>
<body>

    <section class="vh-100 gradient-custom form-register">
        <div class="container">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                        <div class="card-body p-2 px-5 text-center">
                            <div class="md-5 md-4 mt-4 pb-2">
                                <h2 class="fw-bold mb-2 text-uppercase">Create an account</h2>
                                <p class="text-white-50 mb-2">Please enter your Information</p>

                                <div class="row">
                                    <div class="col-md-6 mb-2">
                                        <div class="form-outline">
                                            <label class="form-label" for="form3Example1m">First name</label>
                                            <input type="text" id="form3Example1m"
                                                   class="form-control form-control-lg" />
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-2">
                                        <div class="form-outline">
                                            <label class="form-label" for="form3Example1n">Last name</label>
                                            <input type="text" id="form3Example1n"
                                                   class="form-control form-control-lg" />
                                        </div>
                                    </div>
                                </div>

                                <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">
                                    <h6 class="mb-0 me-4">Gender: </h6>

                                    <div class="form-check form-check-inline mb-0 me-4">
                                        <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                               id="femaleGender" value="option1" />
                                        <label class="form-check-label" for="femaleGender">Female</label>
                                    </div>

                                    <div class="form-check form-check-inline mb-0 me-4">
                                        <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                               id="maleGender" value="option2" />
                                        <label class="form-check-label" for="maleGender">Male</label>
                                    </div>

                                    <div class="form-check form-check-inline mb-0">
                                        <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                               id="otherGender" value="option3" />
                                        <label class="form-check-label" for="otherGender">Other</label>
                                    </div>
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="typePasswordX">Username</label>
                                    <input type="email" id="typePasswordX" class="form-control form-control-lg" />
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="typeEmailX">Password</label>
                                    <input type="email" id="typeEmailX" class="form-control form-control-lg" />
                                </div>

                                <div class="form-outline form-white mb-2">
                                    <label class="form-label" for="typeEmailX">Repeat your password</label>
                                    <input type="email" id="typeEmailX" class="form-control form-control-lg" />
                                </div>

                                <div class="form-check d-flex justify-content-center mb-2">
                                    <input class="form-check-input me-2" type="checkbox" value=""
                                           id="form2Example3cg" />
                                    <label class="form-check-label">
                                        I agree all statements in <a href="#!" class="text-body"><u
                                            style="color: white ;">Terms of
                                        service</u></a>
                                    </label>
                                </div>


                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Register</button>

                                <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                    <a href="#!" class="login-extension text-white"><i
                                            class="fab fa-facebook-f fa-lg"></i></a>
                                    <a href="#!" class="login-extension text-white"><i
                                            class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                    <a href="#!" class="login-extension text-white"><i
                                            class="fab fa-google fa-lg"></i></a>
                                </div>

                                <p class="text-center text-muted mt-2 mb-0">Have already an account? <a href="#!"
                                                                                                        class="fw-bold text-body"><u style="color: white ;">Login here</u></a></p>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>
</html>
