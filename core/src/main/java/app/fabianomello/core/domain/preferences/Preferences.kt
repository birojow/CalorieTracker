package app.fabianomello.core.domain.preferences

import app.fabianomello.core.domain.model.ActivityLevel
import app.fabianomello.core.domain.model.WeightGoal
import app.fabianomello.core.domain.model.Gender
import app.fabianomello.core.domain.model.UserInfo

interface Preferences {
    fun saveGender(gender: Gender)
    fun saveWeightGoal(goal: WeightGoal)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveHeight(height: Int)
    fun saveWeight(weight: Float)
    fun saveAge(age: Int)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)

    fun loadUserInfo(): UserInfo

    fun saveShouldShowOnboarding(shouldShow: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_WEIGHT_GOAL = "weight_goal"
        const val KEY_AGE = "age"
        const val KEY_HEIGHT = "height"
        const val KEY_WEIGHT = "weight"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}
