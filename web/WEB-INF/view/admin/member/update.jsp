<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script type="text/javascript">
			$(function() {
				var a = "";
				$('#m_roles').find('input').each(function(){a += $(this).val()+","});
				$("ul, li").disableSelection();

				var $roles = $('#roles'), $m_roles = $('#m_roles');

				// let the gallery items be draggable
				$('li',$roles).draggable({
					cancel: 'a.ui-icon',// clicking an icon won't initiate dragging
					revert: 'invalid', // when not dropped, the item will revert back to its initial position
					containment: 'document', // stick to demo-frame if present
					cursor: 'move'

				});

				$('li',$m_roles).draggable({
					cancel: 'a.ui-icon',// clicking an icon won't initiate dragging
					revert: 'invalid', // when not dropped, the item will revert back to its initial position
					containment: 'document', // stick to demo-frame if present
					cursor: 'move'
				});

				$m_roles.droppable({
					accept: '#roles > li',
					activeClass: 'ui-state-highlight',
					drop: function(ev, ui) {
						deleteRole(ui.draggable);
					}
				});

				function deleteRole($item) {
					$item.css('left','');
					$item.css('top','');
					$item.find('a').removeClass('ui-icon-circle-plus');
					$item.find('a').addClass('ui-icon-circle-close');
					$item.appendTo($m_roles);
				}

				$roles.droppable({
					accept: '#m_roles > li',
					activeClass: 'ui-state-highlight',
					drop: function(ev, ui) {
						revertRole(ui.draggable);
					}
				});

				function revertRole($item) {
					$item.css('left','');
					$item.css('top','');
					$item.find('a').removeClass('ui-icon-circle-close');
					$item.find('a').addClass('ui-icon-circle-plus');
					$item.appendTo($roles);
				}

				$('#memberUpdate ul > li').click(function(ev) {
					var $item = $(this);
					var $target = $(ev.target);
					if($target.is('a.ui-icon-circle-plus')){
						deleteRole($item);
					}else if($target.is('a.ui-icon-circle-close')){
						revertRole($item);
					}
					return false;
				});
			});
		</script>
   	<div id="memberUpdate">
		<div id="memeberInfo" style="float: left" >
			<dl>
				<dt>
					<label>이메일</label>
				</dt>
				<dd>
					<input type = "text" id ="email" name="email" readonly="readonly" class="text" value="${member.email}">
				</dd>
				<dt>
					<label>이름</label>
		        </dt>
				<dd>
					<input type = "text" id ="name" name="name" class="text"  value="${member.name}">
				</dd>
				<dt>
					<label>새로운 비밀번호</label>
		        </dt>
		        <dd>
					<input type = "password" id ="password" name="password" class="text" value="">
				</dd>
				<dt>
					<label>블로그URL</label>
				</dt>
				<dd>
					<input type = "text" id ="blog" name="blog" class="text" value="${member.blog}">
				</dd>
				<dt>
					<label>가입상태</label>
				</dt>
				<dd>
					<select id="status" name="status">
						<c:forEach var="status" items="${memberStatusList}">
							<c:if test="${member.status.value == status.value}">
								<option selected="selected" value="${status.value}">${status.descr}</option>
							</c:if>
							<c:if test="${member.status.value != status.value}">
								<option value="${status.value}">${status.descr}</option>
							</c:if>
						</c:forEach>
					</select>
				</dd>
				<dd>
					<input type="button" id="cancle" value="취소" onclick="javascritpt:$('#main').unblock();$.unblockUI();">
					<input type="button" id="saven" value="저장" onclick="javascritpt:updateMember('${member.id}');">
				</dd>
			</dl>
		</div>
		<div id="roleList">
			<label>봄싹 권한리스트</label>
			<ul id="roles" class="roles">
				<c:forEach var="role" items="${allRoles}">
					<li>
						<span>${role.descr}</span><a class="ui-icon ui-icon-circle-plus" title="" href="#">aa</a>
						<input type="hidden" value="${role.id}">
					</li>
				</c:forEach>
			</ul>

		</div>

		<div id="arrow" ></div>

		<div id="memeberRoleList">
			<label>사용자 권한</label>
			<ul id="m_roles" class="">
				<c:forEach var="role" items="${member.roles}">
					<li>
						<span>${role.descr}</span><a class="ui-icon ui-icon-circle-close" title="" href="#"></a>
						<input type="hidden" value="${role.id}">
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>