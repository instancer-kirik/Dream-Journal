package com.instance.dreamjournal

import androidx.databinding.adapters.Converters
import androidx.room.*
import com.squareup.moshi.JsonClass
import java.util.*

@Entity(
    tableName = "journal",
    indices = [
        Index(value = ["uuid"], unique = true),
        //Index(value = ["oid"])
        //Index(value = ["trakt_id"], unique = true),
        //Index(value = ["tmdb_id"])
    ],
    /*CREATE TABLE "quests" ( "quest_id" INTEGER NOT NULL UNIQUE, "Quest_Name" INTEGER NOT NULL, "Description" INTEGER, "Reward" INTEGER, "ENCODED_OBJECTIVES_DATES_EVENTMARKERS_PLOTLINES" INTEGER, "SOURCE" INTEGER NOT NULL, "DATE" REAL, "SOURCE_USER_ID" INTEGER, "SOURCE_MEDIA" TEXT, "SOURCE_MODEL" TEXT, "PARTY_SIZE" INTEGER, "TRANSPO_LEVEL" INTEGER, "SPECIAL_TRANSPO" TEXT, "SPECIAL_EQUIPMENT" TEXT, "LOCATION" TEXT, "DURATION" REAL, "LEVEL" INTEGER, PRIMARY KEY("quest_id" AUTOINCREMENT) );
 INSERT INTO "main"."quests" VALUES('0','0','','','','0','','','','','','','','','','','');
 INSERT INTO "main"."quests" VALUES('1','first_quest','this is your context ALSO mode of transportation is -1(walkable) 0(skateboard) 1(bike) 2(car) 3(racecar) 4(rally) 5(offroad vehicle-clearance/snow) 6(truck) 7(drift) 8(SPECIAL_TRANSPO)','twenty chicken nuggets','a bunch of data here worry about this later. also source is -1(I created) 0(usercreated own) 1(pulled from movie/game/story 2(given by someone else)','0','7/7/2021','1','','','-1','0','','nothing','here','now','1');
 INSERT INTO "main"."quests" VALUES('2','0','','','','0','','','','','','','','','','','');

  foreignKeys = [
         ForeignKey(
             entity = ObjectiveEntity::class,
             parentColumns = arrayOf("rqid"),
             childColumns = arrayOf("oid"),
             onUpdate = ForeignKey.CASCADE,
             onDelete = ForeignKey.CASCADE

         )
     ]
 */
)
/*@TypeConverters(Converters::class)*/
@JsonClass(generateAdapter=true)
data class EntryEntity @JvmOverloads constructor(
    @ColumnInfo(name = "uuid") @PrimaryKey val uuid:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "datetime")  val datetime:String = ""){

}