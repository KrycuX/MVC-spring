package games.lab4.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Base64Utils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@Getter
@Setter
public class Photo {

    private String fileName;
    private String type;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] content;
    public String getBase64Content(){
        return Base64Utils.encodeToString(content);
    }
}
