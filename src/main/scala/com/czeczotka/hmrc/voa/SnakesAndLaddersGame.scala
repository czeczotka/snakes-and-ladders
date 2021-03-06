package com.czeczotka.hmrc.voa

import scala.collection.mutable

class SnakesAndLaddersGame(snakesAndLadders: Map[Int, Int] = Map()) {

  private var tokens = 0
  private var positions = mutable.Map[Int, Int]().withDefaultValue(0)

  def addToken(): Int = {
    tokens = tokens + 1
    positions += tokens -> 1
    tokens
  }

  def position(token: Int) = positions(token)

  def moveToken(token: Int, moves: Int): Unit = {
    val next = positions(token) + moves
    if (next <= 100) {
      snakesAndLadders.get(next) match {
        case Some(value) => positions(token) = value
        case None => positions(token) = next
      }
    }
  }

  def result(): Option[Int] = positions.find {
    case (token, position) => position == 100
  }.map(_._1)

}

object SnakesAndLaddersGame {

  private val r = scala.util.Random

  def roll(): Int = r.nextInt(6) + 1

}