
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
        existingDeck: $('#selectExistingDeck'),
        cardsPerRound: $('#cardsPerDraft'),
        magicFormat: $('#selectFormat'),
        general1: $('#selectGeneral1'),
        general2: $('#selectGeneral2'),
        deckSize: $('#deckSize'),
        sideboardSize: $('#sideboardSize'),
        quantityMax: $('#quantityMax'),
        quantityPreferred: $('#quantityPreferred'),
        quantityLegendary: $('#quantityLegendary'),
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
        weightColorless: $('#weightColorless'),
        weightCommon: $('#weightCommon'),
        weightUncommon: $('#weightUncommon'),
        weightRare: $('#weightRare'),
        weightMythic: $('#weightMythic'),
        rareSameAsMythic: $('#rareSameAsMythic'),
        cmcMin: $('#cmcMin'),
        cmcMax: $('#cmcMax'),
        cmcMean: $('#cmcMean'),
        percentLand: $('#percentLand'),
        percentNonbasicLand: $('#percentNonbasicLand'),
        keyWords: $('#selectKeyWords'),
        magicSets: $('#selectSets')
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
    })
}

function createDeck(id) {

    $.getJSON('/api/magicDeck/create/' + id, {
        ajax: 'true'
    }, function(magicDeck) {

    })
}




















