package torres.gabriel.security2

import android.app.backup.BackupAgentHelper
import android.app.backup.SharedPreferencesBackupHelper
import torres.gabriel.security2.MainActivity.sDataLock
import android.os.ParcelFileDescriptor
import android.app.backup.BackupDataInput
import android.app.backup.BackupDataOutput
import java.io.IOException


/**
 * Created by Gabriel on 22/09/2018.
 */
class BackupData: BackupAgentHelper()  {
    companion object {
        val PREFS_TEST = "testprefs"
        val MY_PREFS_BACKUP_KEY = "myprefs"
    }

    override fun onCreate() {
        super.onCreate()
        var helper = SharedPreferencesBackupHelper(this, PREFS_TEST)
        addHelper(MY_PREFS_BACKUP_KEY, helper)
    }

    @Throws(IOException::class)
    override fun onBackup(oldState: ParcelFileDescriptor, data: BackupDataOutput,
                          newState: ParcelFileDescriptor) {
        // Hold the lock while the FileBackupHelper performs backup
        synchronized(MainActivity.sDataLock) {
            super.onBackup(oldState, data, newState)
        }
    }

    @Throws(IOException::class)
    override fun onRestore(data: BackupDataInput, appVersionCode: Int,
                           newState: ParcelFileDescriptor) {
        // Hold the lock while the FileBackupHelper restores the file
        synchronized(MainActivity.sDataLock) {
            super.onRestore(data, appVersionCode, newState)
        }
    }


}