<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Smart Doctor</title>
<link rel="icon" type="image/png" sizes="16x16" href="resources/images/favicon-16x16.png">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">	
<style>
	@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
	
	html, body {
		margin: 0;
		height: 100%;
		overflow-x: hidden;
		overflow-y: auto;
		font-family: "Nanum Gothic", serif;
		overflow-x: hidden;
	}
	
	.popup-outer {
		width: 1250px;
		height: 860px;
		margin: 0;
		box-sizing: border-box;
	}
	
	.popup-title {
		margin: 0;
		width: 100%;
		height: 50px;
		background-color: RGB(65, 125, 122) ;
		font-size: 25px;
		padding: 10px;
		color: white;
		box-sizing: border-box;
	}
	
	.popup-content {
		padding: 20px;
	}
	
	.searchBar {
		margin-left: 15px;
	}
	
	.searchBar>select {
		height: 36px;
		width: 70px;
		border: 1px solid lightgray;
		border-radius: 3px;
		font-family: inherit;
		display: inline-block;
	}
	
	.searchBar>input {
		padding: 5px;
		width: 220px;
		height: 25px;
		border: 1px solid lightgray;
		border-radius: 3px;
	}
	
	.searchBar>svg {
		fill: rgb(26, 188, 156);
		width: 37px;
		display: inline-block;
		vertical-align: bottom;
		height: 100%;
	}
	
	.searchBar>svg:hover {
		cursor: pointer;
		opacity: 70%;
	}
	div {
		box-sizing: border-box;
	}
	
	#wrap {
		margin-top: 20px;
		margin-left: 15px; width : 1200px;
		height: 700px;
		width: 1200px;
	}
	
	#wrap>div {
		float: left;
		margin-right: 18px;
	}
	
	#groupArea {
		height: 100%;
		width: 380px;
		border: 1px solid lightgray;
	}
	
	.group_tree, .group_tree ul {
		list-style: none;
		padding-left: 17px;
	}
	
	.group_tree a {
		text-decoration: none;
		color: rgb(77, 77, 77);
	}
	
	.group_tree *::before {
		width: 17px;
		height: 15px;
		display: inline-block;
	}
	
	.group_tree a:before {
		content: '???';
		font-family: fontello;
	}
	
	.group_tree label {
		cursor: pointer;
	}
	
	.group_tree label:before {
		content: '\E802';
		font-family: fontello;
		color: gray;
	}
	
	.group_tree input[type="checkbox"] {
		display: none;
	}
	
	.group_tree input[type="checkbox"]:checked ~ul{
		display: none;
	}
	
	.group_tree input[type="checkbox"]:checked+label:before {
		content: '\E801';
	}
	
	.group_tree ul, .group_tree li {
		padding-top: 10px;
		height: 35px;
	}
	
	#listArea {
		height: 100%;
		width: 380px;
		border: 1px solid lightgray;
	}
	
	.checkBox {
		zoom: 1.7;
	}
	
	.boardTable {
		width: 100%;
		height: auto;
	}
	
	.boardTable, .boardTable th, .boardTable td {
		border-width: 1px 0;
		border-collapse: collapse;
		text-align: center;
		font-size: 15px;
	}
	
	.boardTable th {
		background-color: #999;
		color: #fff;
		height: 35px;
	}
	
	.boardTable td {
		border-color: lightgray;
		border-style: solid;
		height: 35px;
	}
	
	.boardTable tr:hover td {
		background-color: rgb(224, 224, 224);
		cursor: pointer;
	}
	
	
	#selectArea {
		height: 100%;
		width: 380px;	
	}
	
	#selectArea>div {
		width: 100%;
		height: 50%;	
	}
	#selectArea>div>div{border: 1px solid lightgray;}
	
	.smallBtn {
		width: 60px;
		height: 25px;
		border: 0px;
		border-radius: 5px;
		background: RGB(65, 125, 122) ; /* ?????? : rgb(190, 190, 190) */
		color: white;
		font-size: 12px;
	}
	
	.middleBtn {
		width: 90px;
		height: 35px;
		border: 0px;
		border-radius: 5px;
		background: RGB(65, 125, 122) ; /* ?????? : rgb(190, 190, 190) */
		color: white;
		font-size: 15px;
	}
	
	.middleBtn:hover {
		cursor: pointer;
	}
	
	.bigBtn {
		width: 140px;
		height: 40px;
		border: 0px;
		border-radius: 5px;
		background: orange; /* ?????? : rgb(190, 190, 190) */
		color: white;
		font-size: 18px;
		display: inline-block;
	}
	
	.bigBtn:hover {
		cursor: pointer;
	}
	#btnArea {
		margin-left: 910px;
	}
	
	
	
	.btnDel{
		color:rgbRGB(65, 125, 122) ;
	}
	
	.deptList{
		cursor:pointer;
	}
</style>
</head>

<body>
	<div class="popup-outer">
		<div class="popup-title">?????????</div>
		<div class="popup-content">
			<!-- ????????? -->
			<div class="searchBar">
				<select id="condition" name="condition">
					<option value="empName">??????</option>
					<optio value="rankTitle">??????</option>
					<option value="jobTitle">??????</option>
					<option value="deptTitle">??????</option>
				</select> <input id="keyword" type="text" placeholder="??????/??????/??????/?????? ??????">
				<svg  onclick="searchEmpProfile();" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
					fill="black" width="48px" height="48px">
									<path
						d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" />
									<path d="M0 0h24v24H0z" fill="none" /></svg>
			<button type="button" class="bigBtn" id="signerSubmit" style="margin-left: 680px;">??????</button>
			
			
			<script>
				$(document).on("click","#signerSubmit",function(){
					
					//window.close();
					
					var v = $(".signSel tbody").text();
					if(v==""){
						alert("????????? 1??? ?????? ???????????? ?????????.");
						return false;
					}else{
						
						// ????????? ?????? ??????????????? ??????
						var m1 = $("#signList tbody tr").eq(0).children().eq(2).text();
						opener.document.getElementById("m1").value=m1;
						
						
						var n1 = $("#signList tbody tr input[name=empNo]").eq(0).val();
						if(n1){
							opener.document.getElementById("n1").value=n1 + "@smartdoctor.com";
						}						
						
						
						//????????? ????????? ????????? ??????
						var signerId=new Array();
						var cnt = $("#signList tbody input[name=empNo]").length;
						for(var i=0;i<cnt;i++){
							var signerIdStr = $("#signList tbody input[name=empNo]").eq(i).val();
							signerId.push(signerIdStr);
						}
						//????????? ?????? ????????? ??????
						var signerName=new Array();
						cnt = $("#signList tbody input[name=empName]").length;
						for(var i=0;i<cnt;i++){
							signerName.push($("#signList tbody input[name=empName]").eq(i).val());
						}

						
						opener.getSid(signerId);
						opener.getSname(signerName);
						
						
						// ????????? ???
						var i1 = $("#refList tbody tr").eq(0).children().eq(2).text();
						opener.document.getElementById("i1").value=i1;
						
						var l1 = $("#refList tbody tr input[name=empNo]").eq(0).val();
						if(l1){
						opener.document.getElementById("l1").value=l1 + "@smartdoctor.com";
						}
			
						
						// ????????????????????? ??????/????????? ??????
						var refName=new Array();
						cnt = $("#refList tbody input[name=empName]").length;
						for(var i=0;i<cnt;i++){
							refName.push($("#refList tbody input[name=empName]").eq(i).val());
						}
						
						
						// ??????????????? ????????? ????????? ??????
						var refId=new Array();
						cnt = $("#refList tbody input[name=empNo]").length;
						for(var i=0;i<cnt;i++){
							var refStr = $("#refList tbody input[name=empNo]").eq(i).val();
							refId.push(refStr);
						}
						
						
						opener.getRefId(refId);
						opener.getRefName(refName);
						
						window.self.close();
					
					}
				});
			</script>
			 
			</div>
			<div id="wrap">
				<!-- ?????? ??????????????? -->
				<div id="groupArea">
					<ul class="group_tree">
						<li><input type="checkbox" id="root"> 
						<label for="root" class="deptList" key="all"> ????????????</label>
							 <ul>
			                        <li class="deptList" key="D1">??? ?????????</li>
			                        <li class="deptList" key="D2">??? ?????????</li>
			                        <li class="deptList" key="D3">??? ?????????</li>
			                        <li class="deptList" key="D4">??? ???????????????</li>
			                    </ul>
							</li>
					</ul>
				</div>


				<!-- ?????? ??????????????? -->
				<div id="listArea">
					<div style="width:100%;height:100%;overflow:auto;">
						<table class="boardTable empList">
							<thead>
								<tr>
									<th width="40"><input class="checkBoxAll" type="checkbox" id="checkall" style="zoom: 1.7;"></th>
									<th>??????</th>
									<th>??????</th>
									<th width="120">??????/??????</th>
								</tr>
							</thead>
							<tbody>
							<!-- ???????????? ???????????? ??????????????? -->
							</tbody>
						</table>
					</div>	
				</div>

				<!-- ?????? ???????????? -->
				<div id="selectArea">
					<div id="signArea">
						<button class="middleBtn selSigner">????????????</button>
						<h2 style="display:inline-block;margin:0;">????????????</h2>
						<br><br>
						<div style="width:100%;height:280px;overflow:auto;">
								<table id="signList" class="boardTable signSel">
									<thead>
										<tr>
											<th width="100">??????</th>
											<th>??????</th>
											<th width="120">??????/??????</th>
											<th width="40"></th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
						</div>	
					</div>
					
					<div id="refArea">
						<button class="middleBtn selRef">????????????</button>
						<h2 style="display:inline-block;margin:0;">??????</h2>
						<br><br>
						<div id="signSelDiv">
							<div style="width:100%;height:280px;overflow:auto;">
								<table id="refList" class="boardTable refSel">
								<thead>
									<tr>
										<th width="100">??????</th>
										<th>??????</th>
										<th width="120">??????/??????</th>
										<th width="40"></th>
									</tr>
								</thead>
								<tbody></tbody>
								</table>
							</div>	
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
		//????????? ???????????? ??????
		$("#checkall").click(function(){
		     //??????????????????
		     if($("#checkall").prop("checked")){
		            //input????????? name??? chk??? ???????????? ????????? checked????????? true??? ??????
		            $("input[name=chk]").prop("checked",true);
		            //????????? ???????????????
		     }else{
		            //input????????? name??? chk??? ???????????? ????????? checked????????? false??? ??????
		            $("input[name=chk]").prop("checked",false);
		     }
		 });
		
		$(document).on("click",".btnDel",function(){
			$(this).parent().parent().remove();
		});
		
		
		
		var value1 = "";
		
		$(document).on("click",".selSigner",function(){
			
			 $('.checkBox:checked').each(function() {
				var empNo = $(this).parent().parent().children("input[name=empNo]").clone().wrapAll("<div/>").parent().html();
				var nameVal = $(this).parent().parent().children("input[name=empName]").clone().wrapAll("<div/>").parent().html();
				var name = $(this).parent().parent().children(".name").clone().wrapAll("<div/>").parent().html();
				var dept = $(this).parent().parent().children(".dept").clone().wrapAll("<div/>").parent().html();
				var job = $(this).parent().parent().children(".job").clone().wrapAll("<div/>").parent().html();
				
			
				var regExp = new RegExp($(this).parent().parent().children("input[name=empNo]").val());
		       	
				//$(this).removeAttr("checked");
				
				if(regExp.test($(".signSel tbody").html())){
					console.log("??????");
					return false;
				}
				 
				value1 += '<tr>' +  empNo +nameVal+ name + dept + job
		       			+ '<td><span class="material-icons btnDel">remove_circle</span></td>'
		       			+ '</tr>';
		       			
				$(".signSel tbody").append(value1);
				value1="";
			
				console.log(regExp);
				console.log(dept);
				console.log(nameVal);
				console.log(name);
				console.log(job);
		   });
			
		});
		
		
	</script>

	<script>
	
	// ??????????????? ??????
	var value2 = "";
	
	$(document).on("click",".selRef",function(){
		$('.checkBox:checked').each(function() {
			var empNo = $(this).parent().parent().children("input[name=empNo]").clone().wrapAll("<div/>").parent().html();
			var nameVal = $(this).parent().parent().children("input[name=empName]").clone().wrapAll("<div/>").parent().html();
			var name = $(this).parent().parent().children(".name").clone().wrapAll("<div/>").parent().html();
			var dept = $(this).parent().parent().children(".dept").clone().wrapAll("<div/>").parent().html();
			var job = $(this).parent().parent().children(".job").clone().wrapAll("<div/>").parent().html();
			
		
			var regExp = new RegExp($(this).parent().parent().children("input[name=empNo]").val());
	       	
			//$(this).removeAttr("checked");
			
			if(regExp.test($(".refSel tbody").html())){
				console.log("??????");
				return false;
			}
			 
			value2 += '<tr>' + empNo+nameVal+ name + dept + job
	       			+ '<td><span class="material-icons btnDel">remove_circle</span></td>'
	       			+ '</tr>';
	       			
			$(".refSel tbody").append(value2);
			value2="";
		});
		
	});	
	</script>
	
	
	<script>
	$(function(){
		//selectNoList();

		/* ????????? ????????? ?????? ?????? */
		$(".deptList").click(function(){
			keyword =$(this).attr("key");
			
			$.ajax({
				url: "deptEmpList.gr",
				type: "post",
				data:{"keyword":keyword}, 
				async: false,
				success: function(eList){
				 	console.log(eList);
					var value = "";
					
					if(eList.length == 0){ // ???????????? ???????????? ??????
		            	value = '<td colspan="4">????????? ????????? ????????????. </td>';
					}else{ // ???????????? ???????????? ?????? ??????
		            	//console.log(eList[0]);
						for(var i in eList){
							
							var empName = eList[i].empName;
							var empNo = eList[i].empNo;
							var deptName = eList[i].deptName;
							var jobName = eList[i].jobName;
							
							value += '<tr><input type="hidden" name="empNo" value="'+empNo+'">' +
									 '<input type="hidden" name="empName" value="'+empName+'">' +
									 '<td><input name="chk" class="checkBox" type="checkbox"></td>' +
									 '<td class="name">'+empName + '</td>' +
									 '<td class="dept">'+ deptName + '</td>' +
									 '<td class="job" width="120">'+jobName+'</td></tr>';							 
							
						}
						$(".empList tbody").html(value);
					} 
					
				},
				error:function(){
					console.log("?????? ????????? ?????? ??????");
				}
			});
		});

	});
	
	
	function searchEmpProfile(){
		condition = $("#condition option:selected").val();
		keyword = $("#keyword").val();
		if(keyword==''){
			alert("???????????? ???????????????");
			return;
		}
		$.ajax({
			url:"empListSearch.gr",
			type:"get",
			data:{condition:condition,
				 keyword:keyword},
			success: function(list){
			console.log(list);
				var value = "";
				
				if(list.length == 0){ // ???????????? ???????????? ??????
					value = '<td colspan="4">????????? ????????? ????????????. </td>';
				}else{ // ???????????? ???????????? ?????? ??????
	            
					for(var i in list){

						var empName = list[i].empName;
						var empNo = list[i].empNo;
						var jobName = list[i].jobName;
						var deptName = list[i].deptName;
						
						value += '<tr><input type="hidden" name="empNo" value="'+empNo+'">' + 
						 '<td><input name="chk" class="checkBox" type="checkbox"></td>' +
						 '<td>'+empName + '</td>' +
						 '<td>'+ deptName + '</td>' +
						 '<td width="120">'+jobName+'</td></tr>';	
					}
				}
				$(".empList tbody").html(value);
			},
			error:function(){
				console.log("?????????????????? ?????? ??????");
			}
			
		})
		
	}
	</script>
	
	
	
	<script>
		$(document).ready(function() {
							// ?????? ??? ????????? HTML ????????? ????????? ???????????? ????????? ???????????? ??????.
							var strWidth;
							var strHeight;

							//innerWidth / innerHeight / outerWidth / outerHeight ?????? ????????????
							if (window.innerWidth && window.innerHeight
									&& window.outerWidth && window.outerHeight) {
								strWidth = $(".popup-outer").outerWidth()
										+ (window.outerWidth - window.innerWidth);
								strHeight = $(".popup-outer").outerHeight()
										+ (window.outerHeight - window.innerHeight);
							} else {
								var strDocumentWidth = $(document).outerWidth();
								var strDocumentHeight = $(document)
										.outerHeight();

								window.resizeTo(strDocumentWidth,
										strDocumentHeight);

								var strMenuWidth = strDocumentWidth
										- $(window).width();
								var strMenuHeight = strDocumentHeight
										- $(window).height();

								strWidth = $(".popup-outer").outerWidth()
										+ strMenuWidth;
								strHeight = $(".popup-outer").outerHeight()
										+ strMenuHeight;
							}

							//resize
							window.resizeTo(strWidth, strHeight);
		});
	</script>

</body>
</html>