import kotlin.random.Random

sealed class FoodGoods(override var weight: Int, override var unloadTime: Long, override var loadTime: Long): Goods {

    class Bread(override var name: String = "bread",
        weight: Int = 5,
        unloadTime: Long = 1000,
        loadTime: Long = 1200): FoodGoods(weight, unloadTime, loadTime)

    class Milk(override var name: String = "milk",
               weight: Int = 10,
               unloadTime: Long = 1200,
               loadTime: Long = 1400): FoodGoods(weight, unloadTime, loadTime)

    class Potato(override var name: String = "potato",
                 weight: Int = 20,
                 unloadTime: Long = 1400,
                 loadTime: Long = 1600): FoodGoods(weight, unloadTime, loadTime)

    companion object {
        fun getTheProduct(): FoodGoods {
            return when(Random.nextInt(1,4)) {
                1 -> Bread()
                2 -> Milk()
                else -> Potato()
            }
        }
    }
}