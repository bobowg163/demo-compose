package utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import java.io.File

/**
 * @作者 bobo
 * @项目 demo-compose
 * @日期 2024/10/28 时间 下午3:47
 * October28日Monday
 */

private lateinit var dataStore: DataStore<Preferences>
private val lock = SynchronizedObject()

fun getDataStore(): DataStore<Preferences> {
    return synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.create {
                File("${System.getProperty("user.home")}/$dataStoreFileName")
            }.also { dataStore = it }
        }
    }
}

internal const val dataStoreFileName = "playWeatherData.preferences_pb"