package com.github.raulinn.calculator

// https://stackoverflow.com/questions/76643924/object-and-data-object-in-kotlin
// - Sealed class = class that can have a fixed set of subclasses defined within it.
//   These subclasses are typically used for representing restricted hierarchies.
// - Object = singleton instance of a class. When you declare a class as an object,
//   you're essentially creating a single instance of that class. It's commonly used
//   for implementing the Singleton design pattern or for defining anonymous objects.
// - Data object : add some features similar to those of Data Classes to the object.
//   For instance a toString() is automatically generated for you such that when you
//   print data object you get human readable name of the class without @address.
sealed class CalculatorAction {
  // input a number
  data class Number(val number: Int): CalculatorAction()
  // clear input
  data object Clear: CalculatorAction()
  // delete last character of input
  data object Delete: CalculatorAction()

  // Enter a decimal point
  data object Decimal: CalculatorAction()
  // Perform the calculation
  data object Compute: CalculatorAction()

  // add an operator to the calculation
  data class Operation(val operator: CalculatorOperation): CalculatorAction()
}