<%--
    Custom tag which will render the photo from kivbookImage object.
--%>

<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="kivbookImage" required="true" rtexprvalue="true" type="cz.zcu.pia.valesz.core.domain.KivbookImage" %>
<%@ attribute name="alt" required="true" rtexprvalue="true" %>
<%@ attribute name="classes" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<img src="data:image/png;base64,${kivbookImage.imageData}" class="img-thumbnail
    <c:if test="${classes != null}">${classes}</c:if>
    "
     alt="${alt}"
    style="max-height: 128px; max-width: 128px;"
/>