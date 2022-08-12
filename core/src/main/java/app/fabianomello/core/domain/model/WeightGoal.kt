package app.fabianomello.core.domain.model

sealed class WeightGoal(val name: String) {
    object Lose : WeightGoal("lose")
    object Keep : WeightGoal("keep")
    object Gain : WeightGoal("gain")

    companion object {
        fun fromString(name: String): WeightGoal {
            return when(name) {
                "lose" -> Lose
                "keep" -> Keep
                "gain" -> Gain
                else -> Keep
            }
        }
    }
}
