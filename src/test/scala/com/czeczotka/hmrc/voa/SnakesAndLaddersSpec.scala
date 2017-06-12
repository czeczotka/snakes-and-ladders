package com.czeczotka.hmrc.voa

import org.scalatest.{FeatureSpec, GivenWhenThen}

class SnakesAndLaddersSpec extends FeatureSpec with GivenWhenThen {

  feature("Moving Your Token") {

    scenario("Token Can Move Across the Board") {
      info("As a player")
      info("I want to be able to move my token")
      info("So that I can get closer to the goal")

      Given("the game is started")
      val game = new SnakesAndLadders

      When("the token is placed on the board")
      val token = game.addToken()

      Then("the token is on square 1")
      assert(game.position(token) === 1)

      When("the token is moved 3 spaces")
      game.moveToken(token, 3)

      Then("the token is on square 4")
      assert(game.position(token) === 4)

      When("then it is moved 4 spaces")
      game.moveToken(token, 4)

      Then("the token is on square 8")
      assert(game.position(token) === 8)
    }

    scenario("Moves Are Determined By Dice Rolls") {
      info("As a player")
      info("I want to move my token based on the roll of a die")
      info("So that there is an element of chance in the game")

      Given("the game is started")
      val game = new SnakesAndLadders
      val token = game.addToken()

      When("the player rolls a dice")
      val dice = SnakesAndLadders.roll()

      Then("the result should be between 1-6 inclusive")
      assert((dice +: List.fill(1000)(SnakesAndLadders.roll())).forall(r => r >= 1 && r <= 6))

      When("they move their token")
      game.moveToken(token, dice)

      Then("the token should move by a correct number of spaces")
      assert(game.position(token) === 1 + dice)
    }

    scenario("Player Can Win the Game") {
      info("As a player")
      info("I want to be able to win the game")
      info("So that I can gloat to everyone around")
      pending
    }
  }
}