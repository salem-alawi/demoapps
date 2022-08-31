import * as React from 'react';
import {
  Button,
  DataTable,
  Modal,
  Portal,
  Text,
  TextInput,
} from 'react-native-paper';
import {View} from 'react-native';
import axios from 'axios';
const optionsPerPage = [2, 3, 4];

const MyComponent = prop => {
  const containerStyle = {backgroundColor: 'white', padding: 20};

  const [phone, setPhone] = React.useState('');
  const [name, setName] = React.useState('');
  const [id, setId] = React.useState(0);
  const [visible, setVisible] = React.useState(false);
  const [deleteDialog, setDeleteDialog] = React.useState(false);
  const [editContactForm, setEditContactForm] = React.useState(false);

  const showModal = contact => {
    setVisible(true);
    setName(contact.name);
    setPhone(contact.phone);
    setId(contact.id);
  };
  const hideModal = () => {
    setVisible(false);
  };
  return (
    <View style={{flex: 1}}>
      <Portal>
        <Modal
          visible={visible}
          onDismiss={hideModal}
          contentContainerStyle={containerStyle}>
          <View style={{justifyContent: 'space-around', height: 130}}>
            <Text
              style={{
                textAlign: 'center', // <-- the magic
                fontWeight: 'bold',
                fontSize: 16,
                margin: 5,
              }}>
              {name} - {phone}
            </Text>
            <Button
              onPress={handleEditMenuOption}
              buttonColor="gray"
              mode="contained">
              تعديل
            </Button>
            <Button onPress={handleDelete} buttonColor="red" mode="contained">
              حدف
            </Button>
          </View>
        </Modal>
      </Portal>

      <Portal>
        <Modal
          visible={editContactForm}
          onDismiss={() => setEditContactForm(false)}
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

            <Button mode="contained" onPress={handleEditContactForm}>
              حفط التعديل
            </Button>
          </View>
        </Modal>
      </Portal>

      <DataTable>
        <DataTable.Header>
          <DataTable.Title>الاسم</DataTable.Title>
          <DataTable.Title>رقم الهاتف</DataTable.Title>
        </DataTable.Header>
        <ContactList sss={contact => showModal(contact)} items={prop.data} />
      </DataTable>
    </View>
  );

  function handleEditMenuOption() {
    hideModal();
    setEditContactForm(true);
  }

  function handleDelete() {
    axios.delete('http://10.0.2.2:8085/contacts/' + id).then(resp => {
      if (resp.status == 200) {
        hideModal();
        prop.dateChange();
      }
    });
  }

  function handleEditContactForm() {
    console.log('edit buton trig');

    axios
      .put('http://10.0.2.2:8085/contacts/' + id, {
        phone: '' + phone + '',
        name: '' + name + '',
      })
      .then(resp => {
        if (resp.status == 200) {
          setName('');
          setPhone('');
          setEditContactForm(false);
          prop.dateChange();
        }
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }
};

const ContactList = prop => {
  if (prop.items != null) {
    return prop.items.content.map(contact => {
      return (
        <>
          <DataTable.Row
            onPress={() => {}}
            onLongPress={() => {
              prop.sss(contact);
            }}
            key={contact.id}>
            <DataTable.Cell>{contact.name}</DataTable.Cell>
            <DataTable.Cell>{contact.phone}</DataTable.Cell>
          </DataTable.Row>
        </>
      );
    });
  } else {
    return <></>;
  }
};

export default MyComponent;
