<%@ taglib prefix="view" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="background-color: whitesmoke; height: 300px;">
    <br><br>
    <h1 style="color:#7cbcd6;background-color:ghostwhite" align="center">
        <b>Successed , Now you can enter to your personal profile by Do Login</b>
    </h1>
    <view:if test="${admin_successnewtrip == null}">
        <p style="font-size: large; color:#059BD8;" align="center">
            <b>
                <a href="/spring/" >Continue</a>
            </b>
        </p>
    </view:if>

    <view:if test="${admin_successnewtrip == true}">
        <p style="font-size: large; color: #059BD8;" align="center">
            <b>
                <a href="/spring/secured/customer profile">Administrator Profile</a>
            </b>
        </p>
    </view:if>

</div>