
//Staring point of submit:  Decides if the parameters are custom or not
function submitParameters(){

    var parameterSet = $('#selectParameterSet').val();

    if(parameterSet == -1) {            //Custom Parameters
        saveParameters(createDeck(output));
    }
    else {                              //Preset Parameters
        createDeck(parameterSet);
    }
}

//Will save the parameters to the dataBase and return the Id
function saveParameters(dataHandle) {

    var parameters = {

        id: $('#parametersId'),
        version: $('#parametersVersion'),
        magicFormat: $('#selectFormat'),
        general1: $('#selectGeneral1'),
        general2: $('#selectGeneral2'),
        deckSize: $('#deckSize'),
        sideboardSize: $('#sideboardSize'),
        quantityMax: $('#quantityMax'),
        weightCreature: $('#weightCreature'),
        weightEnchantment: $('#weightEnchantment'),
        weightArtifact: $('#weightArtifact'),
        weightPlaneswalker: $('#weightPlaneswalker'),
        weightInstant: $('#weightInstant'),
        weightSorcery: $('#weightSorcery'),
        weightWhite: $('#weightWhite'),
        weightBlue: $('#weightBlue'),
        weightBlack: $('#weightBlack'),
        weightRed: $('#weightRed'),
        weightGreen: $('#weightGreen'),
        weightCommon: $('#weightCommon'),
        weightUncommon: $('#weightUncommon'),
        weightRare: $('#weightRare'),
        weightMythic: $('#weightMythic'),
        percentLand: $('#percentLand'),
        percentNonbasicLand: $('#percentNonbasicLand'),
    };

    console.log(parameters);

    $.ajax({
        type: "post",
        data: parameters,
        url: "/api/parameters/",
        async: true,
        dataType: "json",
        success: function() {
            dataHandle(parameters);
        }
    });
}

function createDeck(id) {
    var cardsMain = [];
    var cardsSide = [];
    var inList;

    var table = $('#magicDeckTable').find('tbody');

    table.children().remove();

    $.getJSON('/api/magicDeck/create/' + id, {
        ajax: 'true'
    }, function(data) {
        $.each(data.mainDeck, function(index, single) {
            inList = false;
            for(var x = 0; x < cardsMain.length; ++x) {
                if(single.name == cardsMain[x].card.name) {
                    ++cardsMain[x].quantity;
                    inList=true;
                }
            }
            if(!inList)
                cardsMain.push({card: single, quantity: 1});
        });

        $.each(data.sideboard, function(index, single) {
            inList = false;
            for(var x = 0; x < cardsSide.length; ++x) {
                if(single.name == cardsSide[x].card.name) {
                    ++cardsSide[x].quantity;
                    inList=true;
                }
            }
            if(!inList)
                cardsSide.push({card: single, quantity: 1});
        });



        for(var y = 0; y < cardsMain.length; ++y){
            table
                .append("<tr>" +
                "<td>" + cardsMain[y].quantity + "</td>" +
                "<td><a href='" + cardsMain[y].card.imageUrl +"' target='_blank'> " + cardsMain[y].card.name + "</a></td>" +
                "</tr>");
        }

        table.append("<tr> <td>Sideboard</td> </tr>");

        for(var y = 0; y < cardsSide.length; ++y){
            table
                .append("<tr>" +
                "<td>" + cardsSide[y].quantity + "</td>" +
                "<td><a href='" + cardsSide[y].card.imageUrl +"' target='_blank'> " + cardsSide[y].card.name + "</a></td>" +
                "</tr>");
        }

        $('#downloadButton').attr('href',"/api/magicDeck/download/" + data.id);


        $('#deckModal').modal();

    });


}

function fillParameterSet() {
    $.getJSON('/api/parameters/', {
        ajax: 'true'
    }, function(data) {
        $.each(data, function (index, single) {
            $('#selectParameterSet')
                .append("<option value='" + single.id + "'>" + single.name + "</option>")
        });
    });
}

function customParameters(){
    $('#selectParameterSet').change(
        function() {
            var tier1 = document.getElementsByClassName('hidden1'), x;

            if($('#selectParameterSet').val() == -1) {
                for( x =0; x < tier1.length; ++x) {
                    tier1[x].style.display= 'inline';
                }
            }
            else {
                for( x =0; x < tier1.length; ++x) {
                    tier1[x].style.display= 'none';
                }
            }
        }
    )

}

function fillFormat() {
    $.getJSON('https://api.magicthegathering.io/v1/formats', {
        ajax: 'true'
    }, function (data) {
        $.each(data.formats, function (index, single) {
            $('#selectFormat')
                .append("<option value='" + single + "'>" + single + "</option>")
        });
    });
}

function fillGeneral1() {
    $.getJSON('api/magicCard/generals/0', {
        ajax: 'true'
    }, function(data) {
        $.each(data, function (index, single) {
            $('#selectGeneral1')
                .append("<option value ='" + single + "'>" + single.name + "</option>")
        });
    });
}





















