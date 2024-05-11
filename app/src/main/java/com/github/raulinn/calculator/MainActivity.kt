package com.github.raulinn.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.raulinn.calculator.ui.theme.CalculatorTheme
import com.github.raulinn.calculator.ui.theme.MediumGray
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivity: ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CalculatorTheme {
        /* THIS is the compose state, see CalculatorViewModel
        val vm = viewModel<CalculatorViewModel>()
        val state = vm.state
        */

        /**
         * https://www.youtube.com/watch?v=T8vApYJlW8o
         *
         * WHY USE STATE FLOWS?
         *  1. you can use flow operators
         *  2. easier to deal with process death (the state is never is an
         *     "unreachable state" when the app is killed
         *  3. keep view model "compose free" (can use the view model in
         *     a different project that does not use compose
         */
        val vm = viewModel<CalculatorViewModel>()
        val state by vm.state.collectAsState()

        Calculator(
          state = state,
          onAction = vm::onAction,
          buttonSpacing = 8.dp,
          modifier = Modifier
            .fillMaxSize()
            .background(MediumGray)
            .padding(16.dp)
        )
      }
    }
  }
}
