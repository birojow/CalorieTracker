package app.fabianomello.tracker_domain.model

sealed class MealType(val name: String) {
    object Breakfast: MealType("café da manhã")
    object Lunch: MealType("almoço")
    object Dinner: MealType("jantar")
    object Snack: MealType("lanche")

    companion object {
        fun fromString(name: String): MealType {
            return when(name) {
                "café da manhã" -> Breakfast
                "almoço" -> Lunch
                "jantar" -> Dinner
                "lanche" -> Snack
                else -> Breakfast
            }
        }
    }
}
