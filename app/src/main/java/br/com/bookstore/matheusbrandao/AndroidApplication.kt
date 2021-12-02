package br.com.bookstore.matheusbrandao

import androidx.multidex.MultiDexApplication
import br.com.bookstore.matheusbrandao.di.dataSourceModule
import br.com.bookstore.matheusbrandao.di.presentationModule
import br.com.bookstore.matheusbrandao.di.repositoryModule
import br.com.bookstore.matheusbrandao.di.serviceModule
import org.koin.android.ext.android.startKoin

class AndroidApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(
                serviceModule,
                dataSourceModule,
                repositoryModule,
                presentationModule
            )
        )
    }
}