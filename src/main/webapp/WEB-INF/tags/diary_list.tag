<%@tag import="com.example.otherClass.Db"%>
<%@tag import="com.example.modelClass.User"%>
<%@tag import="java.util.List"%>
<%@tag import="com.example.modelClass.Diary"%>
<%@tag description="diaryList" pageEncoding="UTF-8"%>

<% List<Diary> diaryList = (List) request.getAttribute("diaryList"); %>

<% for (Diary diary : diaryList) {

        User diaryUser = Db.userRepository().get(diary.getUserId());
        if (diaryUser != null) {%>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <a href="detail?diary_id=<%=diary.getId()%>"><%=diary.getDateOfDiary()%></a>
            <p class="flow-text"><%=diary.getContent()%></p>
        </div>
        <div class="card-action">
            <a href="profile?user_id=<%=diary.getUserId()%>"><%=diaryUser.toString().toUpperCase()%></a>
        </div>
    </div>
</div>
<% } } %>
