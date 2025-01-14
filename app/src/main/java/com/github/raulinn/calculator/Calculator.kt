package com.github.raulinn.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.raulinn.calculator.ui.theme.LightGray
import com.github.raulinn.calculator.ui.theme.Orange

@Composable
fun Calculator(
  state: CalculatorState,
  onAction: (CalculatorAction) -> Unit,
  modifier: Modifier = Modifier,
  buttonSpacing: Dp = 8.dp,
) {
  Log.d("Calculator", "Calculator was called!")

  Box(modifier = modifier) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter),
      verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
      Text(
        text = state.number1 + (state.operator?.symbol ?: "") + state.number2,
        textAlign = TextAlign.End,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 32.dp),
        fontWeight = FontWeight.Light,
        fontSize = 72.sp,
        lineHeight = 72.sp,
        color = Color.White,
        maxLines = 2,
        overflow = TextOverflow.Clip
      )
      
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
      ) {
        CalculatorButton(
          symbol = "AC",
          modifier = Modifier
            .background(LightGray)
            .aspectRatio(2f)
            .weight(2f),
          onClick = {
            onAction(CalculatorAction.Clear)
          },
        )
        CalculatorButton(
          symbol = "Del",
          modifier = Modifier
            .background(LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Delete)
          },
        )
        CalculatorButton(
          symbol = "/",
          modifier = Modifier
            .background(Orange)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
          },
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
      ) {
        CalculatorButton(
          symbol = "7",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(7))
          },
        )
        CalculatorButton(
          symbol = "8",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(8))
          },
        )
        CalculatorButton(
          symbol = "9",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(9))
          },
        )
        CalculatorButton(
          symbol = "*",
          modifier = Modifier
            .background(Orange)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
          },
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
      ) {
        CalculatorButton(
          symbol = "4",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(4))
          },
        )
        CalculatorButton(
          symbol = "5",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(5))
          },
        )
        CalculatorButton(
          symbol = "6",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(6))
          },
        )
        CalculatorButton(
          symbol = "-",
          modifier = Modifier
            .background(Orange)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
          },
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
      ) {
        CalculatorButton(
          symbol = "1",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(1))
          },
        )
        CalculatorButton(
          symbol = "2",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(2))
          },
        )
        CalculatorButton(
          symbol = "3",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Number(3))
          },
        )
        CalculatorButton(
          symbol = "+",
          modifier = Modifier
            .background(Orange)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Operation(CalculatorOperation.Add))
          },
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
      ) {
        CalculatorButton(
          symbol = "0",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(2f)
            .weight(2f),
          onClick = {
            onAction(CalculatorAction.Number(0))
          },
        )
        CalculatorButton(
          symbol = ".",
          modifier = Modifier
            .background(Color.DarkGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Decimal)
          },
        )
        CalculatorButton(
          symbol = "=",
          modifier = Modifier
            .background(Orange)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onAction(CalculatorAction.Compute)
          },
        )
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorPreview() {
  Calculator(state = CalculatorState(), onAction = {})
}
