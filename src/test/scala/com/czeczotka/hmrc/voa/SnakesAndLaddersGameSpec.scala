package com.czeczotka.hmrc.voa

import org.scalatest.{FeatureSpec, GivenWhenThen}

class SnakesAndLaddersGameSpec extends FeatureSpec with GivenWhenThen {

  feature("Moving Your Token") {

    scenario("Token Can Move Across the Board") {
      info("As a player")
      info("I want to be able to move my token")
      info("So that I can get closer to the goal")

      Given("the game is started")
      val game = new SnakesAndLaddersGame

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
      val game = new SnakesAndLaddersGame
      val token = game.addToken()

      When("the player rolls a dice")
      val dice = SnakesAndLaddersGame.roll()

      Then("the result should be between 1-6 inclusive")
      assert((dice +: List.fill(1000)(SnakesAndLaddersGame.roll())).forall(r => r >= 1 && r <= 6))

      When("they move their token")
      game.moveToken(token, dice)

      Then("the token should move by a correct number of spaces")
      assert(game.position(token) === 1 + dice)
    }

    scenario("Player Can Win the Game") {
      info("As a player")
      info("I want to be able to win the game")
      info("So that I can gloat to everyone around")

      Given("the game is started")
      val game = new SnakesAndLaddersGame
      val token = game.addToken()

      And("the token is on square 97")
      game.moveToken(token, 96)
      assert(game.position(token) === 97)

      When("the token is moved 4 spaces")
      game.moveToken(token, 4)

      Then("the token is on square 97")
      assert(game.position(token) === 97)

      And("the player has not won the game")
      assert(game.result === None)

      When("the token is moved 3 spaces")
      game.moveToken(token, 3)

      Then("the token is on square 100")
      assert(game.position(token) === 100)

      And("the player has won the game")
      assert(game.result === Some(token))
    }
  }

  feature("Snakes and Ladders") {

    scenario("Snakes Go Down, Not Up") {
      info("As a player")
      info("I want snakes to move my token down")
      info("So that the game is more fun")

      Given("there is a snake connecting squares 2 and 12")
      val game = new SnakesAndLaddersGame(Map(12 -> 2))
      val token = game.addToken()

      When("the token lands on square 2")
      game.moveToken(token, 1)

      Then("the token is on square 2")
      assert(game.position(token) === 2)

      When("the token lands on square 12")
      game.moveToken(token, 10)

      Then("the token is on square 2")
      assert(game.position(token) === 2)
    }

    scenario("Ladders Go Up, Not Down") {
      info("As a player")
      info("I want ladders to move my token up")
      info("So that the game is more fun")

      Given("there is a ladder connecting squares 2 and 12")
      val game = new SnakesAndLaddersGame(Map(2 -> 12))
      val token = game.addToken()

      When("the token lands on square 2")
      game.moveToken(token, 1)

      Then("the token is on square 12")
      assert(game.position(token) === 12)

      When("the token lands on square 12")
      game.moveToken(token, 0)

      Then("the token is on square 12")
      assert(game.position(token) === 12)
    }
  }
}