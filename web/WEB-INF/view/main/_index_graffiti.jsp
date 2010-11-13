<%--
  Created by IntelliJ IDEA.
  User: helols
  Date: 2009. 10. 22
  Time: 오후 4:20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(function(){
        var _maxTop = 0, _tStartTop = 0, _tEndTop = 0;
        var graffitiList = $('#tbGraffitiList'), graffitiWindow = $('.graffitiWindow'), graffitiLoadDateTime = $('#txtGraffitiLoadDateTime'),
        	graffitiContent = $("#txtGraffitiContent"); 
        graffitiList
            .draggable({
                         axis:'y'
                        ,contanment:'parent'
                        ,cursor : 'move'
                        ,start:function(ev,ui){
                            _tStartTop = graffitiList.css('top').replace('px','');
                            _maxTop = $('.graffitiWindow').height()- graffitiList.height();
                        }
                        ,stop:function(ev, ui){
                            _tEndTop = graffitiList.css('top').replace('px','');
                            var diffTop = parseInt(_tStartTop) - parseInt(_tEndTop);
                            if(diffTop > 0 && graffitiWindow.height() < graffitiList.height()){ // up drag
                                if(_maxTop > _tEndTop ){
                                    graffitiList.animate({top:_maxTop},{duration: 500, easing: 'backout'});
                                }
                            }else if(diffTop < 0 ||  graffitiWindow.height() > graffitiList.height()){ // down drag
                                if(_tEndTop > 0  ||  graffitiWindow.height() > graffitiList.height() ){
                                    graffitiList.animate({top:'0'},{duration: 500, easing: 'backout'});
                                }
                            }
                        }
                });
        refresh_graffitiList();

        // 낙서장을 지속적으로 갱신시켜주는 재귀함수!
        function refresh_graffitiList(){
            $.getJSON(
                '<c:url value="/main/graffitiList" />',
                {loadDateTime:graffitiLoadDateTime.val()},
                function(returnData, textStatus){
                    if(textStatus === 'success'){
                    	makeGraffitiList(returnData.graffitiList);
                        graffitiLoadDateTime.val(returnData.loadDateTime);
                    }
                    setTimeout(refresh_graffitiList, 10000);
                }
            );
        }

        graffitiContent.live( 'keydown', function (event){
			if (event.keyCode === 13) {
				doWrite();
		    }
		});
		
       	$('#btnGraffitiWrite').live( 'click', function(){
         	doWrite();
		});

		function doWrite() {
			var graffitiContent = $('#txtGraffitiContent'),
				content = $.trim(graffitiContent.val());
			if(!content){
				$.growlUI('기록할 낙서를 입력하세요.');
				return;
			}
           else if(content.length >= 50){
               $.growlUI('낙서가 너무 깁니다. 조금 줄여주시죠? <br/>허용은 [content.length <= 50] 입니다^^;');
               return;
           }

           $.post(
               '<c:url value="/main/graffitiWrite" />',
               { contents:content, lastGraffitiID: $('#tbGraffitiList tr:first-child').attr('id')} ,
               function(data,status){
                   if(status === "success"){
                       if(data.writeResult){
                           graffitiContent.val('');
                           $.growlUI('OK! 낙서를 <br/> 추가했습니다.');
                           makeGraffitiList(data.graffitiList);
                       }
                       else{
                           $.growlUI('warning', '낙서 작성 중 <br/> 오류가 발생했습니다.');
                       }
                   }
               },
               "json"
			);		
		}

		function makeGraffitiList( list) {
			$.each(list, function(idx, graffiti){
            	if(graffiti == null || graffiti.id ==  $('#tbGraffitiList tr#'+graffiti.id+'').attr('id')) return;
               	var rowData = "<tr id='" + graffiti.id +"'>";
    			rowData += "<td class='gt_avatar'><img src='" + graffiti.writerAvatar + "&s=30' class='g_avatar' /></td>";
    			rowData += "<td class='gt_content'>"+ graffiti.contents +" <br/>";
    			rowData += "<strong>" + graffiti.writerName + "</strong>";
    			rowData += "<span class='gt_writedate'> on "+graffiti.formatedDate+"</span></td>";
    			rowData += "</tr>";
    			graffitiList.prepend(rowData);
    		});
        }

//        $('.graffiti').jScrollPane({
//            wheelSpeed:25
//        });
    });
</script>
<div class="graffiti">
    <div class="titlebox">
        <ul class="mbtitle">
            <li>낙서장</li>
        </ul>
    </div>
    <div class="graffitiWriter">
        <sec:authorize ifAnyGranted="ROLE_MEMBER, ROLE_ADMIN">
            <input type="text" id="txtGraffitiContent" maxlength="50" />
            <input type="button" id="btnGraffitiWrite" value="쓰기" />
        </sec:authorize>
    </div>
    <div class="graffitiWindow">
        <input type="hidden" id="txtGraffitiLoadDateTime" value="" />
        <table id='tbGraffitiList' class="graffitiList" >            
        </table>
    </div>
</div>