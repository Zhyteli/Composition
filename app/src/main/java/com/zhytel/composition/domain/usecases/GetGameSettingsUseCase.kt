package com.zhytel.composition.domain.usecases

import com.zhytel.composition.domain.entity.GameSettings
import com.zhytel.composition.domain.entity.Level
import com.zhytel.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}