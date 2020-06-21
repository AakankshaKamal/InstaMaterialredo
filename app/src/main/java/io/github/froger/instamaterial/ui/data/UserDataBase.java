package io.github.froger.instamaterial.ui.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import io.github.froger.instamaterial.ui.Model.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {
    public static final String DB_NAME = "app_db";
    public static final String TABLE_NAME_TODO = "todo";

    public abstract UserDao getUserDao();
}
