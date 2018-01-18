package cz.zcu.pia.valesz.core.domain;

import javax.persistence.*;

/**
 * A simple entity which represents image stored in database.
 */
@Entity
@Table(name = "kivbook_image")
public class KivbookImage {

    /**
     * Database id.
     */
    private Long id;

    /**
     * Image data stored as a string.
     */
    private String imageData;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    @Column(name = "image_data", length = 100000)
    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KivbookImage that = (KivbookImage) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "KivbookImage{" +
                "id=" + id +
                '}';
    }
}
