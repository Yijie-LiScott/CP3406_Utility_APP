package au.edu.jcu.assessment.utilityapp

import android.app.Application
import au.edu.jcu.assessment.utilityapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UtilityApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UtilityApplication)
            modules(appModules)
        }
    }
}