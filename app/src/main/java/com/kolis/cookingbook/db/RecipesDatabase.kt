package com.kolis.cookingbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        private val dbName = "recipes_db"

        @Volatile
        private var INSTANCE: RecipesDatabase? = null

        fun getDatabase(context: Context): RecipesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipesDatabase::class.java,
                    dbName
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
