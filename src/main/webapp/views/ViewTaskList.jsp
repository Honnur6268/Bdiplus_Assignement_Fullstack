<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<head>
    <meta charset="ISO-8859-1">
    <title>All Task</title>

    <link rel="stylesheet"
        	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script
        	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
        	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        a{
            color: white;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
        .buttonPositionTask{
        	float: right;
        	margin-bottom: 10px;
        	margin-right: 5px;
        }
        .buttonPositionHome{
        	float: right;
        	margin-bottom: 10px;
        	
        }
    </style>

</head>
<body>

    <div class="container">

        <h1 class="p-3"> Task List</h1>
        <button type="button" class="btn btn-secondary buttonPositionHome">
        	<a href="/">Home</a>
        </button>
         <button type="button" class="btn btn-primary buttonPositionTask">
        	<a href="addTask">Add New Task</a>
        </button>
        
         

        <form:form>

            <table class="table table-bordered">
            	<tr>
            		<th>ID</th>
            		<th>Task Name</th>
            		<th>Description</th>
            		<th>Status</th>
            		<th>Mark Completed</th>
            		<th>Edit</th>
            		<th>Delete</th>
            	</tr>

            	<c:forEach var="task" items="${list}">
                    <tr>
                		<td>${task.id}</td>
                		<td>${task.taskName}</td>
                		<td>${task.taskDescription}</td>
                		<td>${task.status}</td>
                		<td><button type="button" class="btn btn-success">
                		    <a href="/api/task/updateTaskStatus/${task.id}">Mark Complete</a>
                		</button></td>
                		<td><button type="button" class="btn btn-primary">
                		    <a href="editTask/${task.id}">Update</a>
                		</button></td>
                		<td><button type="button" class="btn btn-danger">
                			<a href="/api/task/deleteTask/${task.id}">Delete</a>
                		</button></td>
                	</tr>

            	</c:forEach>

            </table>

        </form:form>

       

    </div>

    <script th:inline="javascript">
                window.onload = function() {

                    var msg = "${message}";
                    
                    if (msg == "Save Success") {
        				Command: toastr["success"]("Task added successfully!!")
        			} else if (msg == "Delete Success") {
        				Command: toastr["success"]("Task deleted successfully!!")
        			} else if (msg == "Delete Failure") {
        			    Command: toastr["error"]("Some error occurred, couldn't delete task")
        			} else if (msg == "Edit Success") {
        				Command: toastr["success"]("Task updated successfully!!")
        			}
        			else if (msg == "Update Success") {
        				Command: toastr["success"]("Status updated successfully!!")
        			}

        			toastr.options = {
                          "closeButton": true,
                          "debug": false,
                          "newestOnTop": false,
                          "progressBar": true,
                          "positionClass": "toast-top-right",
                          "preventDuplicates": false,
                          "showDuration": "300",
                          "hideDuration": "1000",
                          "timeOut": "5000",
                          "extendedTimeOut": "1000",
                          "showEasing": "swing",
                          "hideEasing": "linear",
                          "showMethod": "fadeIn",
                          "hideMethod": "fadeOut"
                        }
        	    }
            </script>

</body>

</html>