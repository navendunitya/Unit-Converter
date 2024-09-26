package com.example.unitconvertermain

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertermain.ui.theme.UNITCONVERTERMAINTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UNITCONVERTERMAINTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("meters") }
    var outputunit by remember { mutableStateOf("meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oconversionFactor = remember { mutableDoubleStateOf(1.00)}

    fun convertunits(){
        val inputValueDouble= inputvalue.toDoubleOrNull() ?:0.0
        //? elvis operator
        val result= (inputValueDouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt() / 100.0
        outputvalue= result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //here all the elements will be stacked
        Text("Unit Converter", modifier = Modifier.padding(20.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange = {
            inputvalue=it
            convertunits()

        }, label = { Text(text = "Enter Value" ) }//gives label to text box
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
            Box {
                //input button
                Button(onClick = { iExpanded=true }) {
                    Text(text = "Input Unit")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            iExpanded=false
                            inputunit="Centimeters"
                            conversionFactor.value= 0.01
                            convertunits()
                        })


                    DropdownMenuItem(text = { Text("Meters") },
                        onClick = {
                            iExpanded=false
                            inputunit="Meters"
                            conversionFactor.value= 1.00
                            convertunits()
                        })

                    DropdownMenuItem(text = { Text("Millimeters") },
                        onClick = {  iExpanded=false
                            inputunit="Millimeters"
                            conversionFactor.value= 0.001
                            convertunits()})

                }

            }
            Spacer(modifier= Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded=true }) {
                    Text(text = "Output Unit")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            oExpanded=false
                            outputunit="Centimeters"
                            oconversionFactor.value= 0.01
                            convertunits()
                        })


                    DropdownMenuItem(text = { Text("Meters") },
                        onClick = {
                            oExpanded=false
                            outputunit="Meters"
                            oconversionFactor.value= 1.00
                            convertunits()
                        })

                    DropdownMenuItem(text = { Text("Millimeters") },
                        onClick = { oExpanded=false
                            outputunit="Millimeters"
                            oconversionFactor.value= 0.001
                             convertunits()
                        }
                    )

                }



            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputvalue $outputunit", style = MaterialTheme.typography.headlineLarge

        )
    }

}







