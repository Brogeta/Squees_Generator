package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */

import javax.persistence.*;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
@Entity
public class ColorIdentity {

    @Id
    private String color;

    @Version
    private Integer version;

    public ColorIdentity() {
    }

    public ColorIdentity(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
