<template>
  <v-dialog
    :value="open"
    @keydown="$event.key === 'Escape' && toggle()"
    max-width="400px"
    scrollable
    persistent
  >
    <v-card>
      <v-card-title style="border-bottom: 3px solid #277cbe" class="pt-3 pb-1"
        >Thông tin nhận hàng</v-card-title
      >
      <v-card-text>
        <v-container class="pb-0" fluid>
          <v-row>
            <v-col cols="12" class="py-0">
              <v-text-field
                v-model="name"
                :error-messages="nameErrors"
                @input="nameErrors = []"
                label="Tên *"
                outlined
                dense
                light
                clearable
              ></v-text-field>
              <v-text-field
                v-model="address"
                :error-messages="addressErrors"
                @input="addressErrors = []"
                label="Địa chỉ *"
                outlined
                dense
                light
                clearable
              ></v-text-field>
              <v-text-field
                v-model="phone"
                :error-messages="phoneErrors"
                @input="nameErrors = []"
                label="Số điện thoại *"
                outlined
                dense
                light
                clearable
              ></v-text-field>
              <v-text-field
                v-model="note"
                :error-messages="noteErrors"
                @input="noteErrors = []"
                label="Nội dung *"
                outlined
                dense
                light
                clearable
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          text
          height="30px"
          class="primary"
          depressed
          @click="checkValidate"
          >Lưu</v-btn
        >
        <v-btn text height="30px" class="secondary" @click="toggle">
          <div class="font_size">Đóng</div>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'add',
  props: ['open', 'dataCart', 'totalMoney'],
  components: {},
  data() {
    return {
      address: null,
      addressErrors: [],
      phone: null,
      phoneErrors: [],
      note: null,
      noteErrors: [],
      name: null,
      nameErrors: []
    }
  },
  methods: {
    toggle() {
      this.$emit('toggle')
    },
    change(value, field) {
      this[field] = [...value]
      this[field + 'Errors'] = []
    },
    // hành động
    listAction() {
      this.$store
        .dispatch('bo_chi_tieu_ban_hang/getAction', {})
        .then(response => {
          // console.log(response, 'res')
          if (!response.error) {
            let data = []
            for (let i = 0; i < response.data.data.items.length; i++) {
              data.push({
                value: response.data.data.items[i].id,
                text: response.data.data.items[i].name
              })
            }
            this.list_action = [...data]
          }
        })
    },
    // mã bộ chỉ tiêu
    getNest() {
      this.$store.dispatch('cham_soc/getListNest', {}).then(response => {
        let data = []
        // console.log(response, 'qqwqwqwqw')
        for (let i = 0; i < response.data.data.items.length; i++) {
          data.push({
            value: response.data.data.items[i].code,
            text: response.data.data.items[i].code
          })
        }
        this.listnest = [...data]
      })
    },

    Targets() {
      this.$showSuccess(this, 'Đặt hàng thành công')
      this.$router.push('/')
      this.toggle()
      for (let i = 0; i < this.dataCart.length; i++) {
        const data = {
          productId: this.dataCart[i].id,
          quantity: this.dataCart[i].person,
          totalPrice: this.dataCart[i].person * this.dataCart[i].price,
          feeAmount: 0,
          receiverName: this.name,
          receiverAddress: this.address,
          receiverPhone: this.phone,
          note: this.note
        }
        this.$store
          .dispatch('trangChu/order', data)
          .then(response => {})
          .catch(e => {
            this.$log.debug(e)
          })
          .finally(() => {})
      }
    },
    checkValidate(e) {
      let hasErrors = false
      // check tiêu đề
      if (this.$isNullOrEmpty(this.name)) {
        hasErrors = true
        this.nameErrors = ['Không được để trống']
      }
      if (this.$isNullOrEmpty(this.phone)) {
        hasErrors = true
        this.phoneErrors = ['Không được để trống']
      } else if (!this.$isMobilePhone(this.phone)) {
        hasErrors = true
        this.phoneErrors = ['Vui lòng nhập đúng số điện thoại']
      }
      if (this.$isNullOrEmpty(this.note)) {
        hasErrors = true
        this.noteErrors = ['Không được để trống']
      }
      if (this.$isNullOrEmpty(this.address)) {
        hasErrors = true
        this.addressErrors = ['Không được để trống']
      }
      if (!hasErrors) {
        this.Targets()
      }
    }
  },
  watch: {
    open(value) {
      if (value) {
        this.address = null
        this.addressErrors = []
        this.phone = null
        this.phoneErrors = []
        this.note = null
        this.noteErrors = []
        this.name = null
        this.nameErrors = []
      }
    }
  }
}
</script>

<style lang="scss" scoped></style>
