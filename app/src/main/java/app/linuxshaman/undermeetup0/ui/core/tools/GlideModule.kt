package app.linuxshaman.undermeetup0.ui.core.tools

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Created by linuxshaman on 24.03.2025.
 */
@Module
@InstallIn(FragmentComponent::class)
object GlideModule {

    @Provides
    @FragmentScoped
    fun provideGlide(
        @ActivityContext context: Context
    ): RequestManager {
        return Glide.with(context)
    }


}