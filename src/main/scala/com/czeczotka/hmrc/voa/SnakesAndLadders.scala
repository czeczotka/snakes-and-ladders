package com.czeczotka.hmrc.voa

import scala.collection.mutable

class SnakesAndLadders {

  private var tokens = 0
  private var positions = mutable.Map[Int, Int]().withDefaultValue(0)

  def addToken(): Int = {
    tokens = tokens + 1
    positions += tokens -> 1
    tokens
  }

  def position(token: Int) = positions(token)

  def moveToken(token: Int, moves: Int): Unit = {
    val current = positions(token)
    positions(token) = current + moves
  }

}

object SnakesAndLadders {

  private val r = scala.util.Random

  def roll(): Int = r.nextInt(6) + 1

}