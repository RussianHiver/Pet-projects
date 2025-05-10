import kotlin.random.Random

sealed class SmallGoods(override var weight: Int, override var unloadTime: Long, override var loadTime: Long): Goods {
    class Iron(override var name: String = "iron",
               weight: Int = 5,
               unloadTime: Long = 1000,
               loadTime: Long = 1200): SmallGoods(weight, unloadTime, loadTime)
    class Microwave(override var name: String = "microwave",
                    weight: Int = 9,
                    unloadTime: Long = 1800,
                    loadTime: Long = 2000): SmallGoods(weight, unloadTime, loadTime)
    class Kettle(override var name: String = "kettle",
                 weight: Int = 2,
                 unloadTime: Long = 800,
                 loadTime: Long = 1200): SmallGoods(weight, unloadTime, loadTime)

    companion object {
        fun getTheProduct(): SmallGoods {
            return when(Random.nextInt(1,4)) {
                1 -> Iron()
                2 -> Microwave()
                else -> Kettle()
            }
        }
    }
}