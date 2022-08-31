<template>
  <v-app>
    <v-app-bar app color="primary">
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>
    </v-app-bar>

    <v-main>
      <v-container>
        <v-data-table
          :headers="headers"
          :items="contacts"
          class="elevation-1"
          :items-per-page="20"
          :hide-default-footer="true"
        >
          <template v-slot:item.modifation="{ item }">
            <v-icon small class="mr-2" @click="editItem(item)">
              mdi-pencil
            </v-icon>
          </template>

          <template v-slot:item.delete="{ item }">
            <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
          </template>
        </v-data-table>

        <v-btn
          style="position: fixed; bottom: 30px; right: 30px"
          @click="dialog = !dialog"
          class="mx-2"
          fab
          large
          color="primary"
        >
          <v-icon> mdi-plus </v-icon>
        </v-btn>
      </v-container>
      <div class="text-center">
        <v-dialog v-if="dialog" v-model="dialog" width="500">
          <v-card>
            <v-card-title class="text-h5 grey lighten-2">
              انشاء جهات اتصال جديده
            </v-card-title>

            <v-divider></v-divider>
            <br />

            <v-card-text>
              <v-form v-model="valid" @submit.prevent="submit()">
                <v-row justify="center">
                  <v-col cols="12">
                    <v-text-field
                      :rules="nameRules"
                      label="آسم جها الاتصال"
                      required
                      v-model="name"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row justify="center">
                  <v-col cols="12">
                    <v-text-field
                      :rules="nameRules"
                      v-model="phone"
                      label="رقم الهاتف"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-form>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="submit"> حفظ </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-if="dialogDelete" v-model="dialogDelete" width="500">
          <v-card>
            <v-card-title class="text-h5 grey lighten-2">
              حدف جها الاتصال
            </v-card-title>

            <v-divider></v-divider>
            <br />
            <v-card-text>
              هل انت متاكد من حدف جهات الاتصال هدا

              {{ selectedEditContact.name }} - {{ selectedEditContact.phone }}
            </v-card-text>

            <v-divider> </v-divider>
            <div class="d-flex justify-space-around">
              <v-btn
                style="margin: 10px"
                color="error"
                depressed
                @click="deleteSelectedContact"
              >
                نعم
              </v-btn>
              <v-btn
                depressed
                style="margin: 10px"
                color="primary"
                text
                @click="dialogDelete = false"
              >
                لا
              </v-btn>
            </div>
          </v-card>
        </v-dialog>

        <v-dialog v-if="selectedEditContact" v-model="dialogUpdate" width="500">
          <v-card>
            <v-card-title class="text-h5 grey lighten-2">
              تعديل جها الاتصال
            </v-card-title>

            <v-divider></v-divider>
            <br />

            <v-card-text>
              <v-form v-model="validEdit" @submit.prevent="submitEdit()">
                <v-row justify="center">
                  <v-col cols="12">
                    <v-text-field
                      :rules="nameRules"
                      label="آسم جها الاتصال"
                      required
                      v-model="selectedEditContact.name"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row justify="center">
                  <v-col cols="12">
                    <v-text-field
                      :rules="nameRules"
                      v-model="selectedEditContact.phone"
                      label="رقم الهاتف"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-form>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="submitEdit">
                حفظ التعديل
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "App",

  components: {},

  data: () => ({
    nameRules: [(v) => v.length >= 1 || "value must be more than 1 characters"],
    name: "",
    selectedEditContact: null,
    valid: false,
    validEdit: false,
    phone: "",
    dialog: false,
    dialogDelete: false,
    dialogUpdate: false,
    contacts: [],
    headers: [
      { text: "الاسم", value: "name" },
      { text: "رقم ", value: "phone" },
      { text: "تعديل", value: "modifation" },
      { text: "حدف", value: "delete" },
    ],
    //
  }),
  methods: {
    submitEdit() {
      if (this.validEdit) {
        axios
          .put(
            "http://127.0.0.1:8085/contacts/" + this.selectedEditContact.id,
            {
              name: this.selectedEditContact.name,
              phone: this.selectedEditContact.phone,
            }
          )
          .then((resp) => {
            if (resp.status == 200) {
              this.dialogUpdate = false;

              let itemIndex = this.contacts.findIndex(
                (item) => item.id == this.selectedEditContact.id
              );
              this.contacts.splice(itemIndex, 1, resp.data);
              this.selectedEditContact = null;
            }
          });
      } else {
        // alert("البيانات غير مكتمله");
      }
    },

    submit() {
      if (this.valid) {
        axios
          .post("http://127.0.0.1:8085/contacts", {
            name: this.name,
            phone: this.phone,
          })
          .then((resp) => {
            if (resp.status == 200) {
              this.dialog = false;
              this.contacts.splice(0, 0, resp.data);
              this.name = "";
              this.phone = "";
            }
          });
      } else {
        // alert("البيانات غير مكتمله");
      }
    },
    deleteItem(item) {
      this.selectedEditContact = { ...item };
      this.dialogDelete = true;
    },
    editItem(item) {
      this.selectedEditContact = { ...item };
      this.dialogUpdate = true;
    },

    deleteSelectedContact() {
      axios
        .delete("http://127.0.0.1:8085/contacts/" + this.selectedEditContact.id)
        .then((resp) => {
          if (resp.status == 200) {
            const index = this.contacts.findIndex(
              (item) => (item.id = this.selectedEditContact.id)
            );
            this.contacts.splice(index, 1);
            this.dialogDelete = false;
            this.selectedEditContact = null;
          }
        })
        .catch((e) => {
          console.log(e);
          // alert("something happen");
        });
    },
  },
  mounted() {
    axios
      .get("http://127.0.0.1:8085/contacts")
      .then((resp) => {
        if (resp.status == 200) {
          this.contacts = resp.data.content;
        } else {
          // alert("something happen when get content");
        }
      })
      .catch(() => {
        // alert("something happen when get content");
      });
  },
};
</script>
