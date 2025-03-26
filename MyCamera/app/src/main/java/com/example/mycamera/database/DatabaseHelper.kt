package com.example.mycamera.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_IMAGE_PATH TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertImage(imagePath: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_IMAGE_PATH, imagePath)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()

        if (result == -1L) {
            Log.e("DatabaseHelper", "Gagal menyimpan gambar ke database")
        } else {
            Log.d("DatabaseHelper", "Gambar berhasil disimpan: $imagePath")
        }
    }

    fun deleteImage(imagePath: String): Boolean {
        val db = writableDatabase
        val deletedRows = db.delete("images", "image_path = ?", arrayOf(imagePath))
        db.close()
        return deletedRows > 0
    }


    fun getAllImages(): List<String> {
        val db = readableDatabase
        val images = mutableListOf<String>()
        val cursor = db.rawQuery("SELECT $COLUMN_IMAGE_PATH FROM $TABLE_NAME", null)

        cursor.use {
            while (it.moveToNext()) {
                val imagePath = it.getString(it.getColumnIndexOrThrow(COLUMN_IMAGE_PATH))
                images.add(imagePath)
            }
        }
        Log.d("DatabaseHelper", "Gambar di database: $images") // Cek apakah gambar benar-benar ada
        return images
    }

    fun isImageExists(imageUri: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_IMAGE_PATH = ?"
        val cursor = db.rawQuery(query, arrayOf(imageUri))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }


    companion object {
        private const val DATABASE_NAME = "MyCameraDB"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "images"
        const val COLUMN_ID = "id"
        const val COLUMN_IMAGE_PATH = "image_path"
    }
}
