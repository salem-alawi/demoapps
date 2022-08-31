/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useEffect, useState} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  View,
} from 'react-native';
import MyComponent from './component/Table';
import axios from 'axios';
import {
  FAB,
  Portal,
  Appbar,
  Modal,
  TextInput,
  Button,
} from 'react-native-paper';
const App = () => {
  const [contactPage, setContactPage] = useState(null);

  useEffect(() => {
    callApi();
  }, []);

  function callApi() {
    axios.get('http://10.0.2.2:8085/contacts').then(resp => {
      console.log(JSON.stringify(resp.data, null, 2));

      if (resp.status == 200) {
        setContactPage(resp.data);
      }
    });
  }

  return (
    <SafeAreaView style={{flex: 1}}>
      <MyAppBar dataChange={() => callApi()} />
      <ScrollView>
        <MyComponent dateChange={() => callApi()} data={contactPage} />
      </ScrollView>
    </SafeAreaView>
  );
};

function MyAppBar(prop) {
  const [visible, setVisible] = React.useState(false);
  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const containerStyle = {backgroundColor: 'white', padding: 20};

  const [name, setName] = React.useState('');
  const [phone, setPhone] = React.useState('');

  function handleCreateContact() {
    axios
      .post('http://10.0.2.2:8085/contacts', {
        name: name,
        phone: phone,
      })
      .then(resp => {
        if (resp.status == 200) {
          hideModal();
          setName('');
          setPhone('');
          prop.dataChange();
        }
      })
      .catch(err => {
        console.log(err);
      });
  }

  return (
    <>
      <Appbar.Header>
        <Appbar.Content title="test App" />
        <Appbar.Action
          style={{backgroundColor: '#eee'}}
          icon="plus"
          onPress={showModal}
        />
      </Appbar.Header>

      <Portal>
        <Modal
          visible={visible}
          onDismiss={hideModal}
          contentContainerStyle={containerStyle}>
          <View style={{justifyContent: 'space-around', height: 250}}>
            <TextInput
              label="اسم جها الاتصال"
              value={name}
              onChangeText={text => setName(text)}
            />
            <TextInput
              label="رقم الهاتف"
              value={phone}
              onChangeText={text => setPhone(text)}
            />

            <Button mode="contained" onPress={handleCreateContact}>
              حفط
            </Button>
          </View>
        </Modal>
      </Portal>
    </>
  );
}

export default App;
