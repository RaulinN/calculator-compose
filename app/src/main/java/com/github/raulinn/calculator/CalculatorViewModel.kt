package com.github.raulinn.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

// vm = accepts user events (e.g. click on operation button) and map it to new states
// => changes the state (and keep the state since vm is not destroyed on screen rotation
//    which is the case with android and activities)
class CalculatorViewModel: ViewModel() {
  private val _state = MutableStateFlow(CalculatorState())
  val state = _state.asStateFlow()

  /**
   * https://www.youtube.com/watch?v=NW03ZAQcTuY
   *
   * This is absolutely *NOT* necessary for the calculator app, but it shows
   * flow operations. Here what happens is that the currentNumbers is computed
   * every time the state changes
   *
   * ------------------------------ EXAMPLE #1 ------------------------------
   *
   *   val currentNumbers = state.map { s: CalculatorState ->
   *     "${s.number1} & ${s.number2}"
   *   }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
   *
   * ------------------------------ EXAMPLE #2 ------------------------------
   *
   *   private val isLoggedIn = MutableStateFlow(true)
   *   private val chatMessages = MutableStateFlow<List<String>>(emptyList())
   *   private val userIds = MutableStateFlow<List<Int>>(emptyList())
   *
   *   // in these combine, do **not** use a .value (e.g. userIds.value), instead pass the new
   *   // flow you also want to listen to in the combine list. It could lead to race conditions
   *   val chatState = combine(isLoggedIn, chatMessages, userIds) { isLoggedIn, messages, ids ->
   *     // when any of the 3 flows emits a new value, chatState will change
   *     if (isLoggedIn) {
   *       chatState(
   *         // ...
   *       )
   *     } else null
   *   }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
   *
   */


  /* THIS is the compose state, state flow is better */
  /*
  var state by mutableStateOf(CalculatorState())
    private set // can't change the state from the outside but can still access and read it
  */

  fun onAction(action: CalculatorAction) {
    Log.i(TAG, "onAction: action received: $action")

    when (action) {
      CalculatorAction.Clear -> _state.value = CalculatorState()
      CalculatorAction.Compute -> compute()
      CalculatorAction.Decimal -> enterDecimal()
      CalculatorAction.Delete -> performDeletion()
      is CalculatorAction.Number -> enterNumber(action.number)
      is CalculatorAction.Operation -> enterOperation(action.operator)
    }
  }

  private fun enterOperation(operator: CalculatorOperation) {
    if (state.value.number1.isNotBlank()) {
      _state.update { it.copy(
        operator = operator
      ) }
    }
  }

  private fun enterNumber(number: Int) {
    if (state.value.operator == null) {
      if (state.value.number1.length >= MAX_NUM_LENGTH) {
        return
      }
      _state.update { it.copy(
        number1 = state.value.number1 + number
      ) }
      return
    }
    if (state.value.operator != null) {
      if (state.value.number2.length >= MAX_NUM_LENGTH) {
        return
      }
      _state.update { it.copy(
        number2 = state.value.number2 + number
      ) }
      return
    }
  }

  private fun performDeletion() {
    when {
      state.value.number2.isNotBlank() -> _state.update { it.copy(
        number2 = state.value.number2.dropLast(1)
      ) }
      state.value.operator != null -> _state.update { it.copy(
        operator = null
      ) }
      state.value.number1.isNotBlank() -> _state.update { it.copy(
        number1 = state.value.number1.dropLast(1)
      ) }

      else -> {
        Log.e(TAG, "performDeletion: when in else branch, should never happen")
      }
    }
  }

  private fun enterDecimal() {
    if (
      state.value.operator == null
      && !state.value.number1.contains(".")
      && state.value.number1.isNotBlank()
      && state.value.number1.length < MAX_NUM_LENGTH
    ) {
      // add decimal to the first number
      _state.update { it.copy(
        number1 = state.value.number1 + "."
      ) }
      return
    }
    if (
      state.value.operator != null
      && !state.value.number2.contains(".")
      && state.value.number2.isNotBlank()
      && state.value.number2.length < MAX_NUM_LENGTH
    ) {
      // add decimal to the second number
      _state.update { it.copy(
        number2 = state.value.number2 + "."
      ) }
      return
    }
  }

  private fun compute() {
    val number1 = state.value.number1.toDoubleOrNull()
    val number2 = state.value.number2.toDoubleOrNull()

    if (number1 == null || number2 == null) {
      return
    }

    val result = when (state.value.operator) {
      CalculatorOperation.Add -> number1 + number2
      CalculatorOperation.Divide -> number1 / number2
      CalculatorOperation.Multiply -> number1 * number2
      CalculatorOperation.Subtract -> number1 - number2
      null -> return
    }
    _state.update { it.copy(
      number1 = result.toString().take(15),
      number2 = "",
      operator = null,
    ) }
  }

  companion object {
    private const val TAG = "CalculatorViewModel"
    private const val MAX_NUM_LENGTH = 8
  }
}