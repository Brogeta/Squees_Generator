<%@ c:taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/static/js/deckVeiw.js" var="deckVeiw" />
<script src="${deckVeiw}"></script>


<div>
    <table id="deck-table" class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Quantity</th>
            <th>Card</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>