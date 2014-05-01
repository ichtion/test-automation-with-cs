package codeSprinters.refactoring;

import codeSprinters.refactoring.dao.PictureDao;
import codeSprinters.refactoring.domain.Picture;
import codeSprinters.refactoring.domain.User;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PictureRetrieverTest {

    private PictureRetriever pictureRetriever = new PictureRetriever();

    @Test(expected = NoLoggedUserException.class)
    public void testGetPicturesInvalid() throws Exception {
        User user1 = new User();
        pictureRetriever.getPicturesFor(user1, null);
    }

    @Test
    public void testGetPicturesValid() throws NoLoggedUserException {
        Picture picture = new Picture();
        User user1 = new User();
        User user2 = new User();
        user2.addFriend(user1);
        PictureDao.addPicturesForUser(user1, asList(picture));

        List<Picture> list = pictureRetriever.getPicturesFor(user1, user2);

        assertThat(list, containsInAnyOrder(picture));
    }

    @Test
    public void testGetPictureInValid_2() throws NoLoggedUserException {
        Picture picture = new Picture();
        User user1 = new User();
        User user2 = new User();

        List<Picture> list = pictureRetriever.getPicturesFor(user1, user2);

        assertThat(list, is(nullValue()));
    }

    @Test
    public void testGetPictureInValid_3() throws NoLoggedUserException {
        Picture picture = new Picture();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user2.addFriend(user3);
        PictureDao.addPicturesForUser(user1, asList(picture));

        List<Picture> list = pictureRetriever.getPicturesFor(user1, user2);

        assertThat(list, is(nullValue()));
    }
}
