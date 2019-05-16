<link href="/resources/css/style4.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="view" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<div id="page">
    <!-- start content -->
    <div id="content">
        <div class="post">
            <div class="entry">

                <view:if test="${sessionScope.user!= null}">
                    <h2 class="title"><b>Personal Profile</b> For Customers</h2>
                    <p>Your Username : <b>${sessionScope.user.username}</b> </p>
                    <p>Your emial : <b>${sessionScope.user.email}</b> </p>
                    <view:if test="${sessionScope.user.address != null}">
                        <p>Your address :
                            <b>
                              ${sessionScope.user.address}
                            </b>
                        </p>
                    </view:if>
                </view:if>

                <view:if test="${sessionScope.driver!= null}">
                    <h2 class="title"><b>Personal Profile</b> For Driver</h2>
                    <p>Your Username : <b>${sessionScope.driver.username}</b> </p>
                    <p>Your emial : <b>${sessionScope.driver.email}</b> </p>
                    <view:if test="${sessionScope.driver.address != null}">
                        <p>Your address :
                            <b>
                                    ${sessionScope.driver.address}
                            </b>
                        </p>
                    </view:if>
                </view:if>

                <view:if test="${sessionScope.admin != null}">
                    <h2 class="title"><b>Personal Profile</b> For Administrator</h2>
                    <p><h1 style="color:#f7757b">ADMINISTRATOR</h1></p>
                    <p>Your Username : <b>admin</b> </p>
                    <p>Your password : <b>1234asdf</b> </p>
                </view:if>

            </div>

            <div class="meta">
                <p class="links"><a href="#" class="more">Read more details</a></p>
            </div>

        </div>
    </div>

    <%--End Of Content div -------------------------------------------------------------------- --%>

    <%--Show All trips for administrator--%>
    <view:if test="${sessionScope.admin_show != null}">
        <sql:setDataSource
                var="connectiondb"
                driver="oracle.jdbc.driver.OracleDriver"
                url="jdbc:oracle:thin:@localhost:1521:xe"
                user="ehsan" password="123"/>
        <sql:query var="list_users" dataSource="${connectiondb}">
            select * from trip
        </sql:query>
    </view:if>

    <%--Show All trips for administrator--%>
    <view:if test="${sessionScope.showdrivers != null}">
        <sql:setDataSource
                var="connectiondb"
                driver="oracle.jdbc.driver.OracleDriver"
                url="jdbc:oracle:thin:@localhost:1521:xe"
                user="ehsan" password="123"/>
        <sql:query var="driver_list" dataSource="${connectiondb}">
            select * from driver
        </sql:query>
    </view:if>


        <view:if test="${sessionScope.admin_newtrip != null}">

            <div style="display:block;float: left;width: 300px; position: absolute; display: block ;border: groove; border-color: black ; margin-left: 250px; margin-top: 300px;">
                <form action="/spring/secured/registertrip" method="post">
                    <div style="color: #1d3c41;">
                        <div align="center">
                            <P style=" color: #4D9FC7; font-weight: bolder; font-family: Sahel; font-size: x-large">New Trip</P>
                            <div align="center" style="margin: 15px;">
                                <input type="text" name="username" placeholder="Username" required="required"><br>
                            </div>
                        </div>
                        <div>
                            <div align="center" style="margin: 15px;">
                                <input type="text" name="password" placeholder="Admin Password" required="required"><br>
                            </div>
                        </div>
                        <div>
                            <div align="center" style="margin: 15px;">
                                <input type="text" name="begin" placeholder="Start Location" required="required"><br>
                            </div>
                        </div>
                        <div>
                            <div align="center" style="margin: 15px;">
                                <input type="text" name="destination" placeholder="Destination" required="required"><br>
                            </div>
                        </div>
                        <div>
                            <div align="center" style="margin: 15px;">
                                <input type="text" name="customer_name" placeholder="Customer Name" required="required"><br>
                            </div>
                        </div>
                        <div>
                            <div align="center" style="margin: 15px;">
                                <input type="text" name="driver_name" placeholder="Driver Name" required="required"><br>
                            </div>
                        </div>
                        <div>
                            <div align="center">
                                <input type="submit" value="Register" style="margin:15px;font-family: Sahel;height: 35px;background-color: deepskyblue;font-weight: bolder;border-radius: 5px;">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </view:if>



        <view:if test="${sessionScope.admin_deletetrip != null}">

            <div style="width: 300px; height: 200px; border-color: black; float: left; position: absolute ;border: groove; margin-left: 300px; margin-top: 330px;" align="center">
                <h2 style="font-size: large;font-family: Sahel">
                    <p>Delete Trip</p>
                    Search Trips Based On Identity
                </h2>
                <form action="/spring/secured/deletetriprecord" method="post" style="margin: auto">
                    <p>
                        <input type="text" name="id" required="required" size="12" style="font-family: Sahel" placeholder="Identity">
                    </p>
                    <p>
                        <input type="submit" value="Delete" style="background-color: #059BD8 ;color: black;font-size: medium; border-radius: 5px ">
                    </p>
                </form>
            </div>

        </view:if>

        <view:if test="${sessionScope.admin_show != null}">

            <div  style="width: 900px;border: groove;position: absolute ;border-color: black; height: auto ;float: left; margin-left: 50px;margin-top: 320px">
                <table style="color: black;font-size:medium; font-family: Sahel ;">
                    <tr>
                        <th>Trip Id</th>
                        <th>Admin Username</th>
                        <th>start</th>
                        <th>destination</th>
                        <th>Date</th>
                        <th>customer's Name</th>
                        <th>Driver's Name</th>
                    </tr>
                    <view:forEach items="${list_users.rows}" var="trips">
                        <tr>
                            <td>${trips.id}</td>
                            <td>${trips.username}</td>
                            <td>${trips.beginof}</td>
                            <td>${trips.destination}</td>
                            <td>${trips.dateandtime}</td>
                            <td>${trips.customer_name}</td>
                            <td>${trips.driver_name}</td>
                        </tr>
                    </view:forEach>
                </table>
            </div>
        </view:if>

    <view:if test="${sessionScope.showdrivers != null}">

        <div  style="width: 900px;border: groove;position: absolute ;border-color: black; height: auto ;float: left; margin-left: 50px;margin-top: 320px">
            <table style="color: black;font-size:medium; font-family: Sahel ;">
                <tr>
                    <th>Driver Id</th>
                    <th>Driver Username</th>
                    <th>Email Address</th>
                    <th>Address</th>
                </tr>
                <view:forEach items="${driver_list.rows}" var="drivers">
                    <tr>
                        <td>${drivers.id}</td>
                        <td>${drivers.username}</td>
                        <td>${drivers.email}</td>
                        <td>${drivers.address}</td>
                    </tr>
                </view:forEach>
            </table>
        </div>
    </view:if>



    <view:if test="${sessionScope.admin_edittrip != null}">

        <div style="display:block;float: left;width: 300px; position: absolute; display: block ;border: groove; border-color: black ; margin-left: 250px; margin-top: 300px;">
            <form action="/spring/secured/edittrip" method="post">
                <div style="color: #1d3c41;">
                    <P style=" color: #4D9FC7; font-weight: bolder; font-family: Sahel; font-size: x-large " align="center">Edit Trip</P>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="id" placeholder="Identity" required="required"><br>
                        </div>
                    </div>
                    <div align="center">
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="username" placeholder="Username" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="password" placeholder="Admin Password" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="begin" placeholder="Start Location" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="destination" placeholder="Destination" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="customer_name" placeholder="Customer Name" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="driver_name" placeholder="Driver Name" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center">
                            <input type="submit" value="Update" style="margin:15px;font-family: Sahel;height: 35px;background-color: deepskyblue;font-weight: bolder;border-radius: 5px;">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </view:if>

    <view:if test="${entitynotexist != null }" >

        <div style="width: 600px; font-family: Sahel ;height: 100px; border-color: black; float: left; position: absolute ;border: groove; margin-left: 300px; margin-top: 330px;" align="center">
            <h2 style="font-size: x-large;font-family: Sahel; color: red">
                Sorry , Trip With Entered Identity Not Exist
            </h2>
        </div>

    </view:if>

    <view:if test="${deletedrivers != null }" >

        <div style="width: 300px; height: 200px; border-color: black; float: left; position: absolute ;border: groove; margin-left: 300px; margin-top: 330px;" align="center">
            <h2 style="font-size: large;font-family: Sahel">
                <p>Delete Driver</p>
                Search Trips Based On Identity
            </h2>
            <form action="/spring/secured/deletedrivers" method="post" style="margin: auto">
                <p>
                    <input type="text" name="id" required="required" size="12" style="font-family: Sahel" placeholder="Identity">
                </p>
                <p>
                    <input type="submit" value="Delete" style="background-color: #059BD8 ;color: black;font-size: medium; border-radius: 5px ">
                </p>
            </form>
        </div>

    </view:if>

    <view:if test="${sessionScope.editdriverprofile != null}">

        <div style="display:block;float: left;width: 300px; position: absolute; display: block ;border: groove; border-color: black ; margin-left: 250px; margin-top: 300px;">
            <form action="/spring/secured/editdriverprofile" method="post">
                <div style="color: #1d3c41;">
                    <P style=" color: #4D9FC7; font-weight: bolder; font-family: Sahel; font-size: x-large " align="center">Edit Driver Profile</P>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="hidden" name="id"  value="${sessionScope.driver.id}"><br>
                        </div>
                    </div>
                    <div align="center">
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="username" placeholder="Username" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="password" placeholder="Driver Password" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="email" placeholder="Email Address" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="address" placeholder="Address" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center">
                            <input type="submit" value="Update" style="margin:15px;font-family: Sahel;height: 35px;background-color: deepskyblue;font-weight: bolder;border-radius: 5px;">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </view:if>

    <view:if test="${sessionScope.editcustomerprofile != null}">

        <div style="display:block;float: left;width: 300px; position: absolute; display: block ;border: groove; border-color: black ; margin-left: 250px; margin-top: 300px;">
            <form action="/spring/secured/editcustomerprofile" method="post">
                <div style="color: #1d3c41;">
                    <P style=" color: #4D9FC7; font-weight: bolder; font-family: Sahel; font-size: x-large " align="center">Edit Customer Profile</P>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="hidden" name="id"  value="${sessionScope.user.id}"><br>
                        </div>
                    </div>
                    <div align="center">
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="username" placeholder="Username" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="password" placeholder="Customer Password" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="email" placeholder="Email Address" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center" style="margin: 15px;">
                            <input type="text" name="address" placeholder="Address" required="required"><br>
                        </div>
                    </div>
                    <div>
                        <div align="center">
                            <input type="submit" value="Update" style="margin:15px;font-family: Sahel;height: 35px;background-color: deepskyblue;font-weight: bolder;border-radius: 5px;">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </view:if>


    <div id="sidebar">
        <ul>
            <li>
                <div class="box">
                    <h2><b>About</b> Us</h2>
                    <p>We provide corporate and government agencies with top customer support, dedicated and customised solutions coupled with up-to-date technologies.</p>
                </div>
            </li>

            <view:if test="${sessionScope.user != null}">
                <li>
                    <h2><b>Our</b> Services</h2>
                    <ul>
                        <li><a href="/spring/secured/editcustomerprofileflag">Edit profile</a></li>
                        <li><a href="#">Custom Web Design</a></li>
                        <li><a href="#">VPS Servers</a></li>
                        <li><a href="#">Dedicated Servers</a></li>
                        <li><a href="#">Templated Web Design</a></li>
                        <li><a href="#">Custom Web Design</a></li>
                    </ul>
                </li>
            </view:if>

            <view:if test="${sessionScope.driver != null}">
                <li>
                    <h2><b>Our</b> Services</h2>
                    <ul>
                        <li><a href="/spring/secured/editdriverprofileflag">Edit Profile</a></li>
                        <li><a href="#">Custom Web Design</a></li>
                        <li><a href="#">VPS Servers</a></li>
                        <li><a href="#">Dedicated Servers</a></li>
                        <li><a href="#">Templated Web Design</a></li>
                        <li><a href="#">Custom Web Design</a></li>
                    </ul>
                </li>
            </view:if>

            <view:if test="${sessionScope.admin != null}">
                <li>
                    <h2><b>Our</b> Services</h2>
                    <ul>
                        <li><a href="/spring/secured/showtripsflag">Show Trips</a></li>
                        <li><a href="/spring/secured/newtrip">New Trip</a></li>
                        <li><a href="/spring/secured/edittripflag">Edit Trip</a></li>
                        <li><a href="/spring/secured/deletetripflag">Delete Trips</a></li>
                        <li><a href="/spring/secured/showdriversflag">Show Drivers</a></li>
                        <li><a href="/spring/secured/deletedriversflag">Delete Drivers</a></li>
                    </ul>
                </li>
            </view:if>

            <li>
                <h2><b>Latest</b> Clients</h2>
                <ul>
                    <li><a href="#">Top 100 Web Hosting</a></li>
                    <li><a href="#">Free Web Templates</a></li>
                    <li><a href="#">WordPress Themes</a></li>
                    <li><a href="#">Company Name</a></li>
                    <li><a href="#">Another Company Name</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <!-- end sidebar -->

    <div style="clear: both;">&nbsp;
    </div>

</div>