object CoffeeMachine{
    private enum class CoffeeMode{DEFAULT, COFFEE}
    private var mode = CoffeeMode.DEFAULT
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550

    fun start(reader: () -> String){
        do{
            when(mode){
                CoffeeMode.DEFAULT -> {
                    println("\nWrite action (buy, fill, take, remaining, exit):")

                    when(reader()){
                        "buy" -> mode = CoffeeMode.COFFEE
                        "fill" -> fill(reader)
                        "take" -> take()
                        "remaining" -> printStatus()
                        "exit" -> break
                    }
                }
                CoffeeMode.COFFEE -> {
                    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")

                    when(reader()){
                        "1" -> {
                            if(makeCoffee(250, 0, 16)) money += 4
                            mode = CoffeeMode.DEFAULT
                        }
                        "2" -> {
                            if(makeCoffee(350, 75, 20)) money += 7
                            mode = CoffeeMode.DEFAULT
                        }
                        "3" -> {
                            if(makeCoffee(200, 100, 12)) money += 6
                            mode = CoffeeMode.DEFAULT
                        }
                        "back" -> mode = CoffeeMode.DEFAULT
                    }
                }
            }
        }while(true)
    }

    private fun printStatus(){
        println("\nThe coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$cups disposable cups")
        println("$money of money")
    }

    private fun makeCoffee(requiredWater: Int, requiredMilk: Int, requiredBeans: Int): Boolean{
        when{
            requiredWater > water -> println("Sorry, not enough water!")
            requiredMilk > milk -> println("Sorry, not enough milk!")
            requiredBeans > beans -> println("Sorry, not enough coffee beans!")
            else -> {
                println("I have enough resources, making you a coffee!")

                water -= requiredWater
                milk -= requiredMilk
                beans -= requiredBeans
                cups -= 1

                return true
            }
        }
        return false
    }

    private fun fill(input: ()-> String){
        println("\nWrite how many ml of water you want to add:")
        water += input().toInt()
        println("Write how many ml of milk you want to add:")
        milk += input().toInt()
        println("Write how many grams of coffee beans you want to add:")
        beans += input().toInt()
        println("Write how many disposable cups you want to add:")
        cups += input().toInt()
    }

    private fun take() {
        println("\nI gave you $money")
        money = 0
    }
}

fun main(){
    CoffeeMachine.start { readln() }
}