package codeSprinters.refactoring;

import codeSprinters.refactoring.dao.PictureDao;
import codeSprinters.refactoring.domain.Picture;
import codeSprinters.refactoring.domain.User;

import java.util.List;

public class PictureRetriever {
    public List<Picture> getPicturesFor(User user, User currentlyLoggedUser) throws NoLoggedUserException {

        if (currentlyLoggedUser == null) {
            throw new NoLoggedUserException();
        }

        if (isUserFriendOfLoggedUser(user, currentlyLoggedUser)) {
            return PictureDao.getPicturesForUser(user);
        }
        return null;
    }

    private boolean isUserFriendOfLoggedUser(User user, User currentlyLoggedUser) {
        for(User friend : currentlyLoggedUser.getFriends()) {
            if (user.equals(friend)) {
                return true;
            }
        }
        return false;
    }
}
