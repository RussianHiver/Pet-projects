import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

object DistributionCenter {
    object Storehouse {
        val foodGoods = Channel<FoodGoods>(100)
        val largeSizedGoods = Channel<LargeGoods>(100)
        val middleSizedGoods = Channel<MiddleGoods>(100)
        val smallSizedGoods = Channel<SmallGoods>(100)
    }
    private val mutex = Mutex()
    suspend fun unload(cargo: MutableList<Goods>) {
        val items = cargo.iterator()
        while (items.hasNext()) {
            when (val item = items.next()) {
                is FoodGoods -> {
                    delay(item.unloadTime)
                    println("A ${item.name} has been added to the storehouse.")
                    Storehouse.foodGoods.send(item)
                }
                is SmallGoods -> {
                    delay(item.unloadTime)
                    println("A ${item.name} has been added to the storehouse.")
                    Storehouse.smallSizedGoods.send(item)
                }
                is MiddleGoods -> {
                    delay(item.unloadTime)
                    println("A ${item.name} has been added to the storehouse.")
                    Storehouse.middleSizedGoods.send(item)
                }
                is LargeGoods -> {
                    delay(item.unloadTime)
                    println("A ${item.name} has been added to the storehouse.")
                    Storehouse.largeSizedGoods.send(item)
                }
            }
        }
    }

    private suspend fun getItem(itemChannel: Channel<out Goods>): Goods {
        val item: Goods
        mutex.withLock {
            item = itemChannel.receive()
        }
        return item
    }

    suspend fun load(truck: Truck) {
        val chosenGoods = when (Random.nextInt(1, 4)) {
            1 -> Storehouse.foodGoods
            2 -> Storehouse.largeSizedGoods
            3 -> Storehouse.middleSizedGoods
            else -> Storehouse.smallSizedGoods
        }

        var capacityFilled = 0

        cycle@ while (truck.capacity > capacityFilled) {
                val capacityLeft = truck.capacity - capacityFilled
                val cargoToAdd = getItem(chosenGoods)
                if (capacityLeft >= cargoToAdd.weight) {
                        delay(cargoToAdd.loadTime)
                        println("An item (${cargoToAdd.name}) has been loaded in the ${truck.name}.")
                        truck.cargo.add(cargoToAdd)
                        capacityFilled += cargoToAdd.weight
                } else break@cycle
        }
    }
}