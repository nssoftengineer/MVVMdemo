package com.example.app_kotlin.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.app_kotlin.db.converter.DateConverter
import com.example.app_kotlin.db.dao.CommentDao
import com.example.app_kotlin.db.dao.ProductDao
import com.example.app_kotlin.db.entity.CommentEntity
import com.example.app_kotlin.db.entity.ProductEntity
import com.example.app_kotlin.db.entity.ProductFtsEntity
import com.example.app_kotlin.utils.AppExecutors


@Database(entities = [ProductEntity::class, ProductFtsEntity::class, CommentEntity::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun commentDao(): CommentDao

    companion object {
        @VisibleForTesting
        val DATABASE_NAME = "basic-sample-kot-db"


        private val mIsDatabaseCreated = MutableLiveData<Boolean>()


        lateinit var sInstance: AppDatabase
        fun getInstance(context: Context, executors: AppExecutors): AppDatabase {
            if (sInstance == null) {
                synchronized(AppDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.applicationContext, executors)
                        sInstance!!.updateDatabaseCreated(context.applicationContext)
                    }
                }
            }
            return sInstance
        }


        private fun buildDatabase(appContext: Context,
                                  executors: AppExecutors): AppDatabase {
            return Room.databaseBuilder<AppDatabase>(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            executors.diskIO().execute(Runnable {
                                addDelay()
                                // Generate the data for pre-population
                                val database = AppDatabase.getInstance(appContext, executors)
                                val products = DataGenerator.generateProducts()
                                val comments = DataGenerator.generateCommentsForProducts(products)

                                insertData(database, products, comments)
                                // notify that the database was created and it's ready to be used
                                database.setDatabaseCreated()
                            })
                        }
                    })
                    .addMigrations(MIGRATION_1_2)
                    .build()
        }

        /**
         * Check whether the database already exists and expose it via [.getDatabaseCreated]
         */


        private fun insertData(database: AppDatabase, products: List<ProductEntity>,
                               comments: List<CommentEntity>) {
            database.runInTransaction {
                database.productDao().insertAll(products)
                database.commentDao().insertAll(comments)
            }
        }

        private fun addDelay() {
            try {
                Thread.sleep(4000)
            } catch (ignored: InterruptedException) {
            }

        }


        private val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `productsFts` USING FTS4(" + "`name` TEXT, `description` TEXT, content=`products`)")
                database.execSQL("INSERT INTO productsFts (`rowid`, `name`, `description`) " + "SELECT `id`, `name`, `description` FROM products")

            }
        }
    }

    fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }
}