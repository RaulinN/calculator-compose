package com.github.raulinn.calculator

// state = basically anything that can change on our screen
data class CalculatorState(
  val number1: String = "",
  val number2: String = "",
  val operator: CalculatorOperation? = null,
)
