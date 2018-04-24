package ro.siit.mytripsapp.service.currentuser;

import ro.siit.mytripsapp.entity.user.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}