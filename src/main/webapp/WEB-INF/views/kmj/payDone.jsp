<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Smart Doctor</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="resources/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<!-- End plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="resources/css/style.css">
<!-- End layout styles -->
<link rel="icon" type="image/png" sizes="16x16"
	href="resources/images/favicon-16x16.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css"></link>
	<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style>
.wrap11 {
	width: 100%;
	height: 100%;
	background-color: white;
	border-radius: 30px 10px;
	box-shadow: 3px 3px 3px 3px lightgray;
	text-align: center;
}

.button {
	width: 110px;
	height: 30px;
	background-color: rgb(29, 92, 99);
	color: white;
	border-radius: 7px;
	border: 0;
	cursor: pointer;
}
.detail {
	width: 60px;
	height: 30px;
	background-color: rgb(29, 92, 99);
	color: white;
	border-radius: 7px;
	border: 0;
}

.bggray {
	background-color: lightgray;
	color: black;
}

b {
	font-size: 20px;
}

.title {
	border-radius: 7px;
	height: 50px;

}

.table {
	 width: 100%;
}

input {
	height: 100%;
	border: 1px solid black;
	border-radius: 5px;
}

#searchbar {
	height: 30px;

}
#searchbar>div{
	height: 100%;
	box-sizing: border-box;

	float: left;
}
.innerArea{
	width: 1000px;
	height: 100%;
	margin: auto;
}

#tableArea{
	height: 700px;
	width: 100%;
	/* border: 1px solid black; */
	text-align: center;
	overflow: auto;
}
#tableArea>table{
	width: 100%;
	box-sizing: border-box;
}
#pagingArea{width:fit-content;margin:auto;}

#tableArea>.table>tbody>tr:hover{
   	background:#E1F0FF;
    }

</style>


</head>
<body>
	<jsp:include page="../common/navbar.jsp" />

	<div class="main-panel">
		<div class="content-wrapper">
			<!-- 이 안에서 작업해 주세요 -->
			<div class="wrap11">
				<br><br><br>

				<div class="innerArea">
					<table class="table bggray title">
						<tr height="40">
							<td><b>수납관리</B></td>
						</tr>
					</table>
					<br>

					<div align="left">
						<button type="button" class="button bggray">수납대기</button>
						<button type="button" class="button">수납완료</button>
					</div>

					<br>
					<div id="tableArea" align="center">
						<table class="table">
							<thead>
								<tr>
									<th width="85px">수납번호</th>
									<th width="60px">이름</th>
									<th width="50px">성별</th>
									<th width="100px">주민등록번호</th>
									<th width="100px">진료과</th>
									<th width="100px">처방전</th>
									<th width="100px">수납</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>2</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>3</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>4</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>5</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>6</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>7</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>8</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>9</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>
								<tr>
									<td>10</td>
									<td>강개똥</td>
									<td>남</td>
									<td>875558-5555555</td>
									<td>내과</td>
									<td><button class="button">출력</button></td>
									<td><button class="button">출력</button></td>
								</tr>

							</tbody>
						</table>
						<br>
					</div>

					<div id="pagingArea">
						<ul class="pagination">
							<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#">Next</a></li>
						</ul>
						<br><br>
					</div>
					<br><br>

				</div>
				<br><br><br>
			</div>
		</div>
		<jsp:include page="../common/footer.jsp" />
</body>
</html>