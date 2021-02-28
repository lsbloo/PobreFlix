package app.student.movieapp.core.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.student.movieapp.home.storage.dao.SearchedMoviesDAO
import app.student.movieapp.home.storage.entity.SearchedMovies


@Database(entities = [SearchedMovies::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchedMoviesDAO(): SearchedMoviesDAO
}