package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.KivbookImage;
import org.springframework.data.jpa.repository.Query;

public interface KivbookImageDao extends GenericDao<KivbookImage, Long> {

    /**
     * Returns the min id in the table.
     * @return Min id.
     */
    @Query("SELECT MIN(id) FROM KivbookImage ")
    Long getMinId();
}
