<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Dashboard"/>
<%@ include file="header.jsp" %>

<c:choose>
<%-- ================= DONOR VIEW ================= --%>
<c:when test="${sessionScope.user.role == 'DONOR'}">
  <div class="head">
    <h2>Your donations</h2>
    <a class="btn" href="${pageContext.request.contextPath}/add-food">Post surplus food</a>
  </div>

  <h3>Requests for your food</h3>
  <c:choose>
    <c:when test="${empty incoming}"><p class="empty">No requests yet. They will show up here when a receiver asks for your food.</p></c:when>
    <c:otherwise>
      <div class="scroll"><table>
        <tr><th>Food</th><th>Receiver</th><th>Phone</th><th>Status</th><th>Action</th></tr>
        <c:forEach var="r" items="${incoming}">
          <tr>
            <td><c:out value="${r.foodName}"/> (<c:out value="${r.quantity}"/>)</td>
            <td><c:out value="${r.receiverName}"/></td>
            <td><c:out value="${r.receiverPhone}"/></td>
            <td><span class="badge s-${r.status}">${r.status}</span></td>
            <td class="actions">
              <c:if test="${r.status == 'PENDING'}">
                <form action="${pageContext.request.contextPath}/manage-request" method="post">
                  <input type="hidden" name="requestId" value="${r.id}">
                  <button class="btn small" name="action" value="accept">Accept</button>
                  <button class="btn small ghost" name="action" value="reject">Reject</button>
                </form>
              </c:if>
              <c:if test="${r.status == 'ACCEPTED' && r.foodStatus != 'PICKED_UP'}">
                <form action="${pageContext.request.contextPath}/manage-request" method="post">
                  <input type="hidden" name="requestId" value="${r.id}">
                  <button class="btn small" name="action" value="pickup">Mark picked up</button>
                </form>
              </c:if>
              <c:if test="${r.foodStatus == 'PICKED_UP'}">Picked up</c:if>
            </td>
          </tr>
        </c:forEach>
      </table></div>
    </c:otherwise>
  </c:choose>

  <h3>Your posts</h3>
  <c:choose>
    <c:when test="${empty myPosts}"><p class="empty">You have not posted anything yet.</p></c:when>
    <c:otherwise>
      <div class="scroll"><table>
        <tr><th>Food</th><th>Quantity</th><th>Pickup address</th><th>Safe until</th><th>Status</th></tr>
        <c:forEach var="p" items="${myPosts}">
          <tr>
            <td><c:out value="${p.foodName}"/></td>
            <td><c:out value="${p.quantity}"/></td>
            <td><c:out value="${p.location}"/></td>
            <td><fmt:formatDate value="${p.expiryTime}" pattern="dd MMM, hh:mm a"/></td>
            <td>
              <c:choose>
                <c:when test="${p.expired}"><span class="badge s-EXPIRED">EXPIRED</span></c:when>
                <c:otherwise><span class="badge s-${p.status}">${p.status}</span></c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
      </table></div>
    </c:otherwise>
  </c:choose>
</c:when>

<%-- ================= RECEIVER VIEW ================= --%>
<c:otherwise>
  <div class="head"><h2>Food available now</h2></div>
  <c:choose>
    <c:when test="${empty available}"><p class="empty">Nothing is available right now. Check back soon.</p></c:when>
    <c:otherwise>
      <div class="grid">
        <c:forEach var="p" items="${available}">
          <article class="item">
            <h3><c:out value="${p.foodName}"/></h3>
            <p class="qty"><c:out value="${p.quantity}"/></p>
            <p><c:out value="${p.location}"/></p>
            <p class="muted">Safe until <fmt:formatDate value="${p.expiryTime}" pattern="dd MMM, hh:mm a"/></p>
            <p class="muted">From <c:out value="${p.donorName}"/></p>
            <form action="${pageContext.request.contextPath}/request-food" method="post">
              <input type="hidden" name="foodId" value="${p.id}">
              <button class="btn small" type="submit">Request this food</button>
            </form>
          </article>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>

  <h3>Your requests</h3>
  <c:choose>
    <c:when test="${empty myRequests}"><p class="empty">You have not requested any food yet.</p></c:when>
    <c:otherwise>
      <div class="scroll"><table>
        <tr><th>Food</th><th>Pickup address</th><th>Status</th><th>Donor contact</th></tr>
        <c:forEach var="r" items="${myRequests}">
          <tr>
            <td><c:out value="${r.foodName}"/> (<c:out value="${r.quantity}"/>)</td>
            <td><c:out value="${r.location}"/></td>
            <td>
              <span class="badge s-${r.status}">${r.status}</span>
              <c:if test="${r.foodStatus == 'PICKED_UP'}"> <span class="badge s-PICKED_UP">PICKED_UP</span></c:if>
            </td>
            <td>
              <c:choose>
                <c:when test="${r.status == 'ACCEPTED'}"><c:out value="${r.donorName}"/>, <c:out value="${r.donorPhone}"/></c:when>
                <c:otherwise>Shown once accepted</c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
      </table></div>
    </c:otherwise>
  </c:choose>
</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
