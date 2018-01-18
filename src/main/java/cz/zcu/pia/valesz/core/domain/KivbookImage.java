package cz.zcu.pia.valesz.core.domain;

import javax.persistence.*;

/**
 * A simple entity which represents image stored in database.
 */
@Entity
@Table(name = "kivbook_image")
public class KivbookImage {

    /**
     * Ids of profile photos which should be kept in database and used as default ones.
     */
    public static final long[] DEFAULT_PHOTO_IDS = new long[] {1l, 2l, 3l};
    public static final long MAX_IMAGE_SIZE_BYTES = 100000;

    /**
     * Image is store in Base64 encoding which increases size of the data, so more space is needed.
     */
    public static final long MAX_IMAGE_DATA_SIZE = MAX_IMAGE_SIZE_BYTES*2;

    /**
     * Database id.
     */
    private Long id;

    /**
     * Image data stored as a string.
     */
    private String imageData;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    @Column(name = "image_data", length = (int)MAX_IMAGE_DATA_SIZE)
    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    /**
     * Returns true if this object has default id.
     * If the id is null, false is returned.
     * .
     * @return True if this object has default id.
     */
    public boolean hasDefaultId() {
        if(getId() == null) {
            return false;
        }

        long idl = getId().longValue();
        for (int i = 0; i < DEFAULT_PHOTO_IDS.length; i++) {
            if(idl == DEFAULT_PHOTO_IDS[i]) {
                return true;
            }
        }

        return false;
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
