package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */

import javax.persistence.*;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
@Entity
public class MagicTypes {

    @Id
    private String typeName;

    @Version
    private Integer version;

    public MagicTypes() {
    }

    public MagicTypes(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
