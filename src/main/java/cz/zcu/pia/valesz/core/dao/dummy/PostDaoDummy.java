package cz.zcu.pia.valesz.core.dao.dummy;

import cz.zcu.pia.valesz.core.dao.PostDao;
import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class PostDaoDummy extends Dummygeneric<Post, Long> implements PostDao {

    private static List<Post> postRepo = new ArrayList<>();

    @Override
    public List<Post> listPostsForUser(User user, List<FriendRequest> usersFriendships) {
        return null;
    }

    @Override
    public void delete(Post deleted) {

    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findOne(Long id) {
        return Optional.ofNullable(get(id));
    }

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

//    @Override
    public void delete(Long key) {
        Iterator<Post> pIter = postRepo.iterator();

        while(pIter.hasNext()) {
            if(pIter.next().getId().equals(key)) {
                pIter.remove();
            }
        }
    }
}
