<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s2c" tagdir="/WEB-INF/tags/s2c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(document).ready(function(){
        $('#memberSearchBtn').click(function(){
            initMemberList();
        });
    });

    function initMemberList(){
        var param = {s_name:$("input[name=s_name]").val(),s_email:$("input[name=s_email]").val(),p_size:$("input[name=p_size]").val()};
        loadMemberList(getUrl('list'),'#memberList',param,'#t_memberList');
    }

    function goPaging(page){
        var url = getUrl('list');
        if($('#allParam').val()){
            url += '?' + $('#allParam').val();
        }
        var param = {p_page:page,p_size:$('#p_size').val()};
        loadMemberList(url, '#memberList', param, '#t_memberList');
    }

    function oderbyMemberList(field,direction){
        var param = {o_field:field,o_direction:direction,p_size:$('#p_size').val()};
        loadMemberList(getUrl('list'), '#memberList', param, '#t_memberList');
    }

    function loadMemberList(url, src, param, target, callback){
        if(!callback)
            $(target).load(url+' '+src ,param);
        else
            $(target).load(url+' '+src ,param, callback);
    }
    function getUrl(action){
        return '<c:url value="/admin/member/"/>'+action;
    }
    function getMemberView(id){
        divLoad_get(getUrl('update/'+id+''), '', '#t_memberUpdate', function(){
            blockInMessge($('#t_memberUpdate'));
        });
    }


    function updateMember(id){
        var roles = "";
        $('#m_roles').find('input').each(function(){roles += $(this).val()+","});

        var param = {
                        name:$('#name').val(),
                        password:$('#password').val(),
                        blog:$('#blog').val(),
                        status:$('#status').val(),
                        id:id,
                        email:$('#email').val(),
                        roles:roles
                    };

        $.post(getUrl('update/'+id+''), param ,function(data, status){
            if(status === "success" && data.result === 'success'){
                $('#name_'+id).text(data.memberMgtDTO.name);
                $('#blog_'+id).text(data.memberMgtDTO.blog);
                $('#status_'+id).text(data.memberMgtDTO.status.descr);
                $('#rolesName_'+id).text(data.roleName);
                $('#main').unblock();$.unblockUI();
                defaultGrowlUI('저장을 완료했습니다.');
            }else{
                centerGrowlUI(data.errMessge);
            }
        },"json"
    );
}
</script>

<div id ="memberListBox">
	<!--  a id="addButton" href="<c:url value="/member/add"/>">회원 추가</a -->

	<div id = "memberSearchBox" style="width:50em;">
	  	   이름: <input type="text" name="s_name" id="s_name" />
    	이메일: <input type="text" name="s_email"/>
    	<input id = "memberSearchBtn"type="button" value="검색" />
	</div>
	<br>
	<div id="t_memberList">
		<div id ="memberList">
			<c:if test="${empty memberList}">
			회원 목록이 없습니다.
			</c:if>

			<c:if test="${! empty memberList}">
			<table id="mList" cellspacing="0" cellpadding="3" rules="none" frame="hsides" border="1" bordercolor="#999">
			    <tr>
			        <th class="email">이메일</th>
			        <th class="name">이름</th>
			        <th class="blog">블로그주소</th>
			        <th class="status">가입상태</th>
			        <th class="role">Role</th>
			        <th class="edit">Edit</th>
			    </tr>
			<c:forEach var="member" items="${memberList}">
			    <tr>
			        <td class="email" id="email_${member.id}">${member.email}</td>
			        <td class="name" id="name_${member.id}">${member.name}</td>
			        <td class="blog" id="blog_${member.id}">${member.blog}</td>
			        <td class="status" id="status_${member.id}">${member.status.descr}</td>
			        <td class="role" id="rolesName_${member.id}">
				        <c:forEach var="role" items="${member.roles}">
					        [${role.name}]
				        </c:forEach>
			        </td>
			        <td class="edit" style="cursor:pointer;" onclick="javascript:getMemberView('${member.id}')">수정</td>
			    </tr>
			</c:forEach>
			</table>
			</c:if>
			<div style="width: 50em;">
				<s2c:paging/>
			</div>
		</div>
	</div>

	<div id="t_memberUpdate" style="display: none;">
	</div>
</div>
