<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="scope 객체에 할당된 사원, 부서정보를 보여주는 일"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--공통 CSS-->
<link rel="stylesheet" type="text/css" href="http://211.63.89.143/jsp_prj/common/CSS/main_20220321.css">
<style type="text/css">

</style>
<!-- jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- bootstrap CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function(){
	
});//ready
</script>
</head>
<body>
<div>
<a href="login_form.do?cmd=L001">로그인</a>
</div>
<strong>부서정보</strong>
<table class="table">
<thead>
<tr>
<th>부서번호</th>
<th>부서명</th>
<th>위치</th>
</tr>
</thead>
<tbody>
<c:if test="${empty deptData}">
<tr>
<td colspan="3">
부서정보 없음.<img src="http://localhost/mvc_prj2/common/images/error.jpg" style="width:120px"/>
</td>
</tr>
</c:if>
<c:forEach var="deptVO" items="${deptData }">
<tr>
	<td><c:out value="${deptVO.deptno }"/></td>
	<td><a href="search_emp.do?cmd=I001&deptno=${deptVO.deptno }"><c:out value="${deptVO.dname }"/></a></td>
	<td><c:out value="${deptVO.loc }"/></td>
</tr>
</c:forEach>
</tbody>
</table>
<div><strong>사원정보</strong><br/>
<table class="table">
<thead>
<tr>
	<th>사원번호</th>
	<th>사워명</th>
	<th>직무</th>
	<th>연봉</th>
	<th>매니저번호</th>
	<th>보너스</th>
	<th>부서번호</th>
	<th>입사일</th>
</tr>
</thead>
<tbody>
<c:if test="${empty empData }">
<tr>
<td colspan="3">
부서정보 없음.<img src="http://localhost/mvc_prj2/common/images/error.jpg" style="width:120px"/>
</td>
</tr>
</c:if>
<c:forEach var="eVO" items="${ empData }">
<tr>
	<td><c:out value="${eVO.empno }"/></td>
	<td><c:out value="${eVO.ename }"/></td>
	<td><c:out value="${eVO.job }"/></td>
	<td><c:out value="${eVO.sal }"/></td>
	<td><c:out value="${eVO.mgr }"/></td>
	<td><c:out value="${eVO.comm }"/></td>
	<td><c:out value="${eVO.deptno }"/></td>
	<td><fmt:formatDate value="${eVO.hiredate }" pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>

</tbody>
</table>
</div>
</body>
</html>













