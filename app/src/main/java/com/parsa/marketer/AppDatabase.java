package com.parsa.marketer;

//@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDatabase /*extends RoomDatabase*/ {

   /* public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            AppDatabase.class.getName())
                            .allowMainThreadQueries()
//                            .addMigrations(MIGRATION_8_9)
                            .build();
        }
        return INSTANCE;
    }

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();*/


}
