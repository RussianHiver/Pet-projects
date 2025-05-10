import kotlin.random.Random

sealed class MiddleGoods(override var weight: Int, override var unloadTime: Long, override var loadTime: Long): Goods {

    class VacuumCleaner(override var name: String = "vacuum cleaner",
                        weight: Int = 15,
                        unloadTime: Long = 1800,
                        loadTime: Long = 2000): MiddleGoods(weight, unloadTime, loadTime)
    class Fan(override var name: String = "fan",
              weight: Int = 10,
              unloadTime: Long = 1200,
              loadTime: Long = 1600): MiddleGoods(weight, unloadTime, loadTime)
    class Heater(override var name: String = "heater",
                 weight: Int = 12,
                 unloadTime: Long = 1400,
                 loadTime: Long = 1800): MiddleGoods(weight, unloadTime, loadTime)

    companion object {
        fun getTheProduct(): MiddleGoods {
            return when(Random.nextInt(1,4)) {
                1 -> VacuumCleaner()
                2 -> Fan()
                else -> Heater()
            }
        }
    }
}