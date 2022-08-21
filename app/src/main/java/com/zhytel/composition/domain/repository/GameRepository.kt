package com.zhytel.composition.domain.repository

import com.zhytel.composition.domain.entity.GameSettings
import com.zhytel.composition.domain.entity.Level
import com.zhytel.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}