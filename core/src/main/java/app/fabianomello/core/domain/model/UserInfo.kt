package app.fabianomello.core.domain.model

data class UserInfo(
    val gender: Gender,
    val weight: Float,
    val age: Int,
    val height: Int,
    val activityLevel: ActivityLevel,
    val weightGoal: WeightGoal,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)
