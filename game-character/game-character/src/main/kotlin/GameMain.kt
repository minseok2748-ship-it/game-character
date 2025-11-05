abstract class Unit(
    val name: String
) {
    abstract fun moveTo(x: Int, y: Int)
}

interface Attackable {
    fun attack(target: Unit)
}

class Knight(name: String) : Unit(name), Attackable {
    override fun moveTo(x: Int, y: Int) {
        println("$name: 말을 타고 ($x, $y) 위치로 이동합니다.")
    }

    override fun attack(target: Unit) {
        if (target is Griffin) {
            println("$name: 그리핀은 공중에 있어서 공격할 수 없습니다.")
        } else {
            println("$name: 창으로 ${target.name} 을(를) 공격합니다!")
        }
    }
}

class Archer(name: String) : Unit(name), Attackable {
    override fun moveTo(x: Int, y: Int) {
        println("$name: 걸어서 ($x, $y) 위치로 이동합니다.")
    }

    override fun attack(target: Unit) {
        println("$name: 화살로 ${target.name} 을(를) 공격합니다!")
    }
}

class Griffin(name: String) : Unit(name), Attackable {
    override fun moveTo(x: Int, y: Int) {
        println("$name: 날아서 ($x, $y) 위치로 이동합니다.")
    }

    override fun attack(target: Unit) {
        if (target is Griffin) {
            println("$name: 공중에 있는 대상은 공격할 수 없습니다.")
        } else {
            println("$name: 하늘에서 번개를 내려 ${target.name} 을(를) 공격합니다!")
        }
    }
}

class Shuttle(name: String) : Unit(name) {
    private val maxCapacity = 8
    private val passengers = mutableListOf<Unit>()

    override fun moveTo(x: Int, y: Int) {
        println("$name: 날아서 ($x, $y) 위치로 이동합니다.")
    }

    fun board(unit: Unit) {
        if (unit is Griffin) {
            println("$name: 그리핀은 탑승할 수 없습니다.")
        } else if (passengers.size >= maxCapacity) {
            println("$name: 더 이상 탑승할 수 없습니다.")
        } else {
            passengers.add(unit)
            println("${unit.name}: $name 에 탑승했습니다.")
        }
    }

    fun unloadAll() {
        println("$name: 탑승한 모든 유닛을 하차시킵니다.")
        passengers.forEach { println("${it.name} 하차 완료.") }
        passengers.clear()
    }
}

fun main() {
    val knights = List(16) { Knight("기사${it+1}") }
    val archers = List(16) { Archer("궁수${it+1}") }
    val shuttles = List(4) { Shuttle("셔틀${it+1}") }
    val griffins = List(5) { Griffin("그리핀${it+1}") }

    for (i in 0 until 16) {
        shuttles[i / 4].board(knights[i])
        shuttles[i / 4].board(archers[i])
    }

    shuttles.forEach { it.moveTo(10, 20) }
    griffins.forEach { it.moveTo(10, 20) }

    shuttles.forEach { it.unloadAll() }

    val testKnight = knights[0]
    testKnight.attack(knights[1])
    testKnight.attack(archers[1])
    testKnight.attack(griffins[0])
    testKnight.attack(shuttles[0])

    val testArcher = archers[0]
    testArcher.attack(archers[1])
    testArcher.attack(knights[1])
    testArcher.attack(griffins[0])
    testArcher.attack(shuttles[0])

    val testGriffin = griffins[0]
    testGriffin.attack(griffins[1])
    testGriffin.attack(archers[1])
    testGriffin.attack(knights[1])
    testGriffin.attack(shuttles[0])
}