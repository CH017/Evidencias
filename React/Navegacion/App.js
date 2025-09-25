import React from "react";
import { View, Text, Button } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

const Stack = createNativeStackNavigator();

// Pantalla Menú
function MenuScreen({ navigation }) {
  return (
    <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
      <Text style={{ fontSize: 22, marginBottom: 20 }}>Menú Principal</Text>
      <Button title="IMC" onPress={() => navigation.navigate("IMC")} />
      <View style={{ margin: 10 }} />
      <Button title="Cambio de Divisas" onPress={() => navigation.navigate("Divisas")} />
      <View style={{ margin: 10 }} />
      <Button title="Cálculo de Propinas" onPress={() => navigation.navigate("Propinas")} />
    </View>
  );
}

// Pantalla IMC
function IMCScreen() {
  return (
    <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
      <Text style={{ fontSize: 20 }}>Pantalla IMC</Text>
    </View>
  );
}

// Pantalla Divisas
function DivisasScreen() {
  return (
    <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
      <Text style={{ fontSize: 20 }}>Pantalla Cambio de Divisas</Text>
    </View>
  );
}

// Pantalla Propinas
function PropinasScreen() {
  return (
    <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
      <Text style={{ fontSize: 20 }}>Pantalla Cálculo de Propinas</Text>
    </View>
  );
}

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Menu">
        <Stack.Screen name="Menu" component={MenuScreen} />
        <Stack.Screen name="IMC" component={IMCScreen} />
        <Stack.Screen name="Divisas" component={DivisasScreen} />
        <Stack.Screen name="Propinas" component={PropinasScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
