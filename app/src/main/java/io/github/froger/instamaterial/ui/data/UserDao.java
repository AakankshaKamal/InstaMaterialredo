package io.github.froger.instamaterial.ui.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.github.froger.instamaterial.ui.Model.User;

@Dao
public interface UserDao {
@Query("SELECT * FROM User WHERE email=:email and password=:password")
User getUser(String email,String password);
@Insert
    void insert(User user);


}
