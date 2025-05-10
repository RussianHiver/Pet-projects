import kotlin.random.Random

sealed class LargeGoods(override var weight: Int, override var unloadTime: Long, override var loadTime: Long): Goods {

    class WashingMachine(override var name: String = "washing machine",
                         weight: Int = 25,
                         unloadTime: Long = 4000,
                         loadTime: Long = 5000): LargeGoods(weight, unloadTime, loadTime)

    class Fridge(override var name: String = "fridge",
                 weight: Int = 35,
                 unloadTime: Long = 3400,
                 loadTime: Long = 4000): LargeGoods(weight, unloadTime, loadTime)

    class Dishwasher(override var name: String = "dishwasher",
                     weight: Int = 30,
                     unloadTime: Long = 3000,
                     loadTime: Long = 3200): LargeGoods(weight, unloadTime, loadTime)

    companion object {
        fun getTheProduct(): LargeGoods {
            return when(Random.nextInt(1,4)) {
                1 -> WashingMachine()
                2 -> Fridge()
                else -> Dishwasher()
            }
        }
    }
}