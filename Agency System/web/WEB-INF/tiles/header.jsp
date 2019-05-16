<%@ taglib prefix="view" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../../resources/css/layout.css" type="text/css" />
<link rel="stylesheet" href="../../resources/css/dropdown.css" type="text/css" />
      <div class="wrapper col1">
          <div id="header">
              <div id="logo">
                  <h1><a href="#">AgencyToday</a></h1>
                  <p><strong>Fast And Secure</strong></p>
              </div>
              <div id="newsletter">
                  <p>Sign up to our newsletter for the latest news, updates and offers.</p>
                  <form action="#" method="post">
                      <fieldset>
                          <legend>NewsLetter</legend>
                          <input type="text" value="Name&hellip;"  onfocus="this.value=(this.value=='Name&hellip;')? '' : this.value ;" />
                          <input type="text" value="Email&hellip;"  onfocus="this.value=(this.value=='Email&hellip;')? '' : this.value ;" />
                          <input type="submit" name="news_go" id="news_go" value="Sign Up" />
                      </fieldset>
                  </form>
                  <br>
                 <%-- <p style="font-weight: bolder;color: darkcyan">Dear User :${sessionScope.user} Welcome </p>--%>
                  <p >
                      <view:if test="${sessionScope.user!= null}">
                         <div class="dropdown">
                             <p style="font-weight: bolder;color:#059BD8" >
                                 Dear User :${sessionScope.user.username} Welcome
                             </p>
                                    <div class="dropdown-content">
                                          <p>
                                              <a href="/spring/secured/sessiontimeout" methods="get" style="color: black">
                                                  Sign Out
                                              </a>
                                           </p>
                                              <p>
                                                  <a href="/spring/secured/customer profile" methods="get" style="color: black">
                                                      Profile
                                                  </a>
                                              </p>
                                      </div>
                          </div>
                      </view:if>

                      <view:if test="${sessionScope.driver!= null}">

                          <div class="dropdown">
                              <p style="font-weight: bolder;color:#059BD8" >
                                  Dear User :${sessionScope.driver.username} Welcome
                              </p>
                              <div class="dropdown-content">
                                  <p>
                                      <a href="/spring/secured/sessiontimeout" methods="get" style="color: black">
                                          Sign Out
                                      </a>
                                  </p>
                                  <p>
                                      <a href="/spring/secured/customer profile" methods="get" style="color: black">
                                          Profile
                                      </a>
                                  </p>
                              </div>
                          </div>
                      </view:if>

                      <view:if test="${sessionScope.admin!= null}">

                          <div class="dropdown">
                              <p style="font-weight: bolder;color:#059BD8" >
                                  ${sessionScope.admin}
                              </p>
                              <div class="dropdown-content">
                                  <p>
                                      <a href="/spring/secured/sessiontimeout" methods="get" style="color: black">
                                          Sign Out
                                      </a>
                                  </p>
                                  <p>
                                      <a href="/spring/secured/customer profile" methods="get" style="color: black">
                                          Profile
                                      </a>
                                  </p>
                              </div>
                          </div>
                      </view:if>

                  </p>
              </div>
              <br class="clear" />
          </div>
      </div>
<div class="wrapper col2">
    <div id="topbar">
        <div id="topnav">
            <ul>
                <li class="active"><a href="/spring/">Home</a></li>
                <li><a href="/spring/Registration_Form">Log In </a></li>
                <li><a href="#">Contact Us</a></li>
                <li><a href="#">Services</a>
                    <ul>
                        <li><a href="#">Link 1</a></li>
                        <li><a href="#">Link 2</a></li>
                        <li><a href="#">Link 3</a></li>
                    </ul>
                </li>
                <li class="last"><a href="#">Management</a></li>
            </ul>
        </div>
        <div id="search">
            <form action="#">
                <fieldset>
                    <legend>Site Search</legend>
                    <input type="text" value="Search Our Website&hellip;"  onfocus="this.value=(this.value=='Search Our Website&hellip;')? '' : this.value ;" />
                    <input type="submit" name="go" id="go" value="Search" />
                </fieldset>
            </form>
        </div>
        <br class="clear" />
    </div>
</div>