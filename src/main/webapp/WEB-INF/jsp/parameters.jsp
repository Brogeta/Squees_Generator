<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>

<c:url value="/static/js/parameters.js" var="parameters" />
<script src="${parameters}"></script>



<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form>
        <fieldset>

            <hidden id="parametersId"/>
            <hidden id="parametersVersion"/>

            <div class="form-group">
                <label for="selectParameterSet" class="col-lg-2 control-label">Parameter Set</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectParameterSet">
                        <option selected value="0">Select a Parameter Set</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="useExistingDeck" class="col-lg-2 control-label">Check if you want to use an Existing Deck</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="useExistingDeck" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="selectExistingDeck" class="col-lg-2 control-label">selectExistingDeck</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectExistingDeck">
                        <option selected value="0">Select an Existing Deck</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="isDraft" class="col-lg-2 control-label">Check if you want to draft</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="isDraft" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="cardsPerDraft" class="col-lg-2 control-label">Cards per draft round: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="cardsPerDraft">
                </div>
            </div>
            
            <div class="form-group">
                <label for="selectFormat" class="col-lg-2 control-label">Format</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectFormat">
                        <option selected value="0">Select a Format</option>
                    </select>
                </div>
            </div>

            <%--Select General--%>

            <div class="form-group">
                <label for="isPartner" class="col-lg-2 control-label">isPartner</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="isPartner" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="selectGeneral1" class="col-lg-2 control-label">selectGeneral1</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectGeneral1">
                        <option selected value="0">selectGeneral1</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="selectGeneral2" class="col-lg-2 control-label">selectGeneral2</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectGeneral2">
                        <option selected value="0">selectGeneral2</option>
                    </select>
                </div>
            </div>

            <%--Deck Size--%>

            <div class="form-group">
                <label for="deckSize" class="col-lg-2 control-label">Deck Size: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="deckSize">
                </div>
            </div>

            <div class="form-group">
                <label for="sideboardSize" class="col-lg-2 control-label">Sideboard Size: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="sideboardSize">
                </div>
            </div>

            <%--Quantity of cards per deck--%>

            <div class="form-group">
                <label for="quantityMax" class="col-lg-2 control-label">Card Limit: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="quantityMax">
                </div>
            </div>

            <div class="form-group">
                <label for="quantityPreferred" class="col-lg-2 control-label">Perferred: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="quantityPreferred">
                </div>
            </div>

            <div class="form-group">
                <label for="quantityLegendary" class="col-lg-2 control-label">Preferred Legendary: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="quantityLegendary">
                </div>
            </div>

            <%--Give weight to card types--%>

            <div class="form-group">
                <label for="weightCreature" class="col-lg-2 control-label">Creature: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightCreature" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightEnchantment" class="col-lg-2 control-label">Enchantment: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightEnchantment" value="5" min="0" max="10">
                </div>
            </div>


            <div class="form-group">
                <label for="weightArtifact" class="col-lg-2 control-label">Artifact: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightArtifact" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightPlaneswalker" class="col-lg-2 control-label">Planeswalker: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightPlaneswalker" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightInstant" class="col-lg-2 control-label">Instant: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightInstant" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightSorcery" class="col-lg-2 control-label">Sorcery: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightSorcery" value="5" min="0" max="10">
                </div>
            </div>

            <%--+++++++++++++++++++++++++++++++++++++Colors++++++++++++++++++++++++++++++++++++++--%>

            <div class="form-group">
                <label for="white" class="col-lg-2 control-label">white</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="white" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="blue" class="col-lg-2 control-label">blue</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="blue" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="black" class="col-lg-2 control-label">black</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="black" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="red" class="col-lg-2 control-label">red</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="red" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="green" class="col-lg-2 control-label">green</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="green" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="colorless" class="col-lg-2 control-label">colorless</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="colorless" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <%--Color weight--%>

            <div class="form-group">
                <label for="weightWhite" class="col-lg-2 control-label">weightWhite: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightWhite" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightBlue" class="col-lg-2 control-label">weightBlue: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightBlue" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightBlack" class="col-lg-2 control-label">weightBlack: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightBlack" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightRed" class="col-lg-2 control-label">weightRed: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightRed" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightGreen" class="col-lg-2 control-label">weightGreen: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightGreen" value="5" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightColorless" class="col-lg-2 control-label">weightColorless: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightColorless" value="5" min="0" max="10">
                </div>
            </div>

            <%--Rarity Weight--%>

            <div class="form-group">
                <label for="weightWhite" class="col-lg-2 control-label">weightCommon: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightCommon" value="4" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightWhite" class="col-lg-2 control-label">weightUncommon: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightUncommon" value="3" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="weightWhite" class="col-lg-2 control-label">weightRare: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightRare" value="2" min="0" max="10">
                </div>
            </div>

            <div class="form-group">
                <label for="rareSameAsMythic" class="col-lg-2 control-label">Treat rare and mythic rare as the same?</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="rareSameAsMythic" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="weightWhite" class="col-lg-2 control-label">weightMythic: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="weightMythic" value="1" min="0" max="10">
                </div>
            </div>



            <%--CMC--%>
            <div class="form-group">
                <label for="cmcMin" class="col-lg-2 control-label">CMC Min: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="cmcMin">
                </div>
            </div>

            <div class="form-group">
                <label for="cmcMax" class="col-lg-2 control-label">CMC Max: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="cmcMax">
                </div>
            </div>

            <div class="form-group">
                <label for="cmcMean" class="col-lg-2 control-label">CMC Mean: </label>
                <div class="col-lg-10">
                    <input type="number" class="form-control" id="cmcMean">
                </div>
            </div>


            <%--Lands--%>

            <div class="form-group">
                <label for="landFromCmc" class="col-lg-2 control-label">landFromCmc</label>
                <div class="col-lg-10">
                    <input type="checkbox" id="landFromCmc" data-role="flipswitch" data-on-text="True" data-off-text="False"/>
                </div>
            </div>

            <div class="form-group">
                <label for="percentLand" class="col-lg-2 control-label">Percent Land: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="percentLand" value="35" min="0" max="100">
                </div>
            </div>

            <div class="form-group">
                <label for="percentNonbasicLand" class="col-lg-2 control-label">Percent Nonbasic Land: </label>
                <div class="col-lg-10">
                    <input type="range" class="form-control" id="percentNonbasicLand" value="35" min="0" max="100">
                </div>
            </div>

            <%--KeyWords / Abblities--%>

            <div class="form-group">
                <label for="selectKeyWords" class="col-lg-2 control-label">Select Key Words</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectKeyWords" multiple>
                        <option selected value="0">selectKeyWords</option>
                    </select>
                </div>
            </div>

            <%--Sets--%>
            <div class="form-group">
                <label for="selectSets" class="col-lg-2 control-label">Select Sets</label>
                <div class="col-lg-10">
                    <select class="form-control" id="selectSets" multiple>
                        <option selected value="0">selectKeyWords</option>
                    </select>
                </div>
            </div>

            <button type="button" class="btn btn-default" id="submit">Submit</button>
        </fieldset>
    </form>
</div>