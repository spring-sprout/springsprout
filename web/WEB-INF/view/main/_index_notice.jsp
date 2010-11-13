<%--
  Created by IntelliJ IDEA.
  User: helols
  Date: 2009. 10. 22
  Time: 오전 2:39:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
    .notice {
        overflow:scroll;
    }
</style>

<div class="notice left">
    <div class="titlebox">
        <ul class="mbtitle">
            <li>
                ${notice.created}:: ${notice.title}
            </li>
        </ul>
    </div>
    <div class="cbox notice_c">
        <div>
            ${notice.contents}
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $('.notice').jScrollPane({
            wheelSpeed:25    
        });
    });
</script>