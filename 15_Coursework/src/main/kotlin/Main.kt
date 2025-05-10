import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {

    fun CoroutineScope.getFullTrucks() = produce {
        while (true) {
            val truck = Truck.getTheFullTruck()
            println("A ${truck.name} with cargo has been generated.\n" +
                    "It has: ${Truck.cargoList(truck.cargo)}. ")
            send(truck)
            delay(5000)
        }
    }

    fun CoroutineScope.unloadTruck(cargo: ReceiveChannel<Truck>) = launch {
        for (truck in cargo) {
            DistributionCenter.unload(truck.cargo)
        }
    }

    fun CoroutineScope.getEmptyTrucks(): ReceiveChannel<Truck> = produce {
        while (true) {
            val emptyTruck = Truck.getTheEmptyTruck()
            println("A ${emptyTruck.name} to load has been generated.")
            send(emptyTruck)
        }
    }

    fun CoroutineScope.loadTruck(truckGenerator: ReceiveChannel<Truck>) = launch {
        for (truck in truckGenerator) {
            DistributionCenter.load(truck)
        }
    }

    val trucksWithCargo = getFullTrucks()
    val truckWithoutCargo = getEmptyTrucks()
    repeat(3) { unloadTruck(trucksWithCargo) }
    repeat(5) { loadTruck(truckWithoutCargo) }
    delay(60000)
    coroutineContext.cancelChildren()
}
