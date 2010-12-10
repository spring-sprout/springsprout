<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="value" required="true"%>

<table cellspacing="0" cellpadding="0" border="0" width="100%">
    <tbody>
    <tr>
        <td valign="center" class="colour-bar-cont" style="width: ${value}%;">
            <div class="colour-bar"></div>
        </td>
        <td style="width: ${100-value}%;">&nbsp;&nbsp;&nbsp;${value}%</td>
    </tr>
    </tbody>
</table>