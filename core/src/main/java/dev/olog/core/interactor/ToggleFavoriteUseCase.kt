package dev.olog.core.interactor

import dev.olog.core.gateway.FavoriteGateway
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
        private val gateway: FavoriteGateway

) {

    fun execute() {
        gateway.toggleFavorite()
    }
}
