<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Room Form</title>
 <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
 <style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
 <div class="container">
  <spring:url value="/api/rooms" var="saveURL" />
  <h2>Room</h2>
  <c:if test="${error}">
  	<label class="error">The combination Building and Room number must be unique</label>
  </c:if>
  <form:form modelAttribute="roomForm" method="post" action="${saveURL }" cssClass="form" >
   <form:hidden path="id"/>
   <div class="form-group">
    <label>Building</label>
    <form:input path="building" cssClass="form-control" id="building" />
    <form:errors path="building" cssClass="error"/>
   </div>
   <div class="form-group">
    <label>Room number</label>
    <form:input path="roomNumber" cssClass="form-control" id="roomNumber" />
    <form:errors path="roomNumber" cssClass="error"/>
   </div>
   <div class="form-group">
    <label>Seats</label>
    <form:input path="seats" cssClass="form-control" id="seats" /> 
    <form:errors path="seats" cssClass="error"/>
   </div>
      <div class="form-group">
    <label>Projector present </label>
    <form:checkbox path="projectorPresent" id="projectorPresent"/>
   </div>
   <button type="submit" class="btn btn-primary">Save</button>
  </form:form>
  
 </div>
</body>
</html>