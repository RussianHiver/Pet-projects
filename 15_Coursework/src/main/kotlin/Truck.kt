import java.util.*
import kotlin.random.Random

sealed class Truck(val capacity: Int) {
    abstract val name: String
    abstract val cargo: MutableList<Goods>

    class BigTruck(capacity: Int = 300): Truck(capacity) {
        override val name: String = "big truck"
        override val cargo = mutableListOf<Goods>()
    }
    class MiddleTruck(capacity: Int = 200): Truck(capacity) {
        override val name: String = "middle truck"
        override val cargo = mutableListOf<Goods>()
    }
    class SmallTruck(capacity: Int = 100): Truck(capacity) {
        override val name: String = "small truck"
        override val cargo = mutableListOf<Goods>()
    }

    companion object {

        private fun getTheTruckToUnload(): Truck {
            return when(Random.nextInt(1,4)) {
                1 -> BigTruck()
                2 -> MiddleTruck()
                else -> SmallTruck()
            }
        }

        private fun getTheTech(): Goods {
            return when (Random.nextInt(1, 4)) {
                1 -> LargeGoods.getTheProduct()
                2 -> MiddleGoods.getTheProduct()
                else -> SmallGoods.getTheProduct()
            }
        }

        fun getTheFullTruck(): Truck {
            val truck = getTheTruckToUnload()
            val theChosenCargo = if (Random.nextBoolean()) getTheTech() else FoodGoods.getTheProduct()
            truck.cargo.add(theChosenCargo)
            var capacityFilled = 0
            while (truck.capacity > capacityFilled) {
                val capacityLeft = truck.capacity - capacityFilled
                val cargoToAdd = if (theChosenCargo is FoodGoods) FoodGoods.getTheProduct() else getTheTech()
                if (capacityLeft >= cargoToAdd.weight) {
                    truck.cargo.add(cargoToAdd)
                    capacityFilled += cargoToAdd.weight
                } else break
            }
            return truck
        }

        fun getTheEmptyTruck(): Truck {
            return if (Random.nextBoolean()) MiddleTruck() else SmallTruck()
        }

        fun cargoList(cargo: MutableList<Goods>): MutableList<String> {
            val cargoNames = mutableListOf<String>()
            for (item in cargo) {
                cargoNames.add(item.name)
            }
            return cargoNames
        }
    }
}