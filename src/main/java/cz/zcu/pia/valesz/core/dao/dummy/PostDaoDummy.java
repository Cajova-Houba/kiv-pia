package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.PostDao;
import cz.zcu.pia.valesz.core.domain.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PostDaoDummy implements PostDao {

    private static List<Post> postRepo = new ArrayList<>();

    @Override
    public List<Post> listPostsForUser() {
        return postRepo;
    }

    @Override
    public Post get(Long key) {
        for(Post p : postRepo) {
            if (p.getId().equals(key)) {
                return p;
            }
        }

        return null;
    }

    @Override
    public Post save(Post object) {
        postRepo.add(object);
        return object;
    }

    @Override
    public void delete(Long key) {
        Iterator<Post> pIter = postRepo.iterator();

        while(pIter.hasNext()) {
            if(pIter.next().getId().equals(key)) {
                pIter.remove();
            }
        }
    }
}
