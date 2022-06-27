package developer.abdusamid.trafficlawsapp.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import developer.abdusamid.trafficlawsapp.DB.Const.ABOUT
import developer.abdusamid.trafficlawsapp.DB.Const.DB_NAME
import developer.abdusamid.trafficlawsapp.DB.Const.DB_VERSION
import developer.abdusamid.trafficlawsapp.DB.Const.ID
import developer.abdusamid.trafficlawsapp.DB.Const.IMAGE
import developer.abdusamid.trafficlawsapp.DB.Const.LIKED
import developer.abdusamid.trafficlawsapp.DB.Const.NAME
import developer.abdusamid.trafficlawsapp.DB.Const.TABLE_NAME
import developer.abdusamid.trafficlawsapp.DB.Const.TYPE
import developer.abdusamid.trafficlawsapp.Models.User

class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), Service {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_NAME($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,$IMAGE TEXT NOT NULL,$NAME TEXT NOT NULL,$ABOUT TEXT NOT NULL,$TYPE INTEGER NOT NULL,$LIKED TEXT NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addMember(user: User) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(IMAGE, user.image)
        cv.put(NAME, user.name)
        cv.put(ABOUT, user.about)
        cv.put(TYPE, user.type)
        cv.put(LIKED, user.liked)

        db.insert(TABLE_NAME, null, cv)
        db.close()
    }

    override fun updateMember(user: User): Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(ID, user.id)
        cv.put(IMAGE, user.image)
        cv.put(NAME, user.name)
        cv.put(ABOUT, user.about)
        cv.put(TYPE, user.type)
        cv.put(LIKED, user.liked)

        return db.update(TABLE_NAME, cv, "$ID = ?", arrayOf(user.id.toString()))
    }

    override fun deleteMember(user: User) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$ID = ?", arrayOf(user.id.toString()))

        db.close()
    }

    override fun getAllMember(): ArrayList<User> {
        val list = ArrayList<User>()
        val query = "Select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.id = cursor.getInt(0)
                user.image = cursor.getString(1)
                user.name = cursor.getString(2)
                user.about = cursor.getString(3)
                user.type = cursor.getString(4)
                user.liked = cursor.getString(5)
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }
}