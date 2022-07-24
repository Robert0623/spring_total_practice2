<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>

<body>
    <h2>게시물 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form id="form" action="" method="">
        <input type="hidden" name="bno" value="${boardDto.bno}">
        <input type="text" name="title" value="<c:out value='${boardDto.title}'/>" ${mode=="new" ? '' : 'readonly="readonly"'}>
        <textarea name="content" ${mode=="new" ? '' : 'readonly="readonly"'}><c:out value="${boardDto.content}"/></textarea>
        <button type="button" id="writeBtn">글쓰기</button>
        <button type="button" id="modifyBtn">수정</button>
        <button type="button" id="removeBtn">삭제</button>
        <button type="button" id="listBtn">목록</button>
    </form>
<script>
    $(document).ready(function() {
       $('#listBtn').on("click", function() {
          location.href="<c:url value='/board/list?page=${page}&pageSize=${pageSize}'/>";
       });
       $('#removeBtn').on("click", function() {
          if(!confirm("정말로 삭제하겠습니까?")) return;
          let form = $('#form');
          form.attr("action", "<c:url value='/board/remove?page=${page}&pageSize=${pageSize}'/>");
          form.attr("method", "post");
          form.submit();
       });
       $('#writeBtn').on("click", function() {
           let form = $('#form');
           form.attr("action", "<c:url value='/board/write'/>");
           form.attr("method", "post");
           form.submit();
       });
        $('#modifyBtn').on("click", function() {
            //1. 읽기 상태이면 수정 상태로 변경
            let form = $('#form');
            let isReadOnly = $("input[name=title]").attr("readonly");
            if(isReadOnly=='readonly') {
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("h2").html("게시물 수정");
                $("#modifyBtn").html("등록");
                return;
            }
            //2. 수정 상태면 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify?page=${page}&pageSize=${pageSize}'/>");
            form.attr("method", "post");
            form.submit();


            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>
</body>