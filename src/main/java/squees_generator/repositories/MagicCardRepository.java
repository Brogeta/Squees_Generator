package squees_generator.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import squees_generator.domain.ColorIdentity;
import squees_generator.domain.MagicCard;
import squees_generator.domain.MagicTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
public interface MagicCardRepository extends CrudRepository<MagicCard, String> {

    enum NullBehavior {
        EQUALS, IS, IGNORED
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
    @interface NullMeans {
        NullBehavior value() default NullBehavior.EQUALS;
    }

    @MagicCardRepository.NullMeans(MagicCardRepository.NullBehavior.IS)
    List<MagicCard> findByRarityAndMagicTypesListAndColorIdentityList(String rarity, List<String> magicTypesList, List<String> colorIdentityList);

    @MagicCardRepository.NullMeans(MagicCardRepository.NullBehavior.IS)
    @Query(value = "SELECT distinct a.* FROM magic_card a \n" +
                    " INNER JOIN magic_card_magic_types_list c on a.magic_card_id = c.magic_card_MagicCardId " +
                    " INNER JOIN magic_card_legalities_list d on a.magic_card_id = d.magic_card_MagicCardId " +
                    " INNER JOIN legalities e on d.legalities_list_LegalitiesId = e.legalities_id " +
                    "WHERE a.magic_card_id Not IN(" +
                    "SELECT DISTINCT a.magic_card_id from magic_card a \n" +
                    "INNER JOIN magic_card_color_identity_list b on a.magic_card_id = b.magic_card_MagicCardId " +
                    "WHERE b.color_identity_list_color IN(:C1,:C2,:C3,:C4,:C5)) " +
                    "AND c.magic_types_list_typeName = :cardType " +
                    "AND a.rarity = :rarity " +
                    "AND e.format = :format " +
                    "AND e.legality = 'legal' "
                    , nativeQuery = true)
    List<MagicCard> findByRequirements(@Param("C1")String C1,@Param("C2")String C2,@Param("C3")String C3,@Param("C4")String C4,@Param("C5")String C5,
                                        @Param("cardType") String cardType, @Param("rarity") String rarity, @Param("format") String format);
}
