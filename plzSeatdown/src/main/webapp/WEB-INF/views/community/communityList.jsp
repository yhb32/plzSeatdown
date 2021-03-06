<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${contextPath}/resources/css/boardcss.css" />

<style>
button:focus{
	outline:0 !important;
	box-shadow:none !important;
}
input:focus{
	outline:0 !important;
	box-shadow:none !important;
}
select:focus{
	outline:0 !important;
	box-shadow:none !important;
}
</style>
<title>PLEASE SEATDOWN</title>
</head>
<body>

    <jsp:include page="../common/header.jsp"/>
	<jsp:include page="../common/nav.jsp"/>
	
    <div class="container my-5">
        <div class="pb-4">
            <h5>커뮤니티 게시판</h5>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table" id="list-table">
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">카테고리</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성자</th>
                            <th scope="col" class="text-center">조회수</th>
                            <th scope="col" class="text-center">작성일</th>
                        </tr>
                    </thead>
                    <tbody class="commu-body">
                    	<c:if test="${empty list }">
                    		<tr>
                    			<th colspan="6" class="text-muted text-center" style="font-size:13px;">존재하는 게시글이 없습니다.</th>
                    		</tr>
                    	</c:if>
                    	<c:if test="${!empty list }">
                    		<c:forEach var="comm" items="${list}" varStatus="vs">
	                    		<tr>
		                            <td scope="row">${comm.communityNo}</td>
		                            <td>${comm.categoryName}</td>
		                            <td>${comm.communityTitle}&nbsp;
		                           		<c:if test="${comm.replyCount != 0}">
		                            		<span style="color:rgb(145, 126, 198);">[${comm.replyCount}]</span>
		                            	</c:if>
		                            	<c:if test="${fn:contains(comm.communityContent, '<img src=')}">
		                            		<img src="${contextPath}/resources/images/gallery.png" style="width:20px; height:20px;">
		                            	</c:if>
		                            	
		                            </td>
		                            <td >${comm.memberNickname}</td>
		                            <td class="text-center">${comm.communityCount}</td>
		                            <td>${comm.communityModifyDate}</td>
	                        	</tr>
                    		</c:forEach>
                    	</c:if>
                    </tbody>
                </table>
            </div>
            
            <div class="col-md-12">
            <c:if test="${!empty loginMember && loginMember.memberStatus == 'Y'}">
            	<button type="button" class="btn btn-sm float-right" id="insertBtn"
            		onclick="location.href = 'insertForm';">글쓰기</button>
            </c:if>
            </div>
            
            <!-- 페이징바 -->
            <div class="col-md-12 d-flex justify-content-center">
                <ul class="pagination pagination-info">
                	<c:if test="${pInf.currentPage > 1}">
	                	<li class="page-item">
			                    <a class="page-link" 
			                    	href=" 
			                    	<c:url value="list">
			                    		<c:if test="${!empty param.searchKey }">
							        		<c:param name="searchKey" value="${param.searchKey}"/>
							        		<c:param name="searchValue" value="${param.searchValue}"/>
							        	</c:if>
							        	
							        	<c:if test="${!empty param.searchCategory}">
							        		<c:param name="searchCategory" value="${param.searchCategory}"/>
							        	</c:if>
			                    		<c:param name="currentPage" value="1"/>
			                    	</c:url>
		                    	">
				                    &lt;&lt;
				                </a>
			            </li>
	                    <li class="page-item">
	                    	<!-- 이전으로 -->
	                    	<a class="page-link" href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchCategory}">
							        	<c:param name="searchCategory" value="${param.searchCategory}"/>
							        </c:if>
		                    		<c:param name="currentPage" value="${pInf.currentPage-1}"/>
		                    	</c:url>
	                    	">PREV</a>
	                    </li>
                    </c:if>
                    
                    <c:forEach var="p" begin="${pInf.startPage}" end="${pInf.endPage}">
                    
                    	<c:if test="${p == pInf.currentPage}">
                    		<li class="active page-item">
                    			<a class="page-link">${p}</a>
                    		</li>
                    	</c:if>
                    	
                    	<c:if test="${p != pInf.currentPage}">
                    		<li class="page-item">
                    			<a class="page-link"
										href=" 
                                        <c:url value="list">
                                            <c:if test="${!empty param.searchKey }">
                                                <c:param name="searchKey" value="${param.searchKey}"/>
                                                <c:param name="searchValue" value="${param.searchValue}"/>
                                            </c:if>
                                            
                                            <c:if test="${!empty param.searchCategory}">
									        	<c:param name="searchCategory" value="${param.searchCategory}"/>
									        </c:if>
                                            <c:param name="currentPage" value="${p}"/>
                                        </c:url>
                                    ">${p}</a>
                            </li>
	                    </c:if>
                    </c:forEach>
                    
                    <!-- 다음 -->
                    <c:if test="${pInf.currentPage < pInf.maxPage}">
                    	<li class="page-item">
                    		<a class="page-link"
                    			href="
                    			<c:url value="list">
                    				<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchCategory}">
							        	<c:param name="searchCategory" value="${param.searchCategory}"/>
							        </c:if>
		                    		<c:param name="currentPage" value="${pInf.currentPage+1}"/>
                    			</c:url>
                    			">NEXT</a>
                    	</li>
                    	
                    	<!-- 맨 끝으로(>>) -->
		                <li class="page-item">
		                    <a class="page-link" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchCategory}">
							        	<c:param name="searchCategory" value="${param.searchCategory}"/>
							        </c:if>
		                    		<c:param name="currentPage" value="${pInf.maxPage}"/>
		                    	</c:url>
	                    	">
			                    &gt;&gt;
			                </a>
		                </li>
                    </c:if>
                </ul>
            </div>
        </div>

        <form action="list" method="GET" class="text-center" id="searchForm" style="margin-bottom:100px;">
        	<select name="searchCategory" class="form-control" style="width:150px; display: inline-block;">
                <option value="">카테고리 선택</option>
                <option value="1">자유</option>
                <option value="2">양도</option>
                <option value="3">질문</option>
                <option value="4">공연후기</option>
            </select>
            <select name="searchKey" class="form-control" style="width:120px; display: inline-block;">
                <option value="title">글제목</option>
                <option value="content">내용</option>
                <option value="titcont">제목+내용</option>
            </select>
            <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
            <span class="input-group-btn">
                <button class="form-control btn btn-default" style="background-color: #917EC6; width:60px; display: inline-block;"><i class="fas fa-search"></i></button>
            </span>
        </form>	  

		<script>
        <!-- 페이지 이동 후에도 검색 결과가 검색창 input 태그에 표시되도록 하는 script -->
		$(function(){
			var searchKey = "${param.searchKey}";
			var searchValue = "${param.searchValue}";
			var searchCategory = "${param.searchCategory}";
			console.log("searchKey : "+searchKey);
			console.log("searchValue : "+searchValue);
			if(searchKey != "null" && searchValue != "null"){
				$.each($("select[name=searchKey] > option"), function(index, item){
					if($(item).val() == searchKey){
						$(item).prop("selected","true");
					} 
				});
				
				$("input[name=searchValue]").val(searchValue);
			}
			if(searchCategory != "null" || searchCategory != ""){
				$.each($("select[name=searchCategory] > option"), function(index, item){
					if($(item).val() == searchCategory){
						$(item).prop("selected","true");
					}
				});
			}
		});
		
		/* <c:forEach var="ct" items="${paramValues.searchCategory}" varStatus="vs">
			$.each($("select[name=searchCategory] > option"), function(index, item){
				if($(item).val() == "${ct}"){
					$(item).prop("selected","true");
				}
			});
		</c:forEach> */
		</script>

    </div>
    <jsp:include page="../common/footer.jsp"/>
    
    <script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				<c:if test="${empty loginMember}">
					// 로그인 페이지로 이동
					alert("로그인을 해주세요.")
					location.href="${contextPath}/community/list"
				</c:if>
				
				<c:if test="${!empty loginMember}">
				
					var commNo = $(this).parent().children().eq(0).text();
					// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
					<c:url var="detailUrl" value="detail">
	              		<c:if test="${!empty param.searchKey }">
		        			<c:param name="searchKey" value="${param.searchKey}"/>
			        	</c:if>
			        	<c:if test="${!empty param.searchValue }">
			        		<c:param name="searchValue" value="${param.searchValue}"/>
			        	</c:if>
			        	
			        	<c:if test="${!empty param.searchCategory}">
		        			<c:param name = "searchCategory" value="${param.searchCategory}"/>
		        		</c:if>
	                 	<c:param name="currentPage" value="${pInf.currentPage}"/>
	               	</c:url>
					
					location.href="${detailUrl}&no="+commNo;
					
				</c:if>
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
	</script>

</body>
</html>