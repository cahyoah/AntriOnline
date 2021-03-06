package antrionline.antrionline.aon.data.local;

import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.util.Constant;
import antrionline.antrionline.aon.util.SharedPrefUtil;

/**
 * Created by adhit on 24/01/2018.
 */

public class SaveUserData {
    private static SaveUserData ourInstance;

    private SaveUserData() {
    }

    public static SaveUserData getInstance() {
        if (ourInstance == null) ourInstance = new SaveUserData();
        return ourInstance;
    }

    public User getUser() {
        return (User) SharedPrefUtil.getObject(Constant.USER, User.class);
    }

    public void saveUser(User User) {
        SharedPrefUtil.saveObject(Constant.USER, User);
    }

    public void removeUser(){
        SharedPrefUtil.remove(Constant.USER);
    }


    public void saveUserToken(String token){
        SharedPrefUtil.saveString(Constant.TOKEN,token );
    }

    public String getUserToken(){
        return SharedPrefUtil.getString(Constant.TOKEN);
    }

    public void removeUserToken(){
        SharedPrefUtil.remove(Constant.TOKEN);
    }

}
