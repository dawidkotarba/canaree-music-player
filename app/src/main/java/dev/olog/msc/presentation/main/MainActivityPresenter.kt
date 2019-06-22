package dev.olog.msc.presentation.main

import android.content.Context
import dev.olog.core.dagger.ApplicationContext
import dev.olog.msc.domain.gateway.prefs.PresentationPreferences
import dev.olog.msc.domain.interactor.IsRepositoryEmptyUseCase
import dev.olog.msc.presentation.main.widget.CustomBottomNavigator
import dev.olog.shared.Permissions
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val presentationPrefs: PresentationPreferences,
    val isRepositoryEmptyUseCase: IsRepositoryEmptyUseCase
) {

    fun isFirstAccess(): Boolean {
        val canReadStorage = Permissions.canReadStorage(context)
        val isFirstAccess = presentationPrefs.isFirstAccess()
        return !canReadStorage || isFirstAccess
    }

    fun getLastBottomViewPage(): CustomBottomNavigator.Page = presentationPrefs.getLastBottomViewPage()

    fun setLastBottomViewPage(page: CustomBottomNavigator.Page) {
        presentationPrefs.setLastBottomViewPage(page)
    }

    fun canShowPodcastCategory(): Boolean {
        return presentationPrefs.canShowPodcastCategory()
    }

}