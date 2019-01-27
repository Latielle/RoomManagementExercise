<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Room Management Exercise</title>
 <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
 <div class="container">
  <h2>Room Management Exercise</h2>
  <table class="table table-striped">
   <thead>
   <tr>
    <th scope="row">#ID</th>
    <th scope="row">Building</th>
    <th scope="row">Room Number</th>
    <th scope="row">Seats</th>
    <th scope="row">Projector present</th>
    <th scope="row">Update</th>
    <th scope="row">Delete</th>
    </tr>
   </thead>
   <tbody>
    <c:forEach items="${roomList }" var="room" >
     <tr>
      <td>${room.id }</td>
      <td>${room.building }</td>
      <td>${room.roomNumber }</td>
      <td>${room.seats }</td>
      <td>${room.projectorPresent }</td>
      <td>
       <spring:url value="/api/rooms/${room.id }" var="updateURL" />
       <a class="btn btn-primary" href="${updateURL }" role="button" >Update</a>
      </td>
      <td>
       <spring:url value="/api/deleteRoom/${room.id }" var="deleteURL" />
       <a class="btn btn-primary" href="${deleteURL }" role="button" >Delete</a>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  <spring:url value="/api/addRoom/" var="addURL" />
  <a class="btn btn-primary" href="${addURL }" role="button" >Add New Room</a>
 </div>
</body>
</html>