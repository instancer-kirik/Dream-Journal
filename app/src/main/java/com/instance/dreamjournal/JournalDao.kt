package com.instance.dreamjournal

import androidx.room.*
import java.time.LocalDate

@Dao
abstract class JournalDao {
    @Transaction
    @Query("SELECT * FROM journal")
    abstract fun getItAll(): Array<EntryEntity>//probably includes objectives

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun save(objective: ObjectiveEntity)

    /*@Transaction
    @Update
    abstract fun save(objectives: List<ObjectiveEntity>)
*/
    @Upsert
    abstract fun save(entry: EntryEntity)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertentryEntity(entry: entryEntity?):Long
*/

    @Query("SELECT * FROM journal WHERE uuid =:id")
    abstract fun getentryEntity(id: String): EntryEntity

/*
    @Query("SELECT * FROM objectives WHERE qid =:id")
    abstract fun getObjectiveEntityList(id: String): List<ObjectiveEntity>
*/


    @Query("DELETE FROM journal")
    abstract fun deleteAll()
    /*@Query("DELETE FROM journal")
    abstract fun deleteAllRows()
    open fun getentryWithObjectives(id: String): entryWithObjectives {
        val entry = getentryEntity(id)
        val objectives: List<ObjectiveEntity> = getObjectiveEntityList(id)
        val qwo = entryWithObjectives(entry,objectives)
        return qwo
    }
*/


    /* fun insert(qwo: entryWithObjectives):String {
        //val qid = insertentryEntity(qwo.entry)
        save(qwo.entry)

        qwo.objectives.forEach { i -> i.qid= qwo.entry.uuid}
        insertAll(qwo.objectives)
        return qwo.entry.uuid
    }*/

    /*  fun save(qwo: entryWithObjectives): String {
        save(qwo.entry)
        qwo.objectives.forEach { i -> i.qid= qwo.entry.uuid }
        updateAll(qwo.objectives)
        return qwo.entry.uuid
    }*/

    /* fun save(items: Iterable<entryWithObjectives>){
        items.forEach{
            save(it.entry)
            insertAll(it.objectives)
        }
    }*/
    @Delete
    abstract fun delete(entry: EntryEntity)

 /*   @Query("SELECT * FROM recordings WHERE date = :givenDateInLong")
    abstract fun getRecordingOn(date: LocalDate): Array<Recording>?
*/
/*
    @Query("SELECT * FROM journal WHERE date = :givenDateInLong")
    abstract fun getGivenDate(givenDateInLong: Long)*/
}

   /* @Upsert
    abstract fun insertAll(objectives: Iterable<ObjectiveEntity>?)
    @Upsert
    abstract fun updateAll(objectives: List<ObjectiveEntity?>?)
    //
    //abstract fun insertentryEntity(recipe: entryEntity?): Long //return type is the key here.
    @Transaction
    @Delete
    abstract fun delete(entry: entryEntity?, objectives: List<ObjectiveEntity?>?)
    @Transaction
    @Query("SELECT * FROM journal")
    abstract fun loadAll(): Array<entryWithObjectives>

    //    @Update
//    abstract fun update(entry: entryEntity?):Int
    @Upsert
    abstract fun update(vararg entry:entryEntity)

    //@Upsert doesn't work lolz
    *//* @Insert(onConflict = OnConflictStrategy.REPLACE)
     abstract fun save(objective: ObjectiveEntity)
 *//*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: ObjectiveEntity): Long
    @Update
    abstract fun update(item: ObjectiveEntity): Int

    @Transaction
    open fun save(item: ObjectiveEntity) {
        val rowsUpdated = update(item)
        if (rowsUpdated == 0) insert(item)
    }*/