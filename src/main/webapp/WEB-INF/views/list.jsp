<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>​
<html>
<head>
    <title>회원 리스트</title>
</head>
<body>
<%@ include file="include/headerBar.jsp" %>

<h2>회원 리스트</h2><br/><br/>
<table style="border:1px solid black">
    <tr>
        <td></td>
        <td>이름</td>
        <td>이메일</td>
        <td>나이</td>
        <td>날짜</td>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td></td>
        </sec:authorize>
    </tr>
    <c:forEach var = "u" varStatus = "varStatus" items="${users}">
        <tr>
            <td>${varStatus.count}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td>${u.age}</td>
            <td><fmt:formatDate value="${u.dateTime}" pattern="yyyy.MM.dd"/></td>

            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <td>

                    <a href="/user/edit?id=${u.id}">수정</a>
                    <a href="/user/delete?id=${u.id}">삭제</a>

                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>
</body>
</html>