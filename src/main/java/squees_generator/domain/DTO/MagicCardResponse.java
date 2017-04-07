package squees_generator.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import squees_generator.domain.MagicCard;

import java.util.List;

/**
 * Created by Brandon.O'Donnell on 4/7/2017.
 */
public class MagicCardResponse {
    @JsonProperty("cards")
    private List<MagicCard> magicCardList;

    public List<MagicCard> getMagicCardList() {
        return magicCardList;
    }

    public void setMagicCardList(List<MagicCard> magicCardList) {
        this.magicCardList = magicCardList;
    }
}
