function createDeck(id) {

    $.getJSON('/api/magicDeck/create/' + id, {
        ajax: 'true'
    }, function(data) {

        $.each(data.mainDeck, function(index, single) {
            $('#deck-table').find('tbody')
                .append('<tr>' +
                        '<td>' + 1 + '</td>' +
                        '<td>' + single.cardName + '</td>' +
                        '</tr>')
        });

        $('#deck-table').find('tbody')
            .append('<tr>' +
            '<td> </td>' +
            '<td> </td>' +
            '</tr>');

        $.each(data.sideBoard, function(index, single) {
            $('#deck-table').find('tbody')
                .append('<tr>' +
                '<td>' + 1 + '</td>' +
                '<td>' + single.cardName + '</td>' +
                '</tr>')
        });
    })
}
