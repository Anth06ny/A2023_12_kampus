package com.amonteiro.a2023_12_kampus

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.random.Random.Default.nextInt
import kotlin.random.Random.Default.nextLong

fun main() {
    electionResult()
}

fun electionResult() {
    val start = System.currentTimeMillis()

    runBlocking {

        //Pour stocker les async
        val task = ArrayList<Deferred<ResultBean>>()

        repeat(100) {
            task.add(async { getResultFromDepartment(it) })
        }

        var nbGandalf = 0
        var nbDumbledore = 0
        var nbMerlin = 0

        task.awaitAll().forEach{
            nbGandalf += it.nbVoteGandalf
            nbDumbledore += it.nbVoteDumbledore
            nbMerlin += it.nbVoteMerlin
        }

        var sum = nbGandalf + nbDumbledore + nbMerlin

        println("Gandalf : ${(nbGandalf * 100.0 / sum).format(2)}%")
        println("Dumbledore : ${(nbDumbledore * 100.0 / sum).format(2)}%")
        println("Merlin : ${(nbMerlin * 100.0 / sum).format(2)}%")

    }
    println("Done in ${(System.currentTimeMillis() - start)}ms")
}
//Pour afficher avec 2 chiffres après la virgule 12.556.format(2)
fun Double.format(digits: Int) = "%.${digits}f".format(this)

//Récupère les résultats du département e
suspend fun getResultFromDepartment(deprtNumber: Int): ResultBean {
    delay(nextLong(3000)) //temps aléatoire
    println("Le département : $deprtNumber a répondu")
    return ResultBean()
}

class ResultBean {
    val nbVoteGandalf = nextInt(10000)
    val nbVoteDumbledore = nextInt(10000)
    val nbVoteMerlin = nextInt(10000)
}


fun asyncExemple() = runBlocking {
    val resultAsync1 = async { getValue() }
    val resultAsync2: Deferred<Int> = async { getValue() }

    //Attendre le résultat
    val res = resultAsync1.await() + resultAsync2.await()

    println("res=$res")
}

suspend fun getValue(): Int {
    delay(500)
    return 5
}

fun exoCoroutine() {
    val ballot = BallotBoxBean()
    val before = System.currentTimeMillis()


    runBlocking {
        repeat(1000000) {
            launch(Dispatchers.Default) {
                ballot.add1VoiceAndWaitWithDelay()
            }
        }
    }

    println("number=${ballot.current}")

    val after = System.currentTimeMillis()
    println("Done in ${after - before} ms")
}


fun exoThread() {
    val ballot = BallotBoxBean()
    val before = System.currentTimeMillis()

    val list = ArrayList<Thread>()

    repeat(10) {
        list += thread {
            ballot.add1VoiceAndWait()
        }
    }

    list.forEach {
        it.join()
    }

    println("number=${ballot.current}")

    val after = System.currentTimeMillis()
    println("Done in ${after - before} ms")
}

//Classe garantissant un compte ThreadSafe
class BallotBoxBean {
    private val number = AtomicInteger(0)


    suspend fun add1VoiceAndWaitWithDelay() {
        delay(2000)
        number.incrementAndGet()
    }

    fun add1VoiceAndWait() {
        Thread.sleep(2000)
        number.incrementAndGet()
    }

    val current
        get() = number.get()
}

