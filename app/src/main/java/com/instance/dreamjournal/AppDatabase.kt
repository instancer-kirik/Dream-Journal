package com.instance.dreamjournal

import androidx.databinding.adapters.Converters


import android.content.Context
import androidx.room.*


//also has relevant enums below
@Database(entities = [EntryEntity::class,] /*Recording::class]*/,/* ObjectiveEntity::class, AbilityEntity::class,
    User::class, CharacterEntity::class, ItemEntity::class,NoteEntity::class*/
    version = 67, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class AppDatabase() : RoomDatabase(){
    //abstract fun addQuestEntity(title: String, author: String): Any
    //abstract fun getQuestDao(): QuestDao
    abstract fun journalDao(): JournalDao

    //\abstract fun entityDao(): EntityDao

    //fun addQuestEntity(quest:Quest): Any
    //abstract fun addNewQuestEntity(title: String, author: String): Any
    companion object {


        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "db_room_database")
                    //.createFromAsset("database/dbroom.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }


    }



}

/*enum class CalendarDataType{//made by github copilot
    QUEST, OBJECTIVE, ABILITY, ITEM, NOTE, EVENT, TASK, REMINDER, APPOINTMENT, MEETING, DEADLINE, BIRTHDAY, ANNIVERSARY, HOLIDAY, VACATION, OTHER, DEFAULT
}*/


//fantastic, excellent, mediocre, good, bad, awful, abysmal terrible, default