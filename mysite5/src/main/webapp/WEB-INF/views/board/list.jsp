<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board/list"
					method="post">
					<input type="hidden" id="page" name="page" value="1"> <input
						type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>${count-5*(param.page-1)-status.index}</td>
							<td style='text-align:left; padding-left:${20*vo.depth}px;'>
								<c:if test="${vo.depth>0 }">
									<img
										src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
								</c:if> 
								<c:choose>
									<c:when test="${vo.status == true}">
										<a href=" ${pageContext.servletContext.contextPath }/board/view/${vo.no }?page=${param.page }&kwd=${param.kwd }">
											${vo.title } </a>
									</c:when>
									<c:otherwise>
									삭제된 게시물 입니다.	
								</c:otherwise>
								</c:choose>
							</td>
							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							<td><c:if
									test='${(not empty authUser) and authUser.no == vo.user_no and vo.status == true}'>
									<a
										href="${pageContext.servletContext.contextPath }/board/delete/${vo.no }"
										class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<!-- pager -->
				<div class="pager">
					<ul>
						<li>
							<c:choose>
								<c:when test="${page_count!=0 }">
									<a
										href="${pageContext.servletContext.contextPath }/board/list?page=${(page_count*5)}&kwd=${kwd }">
										◀ </a>
								</c:when>
								<c:otherwise>◀</c:otherwise>
							</c:choose>
						</li>

						<c:forEach begin='1' end='5' step='1' var='i'>

							<li
								<c:if test="${page==(page_count*5)+i }">  </c:if>>
								<c:choose>
									<c:when test="${(page_count*5)+i <= (count-1)/5+1}">
										<a
											href="${pageContext.servletContext.contextPath }/board/list?page=${page_count*5+i }&kwd=${kwd}">
											${page_count*5+i } </a>
									</c:when>
									<c:otherwise>${page_count*5+i }</c:otherwise>
								</c:choose>
							</li>

						</c:forEach>

						<li><c:choose>
								<c:when test="${(page_count*5)+6 <= (count-1)/5+1}">
									<a
										href="${pageContext.servletContext.contextPath }/board/list?page=${(page_count*5)+6}&kwd=${kwd}">
										▶</a>
								</c:when>
								<c:otherwise>▶</c:otherwise>
							</c:choose>
						
					</ul>
				</div>
				<c:if test="${!empty authUser }">
					<div class="bottom">
						<a
							href="${pageContext.servletContext.contextPath }/board/write?kwd=${kwd}"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>