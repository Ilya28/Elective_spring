<#macro login_form>
<div class="container" ng-app="login_form" style="margin-top: 2px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <!-- ========================================================================= -->
                            <!-- <form ng-controller="LogCtrl"
                                  id="login-form" role="form" style="display: block;" name="form" autocomplete="off" novalidate ng-submit="form.$valid && sendForm(auth)">-->
                            <form id="login-form" role="form" style="display: block;" name="form" autocomplete="off" method="post" action="/${locale}/login">
                                <div class="form-group">
                                    <input type="text"
                                           name="username"
                                           id="email"
                                           tabindex="1"
                                           class="form-control"
                                           placeholder="Email address"
                                           value=""
                                           required>
                                    <!-- ng-model="auth.email">-->
                                </div>
                                <div class="form-group">
                                    <input type="password"
                                           name="password"
                                           id="password"
                                           tabindex="2"
                                           class="form-control"
                                           placeholder="Password"
                                           required>
                                    <!-- ng-model="auth.password">-->
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit"
                                                   name="login-submit"
                                                   id="login-submit"
                                                   tabindex="4"
                                                   class="form-control btn btn-login"
                                                   value="Log In">
                                            <!-- ng-disabled="form.$invalid"> -->
                                 </div>
                             </div>
                         </div>
                         <!-- <input type="hidden" name="_csrf" value="{_csrf.token}"/> -->
                            </form>
                            <!-- ========================================================================= -->
                            <form ng-controller="RegCtrl"
                                  id="register-form" role="form" style="display: block;" name="form" autocomplete="off" novalidate ng-submit="form.$valid && sendForm(auth)">
                                <div class="form-group">
                                    <input type="text"
                                           name="fullName"
                                           id="fullName"
                                           tabindex="1"
                                           class="form-control"
                                           placeholder="Full name"
                                           value=""
                                           required
                                           ng-model="auth.name">
                                </div>
                                <div class="form-group">
                                    <input type="text"
                                           name="email"
                                           id="email"
                                           tabindex="1"
                                           class="form-control"
                                           placeholder="Email address"
                                           value=""
                                           required
                                           ng-model="auth.email">
                                </div>
                                <div class="form-group">
                                    <input type="password"
                                           name="password"
                                           id="password"
                                           tabindex="2"
                                           class="form-control"
                                           placeholder="Password"
                                           required
                                           ng-model="auth.password">
                                </div>
                                <div class="form-group">
                                    <input type="password"
                                           name="confirm-password"
                                           id="confirm-password"
                                           tabindex="2"
                                           class="form-control"
                                           placeholder="Confirm Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit"
                                                   name="register-submit"
                                                   id="register-submit"
                                                   tabindex="4"
                                                   class="form-control btn btn-register"
                                                   value="Register Now"
                                                   ng-disabled="form.$invalid">
                                        </div>
                                    </div>
                                </div>
                                <!--  <input type="hidden" name="_csrf" value="{_csrf.token}"/> -->
                            </form>
                            <!-- ========================================================================= -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>