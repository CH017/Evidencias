import React,{ useEffect, useState } from 'react';
import { StatusBar } from 'expo-status-bar';
import { Dimensions, StyleSheet, Text, View } from 'react-native';
import *as Location from 'expo-location';
import MapView,{Marker} from 'react-native-maps';

export default function App() {

  const [location, setLocation]=useState({})
  useEffect(()=>{

    const buscaLocation = async ()=>{
      const {status}=await Location.requestForegroundPermissionsAsync()
      if(status!='granted'){
        return Alert.alert("Acceso no autorizado")
      }
      const location= await Location.getCurrentPositionAsync({})
      setLocation(location)
      console.log(location)
    }

    console.log("Buscando localización")
    buscaLocation
  })
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <MapView style={styles.mapa}>
        {location.coords ? <Marker
        title='Mi Oficina'
        descritpion='Esta es mi oficina'
        />:null
        }
        <Marker
        coordinate={{
          latitude:19,
          longitude:-69,
        }}
        />
        </MapView>
        
    </View>
  );
}

const styles = StyleSheet.create({
  mapa:{
    width:Dimensions.get('window').width,
    height:Dimensions.get('window').height,
  },
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
