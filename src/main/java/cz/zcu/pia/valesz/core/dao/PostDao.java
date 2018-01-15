package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Post;

/**
 * Methods for fetching posts-related data.
 */
public interface PostDao extends GenericDao<Post, Long>, PostDaoCustom {

}
