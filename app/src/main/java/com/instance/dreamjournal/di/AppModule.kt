package com.instance.dreamjournal.di

import com.instance.dreamjournal.AppDatabase
import com.instance.dreamjournal.JournalDao



import android.app.Application
import android.content.Context
import androidx.room.Room
import com.instance.dreamjournal.GeneralRepository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideQueryQuestsByName() = FirebaseFirestore.getInstance()
//        .collection(FIRESTORE_COLLECTION)
//        .orderBy(NAME_PROPERTY, Query.Direction.ASCENDING)

//    @Provides
//    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideApplication(app: Application): Context = app

//    @Provides
//    fun provideQuestsRef(
//        db: FirebaseFirestore
//    ) = db.collection(QUESTS)


    /* @Provides
     fun provideDestinationsNavigator(app: DataBranchApp

     ) : DestinationsNavigator = DestinationScope.destinationsNavigator
 */
    /* @Provides
     fun provideUsersRef(
         db: FirebaseFirestore
     ) = db.collection(USERS)

     @Provides
     fun provideResponsesRef(
         db: FirebaseFirestore
     ) = db.collection(RESPONSES)*/

    /*
        @Provides
        @Named("questsRef")
        fun provideQuestCollRef(db: FirebaseFirestore): CollectionReference {
            return db.collection("quests")
        }
        @Provides
        @Named("usersRef")
        fun provideUserCollRef(db: FirebaseFirestore): CollectionReference {
            return db.collection("users")
        }
        @Provides
        @Named("responsesRef")
        fun provideResponsesCollRef(db: FirebaseFirestore): CollectionReference {
            return db.collection("responses")
        }
        @Provides
        @Named("chatRoomRef")
        fun provideChatRoomCollRef(db: FirebaseFirestore): CollectionReference {
            return db.collection("chatRooms")
        }*/

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "debug_extra.db")
            .fallbackToDestructiveMigration()
            .build()
    }
   /* @Singleton
    @Provides
    fun provideLocalQuestsRepository(app:Application,db: AppDatabase):
            LocalQuestsRepository {
        return LocalQuestsRepository(app,db)
    }
    @Singleton
    @Provides
    fun provideItemRepository(app:Application,db: AppDatabase):
            ItemRepository {
        return ItemRepository(app,db)
    }
    @Singleton
    @Provides
    fun provideNoteRepository(app:Application,db: AppDatabase):
            NoteRepository {
        return NoteRepository(app,db)
    }*/
    @Singleton
    @Provides
    fun provideGeneralRepository(app:Application,db: AppDatabase):
           GeneralRepository {
        return GeneralRepository(db)
    }

    /*@Singleton THIS IS FOR ROOM
    @Provides
    fun provideResponseRepository(app:Application,db: AppDatabase):
            ResponseRepository {
        return ResponseRepository(app,db)
    }*/

   /* @Provides
    fun provideUseCases(
        repo: QuestsRepository,
        responseRepo: ResponseRepository,
        localrepo: LocalQuestsRepository,
        socialRepo: SocialRepository,
        dao: QuestDao
    ) = UseCases(
        getQuests = GetQuests(repo),
        addQuest = AddQuest(repo),
        deleteQuest = DeleteQuest(repo),
        addQuestbyQuest = AddQuestbyQuest(repo),
        getLocalQuests= GetLocalQuests(dao),
        addQuestEntitybyQuest= AddQuestEntitybyQuest(dao),
        addQuestEntity = AddQuestEntity(dao),
        addNewQuestEntity = AddNewQuestEntity(localrepo),
        addNewObjectiveEntityToQuestEntity=  AddNewObjectiveEntityToQuestEntity(localrepo),
        addObjectiveEntityToQuestEntity=  AddObjectiveEntityToQuestEntity(localrepo),
        addResponse = AddResponse(responseRepo),
        getResponses = GetResponses(responseRepo),
        addChatRoom= AddChatRoom(socialRepo),
        getChatRooms = GetChatRooms(socialRepo)
    )*/



    /* @Singleton
     @Provides
     fun providesRoomDatabase(): AppDatabase {
         return db
     }
 */


    @Provides
    fun provideJournalDao(db:AppDatabase): JournalDao {
        return db.journalDao()
    }

    /*@Provides
    fun provideMe(localrepo: GeneralRepository): UserWithAbilities {
        val me= localrepo.getMe()
        val abilities = localrepo.getAbilities()
        if(me == null){
            return UserWithAbilities(User(), abilities = abilities)
        }else{return me}


    }*/






    //fun provideQuestDAO(appDatabase: AppDatabase): QuestDAO { return appDatabase.entityDao() }

}

/*
@Provides
    fun provideLocalQuests(app:Application):Array<QuestEntity>{
        return provideQuestDao(provideDb(app = app)).getAll()
    }
    @Provides
    @Named("questsRef")
    fun provideCollectionReference(db: FirebaseFirestore): CollectionReference? {
        return db.collection("quests")
    }
}
*/