<%@tag pageEncoding="UTF-8" body-content="empty" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="path" required="true" %>
<%@attribute name="text" required="true" %>
<div>
    <label for="${path}">${text}</label>
    <form:input path="${path}" type="text"/>
    <form:errors path="${path}"/>
</div>
