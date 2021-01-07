import React, {Component} from 'react';
import CalendarModule from '../../modules/CalendarModule';

import {
  Text,
  TouchableHighlight,
  StyleSheet,
  TextInput,
  View,
  Button,
} from 'react-native';
import Icon from 'react-native-vector-icons/Ionicons';
import NewModuleButton from './NewModuleButton';
import HoverModule from '../../modules/HoverModule';
class SendMoney extends Component {
  state = {name: 'Adama', balance: '0 FCFA'};

  checkMoney = async () => {
    try {
      const balance = await HoverModule.CheckMoney();
      this.setState({balance: balance});
    } catch (e) {
      console.error(e);
    }
  };

  render() {
    return (
      <View>
        <View style={styles.balanceView}>
          <Text>Votre balance est: {this.state.balance}</Text>
          <TouchableHighlight
            activeOpacity={0.2}
            underlayColor="#dddd"
            onPress={this.checkMoney}>
            <Icon
              name="sync-outline"
              style={{marginTop: 20}}
              size={25}
              color="#000000"
            />
          </TouchableHighlight>
        </View>
        <Text>My name is: {this.state.name}</Text>
        <TextInput
          onChangeText={(text) => this.setState({name: text})}
          value={this.state.name}
          style={styles.textInputStyle}
          placeholder="Entrer votre numéro"
        />

        <TextInput
          style={styles.textInputStyle}
          placeholder="Entrer le montant"
        />
        <View style={styles.buttonStyle}>
          <Button title="Envoyé" color="#000000" />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  textInputStyle: {
    borderColor: '#000000',
    borderBottomWidth: 1,
    padding: 2,
    marginTop: 20,
    marginRight: 10,
    marginLeft: 10,
  },
  buttonStyle: {
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 20,
  },
  balanceView: {
    marginBottom: 50,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default SendMoney;
